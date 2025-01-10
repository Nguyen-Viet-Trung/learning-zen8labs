package ExceptionHandling;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class CheckedvsUncheckedException {
  public static void main(String[] args) {
    //checked exception:giúp coder dự đoán và xử lý lỗi hợp lý
    //đảm bảo khai báo cho các lỗi tài nguyên hệ thống (file, csdl, mạng)
    //cần phải có try catch
    try {
        FileReader file = new FileReader("test.txt");
        int ch;
            while ((ch = file.read()) != -1) {
                System.out.print((char) ch);
            }
    } catch (FileNotFoundException | NullPointerException e) { //xử lý nhiều ngoại tệ
        System.out.println(e.getMessage());
    }
    int[] numbers = {1, 2, 3};
    System.out.println(numbers[3]); 
  } 
  //unchecked exception
  //lỗi logic của chương trình, lỗi mà dev chwua dự đoán được
  
}
