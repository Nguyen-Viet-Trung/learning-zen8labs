package CollectionJava;

import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapImplement {
    // so với hashmap thì thread-safe, hiệu suất đa luồng cao nhưng không có phép key, value null, và khó khóa hơn
    public static void main(String[] args) {
        ConcurrentHashMap<String, Integer> test = new ConcurrentHashMap<>();
        Thread thread1 = new Thread(() -> work(test));
        Thread thread2 = new Thread(() -> work2(test));

       thread1.start();
       thread2.start();

    try {
        thread1.join();
        thread2.join(); //đợi thread làm xong trước khi trả kết quả
    } catch (Exception e) {
        e.printStackTrace();
    }
       System.out.println("Kết quả cuối cùng: " + test);
    }
    public static void work(ConcurrentHashMap<String, Integer> test) {
        for (int i = 0; i < 10; i++) {
            test.put("abc" + i, i);
        }
    }
    
    public static void work2(ConcurrentHashMap<String, Integer> test) {
        for (int i = 0; i < 10; i++) {
            test.put("def" + i, i);
        }
    }
    //ConcurrentHashMap không đảm bảo thứ tự chèn hoặc đồng bộ toàn bộ khi có nhiều thread thao tác đồng thời.
}
