package main.array;

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

//        System.out.println(Arrays.toString(getLeastNumbersQuick(new int[]{0, 0, 0, 2, 0, 5}, 0)));
//        System.out.println(thirdMax(new int[]{1, 2, 3, 9}));

        int[] nums = {0, 0, 0, 1, 1, 1, 1, 0};
        exchangeOddEven2(nums);
        System.out.println(Arrays.toString(nums));
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
     * 删除有序数组中的重复项：给你一个升序排列的数组nums，请你原地删除重复出现的元素，使每个元素只出现一次，返回删除后数组的新长度。
     * 元素的相对顺序应该保持一致。
     */
    public static int removeDuplicates(int[] nums) {
        if (nums == null || nums.length <= 0) {
            return 0;
        }
        int realIndex = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[realIndex] != nums[i]) {
                realIndex++;
                nums[realIndex] = nums[i];
            }
        }
        return ++realIndex;
    }

    /**
     * 移除元素：给你一个数组nums和一个值val，你需要原地移除所有数值等于val的元素，并返回移除后数组的新长度。
     */
    public static int removeElement(int[] nums, int val) {
        if (nums == null || nums.length <= 0) {
            return 0;
        }
        int realIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[realIndex] = nums[i];
                realIndex++;
            }
        }
        return realIndex;
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
        while (plusOneForNumbersMax(digits)) {
            System.out.println(Arrays.toString(digits));
        }
    }

    public static boolean plusOneForNumbersMax(int[] digits) {
        if (digits == null || digits.length <= 0) {
            return false;
        }
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] + 1 > 9) {
                if (i == 0) {
                    // 首位
                    return false;
                } else {
                    digits[i] = 0;
                }
            } else {
                digits[i] = digits[i] + 1;
                break;
            }
        }
        return true;
    }

    /**
     * 加一：给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一。
     * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
     * 你可以假设除了整数 0 之外，这个整数不会以零开头。
     */
    public static int[] plusOne(int[] digits) {
        if (digits == null || digits.length <= 0) {
            return digits;
        }
        int plus = 1;
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] + plus > 9) {
                if (i == 0) {
                    // 首位
                    digits[i] = 10;
                } else {
                    digits[i] = 0;
                }
            } else {
                digits[i] = digits[i] + plus;
                break;
            }
        }
        // 如果首位是10
        if (digits[0] == 10) {
            int[] realResult = new int[digits.length + 1];
            realResult[0] = 1;
            // 其他位置都是0，不用赋值
            return realResult;
        }
        return digits;
    }

    /**
     * 合并两个有序数组：给你两个按 非递减顺序 排列的整数数组nums1 和 nums2，另有两个整数 m 和 n ，分别表示 nums1 和 nums2 中的元素数目。
     * 请你 合并 nums2 到 nums1 中，使合并后的数组同样按 非递减顺序 排列。
     * 注意：最终，合并后数组不应由函数返回，而是存储在数组 nums1 中。为了应对这种情况，nums1 的初始长度为 m + n，其中前 m 个元素表示应合并的元素，
     * 后 n 个元素为 0 ，应忽略。nums2 的长度为 n 。
     */
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        if (nums1 == null || nums1.length < m + n) {
            throw new RuntimeException("参数错误");
        }
        if (nums2 == null || n <= 0) {
            return;
        }
        int index1 = m - 1;
        int index2 = n - 1;
        for (int k = m + n - 1; k >= 0; k--) {
            if (index2 < 0) {
                // nums2全部插入完毕
                break;
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
        if (nums == null || nums.length <= 0) {
            throw new IllegalArgumentException("参数不合法");
        }
        // 先排序
        Arrays.sort(nums);
        // 中间的那个数就是多数元素
        return nums[(nums.length) >>> 1];
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
     * 矩阵中的幸运数：给你一个 m * n 的矩阵，矩阵中的数字 “各不相同” 。请你按 任意 顺序返回矩阵中的所有幸运数。
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
     * 在「杨辉三角」中，每个数是它左上方和右上方的数的和，第一行为 1。
     */
    public static List<List<Integer>> generate(int numRows) {
        if (numRows <= 0) {
            return null;
        }
        List<List<Integer>> list = new ArrayList<>(numRows);
        for (int row = 1; row <= numRows; row++) {
            List<Integer> sub = new ArrayList<>(row);
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
     * 杨辉三角 II：给定一个非负索引 rowIndex，rowIndex 为 0时是第一行。返回「杨辉三角」的第 rowIndex 行。
     * 在「杨辉三角」中，每个数是它左上方和右上方的数的和，第一行为 1。
     */
    public List<Integer> getRow(int rowIndex) {
        if (rowIndex < 0) {
            return null;
        }
        List<Integer> lastList = new ArrayList<>();
        List<Integer> curList = new ArrayList<>();
        for (int i = 0; i <= rowIndex; i++) {
            if (i == 0) {
                lastList.add(1);
                curList.add(1);
            } else {
                lastList = curList;
                curList = new ArrayList<>();
                for (int j = 0; j < (i + 1); j++) {
                    int digit = 0;
                    if (j - 1 >= 0) {
                        digit += lastList.get(j - 1);
                    }
                    if (j < lastList.size()) {
                        digit += lastList.get(j);
                    }
                    curList.add(digit);
                }
            }
        }
        return curList;
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
        Set<Integer> set2 = new HashSet<>();
        for (int i : nums2) {
            if (set.contains(i)) {
                set2.add(i);
            }
        }
        int[] results = new int[set2.size()];
        int i = 0;
        for (Integer integer : set2) {
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

    /**
     * 第三大的数：给你一个非空数组，返回此数组中 第三大的数 。如果不存在，则返回数组中最大的数。
     * 注意，要求返回第三大的数，是指在所有不同数字中排第三大的数。
     */
    public static int thirdMax(int[] nums) {
        if (nums == null || nums.length <= 0) {
            throw new NullPointerException("nums为空");
        }
        TreeSet<Integer> set = new TreeSet<>();
        for (int num : nums) {
            set.add(num);
            if (set.size() > 3) {
                set.pollFirst();
            }
        }
        if (set.size() == 3) {
            return set.first();
        } else {
            return set.last();
        }
    }

    /**
     * 在排序数组中查找数字 I：统计一个数字在排序数组中出现的次数。
     */
    public int search(int[] nums, int target) {
        if (nums == null || nums.length <= 0) {
            return 0;
        }
        int left = 0;
        int right = nums.length - 1;
        int mid;
        int count = 0;
        while (left <= right) {
            mid = (left + right) >>> 1;
            if (nums[mid] == target) {
                count++;
                // 向左查找
                int index = mid - 1;
                while (index >= 0) {
                    if (nums[index] == target) {
                        count++;
                        index--;
                    } else {
                        break;
                    }
                }
                // 向右查找
                index = mid + 1;
                while (index < nums.length) {
                    if (nums[index] == target) {
                        count++;
                        index++;
                    } else {
                        break;
                    }
                }
                break;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return count;
    }

    /**
     * 和为s的两个数字：输入一个“递增排序”的数组和一个数字s，在数组中查找两个数，使得它们的和正好是s。如果有多对数字的和等于s，则输出任意一对即可。
     */
    public int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length <= 0) {
            return null;
        }
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int result = nums[left] + nums[right];
            if (result == target) {
                return new int[]{nums[left], nums[right]};
            } else if (result < target) {
                left++;
            } else {
                right--;
            }
        }
        return null;
    }

    /**
     * 买卖股票的最佳时机：给定一个数组 prices ，它的第i 个元素prices[i] 表示一支给定股票第 i 天的价格。
     * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
     * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int min = prices[0];
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < min) {
                min = prices[i];
            } else if (prices[i] - min > maxProfit) {
                maxProfit = prices[i] - min;
            }
        }
        return maxProfit;
    }

    /**
     * 提莫攻击：当提莫攻击艾希，艾希的中毒状态正好持续 duration 秒。
     * 正式地讲，提莫在 t 发起攻击意味着艾希在时间区间 [t, t + duration - 1]（含 t 和 t + duration - 1）处于中毒状态。
     * 如果提莫在中毒影响结束 前 再次攻击，中毒状态计时器将会 重置 ，在新的攻击之后，中毒影响将会在 duration 秒后结束。
     * 给你一个 “非递减” 的整数数组 timeSeries ，其中 timeSeries[i] 表示提莫在 timeSeries[i] 秒时对艾希发起攻击，以及一个表示中毒持续时间的整数 duration 。
     * 返回艾希处于中毒状态的 总 秒数。
     */
    public int findPoisonedDuration(int[] timeSeries, int duration) {
        if (timeSeries == null || timeSeries.length <= 0 || duration <= 0) {
            return 0;
        }
        if (timeSeries.length == 1) {
            return duration;
        }
        int sum = 0;
        for (int i = 1; i < timeSeries.length; i++) {
            if (timeSeries[i] - timeSeries[i - 1] >= duration) {
                sum += duration;
            } else {
                sum += timeSeries[i] - timeSeries[i - 1];
            }
        }
        // 加上最后一秒后的
        sum += duration;
        return sum;
    }

    /**
     * 一个数组有2n个元素，其中有n个奇数、n个偶数，数组无序，写一个算法使得奇数位置放置奇数，偶数位置放置偶数
     */
    public static void exchangeOddEven(int[] nums) {
        if (nums == null || nums.length <= 0 || (nums.length & 1) == 1) {
            throw new IllegalArgumentException("nums为空或长度不为偶数");
        }
        // 存放下标是奇数，值是偶数的下标
        LinkedList<Integer> oddList = new LinkedList<>();
        // 存放下标是偶数，值是奇数的下标
        LinkedList<Integer> evenList = new LinkedList<>();
        int temp;
        for (int i = 0; i < nums.length; i++) {
            if ((i & 1) == 1 && (nums[i] & 1) == 0) {
                // 下标是奇数，值是偶数
                if (evenList.isEmpty()) {
                    oddList.offer(i);
                } else {
                    Integer poll = evenList.poll();
                    temp = nums[i];
                    nums[i] = nums[poll];
                    nums[poll] = temp;
                }
            } else if ((i & 1) == 0 && (nums[i] & 1) == 1) {
                // 下标是偶数，值是奇数
                if (oddList.isEmpty()) {
                    evenList.offer(i);
                } else {
                    Integer poll = oddList.poll();
                    temp = nums[i];
                    nums[i] = nums[poll];
                    nums[poll] = temp;
                }
            }
        }
    }

    /**
     * 一个数组有2n个元素，其中有n个奇数、n个偶数，数组无序，写一个算法使得奇数位置放置奇数，偶数位置放置偶数
     * 空间复杂度更优
     */
    public static void exchangeOddEven2(int[] nums) {
        if (nums == null || nums.length <= 0 || (nums.length & 1) == 1) {
            throw new IllegalArgumentException("nums为空或长度不为偶数");
        }
        // 下标是奇数，值是偶数的指针
        int oddIndex = 1;
        boolean odd = false;
        // 下标是偶数，值是奇数的指针
        int evenIndex = 0;
        boolean even = false;
        int temp;
        while (oddIndex < nums.length && evenIndex < nums.length) {
            while (!odd && oddIndex < nums.length) {
                if ((nums[oddIndex] & 1) == 0) {
                    odd = true;
                } else {
                    oddIndex += 2;
                }
            }
            while (!even && evenIndex < nums.length) {
                if ((nums[evenIndex] & 1) == 1) {
                    even = true;
                } else {
                    evenIndex += 2;
                }
            }
            if (odd && even) {
                temp = nums[oddIndex];
                nums[oddIndex] = nums[evenIndex];
                nums[evenIndex] = temp;
                oddIndex += 2;
                evenIndex += 2;
                odd = false;
                even = false;
            }
        }
    }

}
