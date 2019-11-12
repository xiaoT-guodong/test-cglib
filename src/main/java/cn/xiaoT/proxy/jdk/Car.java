package cn.xiaoT.proxy.jdk;

import java.util.Random;

public class Car implements RunInterface {

    @Override
    public void run() {
        System.out.println("car is run ...");
        try {
            Thread.sleep(new Random().nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
