package com.csc.settlement.dao.impl;

import com.csc.settlement.dao.ITdsReportDao;
import com.csc.settlement.entity.TdsReport;
import com.csc.settlement.repository.TdsReportRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TdsReportDao implements ITdsReportDao {
    private final TdsReportRepository tdsReportRepository;

    public TdsReportDao(TdsReportRepository tdsReportRepository) {
        this.tdsReportRepository = tdsReportRepository;
    }
    @Override
    public void saveAll(List<TdsReport> tdsReport){
        tdsReportRepository.saveAll(tdsReport);
    }
}
