package com.kuang.pojo;

public class Hello {
    private String str;

    public String getStr() {
        return str;
    }

    //spring运用setStr来注入它创建好的实例
    public void setStr(String str) {
        this.str = str;
    }

    @Override
    public String toString() {
        return "Hello{" +
                "str='" + str + '\'' +
                '}';
    }
}
