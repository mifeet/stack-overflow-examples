package com.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String... args) throws Exception {
        // create Spring context that enables AOP under the hood
        ApplicationContext context = new AnnotationConfigApplicationContext(TestConfiguration.class);
        // we get reference to TestClass instance from context; calling on a plain new instance wouldn't work
        TestClass testObject = context.getBean(TestClass.class);
        // prints "bar" !
        testObject.foo();
    }
}
