package com.company;

import com.sun.javafx.logging.PulseLogger;
import org.junit.Before;
import org.junit.Test;

import javax.sound.midi.Soundbank;
import javax.swing.plaf.SpinnerUI;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TestStreamAPI {


    List<Employee> emps = Arrays.asList(
            new Employee(102, "李四", 59, 6666.66, Status.BUSY),
            new Employee(101, "张三", 18, 9999.99, Status.FREE),
            new Employee(103, "王五", 28, 3333.33, Status.VOCATION),
            new Employee(104, "赵六", 8, 7777.77, Status.FREE),
            new Employee(104, "赵六", 8, 7777.77, Status.FREE),
            new Employee(104, "赵六", 8, 7777.77, Status.VOCATION),
            new Employee(105, "田七", 38, 5555.55, Status.BUSY)
    );
    /*
    * 给定一个数字列表，返回一个由每个数字的平方组成的列表
    *
    * */
    @Test
    public void test1(){
        Integer[] nums = {1,2,3,4,5};

        Integer[] integers = Arrays.stream(nums)
                .map((x) -> x * x)
                .toArray(Integer[]::new);
        for (int i =0; i< integers.length; i++){
            System.out.println(integers[i]);
        }
        Long collect = Arrays.stream(nums)
                .map((e) -> 1)
                .collect(Collectors.counting());
        System.out.println(collect);

    }


    /*
    *
    *用map和reduce数流中有多少个参数
    * */
    @Test
    public void test2(){
        Optional<Integer> reduce = emps.stream()
                .map((e) -> 1)
                .reduce(Integer::sum);
        System.out.println(reduce.get());

    }

    List<Transaction> transactions = null;


    @Before
    public void before(){
        Trader zhangsan = new Trader("zhangsan", "成都");
        Trader lisi = new Trader("lisi", "深圳");
        Trader wangwu = new Trader("lisi", "上海");
        Trader zhaoliu = new Trader("lisi", "成都");

         transactions = Arrays.asList(new Transaction(zhangsan, 2011, 300),
                new Transaction(zhangsan, 2013, 500),
                new Transaction(lisi, 2011, 3123),
                new Transaction(wangwu, 2016, 647),
                new Transaction(zhaoliu, 2017, 879),
                new Transaction(lisi, 2019, 234));
    }
    /*
    *1.找出2011年发生的交易，并按交易额排序
    *
    *
    *
    * 5.有没有交易员在上海工作的
    * 6.打印在成都工作的交易员的所有交易额
    * 7.所有交易中交易额最高的
    * 8.找到交易额最小的交易
    *
    * */
    @Test
    public void test3(){
        transactions.stream()
                    .filter((t) -> t.getYear() ==2011)
                    .sorted((t1,t2) -> Integer.compare(t2.getValue(),t1.getValue()))
                    .forEach(System.out::println);
    }
//    2.交易员在哪些不同城市工作过
    @Test
    public void test4(){
        transactions.stream()
                    .map((t) -> t.getTrader().getCity())
                    .distinct()
                     .forEach(System.out::println);
    }

//    3.返回来自成都的交易员，并按姓名排序
    @Test
    public void test5(){
        transactions.stream()
                .filter((t) -> t.getTrader().getCity().equals("成都"))
//                .map(Transaction::getTrader)
//                .sorted((t1,t2) -> t1.getName().compareTo(t2.getName()) )
                .distinct()
                .forEach(System.out::println);
    }

//    4.返回交易员姓名的字符串，并排序
    @Test
    public void test6(){
         transactions.stream()
                     .map((t) -> t.getTrader().getName())
                     .sorted()
                    .distinct()
                     .forEach(System.out::println);


    }

   /* @Test
    public Map<Boolean,List<Integer>> test7(){
        return () -> new HashMap<Boolean,List<Integer>>(){{
            put(Boolean.TRUE,new ArrayList<>());

        }};
    }*/






}

