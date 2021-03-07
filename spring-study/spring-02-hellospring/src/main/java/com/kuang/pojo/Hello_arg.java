package com.kuang.pojo;
//使用有参构造方式初始化对象
public class Hello_arg {
    private String str;

    public Hello_arg(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    @Override
    public String toString() {
        return "Hello_arg{" +
                "str='" + str + '\'' +
                '}';
    }
}
