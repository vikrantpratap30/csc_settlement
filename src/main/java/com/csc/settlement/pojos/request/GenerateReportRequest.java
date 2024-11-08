package com.csc.settlement.pojos.request;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GenerateReportRequest {
    // Expecting FE will pass us Epoch date value
    @Builder.Default
    private long currentDate=0;
}
