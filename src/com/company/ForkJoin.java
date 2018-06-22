package com.company;

import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.OptionalLong;
import java.util.stream.LongStream;

public class ForkJoin {
    @Test
    public void test1(){
        Instant start = Instant.now();
        OptionalLong reduce = LongStream.range(0, 100000000L)
                .parallel()
                .reduce(Long::sum);

        Instant end = Instant.now();
        System.out.println(Duration.between(start,end).toMillis());

    }


}
