package StringManipulation;

public class StringBuilderBuffer{
    //string thì immutable (bất biến) còn StringBuilder và StringBuffer là mutable(có thể thay đổi)
    //StringBuilder có hiệu suất nhanh nhất nhưng lại không thread-safety
    //StringBuffer có đồng bộ hóa => thread-safety nhưng không nhanh bằng
    //string kém hiệu quả nhất trong 3 khi thực hiện thay đổi do tạo nhiều đối tượng mới
    public static void main(String[] args){
        StringBuilder stringBuilder = new StringBuilder("Trung"); // nên đặt kích thước ban đầu cho string builder và buffer để tránh mở rộng không cần thiết => tốn chi phí cấp phát và sao chép bộ nhớ
        StringBuffer stringBuffer = new StringBuffer("Hello");

        Thread thread1 = new Thread(() -> work(stringBuffer));
        Thread thread2 = new Thread(() -> work(stringBuffer));

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static  void work(StringBuffer s){
        for (int i = 0; i < 2; i++) {
               s.append(" Trung");
        }
    }
}
