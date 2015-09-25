package org.daikaixian.jspider.serviceimpl;

import org.daikaixian.jspider.dao.UserDAO;
import org.daikaixian.jspider.models.User;
import org.daikaixian.jspider.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by kaishui on 15-9-19.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public int insertUser(User user) {
        return userDAO.insertUser(user);
    }
}
