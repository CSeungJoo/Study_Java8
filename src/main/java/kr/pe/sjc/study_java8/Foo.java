package kr.pe.sjc.study_java8;

public interface Foo {

    void printName();

    String getName();

    /**
     * @ImplSpec 이 구현체는 getName()으로 가져온 문자열을 대문자로 바꿔 출력한다.
     */
    default void printNameUpperCase() {
        System.out.println(getName().toUpperCase());
    }

    static void printAnything() {
        System.out.println("Foo");
    }


}
