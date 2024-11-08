package com.csc.settlement.dao;

import com.csc.settlement.projection.TdsDataProjection;

import java.time.LocalDateTime;
import java.util.List;

public interface ITxnPostDao {

    List<TdsDataProjection> fetchAllDetailsByDate(LocalDateTime startDate);
}
