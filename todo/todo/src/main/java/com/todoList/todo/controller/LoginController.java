package com.todoList.todo.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.todoList.todo.dto.Login;
import com.todoList.todo.service.LoginService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("login")
public class LoginController {
    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping
    public String login(@RequestBody Login login,
                        HttpServletRequest req,
                        HttpServletResponse res){
        loginService.makeSession(login.getId(), req, res);
        return "true";
    }
    @GetMapping
    public boolean checkSession(HttpServletRequest req,
                                    HttpServletResponse res){

        return loginService.removeSession(req,res);
    }
}
