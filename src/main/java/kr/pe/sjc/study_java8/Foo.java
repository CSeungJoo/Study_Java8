package kr.pe.sjc.study_java8;

import java.util.function.Function;
import java.util.function.UnaryOperator;

public class Foo {
    public static void main(String[] args) {
        UnaryOperator<Integer> plus10 = (number) -> number + 10;
        UnaryOperator<Integer> multiply2 = (number) -> number * 2;

        Function<Integer, Integer> plus10AndThenMultiply2 = plus10.andThen(multiply2);
        System.out.println("plus10AndThenMultiply2.apply(2) = " + plus10AndThenMultiply2.apply(2));
    }
}
