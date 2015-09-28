package org.lee.stumanage.dao;


import org.lee.stumanage.models.User;

public interface UserDAO {
    /**
     * add new user
     * @return
     */
    public int insertUser(User user);
}
