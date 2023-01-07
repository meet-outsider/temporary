package com.outsider.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.outsider.domain.po.User;
import com.outsider.mapper.UserMapper;
import com.outsider.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class OtherServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService {

    @Override
    public String nothing() {
        return "this is other";
    }

    @Override
    public void lambda() {
        System.out.println(this.lambdaQuery().list());
    }

}
