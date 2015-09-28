package org.lee.stumanage.serviceimpl;

import org.lee.stumanage.dao.UserDAO;
import org.lee.stumanage.models.User;
import org.lee.stumanage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDAO userDAO;

    @Override
    public int insertUser(User user) {
        return userDAO.insertUser(user);
    }
}
