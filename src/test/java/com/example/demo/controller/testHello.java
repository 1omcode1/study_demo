package com.example.demo.controller;

import com.example.demo.utils.JarClassLoader;
import com.example.demo.utils.ModuleClassLoader;
import org.aspectj.apache.bcel.classfile.ModuleMainClass;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

//@SpringBootTest
public class testHello {

    /*@Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testHeel() {
        rabbitTemplate.convertAndSend("hello", "hello world!");
    }

    @Test
    public void testJarFile() {
        String url = "D:\\公司项目\\HDRMS\\新版本\\HIP-HDRMS-Server\\target\\hip-hdrms-server-1.0.0.jar";
        try {
            JarFile jarFile = new JarFile(url);
            Enumeration<JarEntry> jarEntryEnumeration = jarFile.entries();
            while (jarEntryEnumeration.hasMoreElements()) {
                JarEntry jarEntry = jarEntryEnumeration.nextElement();
                System.out.println(jarEntry);

                String className = jarEntry.getName().substring(0, jarEntry.getName().length() - 6);
                className = className.replace('/', '.');
                System.out.println(className);
            }
        } catch (IOException e) {
            throw new RuntimeException("获取插件中的文件信息失败，插件名称：" + "testJarFile");
        }

    }

    @Test
    public void testJarFile2() throws IOException, ClassNotFoundException {
        String urlPath = "C:\\Users\\83972\\Desktop\\1\\spring-plugin-cli\\main program\\target\\hope-0.0.1-SNAPSHOToriginal.jar";
        List<String> size = new ArrayList<String>();
        JarFile jarFile = new JarFile(urlPath);
        File file = new File(urlPath);
        URL url = file.toURI().toURL();
        ClassLoader loader = new URLClassLoader(new URL[]{url});
        Enumeration<JarEntry> entries = jarFile.entries();
        while (entries.hasMoreElements()) {
            // 获取jar中的条目
            JarEntry entry = entries.nextElement();
            String name = entry.getName();
            formatName(size, name, loader);
        }
    }

    public void formatName(List<String> size, String name, ClassLoader loader) throws ClassNotFoundException {
        if (name.endsWith(".class")) {
            String d = name.replaceAll("/", ".");
            int n = 6;
            //第一个参数是开始截取的位置，第二个是结束位置。
            String names = d.substring(0, name.length() - n);
            System.out.println(names);
            if(names.contains("BOOT-INF.classes.")){
                names = names.replace("BOOT-INF.classes.","");
                Class<?> cls = loader.loadClass(names);
            }
        }
    }*/


    @Test
    public void testJarClassLoader() throws IOException, ClassNotFoundException {
        String urlPath = "C:\\Users\\83972\\Desktop\\插件式开发\\spring-plugin-cli\\main program\\target\\hope-0.0.1-SNAPSHOT.jar";
        JarFile jarFile = new JarFile(urlPath);
        JarClassLoader jarClassLoader = new JarClassLoader(jarFile);
        Enumeration<JarEntry> entries = jarClassLoader.jarFile.entries();
        while (entries.hasMoreElements()) {
            JarEntry jarEntry = entries.nextElement();
            String name = jarEntry.getName();
            if (name.endsWith(".class") && name.contains("BOOT-INF")) {
                Class clazz1 = jarClassLoader.loadClass(name);
                System.out.println(clazz1);
            }
        }
    }

    @Test
    public void parseJar() throws IOException, ClassNotFoundException {
        String urlPath = "C:\\Users\\83972\\Desktop\\1\\spring-plugin-cli\\main program\\target\\hope-0.0.1-SNAPSHOT.jar";
        File file = new File(urlPath);
        URL url = file.toURI().toURL();
        // 首先定义一个类加载器
        ModuleClassLoader classLoader = new ModuleClassLoader(new URL[]{url});
        // 解析jar包
        JarFile jarFile = new JarFile(urlPath);
        classLoader.loadClasspath(urlPath);
    }

    @Test
    public void test5(){
        double f = 9;
        boolean b = isIntegerForDouble(f);
        if(b){
            System.out.println("是整型："+f);
        }else{
            System.out.println("是浮点形："+f);
        }
    }

    public static boolean isIntegerForDouble(double obj) {
        double eps = 1e-10;  // 精度范围
        return obj-Math.floor(obj) < eps;
    }

    @Test
    public void test8(){
        ServiceLoader<String> load = ServiceLoader.load(String.class);
        Iterator<String> iterator = load.iterator();

        for (String s : load) {

        }

    }
}
