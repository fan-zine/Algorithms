package Huawei;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 题目描述
 * 一贫如洗的樵夫阿里巴巴在去砍柴的路上，无意中发现了强盗集团的藏宝地，藏宝地有编号从0~的箱子，每个箱子上面贴有一个数字，箱子中可能有一个黄金宝箱。
 * 黄金宝箱满足排在它之前的所有箱子数字和等于排在它之后的所有箱子数字之和；第一个箱子左边部分的数字和定义为0：最后一个箱子右边部分的数字和定义为0
 * 请帮阿里巴巴找到黄金宝箱，输出第一个满足条件的黄金宝箱编号，如果不存在黄金宝箱，请返回-1.
 * <p>
 * 输入描述
 * 箱子上贴的数字列表，使用逗号分隔，例如1，-1,0
 * 宝箱的数量不小于1个，不超过10000
 * 宝箱上贴的数值范围不低于-1000，不超过1000
 * <p>
 * 输出描述
 * 第一个黄金宝箱的编号
 */
public class 阿里巴巴找黄金宝箱I {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] num = Arrays.stream(sc.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        int index = getIndex(num);
        System.out.println(index);
    }

    public static int getIndex(int[] num) {
        if (num.length == 1) return 0;
        int sum = Arrays.stream(num).sum();
        int sumLeft = 0, sumRight = sum - num[0];
        if (sum - num[num.length - 1] == 0) return num.length - 1;
        for (int i = 0; i < num.length - 1; i++) {
            if (sumLeft == sumRight) return i;
            sumLeft += num[i];
            sumRight -= num[i + 1];
        }
        return -1;
    }
}
