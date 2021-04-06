package com.kuang.config;


import com.kuang.pojo.User;
import com.kuang.service.UserServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {
    @Autowired
    UserServiceImpl userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了授权doGetAuthorizationInfo");
        //在这里给用户添加请求的权限
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//        info.addStringPermission("user:add"); //给用户添加权限

        Subject subject = SecurityUtils.getSubject();
        User currentUser = (User) subject.getPrincipal();
        System.out.println(currentUser);
        info.addStringPermission(currentUser.getPerms());

        return info;
    }

    //登录时，认证都会走到这个函数当中。
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行了认证doGetAuthenticationInfo");
        UsernamePasswordToken userToken = (UsernamePasswordToken) authenticationToken;
//        String name = "root";
//        String password = "123456";
//        if(!userToken.getUsername().equals(name)){
//            return null;   //抛出异常  UnknownAccountException
//        }
        //连接真实数据库
        User user = userService.queryUserByName(userToken.getUsername());
        if(user==null){  //没有这个人
            return null;
        }

        //密码认证，Shiro自动会做
        //第一个参数user是从数据库中查询出来的用户信息，在认证时传入，可以在授权时通过subject.getPrincipal()取出来，给其进行设置权限操作。
        return new SimpleAuthenticationInfo(user,user.getPwd(),"");
    }
}
