package com.example.demo.controller;


import com.example.demo.entity.User;
import com.example.demo.spring01.UserService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 用户注册
    @PostMapping("/register")
    public String register(){
        // 发送验证码
        rabbitTemplate.convertAndSend("mailExchange","user.mail","438993239@qq.com");
        // 以后更多的业务放入中间件即可...
        // 返回结果
        return "用户注册成功!";
    }*/

}
