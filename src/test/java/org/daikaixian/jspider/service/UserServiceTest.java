package org.daikaixian.jspider.service;

import org.daikaixian.jspider.models.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by kaishui on 15-9-19.
 */
public class UserServiceTest {

    private UserService userService;

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
        user.setNickname("你好");
        user.setState(2);
        userService.insertUser(user);
        System.out.println(user.getId());
    }
}