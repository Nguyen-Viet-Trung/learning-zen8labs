package LambdaAndStreams;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ReduceInJava {
    public static void main(String[] args) {
        //được sử dụng để gộp các phần tử của một Stream thành một giá trị duy nhất bằng cách sử dụng một hàm tích lũy
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        //Sử dụng giá trị khởi tạo (0 trong ví dụ này) để bắt đầu tích lũy
        Integer sum = numbers.stream()
                         .reduce(0, (a, b) -> a + b);
        //Khi không chắc Stream có phần tử nào không.
        System.out.println("Tổng: " + sum);
        Optional<Integer> max = numbers.stream()
                                       .reduce((a, b) -> a > b ? a : b);
        System.out.println("Max: "+ max.get());

        List<String> words = Arrays.asList("Java", "Streams", "are", "powerful");

        String sentence = words.stream()
                               .reduce("", (a, b) -> a + " " + b);

        System.out.println("Câu: " + sentence.trim());

        // => Sử dụng để  tổng hợp dữ liệu thành một giá trị duy nhất, các phép tính đòi hỏi sự tích lũy(đệ quy?)
    }
}
