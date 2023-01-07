package com.outsider.mapper;

import com.outsider.domain.po.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
* @author outsider
* @description 针对表【user】的数据库操作Mapper
* @createDate 2023-01-07 20:23:41
* @Entity com.outsider.domain.po.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {
   @Select("select * from user limit 1")
    public User getOnce();
}




