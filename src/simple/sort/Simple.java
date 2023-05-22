package simple.sort;

import java.util.Arrays;

public class Simple {
    public static void main(String[] args) {
        int[] ints = {2, 3, 5, 11, 28, 1, 6, 1, 2};
//        bubble(ints);
        quick(ints, 0, ints.length - 1);
        System.out.println(Arrays.toString(ints));
    }

    /**
     * 冒泡排序：通过一趟排序将最大的数排到最后
     */
    public static void bubble(int[] nums) {
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
    }

    /**
     * 快速排序：通过一趟排序将要排序的数据分割成独立的两部分，其中一部分的所有数据都比另外一部分的所有数据都要小，
     * 然后再按此方法对这两部分数据分别进行快速排序，整个排序过程可以递归进行，以此达到整个数据变成有序序列。
     */
    public static void quick(int[] nums, int l, int r) {
        if (l >= r || nums == null || nums.length <= 1) {
            return;
        }
        int i = l;
        int j = r;
        // 选第一个数作为基准值
        int x = nums[i];
        while (i < j) {
            // 从右向左找小于x的值
            while (j > i && nums[j] > x) {
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
        nums[i] = x;
        // 递归排序左半部分
        quick(nums, l, i - 1);
        quick(nums, i + 1, r);
    }
}
