package com.outsider.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.outsider.domain.po.User;
import com.outsider.mapper.UserMapper;
import com.outsider.service.UserService;
import java.util.List;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author outsider
 * @description 针对表【user】的数据库操作Service实现
 * @createDate 2023-01-07 20:23:41
 */
@Service
@Primary
public class DefaultServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService {

    @Override
    public String nothing() {
        return "this is default";
    }

    @Override
    public void lambda() {
        List<User> users = this.lambdaQuery()
            .like(User::getName, "a")
            .gt(User::getAge, 11)
            .list();

        System.out.println(users);

        assert (
            this.lambdaUpdate()
                .eq(User::getId, 1)
                .update(new User().setName("lambda"))
        );
    }

    @Override
    @Transactional
    public void transactionTest() {
        saveTT();
        if (true) {
            throw new RuntimeException("ss");
        }
        this.save(new User().setName("b"));
    }
    void saveTT(){
        this.save(new User().setName("naaaa"));
    }
}




