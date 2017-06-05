package com.epam.cdp.javats.lambda.functionalinterface;

public class Runner {

    public static void main(String[] args) {
        final int a = 3;
        final int b = 4;
        final int c = 5;

        final ThreeFunction sumImpl = (a1, b1, c1) -> a1 + b1 + c1;
        final ThreeFunction multImpl = (a1, b1, c1) -> a1 * b1 * c1;

        long sum = sumImpl.calculate(a, b, c);
        long mult = multImpl.calculate(a, b, c);

        System.out.println(String.format("Numbers: %s, %s, %s", a, b, c));
        System.out.println(String.format("Sum: %s", sum));
        System.out.println(String.format("Multiple: %s", mult));
    }
}
