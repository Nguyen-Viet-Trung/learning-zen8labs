package IO;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class BigFileSolver {
    public static void main(String[] args) {
        // Đọc tệp lớn mà không giữ toàn bộ nội dung trong bộ nhớ
        try (FileInputStream inputStream = new FileInputStream("src\\IO\\example.txt");
             Scanner sc = new Scanner(inputStream, "UTF-8")) {
                //scanner không tải toàn bộ dữ liệu
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                // Xử lý từng dòng (ở đây chỉ in ra)
                System.out.println(line);
            }

            // Kiểm tra và ném ngoại lệ nếu xảy ra lỗi trong quá trình đọc
            if (sc.ioException() != null) {
                throw sc.ioException();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //cách này nhanh hơn scanner vì chỉ tập trung vào truy xuất dữ liệu chứ k tập trung parsing(phân tích cú pháp?) 
        try(BufferedReader br = new BufferedReader(new FileReader("src\\IO\\example.txt"))){
            String line = br.readLine();
            while (line != null) {
                System.out.println(line);
                line = br.readLine(); //tránh vòng lặp vô tận
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}

