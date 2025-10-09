package com.fontys.marketplace_backend.persistence.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequest {
    private String displayName;
    private String email;
    private String password;
}
