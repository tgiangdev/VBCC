package com.bgddt.qlvb.models;

import lombok.Data;
import lombok.NonNull;

@Data
public class LoginPayload {
    @NonNull
    private String username;
    @NonNull
    private String password;
}
