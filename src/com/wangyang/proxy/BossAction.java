package com.wangyang.proxy;

import org.junit.Test;

public class BossAction {

    @Test
    public void test1(){
        IBoss boss = new BossImpl();
        boss.save();
        boss.update();
        System.out.println("---------------------------");

        IBoss proxy = ProxyBoss.getProxy(boss);
        proxy.save();
        proxy.update();


    }
}
