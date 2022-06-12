package com.example.demo.utils;

import com.example.demo.cache.ValidateCodeCache;
import com.example.demo.entity.User;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import javax.annotation.Resource;

/**
 * 验证码校验类
 */
public class SendMessageUtil {

    @Resource
    JavaMailSender javaMailSender;
/*
    @Value("${spring.mail.username}")
    private String fromMailAddress;*/

    // 静态方法时使用注入
    private static String fromMailAddress;

    @Value("${spring.mail.username}")
    public void setUserName(String username){
        SendMessageUtil.fromMailAddress = username;
    }
    public String  getFromMailAddress(){
        return fromMailAddress;
    }

    /**
     */
    public static void test() {
        // 生成验证码
        System.out.println(fromMailAddress);
    }


    /**
     * 发送邮件
     * @param user 用户对象
     */
    /*public static Result sendSimpleMail(User user) {
        // 生成验证码
        String code = generateCode();
        // 存入缓存
        ValidateCodeCache.CodeCache.put(user.getUemail(),code);
        // 发送邮箱
        try{
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(fromMailAddress);
            simpleMailMessage.setTo(user.getUemail());
            simpleMailMessage.setSubject("注册用户验证码");
            simpleMailMessage.setText(code);
            javaMailSender.send(simpleMailMessage);
            return ResultUtil.success("");
        }catch (Exception e){
            return ResultUtil.error(e.getMessage());
        }
    }*/
    /**
     * 发送手机短信
     */
    public void sendSms(){

    }

    // 生成验证码
    private static String generateCode(){
        long codeL = System.nanoTime();
        String codeStr = Long.toString(codeL);
        String verifyCode = codeStr.substring(codeStr.length() - 6);
        return verifyCode;
    }

    // 校验验证码
    public Result verifyCode(String key, String code){
        // 比对验证码，成功则移除元素以及清除缓存；失败则返回失败信息
        if(code.equals(ValidateCodeCache.CodeCache.getIfPresent(key))){
            // 清除缓存
            ValidateCodeCache.CodeCache.invalidate(key);
            return ResultUtil.success("");
        }else{
            return ResultUtil.error(400, "验证码错误");
        }
    }

    public static void main(String[] args) throws InterruptedException {

        /*// 存入缓存数据
        ValidateCodeCache.CodeCache.put("111111","111111");
        ValidateCodeCache.CodeCache.put("222222","222222");
        // 校验验证码是否过期：定时存入两个验证码，然后，获取缓存数据
        MyThread1 myThread1 = new MyThread1();
        MyThread2 myThread2 = new MyThread2();
        myThread1.start();
        myThread2.start();*/

        //生成验证码
        for (int i = 0; i< 100; i++){
            String s = SendMessageUtil.generateCode();
            System.out.println(s);
        }
    }
}
