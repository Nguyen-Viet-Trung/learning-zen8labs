package MoreOOP;

public class StaticMethod {
    static void display(){
        System.out.println("This is static display");
    }
    public static void main(String[] args) {
        // không thể override phương thức display vì static method thuộc về chính lớp đó, được gọi qua tên lớp chứ không phải đối tượng
        StaticMethod.display();
        //Static method được liên kết (bound) tại thời điểm biên dịch (compile-time), trong khi override yêu cầu liên kết tại runtime (dynamic binding).
    }
    
}
