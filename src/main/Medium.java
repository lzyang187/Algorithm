package main;

public class Medium {
    public static void main(String[] args) {
//        System.out.println(myPow(2.00000, -2147483648));
//        System.out.println(myPowQuick(2.00000, -2147483648));
        System.out.println(cuttingRope(8));
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

    /**
     * 剪绳子：给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），每段绳子的长度记为 k[0],k[1]...k[m-1] 。
     * 请问 k[0]*k[1]*...*k[m-1] 可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
     * 典型的动态规划
     */
    public static int cuttingRope(int n) {
        if (n <= 1) {
            throw new IllegalArgumentException("绳子长度不合法");
        }
        if (n == 2) {
            return 1;
        } else if (n == 3) {
            return 2;
        }
        // 存储子问题的最优结果
        int[] products = new int[n + 1];
        products[1] = 1;
        products[2] = 2;
        products[3] = 3;
        // 从下往上求解
        for (int i = 4; i <= n; i++) {
            int curMax = 0;
            for (int j = 1; j <= i >> 1; j++) {
                curMax = Math.max(products[j] * products[i - j], curMax);
            }
            products[i] = curMax;
        }
        return products[n];
    }

}
