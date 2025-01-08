package JavaCore;

public class Polymorphism {
    public static void main(String[] args) {
        System.out.println(add(1, 2));
        System.out.println(add(1, 2, 3));
    }
    //method overload => các phương thức cùng tên nhưng khác nhau về thứ tự/kiểu dữ liệu/số lượng tham số
    static int add(int a, int b){
        return a + b;
    }
    static int add(int a, int b, int c){
        return a + b + c;
    }
    class Animal{
        void makeSound(){
            System.out.println("Animal sound");
        }
    }
    class Dog extends Animal{
        @Override //method overriding : triển khai lại phương thức đã được định nghĩa ở lớp cha
        void makeSound(){
            System.out.println("Bark");
        }
    }
}
