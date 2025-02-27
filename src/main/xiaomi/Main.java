package main.xiaomi;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println(findMinDifference(Arrays.asList("23:59", "00:00")));
        String str = "  todAy  iS a nIcE day   ";
        char[] charArray = str.toCharArray();
        System.out.println(formatSentence(charArray));
    }

    /**
     * 最小时间差：给定一个 24 小时制（小时:分钟 "HH:MM"）的时间列表，找出列表中任意两个时间的最小时间差并以分钟数表示。
     * 输入：timePoints = ["23:59","00:00"]
     * 输出：1
     * 输入：timePoints = ["00:00","23:59","00:00"]
     * 输出：0
     */
    public static int findMinDifference(List<String> timePoints) {
        if (timePoints == null || timePoints.size() <= 1) {
            throw new IllegalArgumentException("Invalid argument");
        }
        // 保存转化为分钟数的数组
        int[] times = new int[timePoints.size()];
        for (int i = 0; i < timePoints.size(); i++) {
            String time = timePoints.get(i);
            // 这里认为字符串都符合时间要求
            times[i] = Integer.parseInt(time.substring(0, 2)) * 60 + Integer.parseInt(time.substring(3));
        }
        // 排序
        Arrays.sort(times);
        int result = times[1] - times[0];
        for (int i = 2; i < times.length; i++) {
            result = Math.min(result, times[i] - times[i - 1]);
        }
        // 还需要判断第一个和最后一个时间的间隔
        result = Math.min(result, times[0] + 24 * 60 - times[times.length - 1]);
        return result;
    }

    /**
     * 格式化字符数组。英文句子由空格、大写英文字母、小写英文字母组成。将其中的每个单词首字母大写，其他字母小写，首个单词之前没有空格，每个单子之间最多有一个空格，其余空格都移到末尾。
     * 要求时间复杂度O(n)，空间复杂度为O(1)
     * 示例
     * 输入"  todAy  iS a nIcE day   "，输出"Today Is A Nice Day      "
     */
    public static char[] formatSentence(char[] chars) {
        if (chars == null || chars.length <= 1) {
            return chars;
        }
        // 当前处理到的索引
        int curIndex = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != ' ') {
                if (curIndex == 0 || chars[curIndex - 1] == ' ') {
                    // 是单词的首字母
                    chars[curIndex] = Character.toUpperCase(chars[i]);
                } else {
                    chars[curIndex] = Character.toLowerCase(chars[i]);
                }
                curIndex++;
                if (i < chars.length - 1 && chars[i + 1] == ' ') {
                    // 如果下个字符是空格，则在这个单词后面添加一个空格
                    chars[curIndex] = ' ';
                    curIndex++;
                }
            }
        }
        // 需要将之后的字符变成空格
        for (int i = curIndex; i < chars.length; i++) {
            chars[i] = ' ';
        }
        return chars;
    }
}
