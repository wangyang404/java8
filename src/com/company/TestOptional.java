package com.company;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Optional;

public class TestOptional {

    /*
    * 容器类的常用方法
    * 1. Optional.of(T t) 创建一个Optional实例
    * 2. Optional.empty() 创建一个空的Opional实例
    * 3. Optional.ofNullable(T t) 若t不为null,创建Optional实例，否则创建空实例
    * 4.isPresent() 判断是否包含值
    * 5.orElse(T t) 如果调用对象包含值，返回该值，否则返回t
    * 6.orElseGet(Supplier s) 如果调用对象包含值，返回该值，否则返回s的返回值
    * 7.Map(Function f) 如果有值就对其进行处理，并返回处理后的Optional，否则返回Optionla.empty()
    * 8.flatMap(Function mapper) 与Map类似，要求返回值必须是optional
    * */
    @Test
    public void test1(){
        Optional<Employee> optional = Optional.of(null);
        Employee employee = optional.get();
        System.out.println(employee);


    }

    @Test
    public void test2(){
        Optional<Employee> empty = Optional.empty();
        Employee employee = empty.get();
        System.out.println(employee);
    }

    @Test
    public void test3(){
        Optional<Employee> optional = Optional.ofNullable(new Employee());
        System.out.println(optional.get());
    }
}
