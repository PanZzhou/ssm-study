package com.kuang.springboot04mannageproject.controller;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.jws.WebParam;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @RequestMapping("/user/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Model model,
                        HttpSession session){
        //具体的业务
        if(!StringUtils.isEmpty(username)&&"123456".equals(password)){
            session.setAttribute("loginUser",username);
            return "redirect:/main.html";
        }else{
            model.addAttribute("msg","用户名或者密码错误");
            return "index";
        }
    }
    @RequestMapping("/user/logOut")
    public String logOut(HttpSession session){
        session.invalidate();
        return "redirect:/index.html";
    }
}
