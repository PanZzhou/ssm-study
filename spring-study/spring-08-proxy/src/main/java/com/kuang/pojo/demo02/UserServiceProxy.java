package com.kuang.pojo.demo02;

//使用代理模式后，新增的日志功能在代理中写，而不需要改动原有的代码功能,即UserServiceImpl的功能不用改动（实际生产环境中，改动原有代码是大忌，这会造成
// 难以预料到的错误和后果，使原有的代码发生bug，解决方法就是用代理模式来新增功能）
public class UserServiceProxy implements UserService{
    UserServiceImpl userservice;

    public void setUserservice(UserServiceImpl userservice) {
        this.userservice = userservice;
    }

    @Override
    public void add() {
        log("add");
        userservice.add();
    }

    @Override
    public void delete() {
        log("delete");
        userservice.delete();
    }

    @Override
    public void update() {
        log("update");
        userservice.update();
    }

    @Override
    public void search() {
        log("search");
        userservice.search();
    }
    private void log(String msg){
        System.out.println("额外新增"+msg+"日志功能");
    }
}
