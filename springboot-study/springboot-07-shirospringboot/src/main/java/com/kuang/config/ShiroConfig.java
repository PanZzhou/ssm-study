package com.kuang.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

//创建的下面三个bean对应shiro的外部架构：三个对象。
@Configuration
public class ShiroConfig {

    @Bean
    //ShiroFilterFactoryBean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("defaultWebSecurityManager") DefaultWebSecurityManager manager){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        //设置安全管理器
        bean.setSecurityManager(manager);

        //添加shiro的内置过滤器
        /*
            anon:无需认证就可以访问
            authc: 必须认证了才能访问
            user: 必须拥有  记住我  功能才能用
            perms:  拥有对某个资源权限才能访问
            role:  拥有某个角色权限才能访问

         */
        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<>();
        //授权，请求"/user/add"需要有user:add权限才能访问。
        filterChainDefinitionMap.put("/user/add","perms[user:add]");
        filterChainDefinitionMap.put("/user/update","perms[user:update]");

        //自动拦截/user/*形式的请求，这类请求需要登录后(authc)才能访问。
        filterChainDefinitionMap.put("/user/*","authc");
        //登录拦截
        bean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        //设置登录的请求，当没权限时会自动使用下面的请求跳转到登录页面
        bean.setLoginUrl("/toLogin");
        //未授权时挑战的页面
        bean.setUnauthorizedUrl("/noauth");

        return bean;
    }

    @Bean
    //DefaultWebSecurityManager
    public DefaultWebSecurityManager defaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联UserReaml
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    //创建Realm对象，需要自定义
    @Bean
    public UserRealm userRealm(){
        return new UserRealm();
    }

    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }
}
