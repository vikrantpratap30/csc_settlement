package com.csc.settlement.repository;

import com.csc.settlement.entity.TxnPost;
import com.csc.settlement.projection.TdsDataProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface TxnPostRepository extends JpaRepository<TxnPost,Long> {
    @Query("SELECT \n" +
            "    txnReturn.cscId, \n" +
            "    txnPost.stateCode, \n" +
            "    SUM(txnReturn.txnAmount) AS txn_amount,\n" +
            "    SUM(txnPost.comission) AS com_amount, \n" +
            "    SUM(txnPost.taxCom) AS tax_amount, \n" +
            "    SUM(txnPost.vleAmt) AS vle_amount, \n" +
            "    SUM(txnPost.vleTds) AS vle_tds, \n" +
            "    SUM(txnPost.cscAmt) AS csc_amount, \n" +
            "    COUNT(*) AS tcnt\n" +
            "FROM \n" +
            "    TxnPost txnPost \n" +
            "INNER JOIN \n" +
            "    TxnReturn txnReturn ON txnReturn.cscTxn = txnPost.cscTxn\n" +
            "INNER JOIN \n"+
            "    WalletUser walletUser ON CAST(txnReturn.cscId AS Long) = walletUser.id\n"+
            "WHERE \n" +
            "    DATE(txnPost.creationDate) BETWEEN :startDate AND DATE(CURDATE() - 30 day)\n" +
            "GROUP BY \n" +
            "    txnReturn.cscId, txnPost.stateCode")
    List<TdsDataProjection> findAllByCreationDate(@Param("startDate") LocalDateTime startDate);
}
