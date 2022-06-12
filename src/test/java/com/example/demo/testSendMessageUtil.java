/*
package com.example.demo.controller;

import com.example.demo.utils.testUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Driver;
import java.util.*;

@SpringBootTest
public class testSendMessageUtil {

    @Test
    public void test() {
        testUtil.test();
    }

    @Test
    public void testJdbc() {
        ServiceLoader<Driver> loadedDrivers = ServiceLoader.load(Driver.class);
        Iterator driversIterator = loadedDrivers.iterator();
        while (driversIterator.hasNext()) {
            Driver next = (Driver) driversIterator.next();
            System.out.println(next.getClass());
        }
    }

    @Test
    public void getResourceFile() throws IOException {
        String PREFIX = "META-INF/services/";
        String fullName = PREFIX + "java.sql.Driver";
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        Enumeration<URL> resources = cl.getResources(fullName);
        while (resources.hasMoreElements()){
            URL url = resources.nextElement();
            System.out.println("----------------url(全路径)---------------");
            System.out.println(url);
            Iterator<String> parse = parse(url);
            System.out.println("----------------解析全路径获取全限定名---------------");
            while(parse.hasNext()){
                System.out.println(parse.next());
            }
        }
    }

    //读取u文件，返回文件内容里配置的接口全限路径
    private Iterator<String> parse( URL u)
            throws ServiceConfigurationError {
        InputStream in = null;
        BufferedReader r = null;
        ArrayList<String> names = new ArrayList<>();
        try {
            in = u.openStream();
            r = new BufferedReader(new InputStreamReader(in, "utf-8"));
            int lc = 1;
            while ((lc = parseLine( u, r, lc, names)) >= 0) ;
        } catch (IOException x) {
            System.out.println("youwenti");
        } finally {
            try {
                if (r != null) r.close();
                if (in != null) in.close();
            } catch (IOException y) {

            }
        }
        return names.iterator();
    }

    private int parseLine(URL u, BufferedReader r, int lc,
                          List<String> names)
            throws IOException, ServiceConfigurationError {
        String ln = r.readLine();
        if (ln == null) {
            return -1;
        }
        int ci = ln.indexOf('#');
        if (ci >= 0) ln = ln.substring(0, ci);
        ln = ln.trim();
        int n = ln.length();
        if (n != 0) {
            if ((ln.indexOf(' ') >= 0) || (ln.indexOf('\t') >= 0))
                System.out.println("有问题");
            int cp = ln.codePointAt(0);
            if (!Character.isJavaIdentifierStart(cp))
                System.out.println("有问题");
            for (int i = Character.charCount(cp); i < n; i += Character.charCount(cp)) {
                cp = ln.codePointAt(i);
                if (!Character.isJavaIdentifierPart(cp) && (cp != '.'))
                    System.out.println("有问题");
            }
            if (!names.contains(ln))
                names.add(ln);
        }
        return lc + 1;
    }
}
*/
