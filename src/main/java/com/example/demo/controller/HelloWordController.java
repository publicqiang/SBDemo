package com.example.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloWordController {

    @RequestMapping("hello")
    @ResponseBody
    public String say() {
        return "hello Spring boot";
    }

    @RequestMapping("helloFreemarker")
    public ModelAndView sayFreemarker(){
        ModelAndView mav = new ModelAndView();
        mav.addObject("message","hello spring boot");
        mav.setViewName("helloWord");
        return mav;
    }
}
