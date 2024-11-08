package com.csc.settlement.dao.impl;

import com.csc.settlement.dao.ITxnPostDao;
import com.csc.settlement.projection.TdsDataProjection;
import com.csc.settlement.repository.TxnPostRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TxnPostDaoImpl implements ITxnPostDao {

    private final TxnPostRepository txnPostRepository;

    public TxnPostDaoImpl(TxnPostRepository txnPostRepository) {
        this.txnPostRepository = txnPostRepository;
    }

    @Override
    public List<TdsDataProjection> fetchAllDetailsByDate(LocalDateTime startDate) {
        return txnPostRepository.findAllByCreationDate(startDate);
    }
}
