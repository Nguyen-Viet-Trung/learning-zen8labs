package MoreOOP;

public class OverridingClone {
    //phương thức clone() được sử dụng để tạo bản sao của 1 đối tượng. Để sử dụng cần implement giao diện cloneable rồi override bằng phương thức clone
    static class Shallowcopy implements Cloneable{
        int []array;

        public Shallowcopy(int[] array) {
            this.array = array;
        }

        @Override
        protected Object clone() throws CloneNotSupportedException{
            return super.clone();
        }
    //     @Override
    // protected Object clone() throws CloneNotSupportedException {
    //     DeepCopyExample cloned = (DeepCopyExample) super.clone();
    //     // Sao chép sâu mảng
    //     cloned.array = this.array.clone();
    //     return cloned;
    // } nếu sử dụng deep cloned thì thay đổi trên obj2 không thay đổi obj1
        public static void main(String[] args) throws CloneNotSupportedException{
            int[] array = {1, 2, 3, 4, 5};
            Shallowcopy deepcopy = new Shallowcopy(array);
            Shallowcopy deepcopy2 = (Shallowcopy) deepcopy.clone();

            deepcopy2.array[0] = 10; //với shallow copy thì thay đổi giá trị trên obj2 sẽ ảnh hưởng tới obj1
            System.out.println(deepcopy.array[0]);


        }
    }
}
