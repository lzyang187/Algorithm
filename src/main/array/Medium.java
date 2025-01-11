package main.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Medium {
    public static void main(String[] args) {
//        System.out.println(minSetSize(new int[]{3, 3, 3, 3, 5, 5, 5, 2, 2, 7}));
        int[][] matrix = {{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}};
        System.out.println(searchMatrix(matrix, 1));
//        System.out.println(maxSubArray(new int[]{-1, 0, -2}));
        System.out.println(maxValue(matrix));
    }

    /**
     * 旋转数组的最小数字：把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
     * 给你一个可能存在重复元素值的数组numbers，它原来是一个升序排列的数组，并按上述情形进行了一次旋转。请返回旋转数组的最小元素。
     * 例如，数组[3,4,5,1,2] 为 [1,2,3,4,5] 的一次旋转，该数组的最小值为 1。
     * 注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。
     */
    public static int minArray(int[] numbers) {
        if (numbers == null || numbers.length <= 0) {
            throw new IllegalArgumentException("参数错误");
        }
        if (numbers.length == 1) {
            return numbers[0];
        }
        int front = 0;
        int tail = numbers.length - 1;
        if (numbers[front] < numbers[tail]) {
            // 旋转的数量是0
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
        // Key是元素，Value是出现的次数
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(arr[i])) {
                map.put(arr[i], map.get(arr[i]) + 1);
            } else {
                map.put(arr[i], 1);
            }
        }
        List<Integer> countList = map.values().stream().sorted().collect(Collectors.toList());
        int result = 0;
        int totalCount = 0;
        for (int i = countList.size() - 1; i >= 0; i--) {
            totalCount += countList.get(i);
            result++;
            if (totalCount * 2 >= arr.length) {
                break;
            }
        }
        return result;
    }

    /**
     * 连续子数组的最大和：输入一个整型数组，数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。
     * 要求时间复杂度为O(n)。例如：
     * 输入: nums = [-2,1,-3,4,-1,2,1,-5,4]
     * 输出: 6
     * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
     */
    public static int maxSubArray(int[] nums) {
        if (nums == null || nums.length <= 0) {
            return 0;
        }
        int result = nums[0];
        int curMax = result;
        for (int i = 1; i < nums.length; i++) {
            curMax += nums[i];
            if (curMax < nums[i]) {
                // 与当前数的和还没有当前数大
                curMax = nums[i];
            }
            if (curMax > result) {
                result = curMax;
            }
        }
        return result;
    }

    /**
     * 礼物的最大价值：在一个 m*n 的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于 0）。你可以从棋盘的左上角开始拿格子里的礼物，
     * 并每次向右或者向下移动一格、直到到达棋盘的右下角。给定一个棋盘及其上面的礼物的价值，请计算你最多能拿到多少价值的礼物？
     * 动态规划的经典题目
     */
    public static int maxValue(int[][] grid) {
        if (grid == null || grid.length <= 0 || grid[0].length <= 0) {
            return 0;
        }
        // 以空间换时间，用二维数组优化时间效率。
        int m = grid.length;
        int n = grid[0].length;
        int[][] maxValue = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    maxValue[i][j] = grid[i][j];
                } else {
                    int left = 0;
                    if (j > 0) {
                        left = maxValue[i][j - 1];
                    }
                    int up = 0;
                    if (i > 0) {
                        up = maxValue[i - 1][j];
                    }
                    maxValue[i][j] = grid[i][j] + Math.max(left, up);
                }
            }
        }
        return maxValue[m - 1][n - 1];
    }

    /**
     * 矩阵中的路径：给定一个m x n 二维字符网格board 和一个字符串单词word 。如果word 存在于网格中，返回 true ；否则，返回 false 。
     * 1、路径可以从矩阵中的任意一格开始，每一步可以在矩阵中向左、右、上、下移动一格。
     * 2、如果一条路径经过了矩阵的某一格，那么该路径不能再次进入该格子。
     * 例如：包含“bfce”的路径，但不包含字符串“abfb”的路径
     * ["a","b","c","e"],
     * ["s","f","c","s"],
     * ["a","d","e","e"]
     * 回溯法的典型例子
     */
    public boolean existPath(char[][] board, String word) {
        if (board == null || board.length <= 0 || board[0].length <= 0 || word == null || word.isEmpty()) {
            return false;
        }
        // 是否已经访问过的标记
        boolean[][] visited = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (checkPath(board, word, visited, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 判断以网格的(i,j) 位置出发，能否搜索到单词word[k..]，其中word[k..] 表示字符串 word 从第 k 个字符开始的后缀子串。如果能搜索到，则返回
     * true，反之返回false。
     */
    private boolean checkPath(char[][] board, String word, boolean[][] visited, int i, int j, int k) {
        if (i < 0 || i >= board.length) {
            return false;
        }
        if (j < 0 || j >= board[0].length) {
            return false;
        }
        if (k < 0 || k >= word.length()) {
            return false;
        }
        if (visited[i][j]) {
            // 已经被访问过了
            return false;
        }
        if (board[i][j] == word.charAt(k)) {
            if (k == word.length() - 1) {
                return true;
            }
            // 开始找下一个字符
            visited[i][j] = true;
            // 向上一步
            if (checkPath(board, word, visited, i - 1, j, k + 1)) {
                return true;
            }
            // 向下一步
            if (checkPath(board, word, visited, i + 1, j, k + 1)) {
                return true;
            }
            // 向左一步
            if (checkPath(board, word, visited, i, j - 1, k + 1)) {
                return true;
            }
            // 向右一步
            if (checkPath(board, word, visited, i, j + 1, k + 1)) {
                return true;
            }
            // 上下左右都不满足，则重置这个节点的访问状态
            visited[i][j] = false;
        }
        return false;
    }

    /**
     * 机器人的运动范围：地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。一个机器人从坐标 [0, 0] 的格子开始移动，
     * 它每次可以向左、右、上、下移动一格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。
     * 例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？
     */
    public int movingCount(int m, int n, int k) {
        if (m <= 0 || n <= 0 || k < 0) {
            return 0;
        }
        boolean[][] visited = new boolean[m][n];
        return movingCountCore(m, n, k, visited, 0, 0);
    }

    /**
     * 返回从i、j位置开始能移动到的格子数量
     */
    private int movingCountCore(int m, int n, int k, boolean[][] visited, int i, int j) {
        int count = 0;
        if (checkMove(m, n, k, visited, i, j)) {
            visited[i][j] = true;
            // 向上移动
            int topCount = movingCountCore(m, n, k, visited, i - 1, j);
            // 向下移动
            int bottomCount = movingCountCore(m, n, k, visited, i + 1, j);
            // 向左移动
            int leftCount = movingCountCore(m, n, k, visited, i, j - 1);
            // 向右移动
            int rightCount = movingCountCore(m, n, k, visited, i, j + 1);
            count = 1 + topCount + bottomCount + leftCount + rightCount;
        }
        return count;
    }

    /**
     * 判断i、j位置是否满足条件
     */
    private boolean checkMove(int m, int n, int k, boolean[][] visited, int i, int j) {
        if (i < 0 || i >= m) {
            return false;
        }
        if (j < 0 || j >= n) {
            return false;
        }
        // 已经被访问过了
        if (visited[i][j]) {
            return false;
        }
        return getDigitSum(i) + getDigitSum(j) <= k;
    }

    /**
     * 计算数的数位之和
     */
    private int getDigitSum(int number) {
        int sum = 0;
        while (number > 0) {
            sum += number % 10;
            number /= 10;
        }
        return sum;
    }

}
