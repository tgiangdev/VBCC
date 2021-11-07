package com.bgddt.qlvb.controller;

import com.bgddt.qlvb.services.AccountService;
import com.bgddt.qlvb.services.BaseService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "User")
@RestController
@RequestMapping("/api/user")
public class UserController extends BaseController {
    @Autowired
    UserController(AccountService service) {
        super(service);
    }
}
