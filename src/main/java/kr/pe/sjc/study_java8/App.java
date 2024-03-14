package kr.pe.sjc.study_java8;


import java.time.Duration;
import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {

    public static void main(String[] args) {
        List<OnlineClass> springClasses = new ArrayList<>();
        springClasses.add(new OnlineClass(1, "spring_1", true));
        springClasses.add(new OnlineClass(5, "api", false));

        Optional<OnlineClass> optional = springClasses.stream()
                .filter(e -> e.getTitle().startsWith("spring"))
                .findFirst();
    }

    private static OnlineClass createNewClasses() {
        System.out.println("create new class");
        return new OnlineClass(10, "new_class", false);
    }
}
