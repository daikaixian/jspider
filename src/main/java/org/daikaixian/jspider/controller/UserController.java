package org.daikaixian.jspider.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by kaishui on 15-9-19.
 */
@Controller
@RequestMapping("/")
public class UserController {

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(){
        System.out.println("fuck");
        return "index";
    }

}
