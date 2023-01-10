package com.outsider.controller;

import com.alibaba.fastjson2.JSON;
import com.outsider.domain.po.User;
import com.outsider.service.UserService;
import jakarta.annotation.Resource;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {


    private final UserService defaultService;

    @Resource(name = "otherServiceImpl")
    private final UserService otherService;

    @GetMapping
    @Transactional(rollbackFor = Exception.class)
    public String defaultService(@RequestBody User user,
        @RequestParam(defaultValue = "0") Integer count) {

        System.out.println(count);
        return JSON.toJSONString(user);
    }

    @GetMapping("/2")
    @Transactional(rollbackFor = Exception.class)
    public String ProviderService() {
        return otherService.nothing();
    }

    @PutMapping
    @Transactional(rollbackFor = Exception.class)
    public Map save(@RequestBody User user) {
        return Map.of(true, defaultService.save(user));
    }

    @GetMapping("/tt")
    @Transactional(rollbackFor = Exception.class)
    public String lambda() {
        defaultService.transactionTest();
        return "";
    }
}
