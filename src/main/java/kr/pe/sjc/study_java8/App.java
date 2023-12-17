package kr.pe.sjc.study_java8;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Foo foo = new DefaultFoo("seungjoo");
        foo.printName();
        foo.printNameUpperCase();
        Foo.printAnything();
    }
}
