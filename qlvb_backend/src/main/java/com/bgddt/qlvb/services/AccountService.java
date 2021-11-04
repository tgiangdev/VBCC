package com.bgddt.qlvb.services;

import com.bgddt.qlvb.models.JwtAuthenticationResponse;
import com.bgddt.qlvb.common.exceptions.BusinessException;
import com.bgddt.qlvb.models.LoginPayload;

public interface AccountService {
    JwtAuthenticationResponse login(LoginPayload payload) throws BusinessException;
}
