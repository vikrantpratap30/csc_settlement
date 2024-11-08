package com.csc.settlement.service.impl;

import com.csc.settlement.dao.impl.TdsReportDao;
import com.csc.settlement.dao.impl.TxnPostDaoImpl;
import com.csc.settlement.dao.impl.TxnReturnDaoImpl;
import com.csc.settlement.entity.TdsReport;
import com.csc.settlement.pojos.request.GenerateReportRequest;
import com.csc.settlement.projection.TdsDataProjection;
import com.csc.settlement.service.IReportGenerationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service("TDS_REPORT")
public class TDSReportGenerationService implements IReportGenerationService {
    private final TxnPostDaoImpl txnPostDao;
    private final TxnReturnDaoImpl txnReturnDao;
    private final TdsReportDao tdsReportDao;

    public TDSReportGenerationService(TxnPostDaoImpl txnPostDao, TxnReturnDaoImpl txnReturnDao, TdsReportDao tdsReportDao) {
        this.txnPostDao = txnPostDao;
        this.txnReturnDao = txnReturnDao;
        this.tdsReportDao = tdsReportDao;
    }

    @Override
    public void generateReport(GenerateReportRequest generateReportRequest) {
        // TODO: Need to fix the right time duration
        LocalDateTime startDate = LocalDateTime.ofEpochSecond(generateReportRequest.getCurrentDate(),0, null);
        List<TdsDataProjection> tdsDataProjectionList = txnPostDao.fetchAllDetailsByDate(startDate);
        List<TdsReport> tdsReportList = new ArrayList<>();
        // Generating report Id:
        UUID uuid = UUID.randomUUID();
        tdsDataProjectionList.forEach(
                tdsDataProjection -> {
                    tdsReportList.add(TdsReport.
                            builder()
                            .reportId(uuid)
                            .cscId(tdsDataProjection.getCscId())
                            .tcnt(tdsDataProjection.getTcnt())
                            .comAmount(tdsDataProjection.getComAmount())
                            .vleTds(tdsDataProjection.getVleTds())
                            .stateCode(tdsDataProjection.getStateCode())
                            .taxAmount(tdsDataProjection.getTaxAmount())
                            .txnAmount(tdsDataProjection.getTxnAmount())
                            .cscAmount(tdsDataProjection.getCscAmount())
                            .vleAmount(tdsDataProjection.getVleAmount())
                            .creationDate(LocalDateTime.now())
                            .build());
                }
        );
        tdsReportDao.saveAll(tdsReportList);
    }
}
