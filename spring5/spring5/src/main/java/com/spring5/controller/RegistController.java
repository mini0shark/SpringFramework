package com.spring5.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/register")
public class RegistController {
    @RequestMapping("/step1")
    public String handleStep1(){
        return "register/step1";
    }
    @PostMapping("/step2")
    public String handleStep2(HttpServletRequest request){
        String agreeParam = request.getParameter("agree");
        if(agreeParam == null || !agreeParam.equals("true")){
            return "register/step1";
        }
        return "register/step2";
    }

}
