package com.csc.settlement.pojos.request;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {
    @NonNull
    private String email;
    @NonNull
    private String password;
    private String mobile;
}
