package LambdaAndStreams;

import java.util.List;
import java.util.stream.Collectors;

public class MapvsFlatMap {
    public static void main(String[] args) {
        //ánh xạ (1:1) mỗi phần tử của stream sang một kết quả mới => kết quả là 1 stream chứa các phần tử đã biến đổi 
        List<String> names = List.of("Trung", "Thanh", "Kiet", "Huy", "Long");
        List<String> downcaseNames = names.stream().map(String::toLowerCase).collect(Collectors.toList());

        System.out.println(downcaseNames);

        //khi danh sách lồng nhau thì map() sẽ tạo một stream lồng nhau
        List<List<Integer>> nestedLists = List.of(
            List.of(1, 2, 3),
            List.of(4, 5, 6)
        );

        // flatMap:Ánh xạ mỗi phần tử sang một stream và "phẳng hóa" (flatten) tất cả các stream con thành một stream duy nhất.
        List<Integer> flatList = nestedLists.stream().flatMap(List::stream).collect(Collectors.toList());
        System.out.println(flatList);

        //=> map: chuyển đổi dữ liệu một phần tử thành 1 giá trị/đối tượng khác
        //=>flatMap làm phẳng một mảng/danh sách đa chiều
    }
}
