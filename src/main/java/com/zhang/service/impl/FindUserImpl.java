package com.zhang.service.impl;

import com.zhang.dao.UserDao;
import com.zhang.pojo.User;
import com.zhang.service.FindUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FindUserImpl implements FindUser {

    @Autowired
    private UserDao userDao;

    @Override
    public User findUser(String username) {
        User user = userDao.findUser(username);
        return user;
    }
}
