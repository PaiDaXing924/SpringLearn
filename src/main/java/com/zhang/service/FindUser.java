package com.zhang.service;

import com.zhang.pojo.User;
import org.springframework.stereotype.Service;

public interface FindUser {

    User findUser(String username);

}
