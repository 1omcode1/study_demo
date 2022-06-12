package com.example.demo;
import com.example.demo.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


@SpringBootTest
public class Stream {


    /**
     * stream 去重
     */
    @Test
    public void distinct(){

        List<User> users = new ArrayList<>();
        // 方法一：使用TreeSet去重，但是这个方法有副作用，会根据ID排序（TreeSet特性）
        List<User> newUsers1 = users.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(User::getId))), ArrayList::new));

        /**
         * toMap的第一参数是用来生成key值（去重的条件）；
         * 第二个参数就是用来生成value值的（这里就是指User对象）;b -> b表示选择将原来的对象作为map的value值
         * 第三个参数用在key值冲突的情况下：如果新元素产生的key在Map中已经出现过了，第三个参数就会定义解决的办法
         * 即：如果b1与b2的key值相同，选择b1作为那个key所对应的value值
         */
        // 方法二：map去重
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
    public void lamdbaTest(){
        /*ArrayList<Integer> list = new ArrayList<>();
        list.add(11);
        list.add(22);
        list.add(33);
        list.add(44);
        Collections.sort(list,(o1,o2)->o2-o1);
        System.out.println("排序后：" + list);*/
        String path = "C:\\Users\\83972\\Desktop\\CDA共享文档\\1-Z2106010125"; // 路径
        File f = new File(path);//获取路径  F:测试目录
        if (!f.exists()) {
            System.out.println(path + " not exists");//不存在就输出
            // return null;
        }
        Pattern pattern = Pattern.compile("EMR-SD-(.*)-?");
        List<String> list = new ArrayList<>();
        File fa[] = f.listFiles();//用数组接收  F:笔记总结C#, F:笔记总结if语句.txt
        for (int i = 0; i < fa.length; i++) {//循环遍历
            File fs = fa[i];//获取数组中的第i个
            Matcher match = pattern.matcher(fs.getName());
            if(match.find()){
                String id = match.group(1);
                list.add(id);
            }
        }
//        return list;
    }


    @Test
    public void testCdaUtil(){

        int[] array1 = {1,6,7,15,16,17,20,28,31,32,37,38,39,49,52}; //Z2203260001
        int[] array2 = {1,17,20,23,31,32,34,35,36,51,52}; //Z2201310002
        int[] array3 = {1,7,17,20,23,25,32,34,35,37,39,49,52,53}; //Z2106010125

        // 读取文件夹内所有文件的名字，正则提取其中的数字，然后获取到编号



        // 数组转集合
//        Arrays.asList(array);

//        List<String, > list = new ArrayList<>();

    }

    /**
     * 获取文件夹中的编号
     * @return
     */
    @Test
    public List<String> getFileName(){
        String path = "C:\\Users\\83972\\Desktop\\CDA共享文档\\1-Z2106010125"; // 路径
        File f = new File(path);//获取路径  F:测试目录
        if (!f.exists()) {
            System.out.println(path + " not exists");//不存在就输出
            return null;
        }
        Pattern pattern = Pattern.compile("EMR-SD-(.*)-?");
        List<String> list = new ArrayList<>();
        File fa[] = f.listFiles();//用数组接收  F:笔记总结C#, F:笔记总结if语句.txt
        for (int i = 0; i < fa.length; i++) {//循环遍历
            File fs = fa[i];//获取数组中的第i个
            Matcher match = pattern.matcher(fs.getName());
            if(match.find()){
                String id = match.group(1);
                list.add(id);
            }
        }
        return list;
    }








}
