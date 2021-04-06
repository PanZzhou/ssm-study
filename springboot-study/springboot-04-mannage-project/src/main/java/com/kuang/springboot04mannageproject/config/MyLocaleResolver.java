package com.kuang.springboot04mannageproject.config;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

public class MyLocaleResolver implements LocaleResolver {
    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        //获取请求中的语言参数,这个lan是前端页面定义的变量
        String lan = request.getParameter("lan");
        Locale locale = Locale.getDefault();//如果没有就使用默认的
        //如果请求中携带了国际化的参数，
        if(!StringUtils.isEmpty(lan)){
            //zh_CN
            String[] s = lan.split("_");
            //国家，地区
            locale = new Locale(s[0], s[1]);
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {

    }
}
