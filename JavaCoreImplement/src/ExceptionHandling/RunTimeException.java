package ExceptionHandling;

public class RunTimeException {
    public static void main(String[] args) {
        System.out.println(divide(10, 0));
    } //runtime exception là lỗi của kết quả lập trình sai logic
    //checked exception sử dụng để cung cấp thông tin rõ ràng về lỗi xảy ra
    public static int divide(int a, int b){
        if(b == 0){
            throw new ArithmeticException("Không thể chia cho 0");
        }
        return a / b;
    }
}
