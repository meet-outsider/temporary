package com.outsider.service;

import com.outsider.domain.po.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author outsider
 * @description 针对表【user】的数据库操作Service
 * @createDate 2023-01-07 20:23:41
 */
public interface UserService extends IService<User> {
     String nothing();

     void lambda();
}
