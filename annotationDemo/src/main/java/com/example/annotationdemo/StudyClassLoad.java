package com.example.annotationdemo;

// https://www.bilibili.com/video/BV1vJ41177cw?p=2&spm_id_from=pageDriver
// 系统学习让你轻松定义java类加载器

// https://blog.csdn.net/worldeert/article/details/103389923
// newInstance 方法不推荐使用

// https://blog.csdn.net/u011080472/article/details/51332866
// 【深入理解JVM】：类加载器与双亲委派模型

// https://www.yuque.com/gaohanghang/sgrbwh/hpqkvg#lS4SI
// 系统学习让你轻松定义 Java 类加载器

// https://blog.csdn.net/qq_41701956/article/details/81664921
// Java虚拟机（JVM）你只要看这一篇就够了！



import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;

public class StudyClassLoad {

    // 类加载器 -> 从磁盘读取到内存的过程.


    // 加载 -> 链接 (验证 -> 准备 -> 解析) -> 初始化

    /**
     * 加载 -> 通过类的完全限定名（包名和类名）查找此类的字节码文件，
     * 把类的.class文件中的二进制数据读入到内存中，
     * 并存放在运行时数据区的方法区内，
     * 然后利用字节码文件创建一个Class对象，
     * 用来封装类在方法区内的数据结构并存放在堆区内。
     * 这个过程是由类加载器完成的，我们后面会进行详细讲解。
     *
     * 链接 ->
     * 验证 -> 确保被加载类的正确性。class 文件的字节流中包含的信息符合当前虚拟机要求，不会危害虚拟机自身的安全。
     * 准备 -> 为类的静态变量分配内存，并将其设置为默认值。
     * 此阶段仅仅只为静态类变量（即 static 修饰的字段变量）分配内存，
     * 并且设置该变量的初始值。（比如 static int num = 5，
     * 这里只将 num 初始化为0，5的值将会在初始化时赋值）。
     * 对于 final static 修饰的变量，编译的时候就会分配了，也不会分配实例变量的内存。
     * 解析 -> 把类中的符号引用转换为直接引用。
     * 符号引用就是一组符号来描述目标，而直接引用就是直接指向目标的指针。
     * 相对偏移量或一个间接定位到目标的句柄。（可参考"虚拟机指令"相关内容）
     *
     * 初始化 ->
     * 类加载最后阶段，若该类具有父类，则先对父类进行初始化，
     * 执行静态变量赋值和静态代码块代码，成员变量也将被初始化。
     */

    // 堆区存储class 对象. 方法区 存储字节码二进制数据.


    /**
     *
     * @param args
     * @throws MalformedURLException
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     */
    public static void main(String[] args) throws MalformedURLException, ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        // 虚拟机内置的3个类加载器 -:

        // 1. 虚拟机内置加载器 : 根类加载器.
        ClassLoader classLoader = Object.class.getClassLoader();
        System.out.println(classLoader);// null
        // 2. 扩展类加载器 : 负责加载 ext包下. 类下的文件,
        // 打印结果: ExtClassCloader
        // 3. 系统类加载器. (应用类加载器.)  AppClassLoader
        ClassLoader classLoader1 = StudyClassLoad.class.getClassLoader();
        System.out.println(classLoader1);//
        System.out.println(classLoader1.getParent());//父类加载器 PlatformClassLoader

        // 类加载器的双亲委派机制.
        /**
         * 1. 避免重复加载
         * 2. 保证安全, 不会有两个 Object.
         * 3. 不用需包名, java.lang
         */

        // mac Ctrl+H 可以查看当前类的 继承类.很有用.

//        File file = null;
//        URI uri = file.toURI();
//        URL url = uri.toURL();
//        URLClassLoader urlClassLoader = new URLClassLoader(new URL[]{url});
//
//        Class<?> aClass = urlClassLoader.loadClass("你的类的路径 : 包名+类名.");
////        aClass.newInstance();//初始化. 不推荐使用了. jdk 9之后.
//        aClass.getDeclaredConstructor().newInstance();//初始化. 使用这个方法.
//

        //
        // 自定义类加载器. -> 看下面的那个类...


        // 热部署类加载器.
        /**
         * 当我们调用 loadClass 方法加载类时，会采用双亲委派模式，
         * 即如果类已经被加载，就从缓存中获取，不会重新加载。
         * 如果同一个 class 被同一个类加载器多次加载，则会报错。
         * 因此，我们要实现热部署让同一个class文件被不同的类加载器重复加载即可。
         * 但是不能调用 loadClass 方法，而应该调用 findClass 方法，
         * 避开双亲委托模式，从而实现同一个类被多次加载，实现热部署。
         */


        // 类的显式加载 于 隐式加载.
        /**
         * 刚才用的就是显示加载.
         * 隐式加载指不需要在 java 代码中明确调用加载的代码，
         * 而是通过虚拟机自动加载到内存中。比如在加载某个class 时，
         * 该class引用了另外一个类的对象，
         * 那么这个对象的字节码文件就会被虚拟机自动加载到内存中
         */

        // getContextClassLoader 线程上下文类加载器


    }



}

// 1. 继承ClassLoader
// 2. 覆盖 findClass方法
class MyFileClassLoader extends ClassLoader {

    private String directory; // 被加载类所在的目录

    public MyFileClassLoader(String directory) { // 默认父类加载器就是系统类加载器 AppClassLoader
        this.directory = directory;
    }

    public MyFileClassLoader(ClassLoader parent, String directory) {
        super(parent);
        this.directory = directory;
    }

    // com.itheima.Demo
    @Override
    protected Class<?> findClass(String name) {
        try {
            // 把类名转换为目录
            String file = directory + File.separator + name.replace(".", File.separator) + ".class"; // D:/com/itheima/Demo.class
            // 构建输入流
            InputStream in = new FileInputStream(file);
            // 构建字节输出流
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int len = -1;
            while ((len = in.read(buf)) != -1) {
                baos.write(buf, 0, len);
            }
            byte[] data = baos.toByteArray(); // 读取到的字节码的二进制数据
            in.close();
            baos.close();
            return defineClass(name, data, 0, data.length);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws Exception {
        MyFileClassLoader classLoader = new MyFileClassLoader("d:/");
        Class<?> clazz = classLoader.loadClass("com.itheima.Demo");
        clazz.newInstance();
    }

}
