package CollectionJava;

import java.util.HashMap;
import java.util.Hashtable;

public class HashMapTable {
    //thread-safety đề cập đến việc 2 thread cùng thực thi lên một đoạn mã, phương thức, hoặc lớp đúng đắn (synchronized)
    public static void main(String[] args) {
        HashMap<Integer, String> hashMap = new HashMap<>();
        //không đồng bộ hóa => nhanh hơn, không thread-safe
        hashMap.put(1, "abc");
        hashMap.put(2, "def");
        //tuy nhiên cách tối ưu nhất cho thread-safety là ConcurrentHashMap (thread-safe mà không đồng bộ hóa toàn bộ phương thức)
        Hashtable<Integer, String> hashtable = new Hashtable<>();
        
        
        hashtable.put(1, "One");
        hashtable.put(2, "Two");
        hashtable.put(3, "Three");

        
        System.out.println("Value for key 1: " + hashtable.get(1));
    }
}
