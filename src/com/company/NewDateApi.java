package com.company;

import org.junit.Test;

import java.time.*;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.concurrent.TimeUnit;

public class NewDateApi {
    /*
    * LocalDate 日期  LocalTime 时间  LocalDateTime 时间日期
    *
    * */

    @Test
    public void test1(){
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);

        LocalDateTime plusHours = now.plusHours(2);
        System.out.println(plusHours);

        LocalDateTime minusHours = now.minusHours(2);
        System.out.println(minusHours);

        int year = now.getYear();
        System.out.println(year);

    }

    /*
    * Instant 时间戳：unix元年  1970年1月1日00：00：00到某个时间的毫秒值
    * */
    @Test
    public void test2(){
        //默认获取utc时区 世界协调时间
        Instant instant = Instant.now();
        System.out.println(instant);

        OffsetDateTime offsetDateTime = instant.atOffset(ZoneOffset.ofHours(8));
        System.out.println(offsetDateTime);

        long toEpochMilli = instant.toEpochMilli();
        System.out.println(toEpochMilli);//epoch:时代，纪元

    }

    /*
    * Duration 计算两个“时间”之前的间隔    持续，持续的时间，期间
    * Period 计算两个“日期”之前的间隔
    * */
    @Test
    public void test3() throws Exception {
        Instant instant1 = Instant.now();
        TimeUnit.SECONDS.sleep(1);
        Instant instant2 = Instant.now();
        Duration between = Duration.between(instant1, instant2);
        System.out.println(between.toMillis());

        LocalDateTime localDateTime = LocalDateTime.now();
        TimeUnit.SECONDS.sleep(1);
        LocalDateTime localDateTime2 = LocalDateTime.now();
        Duration duration = Duration.between(localDateTime, localDateTime2);
        System.out.println(duration.toMillis());

    }

    @Test
    public void test4(){
        LocalDate time = LocalDate.of(2015,12,12);
        LocalDate now = LocalDate.now();
        Period between = Period.between(time,now);
        System.out.println(between.getDays());
        System.out.println(between.getMonths());
        System.out.println(between.getYears());
    }

    /*
    * TemporalAjust ：时间校正器
    * */
    @Test
    public void test5(){
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);

        LocalDateTime localDateTime = now.withDayOfMonth(10);//取本月第十天
        System.out.println(localDateTime);


        //下一个周六
        LocalDateTime nextSunday = now.with(TemporalAdjusters.next(DayOfWeek.SATURDAY));
        System.out.println("下个周五是：" + nextSunday);


        //自定义 获取下一个工作日
        LocalDateTime of = LocalDateTime.of(2018, 6, 1, 12, 12);
        LocalDateTime with = of.with((temporal) -> {
            LocalDateTime ldt = (LocalDateTime) temporal;
            DayOfWeek dayOfWeek = ldt.getDayOfWeek();
            if (dayOfWeek.equals(DayOfWeek.FRIDAY)) {
                return ldt.plusDays(3);
            } else if (dayOfWeek.equals(DayOfWeek.SATURDAY)) {
                return ldt.plusDays(2);
            } else {
                return ldt.plusDays(1);
            }
        });
        System.out.println("下一个工作日是" + with);
    }

}
