package main.honor;

import java.util.Scanner;

/**
 * 寻找质数并组合
 */
public class Main2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextInt()) { // 注意 while 处理多个 case
            int a = in.nextInt();
            int b = in.nextInt();
            int minValue = minValue(a, b);
            System.out.println(minValue);
        }
    }

    public static int minValue(int low, int high) {
        int shiCount = 0;
        int geCount = 0;
        for (int i = low; i < high; i++) {
            if (iszhi(i)) {
                int ge = i % 10;
                geCount += ge;
                int shi = i / 10 % 10;
                shiCount += shi;
            }
        }
        return Math.min(shiCount, geCount);
    }

    public static boolean iszhi(int num) {
        for (int i = 2; i < num; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}
