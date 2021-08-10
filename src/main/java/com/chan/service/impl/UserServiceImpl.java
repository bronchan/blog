package com.chan.service.impl;

import com.chan.dao.UserDao;
import com.chan.pojo.User;
import com.chan.service.UserService;
import com.chan.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author bronchan
 */

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public User getUser() {
        return userDao.getUser();
    }

    @Override
    public User getUserByInfo(String username, String password) {
        User user = userDao.getUserByInfo(username, MD5Utils.code(password));
        return user;
    }
}
