package IO;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.Reader;

public class InputStreamvsReader {
    public static void main(String[] args) {
        try (InputStream is = new FileInputStream("src\\IO\\example.txt")){ // đọc dữ liệu dưới dạng byte (8-bit) => phù hợp cho ảnh, video,...
            int data;
            while((data = is.read()) != -1){
                System.out.print((char)data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try (Reader reader = new FileReader("src\\IO\\example.txt")) {
            int data;
            while ((data = reader.read()) != -1) {
                System.out.print((char)data); // Đọc dữ liệu dưới dạng ký tự => phù hợp cho văn bản/dữ liệu có mã hóa
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
