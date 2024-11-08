package com.csc.settlement.service.impl;

import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.csc.settlement.config.AwsS3Config;
import com.csc.settlement.dao.impl.SettlementDaoImpl;
import com.csc.settlement.entity.Settlement;
import com.csc.settlement.exception.ResourceNotFoundException;
import com.csc.settlement.pojos.request.GenerateReportRequest;
import com.csc.settlement.service.IReportGenerationService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service("PAY_SHEET")
public class PaySheetReportGeneration implements IReportGenerationService {

    @Value("${application.aws.s3.bucket-name}")
    private static String bucketName;

    @Value("${application.comms.slack}")
    private static String slackWebhookUrl;

    @Value("${application.comms.email}")
    private static String emailForReportNotification;

    private static final RestTemplate restTemplate = new RestTemplate();

    private static final String FOLDER_NAME = "/settlement_data";

    private final AwsS3Config awsS3Config;

    private final SettlementDaoImpl settlementDaoImpl;

    public PaySheetReportGeneration(AwsS3Config awsS3Config, SettlementDaoImpl settlementDaoImpl) {
        this.awsS3Config = awsS3Config;
        this.settlementDaoImpl = settlementDaoImpl;
    }

    @Override
    public void generateReport(GenerateReportRequest generateReportRequest) throws ResourceNotFoundException, IOException {
        // Pick the date from request body and run query in DB
        if (generateReportRequest.getCurrentDate()==0) {
            throw new ResourceNotFoundException("Cron request is empty");
        }
        // TODO: Data will be coming through Metabase or through Bank database,will update this accordingly
        Settlement settlement = settlementDaoImpl.getSettlementByDate(generateReportRequest.getCurrentDate());
        Date date = Date.valueOf(LocalDate.ofEpochDay(generateReportRequest.getCurrentDate()));
        // ideally the data returned from db should be mapped to pojo libraries
        // it's got to be in json format
        // these are based on assumption as data will be in multiple entries and in list
        List<Settlement> settlementList = new ArrayList<>();
        settlementList.add(settlement);

        // generating the excel sheet with data from DB queried json
        ByteArrayOutputStream byteArrayOutputStream = generateExcelSheetWithData(settlementList, settlementDaoImpl, generateReportRequest.getCurrentDate());
        // mechanism to save file in S3
        uploadFileToS3(byteArrayOutputStream, awsS3Config, date);

        // TODO: get the file using S3 bucket and send it to users Email

    }

    private static ByteArrayOutputStream generateExcelSheetWithData
            (List<Settlement> settlementList, SettlementDaoImpl settlementDaoImpl, Long epochDate) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet finalExcelSheetToBeExported = workbook.createSheet("Payment Settlement");
        double totalAmount = 0.0;
        // TODO: All the columns need to be created as per Report Generation requirement
        Row headerRow = finalExcelSheetToBeExported.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Settlement Date");
        headerRow.createCell(2).setCellValue("Is Settled");
        headerRow.createCell(3).setCellValue("Amount");

        // Populating data rows
        int rowIndex = 1;
        for (Settlement data : settlementList) {
            // TODO: Accordingly set the values to each columns based on data coming as Query result
            Row row = finalExcelSheetToBeExported.createRow(rowIndex++);
            row.createCell(0).setCellValue(data.getId());
            row.createCell(1).setCellValue(data.getSettlementDate().toString()); // Assuming settlementDate is Date or LocalDate
            row.createCell(2).setCellValue(data.getIsSettled());
            row.createCell(2).setCellValue(data.getSettlementAmount());

            // Total Amount creation
            double amount = data.getSettlementAmount(); // Assuming getAmount() returns a double
            row.createCell(3).setCellValue(amount);
            totalAmount += amount; // Calculate the sum of amounts
        }

        // Adding a row at the end to display total amount
        Row totalRow = finalExcelSheetToBeExported.createRow(rowIndex);
        totalRow.createCell(2).setCellValue("Total Amount:");
        totalRow.createCell(3).setCellValue(totalAmount);

        // Write workbook to ByteArrayOutputStream
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        workbook.write(byteArrayOutputStream);
        workbook.close();

        /*TODO: update the Db with details of email sent ,amount ,day and status
           Create a method that notify User/Admin regarding success/failure of report generation
           Saving the data should ideally be done once notifications are pushed*/
        settlementDaoImpl.save(Settlement.builder()
                .settlementAmount(totalAmount)
                .settlementDate(epochDate).build());
        return byteArrayOutputStream;
    }

    private static void uploadFileToS3(ByteArrayOutputStream outputStream, AwsS3Config awsS3Config, java.util.Date date) {
        try {
            // assuming it will store the file at folder with name as "file-epochValue-.xlsx"
            String fileName = FOLDER_NAME + "file" + date + ".xlsx";
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(outputStream.toByteArray());
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType("text/plain");
            metadata.addUserMetadata("title", "someTitle");
            // Create and execute PutObjectRequest
            PutObjectRequest request = new PutObjectRequest(bucketName, fileName, byteArrayInputStream, metadata);
            awsS3Config.amazonS3().putObject(request);
            System.out.println("File uploaded successfully to S3.");
            /*TODO: Fetch the uploaded file URL to attach in the Email and Slack notification
               Make this method return S3 uploaded file link */
            sendMessage("Email has been sent to admin for the date " + date);

        } catch (Exception e) {
            System.err.println("Error uploading file to S3: " + e);
        }
    }

    private static void sendMessage(String message) {
        // Create the payload JSON with the message
        // TODO: Add link of the uploaded file of S3 to the slack message to ensure the file is present with expected data
        String payload = "{\"text\":\"" + message + "\"}";
        // Set headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(payload, headers);
        restTemplate.postForObject(slackWebhookUrl, request, String.class);
    }

//    SELECT
//    txn_return.csc_id,
//    txn_post.state_code,
//    SUM(txn_return.txn_amount) AS txn_amount,
//    SUM(txn_post.comission) AS com_amount,
//    SUM(txn_post.tax_com) AS tax_amount,
//    SUM(txn_post.vle_amt) AS vle_amount,
//    SUM(txn_post.vle_tds) AS vle_tds,
//    SUM(txn_post.csc_amt) AS csc_amount,
//    COUNT(*) AS tcnt
//    FROM
//            txn_post
//    INNER JOIN
//    txn_return ON txn_return.csc_txn = txn_post.csc_txn
//    INNER JOIN
//    wallet_user ON txn_return.walllet_ac = wallet_user.id
//            WHERE
//    DATE(txn_post.creation_date) BETWEEN '2024-11-01' AND '2024-11-30'
//    GROUP BY
//    txn_return.csc_id, txn_post.state_code;
}
