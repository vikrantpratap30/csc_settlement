package com.csc.settlement.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "partner_product_id", length = 255)
    private String partnerProductId;

    @Column(name = "partner_merchant_id", length = 255)
    private String partnerMerchantId;

    @Column(name = "product_name", length = 255)
    private String productName;

    @Column(name = "recon_mode", length = 50)
    private String reconMode;

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

