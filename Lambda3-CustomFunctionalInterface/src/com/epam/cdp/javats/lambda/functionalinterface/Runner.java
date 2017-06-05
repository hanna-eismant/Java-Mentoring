package com.epam.cdp.javats.lambda.functionalinterface;

public class Runner {

    public static void main(String[] args) {
        Long a = 3L;
        Long b = 4L;
        Long c = 5L;

        final ThreeFunction<Long, Long, Long, Long> sumImpl = (a1, b1, c1) -> a1 + b1 + c1;
        final ThreeFunction<String,String,String,String> strImpl = (a1, b1, c1) -> String.format("%s, %s, %s", a1, b1, c1);

        Long sum = sumImpl.calculate(a, b, c);
        String str = strImpl.calculate("a", "b", "c");

        System.out.println(String.format("Numbers: %s, %s, %s", a, b, c));
        System.out.println(String.format("Sum: %s", sum));
        System.out.println(String.format("Str: %s", str));
    }
}
