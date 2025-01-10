package MoreOOP;

public class ThisAndSuper {
    //this() được dùng để gọi một constructor khác trong cùng lớp. super() được dùng để gọi constructor của lớp cha.
    static class Parent {
        Parent() {
            System.out.println("Parent Constructor");
        }
    }
    //sử dụng lớp trừu tượng (absstract class) để ngăn khởi tạo trực tiếp
    static class Child extends Parent {//có thể sử dụng final để ngăn việc class được kế thừa và khởi tạo
        Child() { // để ngăn không cho class bị khởi tạo từ bên ngoài có thể đặt constructor thành private
            this(10); // Gọi constructor khác trong cùng lớp
        }
    
        Child(int value) {
            super(); // Gọi constructor của lớp cha
            System.out.println("Child Constructor with value: " + value);
        }
    }
    public static void main(String[] args) {
        Child child = new Child(); 
    }
}
