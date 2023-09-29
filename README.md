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