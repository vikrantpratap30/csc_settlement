package com.csc.settlement.dao.impl;

import com.csc.settlement.dao.ISettlementDao;
import com.csc.settlement.entity.Settlement;
import com.csc.settlement.repository.SettlementRepository;
import org.springframework.stereotype.Service;

@Service
public class SettlementDaoImpl implements ISettlementDao {
    private final SettlementRepository settlementRepository;

    public SettlementDaoImpl(SettlementRepository settlementRepository) {
        this.settlementRepository = settlementRepository;
    }

    @Override
    public Settlement getSettlementById(Long settlementId) {
        return settlementRepository.getReferenceById(settlementId);
    }

    @Override
    public Settlement getSettlementByDate(Long settlementId) {
        return Settlement.builder().settlementDate(3453464564356l).build();
    }
    @Override
    public Settlement save(Settlement settlement){
        return save(settlement);
    }

}
