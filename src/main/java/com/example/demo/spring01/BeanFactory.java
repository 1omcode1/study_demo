package com.example.demo.spring01;

public class BeanFactory {

    /*
     * 对象的创建方法：
     *      1. 直接调用构造方法创建对象： UserService userService  = new UserServiceImpl(); 但是存在耦合
     *      2. 通过反射的形式 创建对象：有两种方法
     *          Class clazz = Class.forName("com.example.demo.spring01.userServiceImpl")
     *          UserService userService = (UserServiceImpl) clazz.newInstance();
     */
    public static UserServiceImpl getUserServiceImpl() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
//        return new UserServiceImpl();

        Class clazz = Class.forName("com.example.demo.spring01.UserServiceImpl"); // 但是这里使用了固定的路径，仍然是一种耦合
        UserServiceImpl userService = (UserServiceImpl) clazz.newInstance();
        return userService;
    }

}
