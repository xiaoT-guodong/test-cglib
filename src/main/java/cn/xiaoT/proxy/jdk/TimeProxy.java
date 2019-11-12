package cn.xiaoT.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class TimeProxy implements InvocationHandler {

    private Object run;

    public TimeProxy(RunInterface run) {
        this.run = run;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("计时开始！");
        long startTime = System.currentTimeMillis();
        method.invoke(run, args);
        long endTime = System.currentTimeMillis();
        System.out.println("计时结束！用时：" + (endTime - startTime) + "毫秒");
        return null;
    }

}
