package IO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BufferedvsFileReader {
    //BufferedReader là lớp bao bọc (wrapper) cho Reader để cải thiện hiệu năng bằng cách sử dụng bộ đệm + phương thức readLine để đọc theo dòng
    //=> phù hợp cho tệp lớn
    //FileReader là lớp cơ bản để đọc ký tự từ 1 tệp, không có bộ đệm nên truy cập tệp cho mỗi lần đọc ký tự
    //=> sử dụng cho file nhỏ hoặc cần đọc từng ký tự
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("src\\IO\\example.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        try (FileReader fr = new FileReader("src\\IO\\example.txt")) {
            int ch;
            while ((ch = fr.read()) != -1) {
                System.out.print((char) ch);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}
