package com.example;

import com.example.annotation.ReplacingMethod;

public class TestClass {
    @ReplacingMethod("bar")
    public void foo() {
        System.out.println("foo");
    }

    public void bar() {
        System.out.println("bar");
    }
}
