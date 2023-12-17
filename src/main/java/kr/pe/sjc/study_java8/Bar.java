package kr.pe.sjc.study_java8;

public interface Bar extends Foo{
    default void printNameUpperCase() {
        System.out.println("BAR");
    }
}
