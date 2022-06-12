package com.example.demo.utils;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

public class ModuleClassLoader extends URLClassLoader {

    private static ModuleClassLoader instance;
    private static URLClassLoader classLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
    private static final Method ADD_URL = initAddMethod();


    public ModuleClassLoader(URL[] urls) {
        super(urls);
    }

    public static ModuleClassLoader getInstance() {
        if (instance == null) {
            instance = new ModuleClassLoader(new URL[]{});
        }
        return instance;
    }

    private static Method initAddMethod() {
        try {
            Method addUrl = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
            addUrl.setAccessible(true);
            return addUrl;
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 加载jar包
     *
     * @param filepath
     */
    public void loadClasspath(String filepath) {
        File file = new File(filepath);
        loopFiles(file);
    }

    /**
     * 加载源代码
     *
     * @param filepath
     */
    public void loadResourceDir(String filepath) {
        File file = new File(filepath);
        loopDirs(file);
    }

    /**
     * 循环遍历目录，找出所有的资源路径。
     *
     * @param file 当前遍历文件
     */
    private void loopDirs(File file) {
        // 资源文件只加载路径
        if (file.isDirectory()) {
            loadJar(file);
            File[] tmps = file.listFiles();
            for (File tmp : tmps) {
                loopDirs(tmp);
            }
        }
    }

    /**
     * 循环遍历目录，找出所有的jar包。
     *
     * @param file 当前遍历文件
     */
    private void loopFiles(File file) {
        if (file.isDirectory()) {
            File[] tmps = file.listFiles();
            for (File tmp : tmps) {
                loopFiles(tmp);
            }
        } else {
            if (file.getAbsolutePath().endsWith(".jar") || file.getAbsolutePath().endsWith(".zip")) {
                loadJar(file);
            }
        }
    }

    private void loadJar(File file) {
        try {
            ADD_URL.invoke(classLoader, file.toURI().toURL());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
