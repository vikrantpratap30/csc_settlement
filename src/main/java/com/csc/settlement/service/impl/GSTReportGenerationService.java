package com.csc.settlement.service.impl;

import com.csc.settlement.exception.ResourceNotFoundException;
import com.csc.settlement.pojos.request.GenerateReportRequest;
import com.csc.settlement.service.IReportGenerationService;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service("GST_REPORT")
public class GSTReportGenerationService implements IReportGenerationService {

    @Override
    public void generateReport(GenerateReportRequest generateReportRequest) throws ResourceNotFoundException, IOException {

    }
}
