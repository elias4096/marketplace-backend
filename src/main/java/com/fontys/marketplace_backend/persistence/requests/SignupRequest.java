package com.fontys.marketplace_backend.persistence.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequest {
    private String displayName;
    private String email;
    private String password;
}
