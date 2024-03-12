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
        springClasses.add(new OnlineClass(2, "spring_2", true));
        springClasses.add(new OnlineClass(3, "spring_3", false));
        springClasses.add(new OnlineClass(4, "spring_4", false));
        springClasses.add(new OnlineClass(5, "api", false));

        OnlineClass springBoot = new OnlineClass(1, "spring_boot", true);
        Optional<Progress> progress = springBoot.getProgress();
        progress.ifPresent(p -> System.out.println(p.getStudyDuration()));
    }
}
