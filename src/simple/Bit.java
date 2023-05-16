package simple;

public class Bit {
    public static void main(String[] args) {
        System.out.println(0 ^ 1);
//        System.out.println(smallestEvenMultiple(3));
        System.out.println(singleNumber(new int[]{4, 2, 3, 11, 14, 14, 11, 3, 2}));
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

    /**
     * 只出现一次的数字：给你一个 非空 整数数组 nums ，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
     * 你必须设计并实现线性时间复杂度的算法来解决此问题，且该算法只使用常量额外空间。
     * 线性复杂度是O(n)，常量复杂度是O(1)
     */
    public static int singleNumber(int[] nums) {
        if (nums == null || nums.length <= 0) {
            throw new IllegalArgumentException("nums为空");
        }
        int result = nums[0];
        for (int i = 1; i < nums.length; i++) {
            result ^= nums[i];
        }
        return result;
    }

}
