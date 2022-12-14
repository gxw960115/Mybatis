package com.itheima.dao;

import com.itheima.domain.User;

import java.io.IOException;
import java.util.List;

/**
 * @author gxw
 */
public interface UserMapper {

    public List<User> findAll() throws IOException;

    public User findById(int id);

}
