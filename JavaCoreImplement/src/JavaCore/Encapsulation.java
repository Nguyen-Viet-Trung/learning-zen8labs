package JavaCore;

public class Encapsulation {
    public static void main(String[] args) {
        User user = new User();//Kiểm soát truy cập
        user.setUsername("abc");
        user.setPassword("123");
        //Dễ bảo trì
    }
    private static class User{
        //Bảo vệ dữ liệu(tránh thay đổi trực tiếp, chỉ có thể thông qua getter/setter)
        private String username;
        private String password;
        public String getUsername() {
            //Ẩn thông tin
            return username;
        }
        public void setUsername(String username) {
            this.username = username;
        }
        public String getPassword() {
            return password;
        }
        public void setPassword(String password) {
            this.password = password;
        }
    }
}
