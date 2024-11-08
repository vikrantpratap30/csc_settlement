package com.csc.settlement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "settlement")
public class Settlement {
    @Id
    private Long id;

    @Column
    private Long settlementDate;

    @Column
    @Builder.Default
    private Boolean isSettled = Boolean.FALSE;

    @Column(name = "is_comms_sent")
    @Builder.Default
    private Boolean settlementCommsSent = Boolean.FALSE;

    @Column(name = "comms_email")
    private String communicationEmail;

    @Column(name = "settlement_amount")
    private Double settlementAmount;
}
