package simple.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Medium {
    public static void main(String[] args) {
//        System.out.println(minSetSize(new int[]{3, 3, 3, 3, 5, 5, 5, 2, 2, 7}));
//        System.out.println(searchMatrix(new int[][]{{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}}, 1));
        System.out.println(maxSubArray(new int[]{-1, 0, -2}));
    }

    /**
     * 二维数组中的查找
     * 在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
     * 请完成一个高效的函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数
     */
    public static boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix == null || matrix.length <= 0 || matrix[0].length <= 0) {
            return false;
        }
        int row = 0;
        int column = matrix[0].length - 1;
        while (row < matrix.length && column >= 0) {
            if (matrix[row][column] == target) {
                return true;
            } else if (matrix[row][column] < target) {
                row++;
            } else {
                column--;
            }
        }
        return false;
    }

    /**
     * 二维数组中的查找Ⅱ：判断m x n矩阵中，是否存在一个目标值。该矩阵具有如下特性：
     * 每行中的整数从左到右按升序排列。
     * 每行的第一个整数大于前一行的最后一个整数。
     */
    public static boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length <= 0 || matrix[0].length <= 0) {
            return false;
        }
        // 一次二分查找
        int left = 0;
        int right = matrix.length * matrix[0].length - 1;
        int mid;
        // 余数
        while (left <= right) {
            mid = (left + right) >>> 1;
            // 转换为二维数组的坐标
            if (matrix[mid / matrix[0].length][mid % matrix[0].length] == target) {
                return true;
            } else if (matrix[mid / matrix[0].length][mid % matrix[0].length] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }

    /**
     * 转换二维数组：给你一个整数数组 nums 。请你创建一个满足以下条件的二维数组：
     * 二维数组应该 只 包含数组 nums 中的元素。
     * 二维数组中的每一行都包含 不同 的整数。
     * 二维数组的行数应尽可能 少 。
     * 返回结果数组。如果存在多种答案，则返回其中任何一种。
     * 请注意，二维数组的每一行上可以存在不同数量的元素。
     */
    public List<List<Integer>> findMatrix(int[] nums) {
        if (nums == null || nums.length <= 0) {
            return null;
        }
        List<List<Integer>> resultList = new ArrayList<>();
        int curIndex;
        for (int num : nums) {
            curIndex = 0;
            while (true) {
                if (curIndex >= resultList.size()) {
                    List<Integer> list = new ArrayList<>();
                    list.add(num);
                    resultList.add(list);
                    break;
                } else {
                    List<Integer> list = resultList.get(curIndex);
                    if (list.contains(num)) {
                        curIndex++;
                    } else {
                        list.add(num);
                        break;
                    }
                }
            }
        }
        return resultList;
    }

    /**
     * 数组大小减半：给你一个整数数组 arr。你可以从中选出一个整数集合，并从数组中删除集合中的数字。
     * 返回 至少 能删除数组中的一半整数的整数集合的最小大小。
     */
    public static int minSetSize(int[] arr) {
        if (arr == null || arr.length <= 0) {
            return 0;
        }
        // 先对数组进行排序
        Arrays.sort(arr);
        List<Integer> countList = new ArrayList<>();
        int curValue = arr[0];
        int count = 1;
        for (int i = 1; i < arr.length; i++) {
            if (curValue == arr[i]) {
                count++;
            } else {
                curValue = arr[i];
                countList.add(count);
                count = 1;
            }
            if (i == arr.length - 1) {
                // 最后一个元素了
                countList.add(count);
                break;
            }
        }
        // 对数量从大到小进行排序
        countList.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1 > o2) {
                    return -1;
                } else if (o1 < o2) {
                    return 1;
                }
                return 0;
            }
        });
        int totalCount = 0;
        int result = 0;
        for (Integer integer : countList) {
            totalCount += integer;
            result++;
            if (totalCount >= (arr.length >>> 1)) {
                return result;
            }
        }
        return result;
    }

    /**
     * 连续子数组的最大和：输入一个整型数组，数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。
     * 要求时间复杂度为O(n)。
     */
    public static int maxSubArray(int[] nums) {
        if (nums == null || nums.length <= 0) {
            return 0;
        }
        int lastMax = nums[0];
        int curMax = lastMax;
        for (int i = 1; i < nums.length; i++) {
            curMax += nums[i];
            if (curMax < nums[i]) {
                // 与当前数的和还没有当前数大
                curMax = nums[i];
            }
            if (curMax > lastMax) {
                lastMax = curMax;
            }
        }
        return lastMax;
    }

}
