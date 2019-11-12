package cn.xiaoT.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class LogProxy implements InvocationHandler {

    private Object object;

    public LogProxy(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("记录日志开始！");
        method.invoke(object, args);
        System.out.println("记录日志结束！");
        return null;
    }

}
