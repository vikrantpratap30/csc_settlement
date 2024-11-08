package com.csc.settlement.pojos.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SettlementResponse {
    private String settlementAmount;
    private String settlementDate;
    private String settlementEmail;
}
