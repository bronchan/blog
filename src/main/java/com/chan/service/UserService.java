package com.chan.service;

import com.chan.pojo.User;

public interface UserService {
    User getUser();
    User getUserByInfo(String username, String password);
}
