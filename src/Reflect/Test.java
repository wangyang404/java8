package Reflect;

import com.sun.org.apache.xpath.internal.SourceTree;

import javax.sound.midi.Soundbank;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.time.LocalDateTime;

public class Test {
    @org.junit.Test
    public void test1() throws Exception{

        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Class<?> clazz = loader.loadClass("Reflect.Car");

        Constructor<?> cons = clazz.getDeclaredConstructor(null);
        Car car = (Car)cons.newInstance();

        Method setBrand = clazz.getMethod("setBrand", String.class);

        setBrand.invoke(car,"红旗");

        System.out.println(car);

    }

    @org.junit.Test
    public void test2(){
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        System.out.println(contextClassLoader);
        System.out.println(contextClassLoader.getParent());
        System.out.println(contextClassLoader.getParent().getParent());
    }
}
