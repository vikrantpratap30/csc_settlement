package com.csc.settlement.dao;

import com.csc.settlement.entity.TdsReport;

import java.util.List;

public interface ITdsReportDao {
   void saveAll(List<TdsReport> tdsReport);
}
