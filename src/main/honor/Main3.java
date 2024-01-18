package main.honor;

public class Main3 {
    public static void main(String[] args) {
        System.out.println(longestSubstring("pwwkewa"));
    }

    /**
     * 3. Longest Substring Without Repeating Characters
     * 题⽬
     * Given a string, find the length of the longest substring without repeating characters.
     * 在⼀个字符串中寻找没有重复字⺟的最⻓⼦串。
     *
     * Input: "abcabcbb"
     * Output: 3
     * Explanation: The answer is "abc", with the length of 3.
     *
     * Input: "bbbbb"
     * Output: 1
     * Explanation: The answer is "b", with the length of 1.
     *
     * Input: "pwwkew"
     * Output: 3
     * Explanation: The answer is "wke", with the length of 3.
     *  Note that the answer must be a substring, "pwke" is a subsequence
     * and not a substring.
     */
    public static int longestSubstring(String s) {
        if(s == null || s.isEmpty()) {
            return 0;
        }
        int max = 0;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i =0; i< s.length(); i++) {
            int index = stringBuilder.toString().indexOf(s.charAt(i));
            if (index >= 0) {
                max = Math.max(max, stringBuilder.length());
                stringBuilder.delete(0, index + 1);
            }
            stringBuilder.append(s.charAt(i));
        }
        return Math.max(max, stringBuilder.length());
    }
}
