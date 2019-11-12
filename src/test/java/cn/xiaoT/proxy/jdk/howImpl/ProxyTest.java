package cn.xiaoT.proxy.jdk.howImpl;

import cn.xiaoT.proxy.jdk.RunInterface;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class ProxyTest {

    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        RunInterface runInterface = (RunInterface) Proxy.newProxyInstance(RunInterface.class);
        runInterface.run();
        System.out.println(runInterface.getClass().getSimpleName());
    }

}
