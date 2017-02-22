package org.lee.stumanage.dao;


import org.lee.stumanage.models.User;

import java.util.HashMap;
import java.util.List;

public interface UserDAO {
    /**
     * add new user
     * @return
     */
    public int insertUser(User user);

    public int updateUser(User user);

    public List <User> findUserByConditions(HashMap conditionMap);

    public int deleteUserById(int id);


}
