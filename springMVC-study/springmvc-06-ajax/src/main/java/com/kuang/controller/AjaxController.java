package com.kuang.controller;

import com.kuang.pojo.User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController //屏蔽了去加载页面
public class AjaxController {
    @RequestMapping("/test")
    public String test(){
        return "成功";
    }
    @RequestMapping("/all")
    public String all(String name){
        if(name.equals("杨红")){
            return "yes";
        }else
            return "no";
    }
    @RequestMapping("/a2")
    public List<User> a2(){
        List<User> list = new ArrayList<User>();
        list.add(new User("帕潘说java",1,"男"));
        list.add(new User("炎炎说java",1,"女"));
        list.add(new User("洋洋说java",1,"男"));

        return list;
    }

    @RequestMapping("/loginCheck")
    public String a3(String name){
        System.out.println(name);
        String msg="";
        if(name!=null){
            if(name.equals("admin"))
                msg="ok";
            else
                msg="用户名不存在";
        }
        return msg;
    }

    @RequestMapping("/pwdCheck")
    public String a4(String pwd){
        String msg="";
        if(pwd!=null){
            if(pwd.equals("123456"))
                msg="ok";
            else
                msg="密码错误";
        }
        return msg;
    }
}
