package com.kuang.pojo.demo03;

import com.kuang.pojo.demo02.UserService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

//动态代理
public class ProxyInvocationHandler implements InvocationHandler {
    //被代理对象
    private Object target;
    //传入被代理对象
    public void setTarget(Object target) {
        this.target = target;
    }
    //获取代理对象
    public Object getProxy(){
        return Proxy.newProxyInstance(this.getClass().getClassLoader(), target.getClass().getInterfaces(),this);
    }
    //获得动态代理并执行程序之后
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log(method.getName());
        System.out.println(method+ "  " + args);
        Object result = method.invoke(target, args);
        return result;
    }
    public void log(String msg){
        System.out.println("执行了"+msg+"方法");
    }
}
