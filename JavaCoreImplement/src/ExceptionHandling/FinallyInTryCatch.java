package ExceptionHandling;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FinallyInTryCatch {
    //finally được sử dụng để chứa các đoạn mã luôn được thực thi sau khi khối try hoặc catch kết thúc, bất kể ngoại lệ có xảy ra hay không.
    //finally dùng để log lỗi, ghi trạng thái dữ liệu trước khi thoát hoặc reset các biến sau khi dùng
    public static void main(String[] args) {
        System.out.println(testMethod());
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("test.txt"));
            System.out.println(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Đảm bảo tài nguyên luôn được đóng
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
    }
} //try with resources tự động đóng tài nguyên đơn giản, ít lỗi hơn
//nếu trong finally có return thì sẽ ghi đè lên giá trị đã được định nghĩa trên khối try catch
    }
    public static int testMethod() {
        try {
            return 1; // Giá trị này sẽ được trả về
        } finally {
            System.out.println("Dọn dẹp tài nguyên trong finally.");
        }
    }
}