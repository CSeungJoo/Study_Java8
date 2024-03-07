package kr.pe.sjc.study_java8;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {

    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("Keesun");
        names.add("Whiteship");
        names.add("Toby");
        names.add("Foo");
        names.add("CSeungJoo");

        List<String> collect = names.stream()
                .map(s -> {
                    System.out.println(s +" "+ Thread.currentThread().getName());
                    return s.toUpperCase();
                }).collect(Collectors.toList());
        names.forEach(System.out::println);
    }
}
