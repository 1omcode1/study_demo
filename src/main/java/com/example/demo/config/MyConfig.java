package com.example.demo.config;


import com.example.demo.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartFile;

/**
 * 1、告诉springboot MyConfig是一个配置类 == 配置文件
 * 2、配置类本身也是组件
 * 3、proxyBeanMethods： 代理组件方法、@Configuration === @Configuration(proxyBeanMethods = true)，默认为true
 *   疑问：字面理解，Configuration既是一个组件，本身也是一个类，user01是它的一个方法，那如果我们是不是也能通过获取组件的方法获取到MyConfig，然后再直接调用它的user01方法？
 *        那这样如果多调用几次use01方法生成的值，是相等还是不等？
 *   答案：可以调用，并且获取到的值相等。因为外无论对配置类中的这个组件注册方法调用多少次获取的都是之前注册容器中的单实例对象
 */
//@Configuration
public class MyConfig {

/*    @Bean // 给容器中添加组件user01。以方法名作为组件的id。返回类型就是组件类型，返回的值，就是组件在容器中的实例
//    @Bean("userName")  // 也可以在bean注解上对这个组件重命名
    public User user01(){
        return new User("张三",18);
    }*/

}
