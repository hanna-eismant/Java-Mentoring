package com.epam.cdp.javats.lambda.functionalinterface;

@FunctionalInterface
public interface ThreeFunction<A, B, C, R> {

    R calculate(A a, B b, C c);
}
