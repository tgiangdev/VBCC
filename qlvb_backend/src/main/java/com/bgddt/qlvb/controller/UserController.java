package com.bgddt.qlvb.controller;

import com.bgddt.qlvb.dtos.AccountDTO;
import com.bgddt.qlvb.entities.Account;
import com.bgddt.qlvb.services.AccountService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "User")
@RestController
@RequestMapping("/api/user")
public class UserController extends BaseController<AccountDTO, Account> {
    @Autowired
    UserController(AccountService service) {
        super(service);
    }

    @Override
    public void generateDto(AccountDTO dto) {

    }
}
