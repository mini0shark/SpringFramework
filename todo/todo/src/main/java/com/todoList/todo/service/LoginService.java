package com.todoList.todo.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginService {

    public boolean makeSession(String id, HttpServletRequest req, HttpServletResponse res){
        HttpSession session = req.getSession();
        if(id.equals("")|| id==null) return false;
        session.setAttribute("id", id);
        session.setMaxInactiveInterval(60*60*24);

        Cookie cookie= new Cookie("id", id);
        cookie.setMaxAge(60*60*24);
        cookie.setPath("/");
        res.addCookie(cookie);
        return true;
    }
    public String checkSession(HttpServletRequest req, HttpServletResponse res){
        HttpSession session = req.getSession();
        return session.getId()+", "+session.getAttribute("id")+", "+session.getMaxInactiveInterval();
    }
    public boolean removeSession(HttpServletRequest req, HttpServletResponse res){
        HttpSession session = req.getSession();
        session.setAttribute("id", null);
        Cookie cookie = new Cookie("id", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        res.addCookie(cookie);
        return true;
    }
}
