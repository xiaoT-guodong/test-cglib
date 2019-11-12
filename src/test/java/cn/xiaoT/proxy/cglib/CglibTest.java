package cn.xiaoT.proxy.cglib;

public class CglibTest {

    public static void main(String[] args) {
        TimeProxy timeProxy = new TimeProxy();
        Car carTimeProxy = (Car) timeProxy.getProxy(Car.class);
        carTimeProxy.run();

        System.out.println();

        LogProxy logProxy = new LogProxy();
        Car carLogProxy = (Car) logProxy.getProxy(Car.class);
        carLogProxy.run();
    }

}
