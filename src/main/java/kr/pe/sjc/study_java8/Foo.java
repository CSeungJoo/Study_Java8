package kr.pe.sjc.study_java8;

public class Foo {
    public static void main(String[] args) {
//      순수 함수가 되려면 함수 밖의 값을 건드려선 안된다
        int baseNumber = 10;

        RunSomething runSomething = number -> number + baseNumber;
    }
}
