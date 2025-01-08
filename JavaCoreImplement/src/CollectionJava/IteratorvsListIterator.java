package CollectionJava;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

public class IteratorvsListIterator {
    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "a");
        map.put(2, "b");
        map.put(3,"c");

        //iterator được dùng để duyệt list qua vòng lặp
        Iterator<Map.Entry<Integer, String>> ite = map.entrySet().iterator();
        while(ite.hasNext()){ // iterator chỉ có thể duyệt tiến nhưng cho nhiều collection
            System.out.println(ite.next());
        }

        List<Integer> list = new ArrayList<>();
        
        list.add(1);
        list.add(2);
        list.add(3);

        ListIterator<Integer> iteList = list.listIterator();
        System.out.println("Forward");
        while(iteList.hasNext()){
            iteList.add(10); //cho phép thêm phần tử (nhưng vẫn đang duyệt list cũ)
            System.out.println(iteList.next());
        } 
        System.out.println("Backward");
        while(iteList.hasPrevious()){
            System.out.println(iteList.previous());
            iteList.remove(); //remove phần tử hiện tại
        }
        System.out.println("Forward");
        while(iteList.hasNext()){
            iteList.add(10); //cho phép thêm phần tử (nhưng vẫn đang duyệt list cũ)
            System.out.println(iteList.next());
        } 
        // => Xác định được vị trí của con trỏ vị trí
    }
}
