package com.wt.controller;

import com.wt.entity.Login;
import com.wt.entity.User;
import com.wt.service.impl.UserServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/anon")
public class LoginController {
    @Autowired
    private UserServiceImpl userService;
    @RequestMapping("/login")
    public String login(Login login, HttpSession session){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(login.getTel(),login.getPassword());
        try{
            subject.login(token);
            User user = userService.findUser(login.getTel());
            session.setAttribute("user",user);
            return "index";
        }catch (Exception e){
            return "login";
        }
    }
    @RequestMapping("/need_login")
    public String needLogin(){
        return  "login";
    }

    @RequestMapping("/toRegister")
    public String toRegister(){
        return "register";
    }
}
