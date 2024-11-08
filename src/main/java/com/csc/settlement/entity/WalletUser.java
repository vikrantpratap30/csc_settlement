package com.csc.settlement.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;


@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "wallet_user")
public class WalletUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name", length = 255)
    private String fullName;

    @Column(name = "email", length = 255)
    private String email;

    @Column(name = "mobile_number", length = 15)
    private String mobileNumber;

    @Column(name = "gender", length = 15)
    private String gender;

    @Column(name = "dob", length = 10)
    private String dob;

    @Column(name = "state_code", length = 10)
    private String stateCode;

    @Column(name = "kyc_type", length = 50, nullable = false)
    private String kycType;

    @Column(name = "kyc_mode", length = 50, nullable = false)
    private String kycMode;

    @Column(name = "pan", length = 10, nullable = false)
    private String pan;

    @Column(name = "status", nullable = false)
    private Boolean status;

    @Column(name = "user_unique_hash", length = 255)
    private String userUniqueHash;

    @Column(name = "additional_field_1", length = 255)
    private String additionalField1;

    @Column(name = "additional_field_2", length = 255)
    private String additionalField2;

    @Column(name = "create_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Column(name = "create_date_utc")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDateUtc;

    @Column(name = "created_by")
    private Long createdBy;

    @Column(name = "last_update_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdateDate;

    @Column(name = "last_update_date_utc")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdateDateUtc;

    @Column(name = "last_updated_by")
    private Long lastUpdatedBy;

}
