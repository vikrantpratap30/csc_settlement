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
@Table(name = "refund_full")
public class RefundFull {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sno;

    @Column(name = "csc_txn", length = 24)
    private String cscTxn;

    @Column(name = "merchant_id", length = 5)
    private String merchantId;

    @Column(name = "merchant_txn", length = 64)
    private String merchantTxn;

    @Column(name = "wallet_amount", precision = 10, scale = 2)
    private BigDecimal walletAmount;

    @Column(name = "merchant_txn_param", length = 24)
    private String merchantTxnParam;

    @Column(name = "csc_id", length = 12)
    private String cscId;

    @Column(name = "wallet_ac", length = 12)
    private String walletAc;

    @Column(name = "txn_amount", precision = 10, scale = 2)
    private BigDecimal txnAmount;

    @Column(name = "merchant_status", length = 2)
    private String merchantStatus;

    @Column(name = "txn_cnt")
    private Integer txnCnt;

    @Column(name = "refund_mode", length = 1)
    private String refundMode;

    @Column(name = "refund_level", length = 1)
    private String refundLevel;

    @Column(name = "refund_status", length = 1)
    private String refundStatus;

    @Column(name = "refund_bucket")
    private Integer refundBucket;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @Column(name = "ip_addr", length = 16)
    private String ipAddr;

    @Column(name = "flag")
    private Integer flag;

    @Column(name = "flag2")
    private Integer flag2;

    @Column(name = "bms_flag")
    private Byte bmsFlag;

    @Column(name = "product_id", length = 10)
    private String productId;

    @Column(name = "migration_date")
    private LocalDateTime migrationDate;

    @Column(name = "refund_date")
    private LocalDateTime refundDate;
}
