package MemoryManagementJava;

public class StackvsHeap {
    static String globalString; // lưu trong heap(biến tĩnh) => lưu trữ vùng nhớ cho những biến con trỏ được cấp phát động
    public static void main(String[] args) {
        int x = 5; //(biến cục bộ lưu trong stack) =>lưu trữ biến cục bộ là stack
        System.out.println(sum(x));

        globalString = "Hello";
        System.out.println(globalString);

        MyObject myObject = new MyObject(); //tạo đối tượng bằng từ khóa new -> cấp phát vùng nhớ heap cho đối tượng
    }
    public static int sum(int a){
        int b = 10; //biến cục bộ lưu trong stack
        return a + b;
    }
    
}
class MyObject{
    //các thuộc tính của class này được lưu trong heap
}
//Phân biệt: biến được lưu trong stack sẽ chỉ tồn tại khi method chứa nó đang chạy
//Heap sẽ tồn tại khi app còn chạy

//Mối liên hệ: JVM tải một lớp/metadata thì sẽ được lưu trong method area, khi khởi tạo một đối tượng từ lớp đó thì được cấp phát vùng nhớ heap, còn các tham chiếu thì được lưu trong stack