package org.daikaixian.jspider.models;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.daikaixian.alpha.models.user.User;

import java.io.Reader;

/**
 * Created by kaishui on 15-9-10.
 */
public class UserDaoTest {

        public static void main(String[] args) throws Exception {
        Reader reader = Resources.getResourceAsReader("config.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);

        //sessionFactory.getConfiguration().addMapper(UserMapper.class);
        SqlSession session = sessionFactory.openSession();
        try {
            UserMapper userMapper = session.getMapper(UserMapper.class);
            User user = userMapper.findById("001");

            System.out.println(user.getName());
            System.out.println("End!");
        } finally {
            session.close();
        }

    }

}