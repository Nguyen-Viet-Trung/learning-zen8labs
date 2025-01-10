package LambdaAndStreams;

import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Functions {
    //Predicate: kiểm tra một đối tượng và trả về boolean => kiểm tra tính hợp lệ
    public static void main(String[] args) {
        Predicate<Integer> p = n -> n % 2 == 0;
        System.out.println(p.test(10));
        System.out.println(p.test(3));

        //Consumer: Thực hiện hành động trên một đối tượng nhưng không trả về giá trị
        //=> Thường là xử lý dữ liệu như ghi vào file và in ra console
        Consumer<String> c = s -> System.out.println(s.toLowerCase());
        c.accept("HELLO");

        //được sử dụng để cung cấp một giá trị mà không cần đầu vào => Tạo/ sinh giá trị, đọc dữ liệu
        Supplier<String> s = () ->  "Supplier test";
        System.out.println(s.get());
    }


}
