package com.spring5.controller;

import com.spring5.domain.RegisterRequest;
import com.spring5.excptions.DuplicateMemberException;
import com.spring5.service.MemberRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/register")
public class RegistController {
    private final MemberRegisterService memberRegisterService;
    @Autowired
    public RegistController(MemberRegisterService memberRegisterService) {
        this.memberRegisterService = memberRegisterService;
    }

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
    @GetMapping("/step2")
    public String handleStep2Get(){
        return "redirect:/register/step1";
    }

    @PostMapping("/step3")
    public String handleStep3(RegisterRequest regReq){
        try{
            memberRegisterService.regist(regReq);
            return "register/step3";
        }catch(DuplicateMemberException ex){
            return "register/step2";
        }
    }

}
