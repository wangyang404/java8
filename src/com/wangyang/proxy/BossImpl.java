package com.wangyang.proxy;

import com.sun.org.apache.xpath.internal.SourceTree;

import javax.sound.midi.Soundbank;

public class BossImpl implements  IBoss {


    @Override
    public void save() {
        System.out.println("save");
    }

    @Override
    public void update() {
        System.out.println("update");


    }
}
