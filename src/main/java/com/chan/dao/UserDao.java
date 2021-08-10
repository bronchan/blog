package com.chan.dao;

import com.chan.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author bronchan
 */
@Mapper
@Repository
public interface UserDao {
    /**
     * @return  User
     */
    User getUser();

    /**
     * @param username
     * @param password
     * @return User
     */
    User getUserByInfo(@Param("username") String username, @Param("password") String password);

    void addUser(User user);
}
