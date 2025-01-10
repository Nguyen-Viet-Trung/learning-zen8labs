package MoreOOP;

public class DynamicBinding {
    // Dynamic Binding thường được sử dụng khi làm việc với kế thừa và đa hình.
    //là quá trình trong đó việc liên kết (binding) giữa một lời gọi phương thức và mã thực thi của nó được thực hiện tại thời điểm chạy (runtime) thay vì tại thời điểm biên dịch (compile-time).
    public static void main(String[] args) {
        Parent obj = new Child();
        obj.display();
    }
}
class Parent {
    void display(){
        System.out.println("Display from Parent");
    }
}
class Child extends Parent{
    @Override
    void display(){
        System.out.println("Display from child");
    }
}