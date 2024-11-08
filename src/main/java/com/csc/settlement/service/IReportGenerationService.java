package com.csc.settlement.service;

import com.csc.settlement.exception.ResourceNotFoundException;
import com.csc.settlement.pojos.request.GenerateReportRequest;

import java.io.IOException;

public interface IReportGenerationService {

    void generateReport(GenerateReportRequest generateReportRequest) throws ResourceNotFoundException, IOException;
}
