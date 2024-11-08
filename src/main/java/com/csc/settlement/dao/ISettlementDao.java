package com.csc.settlement.dao;

import com.csc.settlement.entity.Settlement;

public interface ISettlementDao {
    Settlement getSettlementById(Long settlementId);

    Settlement getSettlementByDate(Long settlementId);

    Settlement save(Settlement settlement);
}
