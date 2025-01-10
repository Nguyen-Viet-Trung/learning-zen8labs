package StringManipulation;

public class ImmutableStringJava {
    //immutable string là string không thể thay đổi sau khi khởi tạo
    //bất kỳ thao tác nào trên string đó sẽ tạo ra 1 đối tượng stirng mới mà string ban đầu ko bị thay đổi
    public static void main(String[] args) {
        String str = "Hello";
        str.concat(" World");
        System.out.println(str); //kết quả là Hello

        str = str.concat(" World");
        System.out.println(str); //Hello World

        //Đúng là biến s đã thay đổi thành “Hello World”, nhưng đây lại là chuỗi hoàn toàn khác được lưu ở String pool. Bên cạnh đó, chuỗi “Hello” vẫn còn trong bộ nhớ Heap và không hề bị thay đổi, chỉ là không có biến nào tham chiếu đến nó
        
    }
}
