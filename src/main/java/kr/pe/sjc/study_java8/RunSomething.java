package kr.pe.sjc.study_java8;
//어노테이션 문법 위반시 에러
@FunctionalInterface
public interface RunSomething {
//   추상 메소드가 1개 == 함수형 인터페이스
//   추상 메소드가 2개 이상 != 함수형 인터페이스
    int doIt(int number);

}
