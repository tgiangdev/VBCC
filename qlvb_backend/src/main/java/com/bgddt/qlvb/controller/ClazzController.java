package com.bgddt.qlvb.controller;

import com.bgddt.qlvb.services.ClazzService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Clazz")
@RestController
@RequestMapping("/api/class")
public class ClazzController extends BaseController{
    ClazzService clazzService;

    @Autowired
    ClazzController(ClazzService service) {
        super(service);
        this.clazzService = service;
    }
}
