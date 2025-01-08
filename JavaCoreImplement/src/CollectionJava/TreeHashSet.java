package CollectionJava;

import java.util.HashSet;
import java.util.TreeSet;

public class TreeHashSet {
    //Đối với TreeSet (chí phí là O(logn)), Đối với HashSet thì O(1) (chủ yếu) -> O(n) (xấu)
    //Dùng treeset cho dữ liệu có thứ tự hoặc sắp xếp theo thứ tự
    //Dùng hashset nếu cần hiệu suất cao trong tìm kiếm, không quan trọng thứ tự
    public static void main(String[] args) {
        HashSet<Integer> hashSet = new HashSet<>();
        hashSet.add(10);
        hashSet.add(20);
        hashSet.add(30);
        hashSet.add(10); // Phần tử trùng lặp, sẽ bị bỏ qua
        hashSet.add(null); // Hợp lệ

        System.out.println("HashSet: " + hashSet); // Kết quả không theo thứ tự

        TreeSet<Integer> treeSet = new TreeSet<>();
        treeSet.add(30);
        treeSet.add(10);
        treeSet.add(20); //không hợp lệ nếu add null

        System.out.println("TreeSet: " + treeSet); // Kết quả được sắp xếp: [10, 20, 30]

    }
}
