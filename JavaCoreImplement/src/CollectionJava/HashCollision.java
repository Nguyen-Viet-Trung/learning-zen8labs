package CollectionJava;

import java.util.HashMap;
import java.util.Objects;

public class HashCollision {
    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        map.put("1", "test");
        map.put("1", "test2"); // giá trị tại key này sẽ ghì đè lên giá trị lúc trước
        map.put("2","answer");
        System.out.println(map);

        //=> Cách xử lý: Lưu trữ các giá trị chung hash code trong cùng một bucket dưới dang linked list
        Key k1 = new Key(1, "first");
        Key k2 = new Key(2, "second");
        Key k3 = new Key(2, "third");

        HashMap<Key, String> newMap = new HashMap<>();
        newMap.put(k1, "firstValue");
        newMap.put(k2, "secondValue");
        newMap.put(k3, "thirdValue");

        String v1 = newMap.get(k1);
        String v2 = newMap.get(k2);
        String v3 = newMap.get(k3);
        System.out.println(newMap);
        System.out.println(v1 + "\n" + v2 +"\n" + v3);
    }
    public static class Key{
        private String name;
        private int id;

        public Key(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public int hashCode(){ // cố định hashCode để trùng
            return Objects.hash(id,name); //trả về 1 số nguyên duy nhất đại diện cho đối tượng
        }
         @Override
        public boolean equals(Object obj){
            System.out.println("Calling equals for key " + obj);
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Key key = (Key) obj;
            return id == key.id && Objects.equals(name, key.name); //muốn bằng phải cùng hashcode
        }
        @Override
        public String toString() {
            return "Key{" + "id=" + id + ", name='" + name + '\'' + '}';
        }
    }
}
