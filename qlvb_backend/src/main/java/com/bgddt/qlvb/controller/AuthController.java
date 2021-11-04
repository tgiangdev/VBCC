package com.bgddt.qlvb.controller;

import com.bgddt.qlvb.common.exceptions.BusinessException;
import com.bgddt.qlvb.models.JwtAuthenticationResponse;
import com.bgddt.qlvb.services.AccountService;
import com.bgddt.qlvb.models.LoginPayload;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Auth")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AccountService accountService;

    @PostMapping("/login")
    public ResponseEntity<JwtAuthenticationResponse> login(@RequestBody LoginPayload payload) throws BusinessException {
        return ResponseEntity.ok().body(accountService.login(payload));
    }
}
