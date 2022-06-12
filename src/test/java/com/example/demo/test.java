package com.example.demo;

import com.example.demo.entity.TestEntity;
import com.example.demo.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@SpringBootTest
public class test {

    @Resource
    TestEntity testEntity;

    @Test
    public void test(){
        Boolean enabled = testEntity.getEnabled();
        System.out.println(enabled);
    }

    @Test
    public void optionalTest(){
        Optional<String> empty = Optional.empty();
        String res = empty.orElse("");
        List list  = new ArrayList();
        list.add("a");
        list.add(1);
        Optional<List> optionalValue = Optional.of(list);
        List listBak = optionalValue.get();




    }

    /**
     * stream去重
     */
    @Test
    public void distinctTest(){

        List<User> users = new ArrayList<>();
        // 方法一：使用TreeSet去重，但是这个方法有副作用，会根据ID排序（TreeSet特性）
        List<User> newUsers1 = users.stream()
                .collect(
                        Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(User::getId))), ArrayList::new)
                );

        // 方法二：使用Map的key不可重复的特性，进行去重
        /**
         * toMap的第一参数是用来生成key值（去重的条件）；
         * 第二个参数就是用来生成value值的（这里就是指User对象）;b -> b表示选择将原来的对象作为map的value值
         * 第三个参数用在key值冲突的情况下：如果新元素产生的key在Map中已经出现过了，第三个参数就会定义解决的办法
         * 即：如果b1与b2的key值相同，选择b1作为那个key所对应的value值
         */
        List<User> newUsers2 = users.stream().collect(Collectors.toMap(User::getId, b -> b, (b1, b2) -> b2)).values().stream()
                .collect(Collectors.toList());
        // 方法三：自定义方法去重
        List<User> newUsers3 = users.stream().filter(distinctByKey(User::getId)).collect(Collectors.toList());
    }

    private static <T> Predicate< T > distinctByKey(Function< ? super T, ?> keyExtractor){
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    @Test
    public void testLambda(){
        new Thread(new Runnable(){
            @Override
            public void run(){
                System.out.println("hello world!");
            }
        }).start();

        // 开启一个线程并且执行
        new Thread( () -> System.out.println("hello world")).start();
    }
}
