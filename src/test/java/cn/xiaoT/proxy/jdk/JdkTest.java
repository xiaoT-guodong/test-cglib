package cn.xiaoT.proxy.jdk;

import java.lang.reflect.Proxy;

public class JdkTest {

    public static void main(String[] args) {
        Car car = new Car();
        Class<? extends Car> carClass = car.getClass();
        RunInterface run = (RunInterface) Proxy.newProxyInstance(carClass.getClassLoader(), carClass.getInterfaces(), new TimeProxy(car));
        run.run();

        System.out.println();

        Class<? extends RunInterface> runClass = run.getClass();
        RunInterface log = (RunInterface) Proxy.newProxyInstance(runClass.getClassLoader(), runClass.getInterfaces(), new LogProxy(run));
        log.run();
    }

}
