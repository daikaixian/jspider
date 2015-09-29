package org.lee.stumanage.service;

import org.lee.stumanage.models.User;

import java.util.List;


public interface UserService {

    public int insertUser(User user);

    public int updateUser(User user);

    public List<User> findUserByConditions(String number, String pswd, int role, String hometown, int grade, int orderby, int id);

    public int deleteUserById(int id);
}
