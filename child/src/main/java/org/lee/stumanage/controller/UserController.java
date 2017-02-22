package org.lee.stumanage.controller;


import com.sun.org.apache.xpath.internal.operations.Mod;
import org.lee.stumanage.models.User;
import org.lee.stumanage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;


@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private  HttpServletRequest request;

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public ModelAndView index(){
        return new ModelAndView("index");
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ModelAndView login(@RequestParam(value = "number", defaultValue = "")String number,
                               @RequestParam(value = "pswd", defaultValue = "") String pswd){

        if(StringUtils.isEmpty(number)||StringUtils.isEmpty(pswd)){
            return new ModelAndView("loginfailed");
        }
        List<User> userList = userService.findUserByConditions(number, pswd, 2, null, 0, 0, 0, null);
        if(userList.isEmpty()){
            return new ModelAndView("loginfailed");
        }
        User user = userList.get(0);
        if(user.getRole() == 0){        //teacher
            request.getSession().setAttribute("teacher", user);
            HashMap model = findStudentList();
            return new ModelAndView("teacher", model);
        }else {
            request.getSession().setAttribute("student", user);
            return new ModelAndView("student");
        }
    }

    @RequestMapping(value = "createstudent/", method = RequestMethod.POST)
    public ModelAndView createStudent(@RequestParam(value = "number",defaultValue = "")String number,
                                      @RequestParam(value = "name",defaultValue = "")String name,
                                      @RequestParam(value = "grade",defaultValue = "")int grade,
                                      @RequestParam(value = "telephone",defaultValue = "")String telephone,
                                      @RequestParam(value = "hometown",defaultValue = "")String hometown){
        User user = new User();
        user.setName(name);
        user.setNumber(number);
        user.setPassword("123456");
        user.setGrade(grade);
        user.setRole(1); //0 :teacher, 1:student
        user.setTelephone(telephone);
        user.setHometown(hometown);
        userService.insertUser(user);

        HashMap model = findStudentList();
        return new ModelAndView("teacher", model);
    }

    @RequestMapping(value = "deletestudent/", method = RequestMethod.GET)
    public ModelAndView deleteStudent(@RequestParam(value = "studentId",defaultValue = "")int studentId ){
        userService.deleteUserById(studentId);
        HashMap model = findStudentList();
        return new ModelAndView("teacher", model);
    }

    @RequestMapping(value = "updatestudent/", method = RequestMethod.POST)
    public ModelAndView updateStudent(@RequestParam(value = "number",defaultValue = "")String number,
                                      @RequestParam(value = "name",defaultValue = "")String name,
                                      @RequestParam(value = "grade",defaultValue = "")int grade,
                                      @RequestParam(value = "telephone",defaultValue = "")String telephone,
                                      @RequestParam(value = "hometown",defaultValue = "")String hometown,
                                      @RequestParam(value = "id",defaultValue = "")int id){
        List<User> studentList = userService.findUserByConditions(null, null, 1, null, 0, 0, id, null); //search the students
        User user = studentList.get(0);
        user.setName(name);
        user.setNumber(number);
        user.setGrade(grade);
        user.setTelephone(telephone);
        user.setHometown(hometown);
        userService.updateUser(user);

        HashMap model = findStudentList();
        return new ModelAndView("teacher", model);
    }

    @RequestMapping(value = "countstudent/" ,method = RequestMethod.GET)
    public ModelAndView countStudent(@RequestParam(value = "grade" ,defaultValue = "400")int grade){
        HashMap model = new HashMap();
        List<User> studentList = userService.findUserByConditions(null, null, 1, null, grade, 0, 0, null); //search all students
        model.put("studentList", studentList);
        return new ModelAndView("count", model);
    }

    @RequestMapping(value = "/querybyname/" ,method = RequestMethod.GET)
    public ModelAndView querybyname(@RequestParam(value = "name",defaultValue = "张三")String name){
        HashMap model = new HashMap();
        System.out.println(name);
        List<User> studentList = userService.findUserByConditions(null, null, 1, null, 0, 0, 0, name); //search all students
        model.put("studentList", studentList);
        System.out.println(studentList.size());
        return new ModelAndView("student", model);
    }


    private HashMap findStudentList(){
        HashMap model = new HashMap();
        List<User> studentList = userService.findUserByConditions(null, null, 1, null, 0, 0, 0, null); //search all students
        model.put("studentList", studentList);

        return model;
    }



}
