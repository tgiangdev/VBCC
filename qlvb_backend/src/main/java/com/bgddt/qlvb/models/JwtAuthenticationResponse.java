package com.bgddt.qlvb.models;

import com.bgddt.qlvb.entities.Account;
import lombok.Data;

@Data
public class JwtAuthenticationResponse {
    private String accessToken;
    private String tokenType;
    private Account account;

    public JwtAuthenticationResponse(String accessToken, Account account) {
        this.accessToken = accessToken;
        this.tokenType = "Bearer";
        this.account = account;
    }
}
