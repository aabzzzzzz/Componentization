package com.example.annotationdemo;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

/**
 * 这个很重要, 很多很多开源项目都是通过 这种方式来实现代码的. 太难了, 太难了....
 */
public class ReflectionAndAnnotation {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException {


        Class<?> aClass = Class.forName("com.example.annotationdemo.Student");

        Annotation[] annotations = aClass.getAnnotations();

        for (int i = 0; i < annotations.length; i++) {
            System.out.println("annotation -- " + annotations[i]);
        }

        TableABB annotation = aClass.getAnnotation(TableABB.class);
        String value = annotation.value();
        System.out.println(value);

        Field id = aClass.getDeclaredField("id");
        FieldABB annotation1 = id.getAnnotation(FieldABB.class);
        System.out.println(annotation1.columnName());
        System.out.println(annotation1.type());
        System.out.println(annotation1.length());


    }




}

@TableABB("db_student")
class Student {

    @FieldABB(columnName = "db_Id", type = "int", length = 10)
    private int id;
    @FieldABB(columnName = "db_age", type = "int", length = 10)
    private int age;
    @FieldABB(columnName = "db_name", type = "varchar", length = 3)
    private int name;

    public Student(int id, int age, int name) {
        this.id = id;
        this.age = age;
        this.name = name;
    }

    public Student() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }
}

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface TableABB{
    String value();//
}

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface FieldABB{
    String columnName();
    String type();
    int length();
}


