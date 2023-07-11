import java.util.Arrays;
import java.util.Scanner;

public class Utils {
    Scanner sc = new Scanner(System.in);

    // 输入形如2,5,-1,8,6的字符串，将其转换成数组
    int[] num = Arrays.stream(sc.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
}
