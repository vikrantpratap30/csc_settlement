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
@Table(name = "product_paysheet")
public class ProductPaysheet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sno;

    @Column(name = "merchant_id", length = 5)
    private String merchantId;

    @Column(name = "merchant_name", length = 100)
    private String merchantName;

    @Column(name = "product_id", length = 10)
    private String productId;

    @Column(name = "product_name", length = 100)
    private String productName;

    @Column(name = "payment_mode", length = 50)
    private String paymentMode;

    @Enumerated(EnumType.STRING)
    @Column(name = "com_mode", columnDefinition = "enum('S', 'I')")
    private ComMode comMode;

    @Column(name = "payment_tds", length = 50)
    private String paymentTds;

    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;

    // Enum for 'com_mode'
    public enum ComMode {
        S, I
    }
}