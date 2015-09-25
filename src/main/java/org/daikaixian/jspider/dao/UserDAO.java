package org.daikaixian.jspider.dao;

import org.daikaixian.jspider.models.User;

/**
 * Created by kaishui on 15-9-19.
 */

public interface UserDAO {
    /**
     * add new user
     * @return
     */
    public int insertUser(User user);
}
