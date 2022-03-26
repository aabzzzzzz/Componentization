package com.example.annotationdemo;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 反射, 很重要.
 */
public class Reflection {

    // https://www.bilibili.com/video/BV1p4411P7V3?p=15
    // 【狂神说Java】注解和反射

    // https://www.zhihu.com/question/316509027
    // 动态语言和静态语言的本质区别是什么？

    // https://blog.csdn.net/weixin_45806131/article/details/107904164
    // java反射之所有类型的class对象

    // https://juejin.cn/post/6844903663496871943
    // 可能是把Java内存区域讲的最清楚的一篇文章

    // https://blog.csdn.net/yiyongjiajun521/article/details/80609072
    // Java之通过反射来操作泛型


    public static void main(String[] args) throws Exception {

        Class<?> c1 = Class.forName("com.example.annotationdemo.User");
        Class<?> c2 = Class.forName("com.example.annotationdemo.User");
        Class<?> c3 = Class.forName("com.example.annotationdemo.User");

        //3种方式.

        Class<?> c4 =  User.class;

        User user = new User("小V", 2);
        Class<? extends User> c5 = user.getClass();
        Class<?> superclass = c5.getSuperclass();

        // 一个 类被加载到内存中后, 只有一份类实例的在内存中.
        System.out.println(c1.hashCode());
        System.out.println(c2.hashCode());
        System.out.println(c3.hashCode());
        System.out.println(c4.hashCode());
        System.out.println(c5.hashCode());
        System.out.println(superclass.getName());


        Field[] declaredFields = c3.getDeclaredFields();
        Method[] methods = c3.getMethods();

        for (int i = 0; i < declaredFields.length; i++) {
            System.out.println(declaredFields[i]);
        }

        for (int i = 0; i < methods.length; i++) {
            System.out.println(methods[i]);
        }

        Constructor<?>[] declaredConstructors = c3.getDeclaredConstructors();
        for (int i = 0; i < declaredConstructors.length; i++) {
            System.out.println(declaredConstructors[i]);
        }

        User user3 = (User) c3.getDeclaredConstructor().newInstance();

        Method setName = c3.getDeclaredMethod("setName", String.class);

        setName.invoke(user3, "你好呀");

        Field age = c3.getDeclaredField("age");

        age.setAccessible(true);//关掉权限检查.
        age.set(user3, 10);

        System.out.println(user3);

    }

}

class User {
    String name;
    int age;

    public User() {
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

