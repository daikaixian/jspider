package org.lee.stumanage.service;

import org.junit.Before;
import org.junit.Test;
import org.lee.stumanage.models.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by kaishui on 15-9-28.
 */
public class UserServiceTest {
    private UserService userService ;

    @Before
    public void before() {
        @SuppressWarnings("resource")
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:conf/spring.xml"
                , "classpath:conf/spring-mybatis.xml"});
        userService = (UserService) context.getBean("userServiceImpl");
    }

    @Test
    public void testInsertUser() throws Exception {
        User user = new User();
        user.setNumber("201210314053");
        user.setName("Bob");
        user.setGrade(410);
        user.setTelephone("13838143678");
        user.setPassword("123456");
        user.setHometown("London");
        user.setRole(1);
        userService.insertUser(user);
        System.out.println(user.getId());



    }

    @Test
    public void testDeleteUser() throws Exception{
        userService.deleteUserById(2);

    }

    @Test
    public void testUpdateUser() throws Exception{
        User user = new User();
        user.setId(1);
        user.setName("sb");
        userService.updateUser(user);

    }

    @Test
    public void testFindUserByConditions(){
        List<User> userList = userService.findUserByConditions(null,null,1,null,0,0,0, null);
        System.out.println(userList.size());
        System.out.println(userList.get(0).getName());


    }
}