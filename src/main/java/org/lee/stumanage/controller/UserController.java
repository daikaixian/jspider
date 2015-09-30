package org.lee.stumanage.controller;


import org.lee.stumanage.models.User;
import org.lee.stumanage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;


@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public ModelAndView index(){
        return new ModelAndView("index");
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    private ModelAndView login(@RequestParam(value = "number", defaultValue = "")String number,
                               @RequestParam(value = "pswd", defaultValue = "") String pswd){

        if(StringUtils.isEmpty(number)||StringUtils.isEmpty(pswd)){
            return new ModelAndView("loginfailed");
        }
        List<User> userList = userService.findUserByConditions(number, pswd, 2, null, 0, 0, 0);
        if(userList.isEmpty()){
            return new ModelAndView("loginfailed");
        }
        User user = userList.get(0);
        HashMap model = new HashMap();
        if(user.getRole() == 0){        //teacher
            List<User> studentList = userService.findUserByConditions(null, null, 1, null, 0, 0, 0); //search all students
            model.put("teacher", user);
            model.put("studentList", studentList);
            return new ModelAndView("teacher", model);
        }else {
            model.put("student", user);
            return new ModelAndView("student", model);
        }
    }





}
