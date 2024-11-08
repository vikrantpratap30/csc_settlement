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
@Table(name = "txn_post")
public class TxnPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sno;

    @Column(name = "csc_txn", length = 24)
    private String cscTxn;

    @Column(name = "merchant_txn", length = 64)
    private String merchantTxn;

    @Column(name = "merchant_id", length = 5)
    private String merchantId;

    @Column(name = "product_id", length = 12)
    private String productId;

    @Column(name = "csc_id", length = 12)
    private String cscId;

    @Column(name = "wallet_ac", length = 12)
    private String walletAc;

    @Column(name = "wallet_balance", precision = 12, scale = 2)
    private BigDecimal walletBalance;

    @Column(name = "merchant_amount", precision = 12, scale = 2)
    private BigDecimal merchantAmount;

    @Column(name = "comission", precision = 10, scale = 2)
    private BigDecimal comission;

    @Column(name = "tax_com", precision = 10, scale = 2)
    private BigDecimal taxCom;

    @Column(name = "vle_amt", precision = 10, scale = 2)
    private BigDecimal vleAmt;

    @Column(name = "vle_tds", precision = 8, scale = 2)
    private BigDecimal vleTds;

    @Column(name = "vle_tax", precision = 8, scale = 2)
    private BigDecimal vleTax;

    @Column(name = "sca_amt", precision = 10, scale = 2)
    private BigDecimal scaAmt;

    @Column(name = "sda_amt", precision = 10, scale = 2)
    private BigDecimal sdaAmt;

    @Column(name = "actor_amt", precision = 10, scale = 2)
    private BigDecimal actorAmt;

    @Column(name = "csc_amt", precision = 10, scale = 2)
    private BigDecimal cscAmt;

    @Column(name = "vendor_amt", precision = 8, scale = 2)
    private BigDecimal vendorAmt;

    @Column(name = "csc_markup", precision = 8, scale = 2)
    private BigDecimal cscMarkup;

    @Column(name = "tax_cscmarkup", precision = 8, scale = 2)
    private BigDecimal taxCscMarkup;

    @Column(name = "pg_charge", precision = 8, scale = 2)
    private BigDecimal pgCharge;

    @Column(name = "tax_pgcharge", precision = 6, scale = 2)
    private BigDecimal taxPgCharge;

    @Column(name = "vle_markup", precision = 6, scale = 2)
    private BigDecimal vleMarkup;

    @Column(name = "tax_vle_markup", precision = 8, scale = 2)
    private BigDecimal taxVleMarkup;

    @Column(name = "tds_vlemarkup", precision = 8, scale = 2)
    private BigDecimal tdsVleMarkup;

    @Column(name = "wallet_amount", precision = 8, scale = 2)
    private BigDecimal walletAmount;

    @Column(name = "hash_key", length = 64)
    private String hashKey;

    @Column(name = "wallet_type", length = 2)
    private String walletType;

    @Column(name = "tax_breakup", length = 200)
    private String taxBreakup;

    @Column(name = "txn_rules", length = 200)
    private String txnRules;

    @Column(name = "txn_locate", length = 200)
    private String txnLocate;

    @Column(name = "state_code", length = 2)
    private String stateCode;

    @Column(name = "district_code", length = 5)
    private String districtCode;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @Column(name = "flag")
    private Byte flag;

    @Column(name = "ip_addr", length = 16)
    private String ipAddr;

    @Column(name = "migration_date")
    private LocalDateTime migrationDate;
}


