# Study_Java8
백기선님의 더 자바, java 8의 강의의 공부 기록입니다.

## 함수형 인터페이스와 람다 표현식 소개

### 함수형 인터페이스(Single Abstract Method)
>1. 1개의 추상메소드를 가지고있는 인터페이스.
>2. @FunctionalInterface 어노테이션 사용가능

### 람다 표현식(Lambda Expressions)
>1. 함수령 인터페이스의 인스턴스 생성 방식으로 사용할수 있다.
>2. 코드를 간결하게 만들수 있다.
>3. 메소드를 매개변수, 리턴 타입, 변수로 만들어 사용할 수도 있다.

### 자바에서 함수 프로그래밍 특징
>1. 함수를 일급 객체(First Class Object)로 사용할수 있다.
>2. 순수 함수
>   - 사이드 이팩트를 만들 수 없다. (함수 밖에 값을 변경할수 없다.)
>   - 함수 밖에 정의되어 있는 상태가 없다.
>3. 고차함수
>   - 함수가 함수를 매개변수로 받을 수 있고 함수를 리턴할 수도 있다.
>4. 불변성

## 자바에서 제공하는 함수형 인터페이스

### Function<T, R>
>1. T타입을 입력받아 R타입으로 리턴하는 함수 인터페이스
>   - R apply(T t)
>2. 함수 조합용 메소드
>   - andThen ex(func1.andThen(func2) = func1 first, func2 second) 
>   - compose ex(func1.compose(func2) = func1 second, func2 first)

### BiFunction<T, U, R>
>1. 두개의 값(T, U)를 받아서 R 타입을 리턴하는 함수 인터페이스
>   - R apply(T t)

### Consumer<T>
>1. T 타입을 받아서 아무값도 리턴하지 않는 함수 인터페이스
>   - void Accept(T t)
>2. 함수 조합용 메소드
>   - andThen

### Supplier<T>
>1. T 타입의 값을 제공하는 함수 인터페이스
>   - T get()

### Predicate<T>
>1. T 타입을 받아 Boolean을 리턴하는 함수 인터페이스
>   - boolean test(T t)
>2. 함수 조합용 메소드
>   - And
>   - Or
>   - Negate

### UnaryOperator<T>
>1. Function<T, R>의 특수한 형태, 입력값 하나를 받아서 동일한 타입으로 리턴하는 함수 인터페이스
>   - ex(Function<Integer, Integer> -> UnaryOperator<Integer>)

### BinaryOperator<T>
>1. BiFunction(T, U, R)의 특수 형, 동일한 타입의 입력값 두개를 받아 리턴하는 함수 인터페이스
>   - ex(BiFunction<Integer, Integer> -> BinaryOperator<Integer>)

## 람다 표현식

### 람다
>- (인자 리스트) -> (바디)

### 인자 리스트
>- 인자가 없을때 ()
>  - ```java
>    () -> System.out.println("Hello World");
>- 인자가 한개일 때 (one) 또는 one
>  - ```java
>    (one) -> System.out.println("Hello World"+ one);
>- 인자가 여러개 일 때 (one, two)
>  - ```java
>    (one, two) -> System.out.println("Hello World"+ one + two);
>- 인자의 타입은 생략 가능, 컴파일러가 추론(infer)하지만 명시할 수도 있다. (Integer one, Integer two)
>  - ```java
>    (Integer one, Integer two) -> System.out.println("Hello World"+ one + two);

## 바디
>- 화살표 오른쪽에 함수 본문을 정의한다
>- ```java
>  () -> System.out.println("Hello World");
>- 여러 줄인 경우에 {}를 사용해서 묶는다.
>- ```java
>  () -> {
>     System.out.println("Hello World1");
>     System.out.println("Hello World2");
>  }

### 변수 캡쳐 (Variable Capture)
>- 로컬 변수 캡쳐
>  - final 이거나 effective final(사실상 final) 인 경우에만 참조할 수 있다.
>  - 그렇지 않을 경우 concurrency(동시성) 문제가 생길 수 있어서 컴파일
>- effective final(사실상 final)
>  - 자바 8 부터 지원하는 기능으로 "사실상" final 인 변수(final 키워드가 없지만 값 변경이 일어나지 않는 변수)
>  - final 키워드를 사용하지 않은 변수를 익명 클래스 구현체 또는 람다에서 참조할 수 있다.
>    - ```java
>      public class Foo {
>            public static void main(String[] args) {
>            Foo foo = new Foo();
>            foo.run();
>        }
>
>      private void run() {
>            int baseNumber = 10;
>
>            // 로컬 클래스
>            class LocalClass {
>                void printBaseNumber() {
>                    System.out.println(baseNumber);
>                }
>            }
>
>            // 익명 클래스
>            Consumer<Integer> integerConsumer = new Consumer<Integer>() {
>                @Override
>                public void accept(Integer integer) {
>                    System.out.println(baseNumber);
>                }
>            };
>
>            // 람다
>            IntConsumer printInt = (number) -> {
>                System.out.println(number + baseNumber);
>            };
>        }
>      }
>- 익명 클래스 구현체와 달리 '쉐도윙'하지 않는다.
>  - 익명 클래스는 새로 스콥을 만들지만, 람다는 람다를 감싸고 있는 스콥과 같다
   >    - ```java
>         public class Foo {
>            public static void main(String[] args) {
>            Foo foo = new Foo();
>            foo.run();
>        }
>
>         private void run() {
>            int baseNumber = 10;
>
>            // 로컬 클래스
>            class LocalClass {
>                void printBaseNumber() {
>                    System.out.println(baseNumber);
>                }
>            }
>
>            // 익명 클래스
>            Consumer<Integer> integerConsumer = new Consumer<Integer>() {
>                // new scope
>                // int baseNumber = 12; = ok
>                @Override
>                public void accept(Integer integer) {
>                    System.out.println(baseNumber);
>                }
>            };
>
>            // 람다
>            IntConsumer printInt = (number) -> {
>                // int baseNumer = 10; = error 
>                // run() 과 동일 스코프
>                System.out.println(number + baseNumber);
>            };
>           }
>         }
## 메소드 레퍼런스

### 스태틱 메소드 참조
>- 타입::스태틱 메소드
>  - ex(UnaryOperator<String> hi = Greeting::hi;)

### 특정 객체의 인스턴스 메소드 참조
>- 객체 레퍼런스::인스턴스 메소드
>  - ex(greeting::hello;, greeting::getName;) 

### 임의 객체의 인스턴스 메소드 참조
>- 타입::인스턴스 메소드
>  - ex
>    - ```java
>      String[] names = {"keesun", "whiteship", "toby"};
>      Arrays.sort(names, String::compareToIgnoreCase);

### 생성자 참조
>- 타입::new
>  - ex(Supplier<Greeting> newGreeting = Greeting::new;)