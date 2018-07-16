package com.efuture.demo.mapper;

import com.efuture.demo.model.User;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserMapper {
    int add(User user);

    User findOne(User user);
}
