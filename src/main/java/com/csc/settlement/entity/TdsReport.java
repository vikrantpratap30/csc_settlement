package com.csc.settlement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tds_report")
public class TdsReport {

    @Id
    @Column(name = "csc_id")
    private String cscId;

    @Column(name = "state_code")
    private String stateCode;

    @Column(name = "txn_amount")
    private BigDecimal txnAmount;

    @Column(name = "com_amount")
    private BigDecimal comAmount;

    @Column(name = "tax_amount")
    private BigDecimal taxAmount;

    @Column(name = "vle_amount")
    private BigDecimal vleAmount;

    @Column(name = "vle_tds")
    private BigDecimal vleTds;

    @Column(name = "csc_amount")
    private BigDecimal cscAmount;

    @Column(name = "tcnt")
    private Long tcnt;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    // New column to group rows into a report
    @Column(name = "report_id")
    private UUID reportId;

    // Getters and setters for all the fields
}
