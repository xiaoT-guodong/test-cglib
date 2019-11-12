package cn.xiaoT.proxy.jdk.howImpl;

import org.apache.commons.io.FileUtils;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Proxy {

    public static Object newProxyInstance(Class clazz) throws IOException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Method[] methods = clazz.getMethods();
        String methodContent = "";
        for (Method method : methods) {
            methodContent += "    @Override\n" +
                    "    public void " + method.getName() + "() {\n" +
                    "        System.out.println(\"计时开始！\");\n" +
                    "        long start = System.currentTimeMillis();\n" +
                    "        run." + method.getName() + "();\n" +
                    "        long end = System.currentTimeMillis();\n" +
                    "        System.out.println(\"计时结束！用时：\" + (end - start) + \"毫秒\");\n" +
                    "    }\n";
        }
        String content = "package cn.xiaoT.proxy.jdk;\n" +
                "\n" +
                "public class $Proxy0 implements " + clazz.getSimpleName() + " {\n" +
                "\n" +
                "    private " + clazz.getSimpleName() + " run;\n" +
                "\n" +
                "    public $Proxy0(" + clazz.getSimpleName() + " run) {\n" +
                "        this.run = run;\n" +
                "    }\n" +
                "\n" +
                methodContent +
                "}";
        File file = new File(System.getProperty("user.dir") + "/target/classes/cn/xiaoT/proxy/jdk/howImpl/$Proxy0.java");
        FileUtils.writeStringToFile(file, content, "UTF-8");

        //获取系统java编译器
        JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
        //文件管理者
        StandardJavaFileManager standardFileManager = javaCompiler.getStandardFileManager(null, null, null);
        //获取文件
        Iterable<? extends JavaFileObject> javaFileObjects = standardFileManager.getJavaFileObjects(file);
        //编译任务
        JavaCompiler.CompilationTask task = javaCompiler.getTask(null, standardFileManager, null, null, null, javaFileObjects);
        //编译
        task.call();

        //加载到内存
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        Class<?> loadClass = systemClassLoader.loadClass("cn.xiaoT.proxy.jdk.howImpl.$Proxy0");

        Constructor<?> constructor = loadClass.getConstructor(clazz);
        return constructor.newInstance();
    }

}
