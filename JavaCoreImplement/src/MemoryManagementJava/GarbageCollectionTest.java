package MemoryManagementJava;

public class GarbageCollectionTest {
    //thu hồi bộ nhớ của các đối tượng không còn được sử dụng/truy cập trong chương trình => giảm thiểu memory leak
    public static void main(String[] args) {
        //nguyên tắc hoạt động:
        // Xác định đối tượng thông qua GC roots => Thu hồi bộ nhớ
        GarbageCollectionTest test = new GarbageCollectionTest();
        test = null;
        System.gc(); //runs the garbage collector
    }
    @Override
    protected void finalize() throws Throwable {
        System.out.println("Garbage collection completed");
    }
}
