package simple.array;

import java.util.*;

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
//        System.out.println(majorityElement(new int[]{3, 3, 4}));
//        System.out.println(containsNearbyDuplicate(new int[]{1, 0, 1, 1}, 1));
//        System.out.println(luckyNumbers(new int[][]{{3, 6}, {7, 1}, {5, 2}, {4, 8}}));
//        printNumbersMax(10);
//        System.out.println(generate(4));
//        System.out.println(missingNumber(new int[]{0, 2}));
    }

    /**
     * 旋转数组的最小数字：把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
     * 给你一个可能存在重复元素值的数组numbers，它原来是一个升序排列的数组，并按上述情形进行了一次旋转。请返回旋转数组的最小元素。
     * 例如，数组[3,4,5,1,2] 为 [1,2,3,4,5] 的一次旋转，该数组的最小值为 1。
     * 注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。
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
        if (nums == null || nums.length <= 0) {
            return 0;
        }
        if (nums.length == 1) {
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
        if (nums == null || nums.length <= 0) {
            return 0;
        }
        if (nums.length == 1) {
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
     * 打印从1到最大的n位数，考虑大数。模拟字符串+1
     */
    public static void printNumbersMax(int n) {
        if (n <= 0) {
            return;
        }
        int[] digits = new int[n];
        boolean loop;
        do {
            digits = plusOne(digits);
            loop = false;
            for (int digit : digits) {
                if (digit != 9) {
                    loop = true;
                    break;
                }
            }
            System.out.println(Arrays.toString(digits));
        } while (loop);
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

    /**
     * 存在重复元素：给你一个整数数组 nums 。如果任一值在数组中出现 至少两次 ，返回 true ；如果数组中每个元素互不相同，返回 false 。
     */
    public static boolean containsDuplicate(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return false;
        }
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (set.contains(num)) {
                return true;
            } else {
                set.add(num);
            }
        }
        return false;
    }

    /**
     * 存在重复元素 II：给你一个整数数组nums 和一个整数k ，判断数组中是否存在两个 不同的索引i和j ，
     * 满足 nums[i] == nums[j] 且 abs(i - j) <= k 。如果存在，返回 true ；否则，返回 false 。
     */
    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        if (nums == null || nums.length <= 1) {
            return false;
        }
        // Map的Key为数组的value，Value为数组的Index
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                int j = map.get(nums[i]);
                if (Math.abs(i - j) <= k) {
                    return true;
                }
            }
            map.put(nums[i], i);
        }
        return false;
    }

    /**
     * 矩阵中的幸运数：给你一个 m * n 的矩阵，矩阵中的数字 各不相同 。请你按 任意 顺序返回矩阵中的所有幸运数。
     * 幸运数 是指矩阵中满足同时下列两个条件的元素：
     * 在同一行的所有元素中最小
     * 在同一列的所有元素中最大
     */
    public static List<Integer> luckyNumbers(int[][] matrix) {
        if (matrix == null || matrix.length <= 0 || matrix[0].length <= 0) {
            return null;
        }
        List<Integer> list = new ArrayList<>();
        int colMinIndex;
        int rowMaxIndex;
        // 记录已经找到了幸运数字的列，后面就不用再处理这些列了
        Set<Integer> colSet = new HashSet<>();
        for (int row = 0; row < matrix.length; row++) {
            // 找row行最小值的列索引
            colMinIndex = 0;
            for (int col = 1; col < matrix[row].length; col++) {
                if (matrix[row][col] < matrix[row][colMinIndex]) {
                    colMinIndex = col;
                }
            }
            if (colSet.contains(colMinIndex)) {
                continue;
            }
            // 在对应的列中值是不是最大的
            rowMaxIndex = row;
            for (int secondRow = 0; secondRow < matrix.length; secondRow++) {
                if (matrix[secondRow][colMinIndex] > matrix[row][colMinIndex]) {
                    // 不是幸运数字
                    rowMaxIndex = secondRow;
                    break;
                }
            }
            if (rowMaxIndex == row) {
                // 是幸运数字
                list.add(matrix[row][colMinIndex]);
                colSet.add(colMinIndex);
            }
        }
        return list;
    }

    /**
     * 杨辉三角：给定一个非负整数 numRows，生成「杨辉三角」的前 numRows 行。
     * 在「杨辉三角」中，每个数是它左上方和右上方的数的和。
     */
    public static List<List<Integer>> generate(int numRows) {
        if (numRows <= 0) {
            return null;
        }
        List<List<Integer>> list = new ArrayList<>();
        for (int row = 1; row <= numRows; row++) {
            List<Integer> sub = new ArrayList<>();
            if (row == 1) {
                sub.add(1);
            } else {
                // 上一行的list
                List<Integer> last = list.get(row - 2);
                for (int i = 0; i < row; i++) {
                    int result = 0;
                    if (i - 1 >= 0) {
                        result += last.get(i - 1);
                    }
                    if (i < last.size()) {
                        result += last.get(i);
                    }
                    sub.add(result);
                }
            }
            list.add(sub);
        }
        return list;
    }

    /**
     * 丢失的数字；给定一个包含 [0, n] 中 n 个数的数组 nums ，找出 [0, n] 这个范围内没有出现在数组中的那个数。
     */
    public static int missingNumber(int[] nums) {
        if (nums == null) {
            throw new NullPointerException("数组为空");
        }
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i <= nums.length; i++) {
            set.add(i);
        }
        for (int i = 0; i < nums.length; i++) {
            set.remove(nums[i]);
        }
        return set.iterator().next();
    }

    /**
     * 两个数组的交集：给定两个数组 nums1 和 nums2 ，返回 它们的交集 。输出结果中的每个元素一定是 唯一 的。我们可以 不考虑输出结果的顺序 。
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length <= 0 || nums2 == null || nums2.length <= 0) {
            return null;
        }
        Set<Integer> set = new HashSet<>();
        for (int i : nums1) {
            set.add(i);
        }
        Set<Integer> result = new HashSet<>();
        for (int i : nums2) {
            if (set.contains(i)) {
                result.add(i);
            }
        }
        int[] results = new int[result.size()];
        int i = 0;
        for (Integer integer : result) {
            results[i] = integer;
            i++;
        }
        return results;
    }

    /**
     * 两个数组的交集 II：给你两个整数数组nums1 和 nums2 ，请你以数组形式返回两数组的交集。
     * 返回结果中每个元素出现的次数，应与元素在两个数组中都出现的次数一致（如果出现次数不一致，则考虑取较小值）。可以不考虑输出结果的顺序。
     */
    public int[] intersect(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length <= 0 || nums2 == null || nums2.length <= 0) {
            return null;
        }
        // key是元素，value是key出现的次数
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums1.length; i++) {
            if (map.containsKey(nums1[i])) {
                map.put(nums1[i], map.get(nums1[i]) + 1);
            } else {
                map.put(nums1[i], 1);
            }
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums2.length; i++) {
            if (map.containsKey(nums2[i])) {
                // 是重复数字
                if (map.get(nums2[i]) <= 0) {
                    // 说明num1中此数字的数量少于num2，就不再添加了
                } else {
                    list.add(nums2[i]);
                    map.put(nums2[i], map.get(nums2[i]) - 1);
                }
            }
        }
        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    /**
     * 移动零：给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
     * 请注意 ，必须在不复制数组的情况下原地对数组进行操作。
     */
    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }
        // 非0元素放在前count位
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[count] = nums[i];
                count++;
            }
        }
        // 将count后的元素设置为0
        for (int i = count; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

    /**
     * 最小的k个数：输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。
     */
    public int[] getLeastNumbers(int[] arr, int k) {
        if (arr == null || arr.length <= 0 || k >= arr.length) {
            return arr;
        }
        Arrays.sort(arr);
        return Arrays.copyOfRange(arr, 0, k);
    }

}
