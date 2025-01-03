package main.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * 在计算机科学中，时间复杂度中的对数通常是以 2 为底的，尤其是在涉及二分查找、树结构等情况下。
 * 这是因为这些算法和数据结构通常涉及将问题规模减半的操作，因此自然地与以 2 为底的对数相关。
 * 然而，在数学中，如果对数没有明确指定底数，通常默认是以 10 为底的（常称为常用对数），或者以 e 为底的（自然对数，记作 ln）。
 * 但在计算机科学的复杂度分析中，底数通常是 2，除非另有说明。
 */
public class Simple {
    public static void main(String[] args) {
        int[] ints = new int[100000];
        Random random = new Random();
        for (int i = 0; i < ints.length; i++) {
            ints[i] = random.nextInt(10000);
        }
//        System.out.println(Arrays.toString(ints));
        int[] copy = Arrays.copyOfRange(ints, 0, ints.length);
        int[] copy1 = Arrays.copyOfRange(ints, 0, ints.length);
        int[] nums = {2, 3, 5, 11, 28, 1, 6, 1, 2};
//        bubble(ints);
//        System.out.println("bubble: " + Arrays.toString(ints));
//        insertSort(nums);
//        System.out.println("insertSort：" + Arrays.toString(nums));
        long start = System.currentTimeMillis();
        quick(copy, 0, copy.length - 1);
        System.out.println("quick用时：" + (System.currentTimeMillis() - start));
//        System.out.println("quick: " + Arrays.toString(copy));
//        System.out.println(Arrays.toString(copy));

        long start1 = System.currentTimeMillis();
        Arrays.sort(copy1);
        System.out.println("系统的quick用时：" + (System.currentTimeMillis() - start1));
//        System.out.println("系统的quick: " + Arrays.toString(copy1));
    }

    /**
     * 冒泡排序：通过一趟排序将最大的数排到最后。
     * 平均时间复杂度O(n^2)
     */
    public static void bubble(int[] nums) {
        long start = System.currentTimeMillis();
        if (nums == null || nums.length <= 1) {
            return;
        }
        int temp;
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = 0; j < nums.length - i - 1; j++) {
                if (nums[j] > nums[j + 1]) {
                    temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                }
            }
        }
        System.out.println("bubble用时：" + (System.currentTimeMillis() - start));
    }

    /**
     * 快速排序：通过一趟排序将要排序的数据分割成独立的两部分，其中一部分的所有数据都比另外一部分的所有数据都要小，
     * 然后再按此方法对这两部分数据分别进行快速排序，整个排序过程可以递归进行，以此达到整个数据变成有序序列。
     * 平均时间复杂度O(nlogn)
     */
    public static void quick(int[] nums, int l, int r) {
        if (nums == null || nums.length <= 1 || l < 0 || r >= nums.length || l >= r) {
            return;
        }
        int partition = partition(nums, l, r);
        // 递归排序左半部分
        quick(nums, l, partition - 1);
        // 递归排序右半部分
        quick(nums, partition + 1, r);
    }

    /**
     * 一趟快速排序
     */
    public static int partition(int[] nums, int l, int r) {
        if (nums == null || nums.length <= 0 || l < 0 || r >= nums.length || l >= r) {
            throw new IllegalArgumentException("参数错误");
        }
        int i = l;
        int j = r;
        // 选第一个数作为基准值
        int x = nums[i];
        while (i < j) {
            // 从右向左找小于x的值
            while (i < j && nums[j] >= x) {
                j--;
            }
            if (i < j) {
                nums[i] = nums[j];
                i++;
            }
            // 从左到右找大于x的值
            while (i < j && nums[i] < x) {
                i++;
            }
            if (i < j) {
                nums[j] = nums[i];
                j--;
            }
        }
        // 将基准值放入i位置
        nums[i] = x;
        return i;
    }

    /**
     * 直接插入排序
     */
    public static void insertSort(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }
        for (int i = 1; i < nums.length; i++) {
            int iValue = nums[i];
            for (int j = i - 1; j >= -1; j--) {
                if (j == -1 || iValue > nums[j]) {
                    // 找到了插入位置：j+1
                    nums[j + 1] = iValue;
                    break;
                } else {
                    // j位置的数往后移1位
                    nums[j + 1] = nums[j];
                }
            }
        }
    }

}
