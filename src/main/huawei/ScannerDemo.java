package main.huawei;

import java.util.Arrays;
import java.util.Scanner;

public class ScannerDemo {
    public static void main(String[] args) {
        splitDemo();

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            // next() 读取一个字符串，遇到空格就结束
//            String next = scanner.next();
//            System.out.println(next);

            // nextInt() 读取一个整数
            int nextInt = scanner.nextInt();
            System.out.println(nextInt);

            // nextLine() 读取一行字符串，遇到回车就结束
//            String nextLine = scanner.nextLine();
//            System.out.println(nextLine);
        }
    }

    public static void splitDemo() {
        String s = " A  b c  ";
        String[] strings = s.split(" ");
        System.out.println(Arrays.toString(strings));
    }
}
