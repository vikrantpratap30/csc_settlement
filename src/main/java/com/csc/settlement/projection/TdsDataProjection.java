package com.csc.settlement.projection;

import java.math.BigDecimal;

public interface TdsDataProjection {

    String getCscId();  // Corresponds to TxnReturn.cscId

    String getStateCode();  // Corresponds to TxnPost.stateCode

    BigDecimal getTxnAmount();  // Corresponds to SUM(TxnReturn.txnAmount)

    BigDecimal getComAmount();  // Corresponds to SUM(TxnPost.comission)

    BigDecimal getTaxAmount();  // Corresponds to SUM(TxnPost.taxCom)

    BigDecimal getVleAmount();  // Corresponds to SUM(TxnPost.vleAmt)

    BigDecimal getVleTds();  // Corresponds to SUM(TxnPost.vleTds)

    BigDecimal getCscAmount();  // Corresponds to SUM(TxnPost.cscAmt)

    Long getTcnt();  // Corresponds to COUNT(*)
}
