package com.example.demo.utils;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class testUtil {

    public static String fromMailAddress;

    @Value("${spring.mail.username}")
    public void setUserName(String name){
        testUtil.fromMailAddress = name;
    }

    public String getFromMailAddress(){
        return fromMailAddress;
    }

    /**
     * 测试静态方法中使用@Value注解获取到的值
     */
    public static void test() {
        System.out.println("aaaaaaaaaaaaaaaa");
        System.out.println(fromMailAddress);
    }
}
