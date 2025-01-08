package CollectionJava;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ImmutableCollection {
    public static void main(String[] args) {
        //immutable collection là tập hợp không thể thay đổi sau khi được tạo

        List<String> mutableList = new ArrayList<>();
        mutableList.add("abc");
        mutableList.add("d");

        List<String> immutableList = Collections.unmodifiableList(mutableList);
        // Thử thay đổi danh sách sẽ gây ra lỗi UnsupportedOperationException
        System.out.println(immutableList);
        //List/Set/Map of
        List<String> immutableList2 = List.of("a", "b", "c");

        System.out.println(immutableList2);
    }
}
