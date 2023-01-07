package com.outsider.controller;

import com.outsider.service.UserService;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {


    private final UserService defaultService;

    @Resource(name = "otherServiceImpl")
    private final UserService otherService;

    @GetMapping("/1")
    @Transactional(rollbackFor = Exception.class)
    public String defaultService() {
        return defaultService.nothing();
    }

    @GetMapping("/2")
    @Transactional(rollbackFor = Exception.class)
    public String ProviderService() {
        return otherService.nothing();
    }

    @GetMapping
    @Transactional(rollbackFor = Exception.class)
    public String lambda() {
        return otherService.lambdaQuery().list().toString();
    }
}
