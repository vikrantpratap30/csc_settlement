package com.csc.settlement.controller;

import com.csc.settlement.exception.ResourceNotFoundException;
import com.csc.settlement.pojos.request.GenerateReportRequest;
import com.csc.settlement.pojos.response.GenerateReportResponse;
import com.csc.settlement.service.IReportGenerationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

@RestController
public class ReportGenerationController {
    private final Map<String,IReportGenerationService> reportGenerationService;

    public ReportGenerationController(Map<String, IReportGenerationService> reportGenerationService) {
        this.reportGenerationService = reportGenerationService;
    }

    @PostMapping()
    public GenerateReportResponse generateReport(@RequestParam String generationType, GenerateReportRequest generateReportRequest) throws IOException, ResourceNotFoundException {
        reportGenerationService.get(generationType).generateReport(generateReportRequest);
        return GenerateReportResponse.builder().build();
    }
}
