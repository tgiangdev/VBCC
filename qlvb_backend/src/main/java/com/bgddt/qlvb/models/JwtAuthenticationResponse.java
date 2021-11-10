package com.bgddt.qlvb.models;

import com.bgddt.qlvb.dtos.AccountDTO;
import com.bgddt.qlvb.entities.Account;
import lombok.Data;

@Data
public class JwtAuthenticationResponse {
    private String accessToken;
    private String tokenType;
    private AccountDTO account;

    public JwtAuthenticationResponse(String accessToken, AccountDTO account) {
        this.accessToken = accessToken;
        this.tokenType = "Bearer";
        this.account = account;
    }
}
