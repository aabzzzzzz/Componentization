package com.example.annotationdemo;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;

@MyAnnotation
class MyClass extends Object {
    // 1. @Override 重写父类声明.
    // 2. @Deprecated 不推荐使用某方法
    // 3. @SuppressWarnings() 镇压警告.
    // 4. @Retention(RetentionPolicy.RUNTIME)
    // 5. @Target(value = {ElementType.METHOD,ElementType.TYPE})
    // 6. @Documented// 说明该注解将被包含在javaDoc中.
    // 7. @Inherited// 说明子类可以继承父类的注解

    // https://blog.csdn.net/xcy1193068639/article/details/81464165
    // java注解-ElementType详解

    @Override
    public String toString() {
        test();
        return super.toString();
    }

    @Deprecated // (不推荐使用, 但是可以使用, 但是存在更好的方式.)
    private void test() {
        test();//会有斜杠.
    }

    @SuppressWarnings("all")
    @MyAnnotation(name = "你好")
    public void test02(){
        List arrayList = new ArrayList();

    }

}

@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD,ElementType.TYPE})
@Documented// 说明该注解将被包含在javaDoc中.
@Inherited// 说明子类可以继承父类的注解
@interface MyAnnotation{
    // 注解的参数: 参数类型 + 参数名();
    String value() default "默认值可以不用写...";
    String name() default "空空空";
    int age() default -1;
    String[] schools() default {"西工大", "清华"};
}