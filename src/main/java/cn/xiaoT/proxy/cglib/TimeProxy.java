package cn.xiaoT.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class TimeProxy implements MethodInterceptor {

    private Enhancer enhancer = new Enhancer();

    public Object getProxy(Class clazz) {
        enhancer.setCallback(this);
        enhancer.setSuperclass(clazz);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("计时开始！");
        long startTime = System.currentTimeMillis();
        methodProxy.invokeSuper(o, objects);
        long endTime = System.currentTimeMillis();
        System.out.println("计时结束！用时：" + (endTime - startTime) + "毫秒");
        return null;
    }

}
