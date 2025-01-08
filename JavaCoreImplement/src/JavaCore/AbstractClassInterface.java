package JavaCore;

public class AbstractClassInterface {
    // Abstract class example
    //=> Sử dụng khi có logic hoặc trạng thái cần chia sẻ giữa các lớp con
abstract class Animal {
    static int legs = 4;
    abstract void makeSound(); // phương thức trừu tượng
    void eat() { // phương thức cụ thể
        System.out.println("This animal eats food.");
    }
}

class Dog extends Animal { // chỉ có thể kế thừa từ 1 abstract class
    void makeSound() {
        System.out.println("Woof!");
    }
}

// Interface example
//hỗ trợ đa kế thừa => Một lớp có thể triển khai nhiều interface
//chỉ có phương thức trừu tượng ( trừ khi dùng default, static)
//chỉ có thể chứa hằng số (final)
//không có constructor
//=> Định nghĩa hành vi mà không liên quan đến trạng thái
interface Flyable {
    void fly();
}

interface Swimable {
    void swim();
}

class Bird implements Flyable {
    public void fly() {
        System.out.println("This bird can fly.");
    }
}

class Duck implements Flyable, Swimable {
    public void swim() {
        System.out.println("This fish can swim.");
    }
    public void fly(){
        System.out.println("Flyable");
    }
}

}
