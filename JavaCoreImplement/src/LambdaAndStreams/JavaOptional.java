package LambdaAndStreams;

import java.util.Optional;

public class JavaOptional {
 public static void main(String[] args) {
    // Optional được sử dụng để biểu diễn một giá trị có thể có hoặc không (null hoặc không null)
    // => Giảm thiểu việc sử dụng null trực tiếp => Tránh null pointer exception
    Optional<String> optional = Optional.of("Hello, World!");
    Optional<String> optional1 = Optional.ofNullable(null);
    Optional<String> optional2 = Optional.empty();
    System.out.println(optional.isPresent()); // true
    System.out.println(optional.isEmpty());   // false

    System.out.println(optional.get());
    System.out.println(optional1.orElse("Is null"));

    optional.ifPresent(System.out::println);
    Optional<Integer> length = optional.map(String::length);
    System.out.println(length.get());
 }   
}
