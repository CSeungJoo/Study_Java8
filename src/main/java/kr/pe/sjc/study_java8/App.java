package kr.pe.sjc.study_java8;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class App {

    public static void main(String[] args) {
        List<String> name = new ArrayList<>();
        name.add("Keesun");
        name.add("Whiteship");
        name.add("Toby");
        name.add("Foo");
        name.add("CSeungJoo");

        name.forEach(System.out::println);
    }
}
