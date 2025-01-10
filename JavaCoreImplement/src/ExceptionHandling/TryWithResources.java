package ExceptionHandling;

import java.io.FileReader;

public class TryWithResources {
    //try-with-resources có cấu trúc là try(resources){}catch(Exception e){}
    public static void main(String[] args) {
        try(FileReader fr = new FileReader("test.txt")){
            Integer line;
            while((line = fr.read()) != -1){
                System.out.println(line);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    } //=> cải thiện việc quản lý tài nguyên bằng cách tự động đóng, đảm bảo hiệu quả và an toàn khi xử lý các tài nguyên hệ thống như file, socket, db
}
