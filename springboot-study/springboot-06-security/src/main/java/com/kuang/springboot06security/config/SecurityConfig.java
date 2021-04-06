package com.kuang.springboot06security.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

//Aop：拦截器！
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override //http的授权
    protected void configure(HttpSecurity http) throws Exception {
        //链式编程
        //授权，类似于拦截器的功能，使用AOP实现
        http.authorizeRequests()
                .antMatchers("/").permitAll()    //根请求所有人能访问
                .antMatchers("/level1/**").hasRole("vip1")   //只有vip1角色才能访问这个请求
                .antMatchers("/level2/**").hasRole("vip2")   //只有vip2角色才能访问这个请求
                .antMatchers("/level3/**").hasRole("vip3");  //只有vip3角色才能访问这个请求

        //没有权限默认会到登录页面,需要开启登录的页面,页面是自动生成的，自动匹配/login请求，或者权限不足时跳转到/login请求
        http.formLogin().loginPage("/toLogin")
                .loginProcessingUrl("/login")
                .usernameParameter("name")
                .passwordParameter("pwd"); //定制登录页

        //防止网站攻击：get方法不安全，需要用post方法，但是logout又不是一个表单，只能是get方法。
        //springboot默认开启了CSRF，防止get方法的网站攻击。但这样的话可能会导致登录和登出出现404错误，需要关闭它。
        http.csrf().disable();

        //记住我功能，就是设置cookie，默认保存两周
        http.rememberMe().rememberMeParameter("rememberMe");

        //开启了注销功能  自动匹配/logout请求
        http.logout().deleteCookies("remove").invalidateHttpSession(true).logoutSuccessUrl("/login");
    }

    //认证，springboot2.1.x可以直接使用,给用户分配角色
    //密码编码：PasswordEncoder
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.jdbcAuthentication();  //从数据库中来认证用户
        //从内存中的数据认证用户,下面是设置几个用户。另外还可以从数据库中读取用户后设置权限。
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("kuangshen").password(new BCryptPasswordEncoder().encode("123456")).roles("vip2","vip3")
                .and()
                .withUser("yang").password(new BCryptPasswordEncoder().encode("yang")).roles("vip1","vip2","vip3");
    }
}
