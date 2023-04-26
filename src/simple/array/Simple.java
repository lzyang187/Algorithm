package simple.array;

import java.util.Arrays;

public class Simple {
    public static void main(String[] args) {
//        System.out.println(removeDuplicates(new int[]{1, 1, 2}));
//        System.out.println(removeElement(new int[]{3, 1, 2, 3, 5, 3, 3, 3, 7}, 3));
//        System.out.println(searchInsert(new int[]{1, 3, 5, 6}, 5));
//        int[] result = plusOne(new int[]{9, 9});
//        for (int i = 0; i < result.length; i++) {
//            System.out.println(result[i]);
//        }
//        int[] num1 = new int[]{0};
//        merge2(num1, 0, new int[]{1}, 1);
//        for (int i = 0; i < num1.length; i++) {
//            System.out.print(num1[i] + " ");
//        }
        System.out.println(majorityElement(new int[]{3, 3, 4}));
    }

    /**
     * 旋转数组的最小数字
     *
     * @param numbers
     * @return
     */
    public int minArray(int[] numbers) {
        if (numbers.length == 0) {
            return -1;
        }
        if (numbers.length == 1) {
            return numbers[0];
        }
        int front = 0;
        int tail = numbers.length - 1;
        if (numbers[front] < numbers[tail]) {
            return numbers[0];
        } else {
            int mid = numbers.length / 2;
            while (front < tail - 1) {
                if (numbers[mid] > numbers[front]) {
                    front = mid;
                    mid = (front + tail) / 2;
                } else if (numbers[mid] < numbers[front]) {
                    tail = mid;
                    mid = (front + tail) / 2;
                } else {
                    // 只能按顺序查找了
                    int min = numbers[0];
                    for (int i = 1; i < numbers.length; i++) {
                        if (numbers[i] < min) {
                            return numbers[i];
                        }
                    }
                    break;
                }
            }
            return numbers[tail];
        }
    }

    /**
     * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数在数组的前半部分，所有偶数在数组的后半部分。
     */
    public static int[] exchange(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return nums;
        }
        int leftindex = 0;
        int rightIndex = nums.length - 1;
        while (leftindex < rightIndex) {
            if ((nums[leftindex] & 1) == 0) {
                if ((nums[rightIndex] & 1) == 1) {
                    // 交换
                    int temp = nums[rightIndex];
                    nums[rightIndex] = nums[leftindex];
                    nums[leftindex] = temp;
                    leftindex++;
                    rightIndex--;
                } else {
                    rightIndex--;
                }
            } else {
                leftindex++;
            }
        }
        return nums;
    }

    /**
     * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字
     */
    public static int[] spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length <= 0 || matrix[0].length <= 0) {
            return new int[]{};
        }
        int[] resultArray = new int[matrix.length * matrix[0].length];
        int curArrayIndex = 0;
        int startRow = 0;
        int startCol = 0;
        int endRow = matrix.length - 1;
        int endCol = matrix[0].length - 1;
        while (startRow <= endRow && startCol <= endCol) {
            int curRow = startRow;
            int curCol = startCol;
            if (startRow == endRow) {
                // 只有一行了
                while (curCol <= endCol) {
                    resultArray[curArrayIndex] = matrix[startRow][curCol];
                    curArrayIndex++;
                    curCol++;
                }
            } else if (startCol == endCol) {
                // 只有一列了
                while (curRow <= endRow) {
                    resultArray[curArrayIndex] = matrix[curRow][startCol];
                    curArrayIndex++;
                    curRow++;
                }
            } else {
                // 一周
                // 上
                while (curCol <= endCol) {
                    resultArray[curArrayIndex] = matrix[startRow][curCol];
                    curArrayIndex++;
                    curCol++;
                }
                // 右
                curRow++;
                while (curRow <= endRow) {
                    resultArray[curArrayIndex] = matrix[curRow][endCol];
                    curArrayIndex++;
                    curRow++;
                }
                // 下
                curCol -= 2;
                while (curCol >= startCol) {
                    resultArray[curArrayIndex] = matrix[endRow][curCol];
                    curArrayIndex++;
                    curCol--;
                }
                // 左
                curRow -= 2;
                while (curRow > startRow) {
                    resultArray[curArrayIndex] = matrix[curRow][startCol];
                    curArrayIndex++;
                    curRow--;
                }
            }
            startRow++;
            startCol++;
            endRow--;
            endCol--;
        }
        return resultArray;
    }

    /**
     * 删除有序数组中的重复项：给你一个升序排列的数组nums，请你原地删除重复出现的元素，使每个元素只出现一次，返回删除后数组的新长度。
     * 元素的相对顺序应该保持一致。
     */
    public static int removeDuplicates(int[] nums) {
        if (nums == null) {
            return 0;
        }
        if (nums.length <= 1) {
            return 1;
        }
        int index = 0;
        int left = 0;
        int right = 1;
        while (right < nums.length) {
            if (nums[left] == nums[right]) {
                // 相同
            } else {
                index++;
                nums[index] = nums[right];
                left = index;
            }
            right++;
        }
        return ++index;
    }

    /**
     * 移除元素：给你一个数组nums和一个值val，你需要原地移除所有数值等于val的元素，并返回移除后数组的新长度。
     */
    public static int removeElement(int[] nums, int val) {
        if (nums == null || nums.length < 1) {
            return 0;
        }
        if (nums.length < 2) {
            if (nums[0] == val) {
                return 0;
            } else {
                return 1;
            }
        }
        int left = 0;
        int right = 0;
        while (right < nums.length) {
            if (nums[right] == val) {

            } else {
                nums[left] = nums[right];
                left++;
            }
            right++;
        }
        return left;
    }

    /**
     * 搜索插入位置：给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
     * 请必须使用时间复杂度为 O(log n) 的算法。
     */
    public static int searchInsert(int[] nums, int target) {
        if (nums == null || nums.length < 1) {
            return 0;
        }
        int left = 0;
        int right = nums.length - 1;
        int mid;
        while (left <= right) {
            mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    /**
     * 加一：给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一。
     * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
     * 你可以假设除了整数 0 之外，这个整数不会以零开头。
     */
    public static int[] plusOne(int[] digits) {
        if (digits == null) {
            return null;
        }
        int[] result = new int[digits.length];
        int plus = 1;
        for (int i = digits.length - 1; i >= 0; i--) {
            if (plus <= 0) {
                result[i] = digits[i];
            } else {
                plus = digits[i] + plus;
                if (i > 0) {
                    result[i] = plus % 10;
                } else {
                    result[i] = plus;
                }
                plus = plus / 10;
            }
        }
        // 如果首位是10
        if (result[0] == 10) {
            int[] realResult = new int[result.length + 1];
            realResult[0] = 1;
            // 其他位置都是0，不用赋值
            return realResult;
        }
        return result;
    }

    /**
     * 合并两个有序数组：给你两个按 非递减顺序 排列的整数数组nums1 和 nums2，另有两个整数 m 和 n ，分别表示 nums1 和 nums2 中的元素数目。
     * 请你 合并 nums2 到 nums1 中，使合并后的数组同样按 非递减顺序 排列。
     */
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        if (nums2 == null || n <= 0) {
            return;
        }
        for (int i = m; i < m + n; i++) {
            nums1[i] = nums2[i - m];
        }
        // 对m+n个元素进行排序，冒泡
        int temp;
        for (int i = 0; i < m + n - 1; i++) {
            for (int j = 0; j < m + n - 1 - i; j++) {
                if (nums1[j] > nums1[j + 1]) {
                    temp = nums1[j];
                    nums1[j] = nums1[j + 1];
                    nums1[j + 1] = temp;
                }
            }
        }
    }

    /**
     * merge的效率优化：先从后面插入
     */
    public static void merge2(int[] nums1, int m, int[] nums2, int n) {
        if (nums2 == null || n <= 0) {
            return;
        }
        int index1 = m - 1;
        int index2 = n - 1;
        for (int k = m + n - 1; k >= 0; k--) {
            if (index2 < 0) {
                // nums2全部插入完毕
                nums1[k] = nums1[index1];
                index1--;
            } else if (index1 < 0) {
                // nums1全部插入完毕
                nums1[k] = nums2[index2];
                index2--;
            } else if (nums1[index1] >= nums2[index2]) {
                nums1[k] = nums1[index1];
                index1--;
            } else {
                nums1[k] = nums2[index2];
                index2--;
            }
        }
    }

    /**
     * 多数元素：给定一个大小为 n 的数组nums ，返回其中的多数元素。多数元素是指在数组中出现次数 大于 n/2 的元素。
     * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
     */
    public static int majorityElement(int[] nums) {
        // 先排序
        Arrays.sort(nums);
        // 中间的那个数就是多数元素
        return nums[(nums.length - 1) >>> 1];
    }
}
