package com.csc.settlement.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "txn_return")
public class TxnReturn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sno;

    @Column(name = "csc_txn", length = 20)
    private String cscTxn;

    @Column(name = "wallet_txn", length = 20)
    private String walletTxn;

    @Column(name = "merchant_txn", length = 50)
    private String merchantTxn;

    @Column(name = "merchant_id", length = 10)
    private String merchantId;

    @Column(name = "csc_id", length = 20)
    private String cscId;

    @Column(name = "wallet_ac", length = 20)
    private String walletAc;

    @Column(name = "txn_mode", columnDefinition = "CHAR")
    private String txnMode;

    @Column(name = "product_id", length = 20)
    private String productId;

    @Column(name = "txn_remarks", length = 50)
    private String txnRemarks;

    @Column(name = "txn_type")
    private Byte txnType;

    @Column(name = "txn_amount", precision = 10, scale = 2)
    private BigDecimal txnAmount;

    @Column(name = "wallet_amount", precision = 10, scale = 2)
    private BigDecimal walletAmount;

    @Column(name = "txn_status")
    private Integer txnStatus;

    @Column(name = "created_by", length = 20)
    private String createdBy;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @Column(name = "hash_key", length = 40, columnDefinition = "CHAR(40)")
    private String hashKey;

    @Column(name = "ip_addr", length = 15)
    private String ipAddr;

    @Column(name = "flag")
    private Integer flag;
}
