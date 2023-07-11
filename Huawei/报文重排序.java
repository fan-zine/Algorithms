package Huawei;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 题目描述
 * 对报文进行重传和重排序是常用的可靠性机制，重传缓中区内有一定数量的子报文，每个子报文在原始报文中的顺序已知，现在需要恢复出原始报文。
 * <p>
 * 输入描述
 * 输入第一行为N,表示子报文的个数，0<N≤1000。
 * 输入第二行为N个子报文，以空格分开，子报文格式为：
 * 字符审报文内容 后缀顶序索引
 * 字符串报文内容由[a-z,A-Z]阻成，后缀为整型值，表示顺序。
 * 顺序值唯一，不重复。
 * <p>
 * 输出描述
 * 输出恢复出的原始报文，按照每个子报文的顺序的升序排序恢复出原始报文，顺序后缀需要从恢复出的报文中删除掉
 */
public class 报文重排序 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        String[] s1 = sc.nextLine().split(" ");
        List<String> collect = Arrays.stream(s1).collect(Collectors.toList());
        Map<Integer, String> ans = new HashMap<>();
        for (String s : collect) {
            StringBuilder content = new StringBuilder();
            String before = "";
            int after = 0;
            for (int i = 0; i < s.length(); i++) {
                if ((s.charAt(i) >= 'a' && s.charAt(i) <= 'z') || (s.charAt(i) >= 'A' && s.charAt(i) <= 'Z')) {
                    content.append(s.charAt(i));
                } else {
                    before = content.toString();
                    after = Integer.parseInt(s.substring(i));
                    break;
                }
            }
            ans.put(after, before);
        }
        StringBuilder sentence = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            sentence.append(ans.get(i)).append(" ");
        }
        String output = sentence.substring(0, sentence.length() - 1);
        System.out.println(output);
    }
}
