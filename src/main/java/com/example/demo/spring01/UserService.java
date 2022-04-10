package com.example.demo.spring01;

public interface UserService {

    // 注册方法
    public void register(User user);

    // 登录方法
    public void login(String name,String password);
}
