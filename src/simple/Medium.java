package simple;

public class Medium {
    public static void main(String[] args) {
//        System.out.println(myPow(2.00000, -2147483648));
        System.out.println(myPowQuick(2.00000, -2147483648));
    }

    /**
     * 数值的整数次方：实现 pow(x, n) ，即计算 x 的 n 次幂函数。不得使用库函数，同时不需要考虑大数问题。
     */
    public static double myPow(double x, int n) {
        if (x == 0) {
            return 0;
        } else if (x == 1 || n == 0) {
            return 1;
        }
        long absN = Math.abs((long) n);
        double result = 1.0;
        for (int i = 0; i < absN; i++) {
            result *= x;
        }
        if (n < 0) {
            return 1 / result;
        }
        return result;
    }

    public static double myPowQuick(double x, int n) {
        if (x == 0) {
            return 0;
        } else if (x == 1 || n == 0) {
            return 1;
        }
        long absN = Math.abs((long) n);
        double half = myPowQuick(x, (int) (absN >>> 1));
        double result = half * half;
        if ((absN & 1) == 1) {
            // 奇数
            result = result * x;
        }
        if (n < 0) {
            return 1 / result;
        }
        return result;
    }

}
