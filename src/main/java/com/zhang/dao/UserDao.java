package com.zhang.dao;

import com.zhang.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {

    User findUser(String username);

}
