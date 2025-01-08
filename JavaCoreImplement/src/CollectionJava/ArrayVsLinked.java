package CollectionJava;

import java.util.ArrayList;
import java.util.LinkedList;

public class ArrayVsLinked {
    public static void main(String[] args) {
        ArrayList<Integer> aList = new ArrayList<>();
        //index => get/set O(1), dựa trên mảng động, được lưu liên tiếp
        aList.add(1); //O(1)
        aList.addFirst(2); //O(n)
        aList.addLast(3); //O(1)
        aList.get(1); //O(1)
        aList.set(1, 3); //O(1)
        aList.remove(1); //O(n)
        //Nên sử dụng với kích thước cố định, cần truy cập ngẫu nhiên

        LinkedList<Integer> lList = new LinkedList<>();
        lList.add(1); //O(1)
        lList.addFirst(2); //O(1)
        lList.addLast(3); //O(1)
        lList.get(1); //O(n)
        lList.set(1, 3); //O(n)
        lList.remove(1); //O(n)
        //Nên sử dụng với mảng cần cập nhật số lượng, thêm bớt phần tử ở đầu, giữa danh sách
    }   
    //search của cả 2 đều là O(n)
}
