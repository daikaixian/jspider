package org.lee.stumanage.serviceimpl;

import org.lee.stumanage.dao.UserDAO;
import org.lee.stumanage.models.User;
import org.lee.stumanage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;


@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDAO userDAO;

    @Override
    public int insertUser(User user) {
        return userDAO.insertUser(user);
    }

    @Override
    public int updateUser(User user) {
        return userDAO.updateUser(user);
    }

    @Override
    public List <User> findUserByConditions(String number, String pswd, int role, String hometown, int grade, int orderby, int id) {


        HashMap conditionMap = new HashMap();
        conditionMap.put("number", number);
        conditionMap.put("pswd", pswd);
        conditionMap.put("hometown", hometown);
        conditionMap.put("orderby", orderby);

        if(role == 2){
            conditionMap.put("role", null);
        }else {
            conditionMap.put("role", role);
        }

        if(grade == 0){
            conditionMap.put("grade", null);
        }else {
            conditionMap.put("grade", grade);
        }

        if(id == 0) {
            conditionMap.put("id", null);
        } else{
            conditionMap.put("id", id);
        }

        return userDAO.findUserByConditions(conditionMap);
    }

    @Override
    public int deleteUserById(int id) {
        return userDAO.deleteUserById(id);
    }
}
