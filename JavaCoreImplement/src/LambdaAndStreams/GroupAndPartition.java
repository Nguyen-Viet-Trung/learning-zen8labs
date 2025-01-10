package LambdaAndStreams;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GroupAndPartition {
    public static void main(String[] args) {
        List<String> fruits = List.of("Banana", "Watermelon","Strawberry","Banana");
        //không giới hạn số lượng nhóm => bài toán chia phức tạp dựa trên thuộc tính
        Map<String, Long> fruitsMap = fruits.stream().collect(Collectors.groupingBy(item -> item, Collectors.counting())); // phải map với Long vì phương thức counting trả về thuộc tính count
        
        System.out.println(fruitsMap);

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        //luôn chỉ tạo 2 nhóm true false => bài toán chia dữ liệu thành 2 nhóm true false
        Map<Boolean, List<Integer>> partitioned = numbers.stream()
            .collect(Collectors.partitioningBy(num -> num % 2 == 0));

        System.out.println(partitioned);
    }

    

}
