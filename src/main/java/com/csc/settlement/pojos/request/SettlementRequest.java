package com.csc.settlement.pojos.request;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SettlementRequest {
    private String settlementAmount;
    private String settlementDate;
    private String settlementEmail;
}
