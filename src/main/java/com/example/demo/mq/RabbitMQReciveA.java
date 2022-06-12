/*
package com.example.demo.mq;


import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQReciveA {

    @Autowired
    private MailSender myMailSender;

    @RabbitListener(bindings = @QueueBinding(value = @Queue, exchange = @Exchange(name = "mailExchange",type = "topic"), key = {"user.*"}))
    private void sendEMail(String uemail){
        String subject = "Pornhub！";
        String content = "恭喜你注册成功！";
//        myMailSender.sendSimpleMail(uemail, subject, content);
        //发送邮件
        System.out.println("已发送邮件");
    }
}
*/
