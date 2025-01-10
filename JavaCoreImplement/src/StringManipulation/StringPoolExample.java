package StringManipulation;

public class StringPoolExample {
    public static void main(String[] args) {
        //String Pool là một vùng bộ nhớ đặc biệt trong Heap Memory của Java, nơi các đối tượng String được lưu trữ để tái sử dụng.
        String str1 = "Hello"; // Tạo chuỗi trong String Pool
        String str2 = "Hello"; // Trỏ tới chuỗi đã tồn tại trong String Pool
        String str3 = new String("Hello"); // Tạo đối tượng mới trong Heap Memory
        String str4 = str3.intern(); // Thêm chuỗi vào String Pool hoặc trả về tham chiếu

        System.out.println(str1 == str2); // true - cùng tham chiếu trong String Pool
        System.out.println(str1 == str3); // false - khác tham chiếu (Heap vs Pool)
        System.out.println(str1 == str4); // true - cùng tham chiếu trong String Pool
    }
}
