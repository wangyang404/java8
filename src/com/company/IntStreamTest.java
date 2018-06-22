package com.company;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class IntStreamTest {

    public static void main(String[] args) {
        Map<Boolean, List<Integer>> collect = IntStream.rangeClosed(1, 100)
                .boxed()
                .collect(Collectors.groupingBy((t) -> isPrime(t)));

        System.out.println(collect);

    }

    public static boolean isPrime(Integer num){
            int value = (int)Math.sqrt(num);
            return IntStream.rangeClosed(2,value)
                    .noneMatch(t -> num%t == 0);
    }
}
