package com.example.demo;

import com.example.demo.config.MyConfig;
import com.example.demo.entity.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;


//springboot默认是扫描主程序所以包及其下面所有子包里的组件，如果想改变扫描路径，可以使用scanBasePackages
//@SpringBootApplication(scanBasePackages = "com.example"):表示在example包内的所有组件，往上扩大了一个范围
//也可以使用@ComponentScan指定扫描路径
//@SpringBootApplication其实是一个合成注解，等于下面的注解：
@SpringBootConfiguration  //
@EnableAutoConfiguration
@ComponentScan("com.example.demo")
//所以如果非要使用@ComponentScan改变扫描路径，就可以不使用@SpringBootApplication，换回原本的三个注解
//上面两个注解加上@ComponentScan("com.example") === @SpringBootApplication(scanBasePackages = "com.example")
//@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);

//        // 1、返回我们的IOC容器
//        ConfigurableApplicationContext run = SpringApplication.run(DemoApplication.class, args);
//
//        // 2、查看容器里面的组件
//        String[] names = run.getBeanDefinitionNames();
//        for (String name : names) {
//            System.out.println(name);
//        }
//
//        // 3、验证默认注册的组件是不是单实例==>结果为true，得出结论，是单实例。
//        // 获取组件
//        User user01 = run.getBean("user01", User.class);
//        User user02 = run.getBean("user01", User.class);
//        System.out.println("组件：" + (user01 == user02));
//
//        // 4、proxyBeanMethods方法的分析
//        MyConfig myConfig = run.getBean(MyConfig.class);
//        User user = myConfig.user01();
//        User user1 = myConfig.user01();
//        System.out.println((user == user1));
    }

}
