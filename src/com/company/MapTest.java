package com.company;

import com.sun.org.apache.xpath.internal.SourceTree;
import org.junit.Test;

import javax.sound.midi.Soundbank;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MapTest {

    List<Employee> emps = Arrays.asList(
            new Employee(102, "李四", 59, 6666.66, Status.BUSY),
            new Employee(101, "张三", 18, 9999.99, Status.FREE),
            new Employee(103, "王五", 28, 3333.33, Status.VOCATION),
            new Employee(104, "赵六", 8, 7777.77, Status.FREE),
            new Employee(104, "赵六", 8, 7777.77, Status.FREE),
            new Employee(104, "赵六", 8, 7777.77, Status.VOCATION),
            new Employee(105, "田七", 38, 5555.55, Status.BUSY)
    );

    @Test
    public void test101(){
        emps.stream()
                .sorted()
                .forEach(System.out::println);



    }

    @Test
    public void tess(){
        int x = 8;
        int[] arr = {1,3,5,7};
        for(int i = 0;i < arr.length -1 ;i++){
            for(int j = i+1; j<arr.length; j++ ){
                if(arr[i] + arr[j] ==x){
                    System.out.println(arr[i] +" and" + arr[j]);

                }
            }

        }



    }


    @Test
    public void test1(){
        //筛选到两条记录后就不会继续执行了
        //中间操作，不会执行任何操作，
        Stream<Employee> stream = emps.stream()
                                     .filter((e) -> {
                                        System.out.println("短路！"); // &&  ||
                                         return e.getSalary() >= 5000; })
                                    .limit(2);
        System.out.println("------------------------");
        //一次性执行全部内容 “惰性求值”
        stream.forEach(System.out::println);
    }

   /*
	 * 映射
	 * map-接受lambda，将元素转换成其他形式或者提取信息。接受一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素
	 * flatMap:接受一个函数作为参数，将流中的每一个值都换成另一个流，然后把所有流连接成一个流
	 *
	 */
   @Test
    public void test2(){
        List<String> list = Arrays.asList("aaa","bbb","ccc");
        list.stream()
                .map((str) -> str.toUpperCase())
                .forEach(System.out::println);
       System.out.println("------------");
       emps.stream()
               .distinct()
               .map((emp) -> emp.getName())
               .forEach(System.out::println);
       System.out.println("----------------------");
    /*   Stream<Stream<Character>> stream1 = list.stream()
               .map(MapTest::getChar);
       stream1.forEach((s) ->s.forEach(System.out::println) );*/
       Stream<Character> characterStream = list.stream()
               .flatMap(MapTest::getChar);
       characterStream.forEach(System.out::println);
   }

   @Test
   public  void testw(){
       List<Double> collect = emps.stream()
               .map((emp) -> emp.getSalary())
               .collect(Collectors.toList());

       System.out.println(collect);
   }

   public static Stream<Character> getChar(String str){
       char[] chars = str.toCharArray();
       List<Character> list = new ArrayList<>();
       for (char aChar : chars) {
            list.add(aChar);
       }
       return list.stream();
   }

   /*
        排序
        自然排序 sorted(Comparable)

        定制排序   sorted(Comparator)
    */
    @Test
    public void test3(){
        List<String> list = Arrays.asList("bbb","aaa","ccc");
        list.stream()
                .sorted()
                .forEach(System.out::println);

        System.out.println("--------------");
        List<Employee> collect = emps.stream()
                .sorted((emp1, emp2) -> {
                    if (emp1.getAge() == emp2.getAge()) {
                        return emp1.getName().compareTo(emp2.getName());
                    }
                    return Integer.compare(emp1.getAge(), emp2.getAge());
                })
                .collect(Collectors.toList());
        for (Employee employee : collect) {
            System.out.println(employee);

        }
    }

    /*
    *  终止操作
    *
    *  查找与匹配
    *  allMatch————检查是否匹配所有元素
    *  anyMatch————检查是否至少匹配一个元素
    *  noneMatch————检查是否没有匹配所有元素 有没有匹配的元素
    *  findFirst————返回第一个元素
    *  findAny————返回当前流中任意元素
    *  count————返回当前流中的总个数
    *
    * */
    @Test
    public void test4(){
        boolean b = emps.stream()
                .anyMatch((emp) -> emp.getStatus().equals(Status.BUSY));

        System.out.println(b);

        Optional<Employee> first = emps.stream()
                .sorted((emp1, emp2) -> Double.compare(emp1.getSalary(),emp2.getSalary()))
                .findFirst();



        System.out.println(first.get());

    }


    @Test
    public void  test5(){
        //获取最小的工资的值
        Optional<Double> min = emps.stream()
                                    .map(Employee::getSalary)
                                    .min(Double::compareTo);
        System.out.println(min.get());

    }

    /*
    * reduce(T identity,binaryOperator) /reduce(BinaryOpertor) ————可以讲流中元素反复结合起来，得到一个值(归约)
    *
    * */
    @Test
    public void test6(){
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        Integer sum = list.stream()
                .reduce(0, (x, y) -> x + y);
        System.out.println(sum);
        System.out.println("---------------------");
        Optional<Double> reduce = emps.stream()
                .map(Employee::getSalary)
                .reduce(Double::sum);

        Double sum1 = reduce.get();
        System.out.println(sum1);



    }

     /*
    * 收集
    * collect ————将流转换为其他形式，接受一个Collerctor的实现，用于给Stream中元素做汇总的方法
    *
    *
    *
    * */
     @Test
     public void test7(){
         Set<String> collect = emps.stream()
                 .map(Employee::getName)
                 .collect(Collectors.toSet());
         System.out.println(collect);

         System.out.println("--------------------");

         emps.stream()
                 .collect(Collectors.toCollection(LinkedHashSet::new));
     }

     @Test
    public void test8(){
         Optional<Employee> employee = emps.stream()
                 .collect(Collectors.minBy((emp1, emp2) -> Double.compare(emp1.getSalary(), emp2.getSalary())));
         System.out.println(employee);

         Optional<Double> collect = emps.stream()
                 .map(Employee::getSalary)
                 .collect(Collectors.minBy(Double::compare));
         System.out.println(collect);

     }

     //分组
     @Test
    public  void test9(){
         Map<Status, List<Employee>> collect = emps.stream()
                 .collect(Collectors.groupingBy(Employee::getStatus));

         Iterator<Map.Entry<Status, List<Employee>>> iterator = collect.entrySet().iterator();

         while (iterator.hasNext()){
             Map.Entry<Status, List<Employee>> entry = iterator.next();
             System.out.println(entry.getKey());
             System.out.println(entry.getValue());
         }
     }
     //多级分组
    @Test
    public void test10(){
        Map<Status, Map<String, List<Employee>>> collect = emps.stream()
                .collect(Collectors.groupingBy(Employee::getStatus, Collectors.groupingBy((emp) -> {
                    if (emp.getAge() < 35) {
                        return "青年";
                    } else if (emp.getAge() < 50) {
                        return "中年";
                    } else {
                        return "老年";
                    }
                })));

        System.out.println(collect);
    }


    @Test
    public void test11(){
        DoubleSummaryStatistics collect = emps.stream()
                .collect(Collectors.summarizingDouble(Employee::getSalary));

        double sum = collect.getSum();
        System.out.println(sum);

        String collect1 = emps.stream()
                .map(Employee::getName)
                .collect(Collectors.joining(","));
        System.out.println(collect1);

        //分区
        Map<Boolean, List<Employee>> collect2 = emps.stream()
                .collect(Collectors.partitioningBy((emp) -> emp.getAge() > 30));
        Iterator<Map.Entry<Boolean, List<Employee>>> iterator = collect2.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<Boolean, List<Employee>> next = iterator.next();

            System.out.println(next.getKey());
            System.out.println(next.getValue());
        }
    }
}
