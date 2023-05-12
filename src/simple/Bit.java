package simple;

public class Bit {
    public static void main(String[] args) {
        System.out.println(0 ^ 1);
//        System.out.println(smallestEvenMultiple(3));

    }

    /**
     * 输入是一个无符号整数（以二进制串的形式），返回其二进制表达式中数字位数为 '1' 的个数
     */
    public static int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            n = n & (n - 1);
            count++;
        }
        return count;
    }

    /**
     * 判断一个整数是不是2的整数次方
     */
    public boolean isPowerOfTwo(int n) {
        if (n <= 0) {
            return false;
        } else {
            return (n & n - 1) == 0;
        }
    }

    /**
     * 最小偶倍数：给你一个正整数 n ，返回 2 和 n 的最小公倍数（正整数）。
     */
    public static int smallestEvenMultiple(int n) {
        if (n == 0) {
            return 0;
        }
        if ((n & 1) == 0) {
            return n;
        }
        return n << 1;
    }


}
