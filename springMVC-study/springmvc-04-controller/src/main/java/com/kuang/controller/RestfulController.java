package com.kuang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RestfulController {
//传统的请求方式：https:localhost:8080/add?a=1&b=3
    //Restful风格：https:localhost:8080/add/a/b


    @RequestMapping(value="/add/{a}/{b}",method = RequestMethod.GET)
    public String test(@PathVariable int a,@PathVariable int b, Model model){
        int res = a+b;
        model.addAttribute("msg","结果1为"+res);
        return "test";
    }
//重定向
    @RequestMapping(value="/add",method = RequestMethod.GET)
    public String test3(Model model){
        return "redirect:/index.jsp";
    }

//    @RequestMapping(value="/add/{a}/{b}",method = RequestMethod.GET)
    @GetMapping("/add1/{a}/{b}")
    public String test1(@PathVariable int a,@PathVariable int b, Model model){
        int res = a+b;
        model.addAttribute("msg","结果为"+res);
        return "test";
    }

    @RequestMapping(value="/add/{a}/{b}",method = RequestMethod.POST)
    public String test2(@PathVariable int a,@PathVariable int b, Model model){
        int res = a+b;
        model.addAttribute("msg","结果2为"+res);
        return "test";
    }

}
