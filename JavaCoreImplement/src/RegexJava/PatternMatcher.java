package RegexJava;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternMatcher {
    public static void main(String[] args) {
        String pattern = "^[a-zA-Z0-9]+$"; // Mẫu: chỉ chứa chữ và số
        String input = "Java123";

        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(input);
        //cách này tối ưu nhất vì có thể tái sử dụng matcher với mẫu regex
        if (matcher.matches()) {
            System.out.println("Chuỗi khớp với mẫu!");
        } else {
            System.out.println("Chuỗi không khớp với mẫu.");
        }

        // if(Pattern.matches(pattern, input)){
        //     System.out.println("Khớp");
        // }
        // else{
        //     System.out.println("Không");
        // }
    }
}
