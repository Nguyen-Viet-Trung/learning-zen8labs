package MemoryManagementJava;
// 1 số dấu hiệu: Ứng dụng chậm, JVM dùng bộ nhớ tăng không kiểm soát và không giảm sau Garbage Collection hoặc out of memory error
//1 số công cụ kiểm tra: VisualVM,Eclipse MAT,...

import java.util.ArrayList;
import java.util.List;

// Nguyên nhân: Tham chiếu tĩnh(static) không được giải phóng
public class MemoryLeakIncident {
    //Xử lý: Xóa tham chiếu không cần thiết => Chuyển về null rồi cho Garbage Collection xử lý:
    //object = null
    //Sử dụng weak references hoặc soft references
    //WeakReference<MyClass> weakRef = new WeakReference<>(new MyClass());
    //dọn dẹp thread local: threadLocal.remove();
    //Ví dụ listener không được hủy
    public static void main(String[] args) {
        EventSource source = new EventSource();
        source.addListener(new EventListener(){
            @Override
            public void onEvent() {
                System.out.println("Event occurred");
            }
        });
        //=> đến đây thì memory leak vì listener chưa được hủy
        source.removeListener(new EventListener(){
            @Override
            public void onEvent() {
                System.out.println("Event removed");
            }
        });
    }
    static interface EventListener{
        void onEvent();
    }
    static class EventSource{
        private List<EventListener> listeners = new ArrayList<>();
        public void addListener(EventListener listener){
            listeners.add(listener);
        }
        //đó là lý do cần
        public void removeListener(EventListener listener){
            listeners.remove(listener);
        }
   }







}   
