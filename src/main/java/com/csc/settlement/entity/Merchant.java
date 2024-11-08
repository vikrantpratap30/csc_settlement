package com.csc.settlement.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "merchant")
public class Merchant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "partner_merchant_id", length = 255)
    private String partnerMerchantId;

    @Column(name = "merchant_name", length = 255)
    private String merchantName;

    @Column(name = "status")
    private Byte status;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "create_date_utc")
    private LocalDateTime createDateUtc;

    @Column(name = "created_by")
    private Long createdBy;

    @Column(name = "last_update_date")
    private LocalDateTime lastUpdateDate;

    @Column(name = "last_update_date_utc")
    private LocalDateTime lastUpdateDateUtc;

    @Column(name = "last_updated_by")
    private Long lastUpdatedBy;
}
