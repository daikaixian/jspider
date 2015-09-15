package org.daikaixian.jspider.models;

import org.apache.ibatis.annotations.Select;
import org.daikaixian.alpha.models.user.User;

/**
 * Created by kaishui on 15-9-10.
 */
public interface UserMapper {

    @Select("select * from t_user where id= #{id}")
    public User findById(String id);

}
