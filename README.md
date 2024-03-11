# Study_Java8
백기선님의 더 자바, java 8의 강의의 공부 기록입니다.

# 함수형 인터페이스와 람다

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
>            Foo fooClass = new Foo();
>            fooClass.run();
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
>            Foo fooClass = new Foo();
>            fooClass.run();
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

# 인터페이스의 변화

## 인터페이스 기본 메소드와 스태틱 메소드

### 기본 메소드
>- 인터페이스에 메소드 선언이 아니라 구현체를 제공하는 방법
>  - 이미 구현된 메소드를 제공
>- 해당 인터페이스를 구현한 클래스를 깨트리지 않고 새 기능을 추가할 수 있다.
>  - 모든 구현체에 구현하지 않아도 된다. 
>- 기본 메소드는 구현체가 모르게 추가된 기능으로 그만큼 리스크가 있다.
>  - 기본 메소드로 선언한 것이 구현체에 존재하는 경우 원치 않는 동작 변경이 있을수 있다.
>- Object가 제공하는 기능(equals, hasCode)는 기본 메소드로 제공할 수 없다.
>  - ex(default void equals() {} 에러는 없지만 사용이 불가능함)
>- 
>- 본인이 수정할 수 있는 인터페이스에만 기본 메소드를 제공할 수 있다.
>  - JpaRepository 같은 인터페이스는 수정 불가능
>- 인터페이스를 상속받는 인터페이스에서 다시 추상 메소드로 변경할 수 있다.
>- 인터페이스 구현체가 재정의 할 수도 있다.
>  - 오버라이딩이 가능하다

## 스태틱 메소드
>- 해당 타입 관련 헬퍼 또는 유틸리티 메소드를 제공할 때 인터페이스에 스태틱 메소드를 제공할 수 있다.
>  - 인스턴스를 생성하지 않고 메소드를 사용할수 있다.

## 자바 8 기본 메소드와 스태틱 메소드

### Iterable의 기본 메소드
>- forEach()
>  - ex(name.forEach(System.out::println))
>- spliterator()

### Collection의 기본 메소드
>- stream() / parallelStream() 
>  - 다음 강의에서 설명
>- removeIf(Predicate)
>  - ex(name.removeIf(s -> s.startWith("K"))
>- spliterator()

### Comparator의 기본 메소드 및 스태틱 메소드
>- reversed()
>  - 정렬 규칙 반대로 변경
>- thenComparing()
>  - 정렬 규칙 추가
>- static reverseOrder() / naturalOrder()
>  - 내림차순 정렬 / 오름차순 정렬
>- static nullsFirst() / nullsLast()
>  - null 데이터 맨 앞에 정렬 / null 데이터 맨 뒤에 정렬 (정렬 null 처리)
>- static comparing()
>  - 정렬 규칙을 람다현으로 구현 가능

# Stream

## Stream 소개

### Stream
>- 데이터를 담고 있는 저장소 (컬렉션)이 아니다.
>  - 데이터를 저장하지 않는다.
>- Functional in nature 스트림이 처리하는 데이터 소스를 변경하지 않는다.
>  - 원본을 수정하지 않는다.
>- 스트림으로 처리하는 데이터는 오직 한번만 처리한다.
>  - 스트림은 한번 건든 데이터는 더이상 건들지 않는다.
>- 무제한일 수도 있다. (Short Circuit 메소드를 사용해서 제한할 수 있다.)
>  - 스트림으로 무제한의 데이터를 처리할수 있지민 Short Circuit 메소드를 사용하여 리미트를 걸수 있다.
>- 중계 오퍼레이션은 근본적으로 LAZY 하다.
>  - 종료 오퍼레이션이 나오기 전까지 중계 오퍼레이션은 실행되지 않는다.
>- 손쉽게 병렬 처리할 수 있다.

### 스트림 파이프라인
>- 0 또는 다수의 중계 오퍼레이션과 한개의 종료 오퍼레이션으로 구성한다.
>  - 스트림 파이프라인에는 다수의 중계 오퍼레이션이 들어갈수 있으나 종료 오퍼레이션은 무조건 1개이다.
>- 스트림의 데이터 소스는 오직 터미널 오퍼레이션(종료 오퍼레이션)을 실행할 때에만 처리한다.
>  - 종료 오퍼레이션이 나오기 전까지 중계 오퍼레이션은 실행되지 않는다.

### 중계 오퍼레이션
>- Stream을 리턴한다.
>  - 여전히 스트림 파이프 라인이다. 
>- Stateless / Stateful 오퍼레이션으로 더 상세하게 구분할 수도 있다. (대부분은 Stateless지만 distinct 나 sorted 처럼 이전 이전 소스 데이터를 참조해야 하는 오퍼레이션은 Stateful 오퍼레이션이다.)
>  -  Stateless / Stateful 나뉘지만 대부분 Stateless고 distain나 sorted등 이전 데이터를 참조하는 오퍼레이션의 경우 Stateful할 오퍼레이션이다.

### 종료 오퍼레이션
>- Stream을 리턴하지 않는다.
>  - 스트림 파이프라인이 끝이 났다.

## 스트림 API

### 걸러내기
>- Filter(Predicate)
>- 예) 이름이 3글자 이상인 데이터만 새로운 스트림으로
>  - Filter(e -> e.getName().length >= 3)

### 변경하기
>- Map(Function) 또는 FlatMap(Function)
>- 예) 각각의 Post 인스턴스에서 String title만 새로운 스트림으로
>  - Map(Post::getTitle) & Map(post -> post.getTitle)
>- 예) List<Stream<String>>을 String의 스트림으로
>  - FlatMap(Collection::stream)

### 생성하기
>- generate(Supplier) 또는 iterate(T seed, UnaryOperator)
>- 예) 10부터 1씩 증가하는 무제한 숫자 스트림
>  - ```java
>    Stream.iterate(10, i -> i + 1)
>            .skip(10)
>            .limit(10)
>            .forEach(System.out::println);
>- 예) 랜덤 int 무제한 스트림

### 제한하기
>- limit(long) 또는 skip(long)
>- 예) 최대 5개의 요소가 담긴 스트림을 리턴한다.
>  - limit(5)
>- 예) 앞에서 3개를 뺀 나머지 스트림을 리턴한다.
>  - skip(3)