package main.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Hard {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(maxSlidingWindow(new int[]{9, 10, 9, -7, -4, -8, 2, -6}, 5)));
//        System.out.println(Arrays.deepToString(findContinuousSequence(9)));
    }

    /**
     * 滑动窗口的最大值：给定一个数组 nums 和滑动窗口的大小 k，请找出所有滑动窗口里的最大值。
     */
    public static int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length <= 0 || k <= 0 || k > nums.length) {
            return null;
        }
        int[] result = new int[nums.length - k + 1];
        int resultIndex = 0;
        // 存储下标的双向队列
        LinkedList<Integer> dequeue = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            if (dequeue.isEmpty()) {
                dequeue.addFirst(i);
            } else {
                if (i - dequeue.peekFirst() >= k) {
                    // 移出滑动窗口了
                    dequeue.removeFirst();
                }
                if (dequeue.isEmpty()) {
                    dequeue.addFirst(i);
                } else {
                    if (nums[i] > nums[dequeue.peekFirst()]) {
                        // 待加入的比队列中所有数都大
                        dequeue.clear();
                        dequeue.addFirst(i);
                    } else {
                        // 从尾部开始找大于待加入的数，执行插入
                        while (!dequeue.isEmpty() && nums[dequeue.peekLast()] < nums[i]) {
                            dequeue.removeLast();
                        }
                        dequeue.addLast(i);
                    }
                }
            }
            if (i >= (k - 1) && !dequeue.isEmpty()) {
                result[resultIndex] = nums[dequeue.peekFirst()];
                resultIndex++;
            }
        }
        return result;
    }

    /**
     * 和为s的连续正数序列：输入一个正整数 target ，输出所有和为 target 的连续正整数序列（至少含有两个数）。
     * 序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。
     */
    public static int[][] findContinuousSequence(int target) {
        if (target <= 2) {
            return null;
        }
        int small = 1;
        int large = 2;
        int maxLarge = (target + 1) >>> 1;
        List<int[]> list = new ArrayList<>();
        int subMaxCount = 0;
        while (small < large && large <= maxLarge) {
            int count = 0;
            for (int i = small; i <= large; i++) {
                count += i;
            }
            if (count == target) {
                int[] sub = new int[large - small + 1];
                for (int i = small; i <= large; i++) {
                    sub[i - small] = i;
                }
                subMaxCount = Math.max(subMaxCount, sub.length);
                list.add(sub);
                large++;
            } else if (count > target) {
                small++;
            } else {
                large++;
            }
        }
        int[][] result = new int[list.size()][subMaxCount];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    /**
     * 扑克牌中的顺子：从若干副扑克牌中随机抽 5 张牌，判断是不是一个顺子，即这5张牌是不是连续的。
     * 2～10为数字本身，A为1，J为11，Q为12，K为13，而大、小王为 0 ，可以看成任意数字。
     * 限制：数组长度为 5，数组的数取值为 [0, 13] .
     * 输入: [0,0,1,2,5]
     * 输出: True
     * 抽象建模能力
     */
    public static boolean isStraight(int[] nums) {
        int LENGTH = 5;
        if (nums == null || nums.length != LENGTH) {
            return false;
        }
        // 排序
        Arrays.sort(nums);
        int zeroCount = 0;
        int lostCount = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                zeroCount++;
            } else if (i > zeroCount) {
                if (nums[i] == nums[i - 1]) {
                    // 是对子
                    return false;
                }
                if (nums[i] != nums[i - 1] + 1) {
                    lostCount += (nums[i] - nums[i - 1] - 1);
                }
            }
        }
        // 0的个数 >= 空缺的个数，则是顺子
        return zeroCount >= lostCount;
    }

}
