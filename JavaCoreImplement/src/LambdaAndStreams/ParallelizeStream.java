package LambdaAndStreams;

import java.util.List;

public class ParallelizeStream {
    //chuyển một Stream tuần tự (sequential stream) thành Parallel Stream bằng cách sử dụng phương thức .parallel()
    public static void main(String[] args) {
        // cách 1
        System.out.println("First way: \n");
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // Stream tuần tự => ĐƠn luồng, hiệu suất thấp, đơn giản, dễ dự đoán
        numbers.stream()
               .forEach(System.out::println);

        System.out.println("--- Parallel Stream ---");

        // Stream song song => Đa luồng, thích hợp với tập dữ liệu lớn nhưng dễ xảy ra lỗi bất đồng bộ và kết quả không mong muốn
        numbers.stream()
               .parallel()
               .forEach(System.out::println);
        System.out.println("Second way: \n");
        //cách 2
        numbers.parallelStream()
       .forEach(System.out::println);
            }
            //sử dụng parallel stream để làm gì?
            // hiệu suất cao hơn trên các hệ thống đa lõi bằng cách chia nhỏ công việc thành các tác vụ nhỏ hơn và phân bố trên nhiều lõi CPU
            //tự động hóa quản lý luồng
            //Nhược điểm: Không đảm bảo thứ tự xử lý, phụ thuộc vào tài nguyên hệ thống,không an toàn trong môi trường đồng thời, overhead trong quản lý luồng
}
