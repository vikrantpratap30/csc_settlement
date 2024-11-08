package com.csc.settlement.repository;

import com.csc.settlement.entity.TxnReturn;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TxnReturnRepository extends JpaRepository<TxnReturn,Long> {
}
