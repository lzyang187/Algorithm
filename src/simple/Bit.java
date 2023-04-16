package simple;

public class Bit {
    public static void main(String[] args) {

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
}
