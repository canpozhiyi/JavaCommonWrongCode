package com.csd.wrongCode.controller;

/**
 * @author csd
 */
public class ExtendTest {


    public static void main(String[] args) {
        MySubclass mySubclass = new MySubclass();
        mySubclass.printValue(42); // 会调用参数类型是泛型类型上界的方法
        mySubclass.printValue("Hello"); // 会调用参数类型是实际的泛型类型的方法
    }

    interface MyInterface<T> {
        void printValue(T value);
    }

    static class MyBaseclass implements MyInterface<Object> {
        @Override
        public void printValue(Object value) {
            System.out.println("Baseclass: " + value);
        }
    }

    static class MySubclass extends MyBaseclass {
        // 这里没有桥接方法
        @Override
        public void printValue(Object value) {
            System.out.println("Subclass: " + value);
        }

        // 新增一个方法，参数类型是实际的泛型类型
        public void printValue(String value) {
            System.out.println("Subclass String: " + value);
        }
    }

}
