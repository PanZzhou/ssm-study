package com.kuang.pojo;

public class User {
    private String name;
    private int age;

    //测试c命名空间  contructor_arg  有参构造器
    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }
    //测试p命名空间  property 无参构造器
    public User() {}


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
