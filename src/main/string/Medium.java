package main.string;

import java.util.HashMap;
import java.util.Map;

public class Medium {
    public static void main(String[] args) {
//        System.out.println(lengthOfLongestSubstring("dvdf"));
        long time = System.currentTimeMillis();
        System.out.println(longestPalindrome("zudfweormatjycujjirzjpyrmaxurectxrtqedmmgergwdvjmjtstdhcihacqnothgttgqfywcpgnuvwglvfiuxteopoyizgehkwuvvkqxbnufkcbodlhdmbqyghkojrgokpwdhtdrwmvdegwycecrgjvuexlguayzcammupgeskrvpthrmwqaqsdcgycdupykppiyhwzwcplivjnnvwhqkkxildtyjltklcokcrgqnnwzzeuqioyahqpuskkpbxhvzvqyhlegmoviogzwuiqahiouhnecjwysmtarjjdjqdrkljawzasriouuiqkcwwqsxifbndjmyprdozhwaoibpqrthpcjphgsfbeqrqqoqiqqdicvybzxhklehzzapbvcyleljawowluqgxxwlrymzojshlwkmzwpixgfjljkmwdtjeabgyrpbqyyykmoaqdambpkyyvukalbrzoyoufjqeftniddsfqnilxlplselqatdgjziphvrbokofvuerpsvqmzakbyzxtxvyanvjpfyvyiivqusfrsufjanmfibgrkwtiuoykiavpbqeyfsuteuxxjiyxvlvgmehycdvxdorpepmsinvmyzeqeiikajopqedyopirmhymozernxzaueljjrhcsofwyddkpnvcvzixdjknikyhzmstvbducjcoyoeoaqruuewclzqqqxzpgykrkygxnmlsrjudoaejxkipkgmcoqtxhelvsizgdwdyjwuumazxfstoaxeqqxoqezakdqjwpkrbldpcbbxexquqrznavcrprnydufsidakvrpuzgfisdxreldbqfizngtrilnbqboxwmwienlkmmiuifrvytukcqcpeqdwwucymgvyrektsnfijdcdoawbcwkkjkqwzffnuqituihjaklvthulmcjrhqcyzvekzqlxgddjoir"));
        System.out.println(System.currentTimeMillis() - time);
        time = System.currentTimeMillis();
        System.out.println(longestPalindromeQuick("zudfweormatjycujjirzjpyrmaxurectxrtqedmmgergwdvjmjtstdhcihacqnothgttgqfywcpgnuvwglvfiuxteopoyizgehkwuvvkqxbnufkcbodlhdmbqyghkojrgokpwdhtdrwmvdegwycecrgjvuexlguayzcammupgeskrvpthrmwqaqsdcgycdupykppiyhwzwcplivjnnvwhqkkxildtyjltklcokcrgqnnwzzeuqioyahqpuskkpbxhvzvqyhlegmoviogzwuiqahiouhnecjwysmtarjjdjqdrkljawzasriouuiqkcwwqsxifbndjmyprdozhwaoibpqrthpcjphgsfbeqrqqoqiqqdicvybzxhklehzzapbvcyleljawowluqgxxwlrymzojshlwkmzwpixgfjljkmwdtjeabgyrpbqyyykmoaqdambpkyyvukalbrzoyoufjqeftniddsfqnilxlplselqatdgjziphvrbokofvuerpsvqmzakbyzxtxvyanvjpfyvyiivqusfrsufjanmfibgrkwtiuoykiavpbqeyfsuteuxxjiyxvlvgmehycdvxdorpepmsinvmyzeqeiikajopqedyopirmhymozernxzaueljjrhcsofwyddkpnvcvzixdjknikyhzmstvbducjcoyoeoaqruuewclzqqqxzpgykrkygxnmlsrjudoaejxkipkgmcoqtxhelvsizgdwdyjwuumazxfstoaxeqqxoqezakdqjwpkrbldpcbbxexquqrznavcrprnydufsidakvrpuzgfisdxreldbqfizngtrilnbqboxwmwienlkmmiuifrvytukcqcpeqdwwucymgvyrektsnfijdcdoawbcwkkjkqwzffnuqituihjaklvthulmcjrhqcyzvekzqlxgddjoir"));
        System.out.println(System.currentTimeMillis() - time);
//        System.out.println(longestCommonSubsequence("abcde", "ace"));
    }

    /**
     * 无重复字符的最长子串：给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
     */
    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        int maxLength = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            int indexOf = sb.toString().indexOf(s.charAt(i));
            if (indexOf >= 0) {
                maxLength = Math.max(maxLength, sb.length());
                // 将与这个字符相同及之前的字符都移除
                sb.delete(0, indexOf + 1);
            }
            sb.append(s.charAt(i));
        }
        maxLength = Math.max(maxLength, sb.length());
        return maxLength;
    }

    /**
     * 最长回文子串：给你一个字符串 s，找到 s 中最长的回文子串。如果字符串的反序与原始字符串相同，则该字符串称为回文字符串。
     * 输入：s = "babad"
     * 输出："bab"
     * 解释："aba" 同样是符合题意的答案。
     */
    public static String longestPalindrome(String s) {
        if (s == null || s.isEmpty()) {
            return null;
        }
        if (isPalindromeStr(s)) {
            return s;
        } else {
            Map<String, String> map = new HashMap<>();
            return longestPalindrome(s, map);
        }
    }

    /**
     * 自上向下递归，相对耗时
     */
    public static String longestPalindrome(String s, Map<String, String> map) {
        if (s == null || s.isEmpty()) {
            return null;
        }
        if (isPalindromeStr(s)) {
            return s;
        } else {
            String head = s.substring(0, s.length() - 1);
            String tail = s.substring(1);
            String headP;
            if (map.containsKey(head)) {
                headP = map.get(head);
            } else {
                headP = longestPalindrome(head, map);
                map.put(head, headP);
            }
            String tailP;
            if (map.containsKey(tail)) {
                tailP = map.get(tail);
            } else {
                tailP = longestPalindrome(tail, map);
                map.put(tail, tailP);
            }
            int headLen = headP != null ? headP.length() : 0;
            int tailLen = tailP != null ? tailP.length() : 0;
            if (headLen > tailLen) {
                return headP;
            }
            return tailP;
        }
    }

    public static boolean isPalindromeStr(String s) {
        if (s == null || s.isEmpty()) {
            return false;
        }
        if (s.length() == 1) {
            return true;
        }
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    /**
     * 最长回文子串：动态规划，分解子问题，如果i到j是回文串并且i-1和j+1相同，则i-1到j+1也是回文串
     */
    public static String longestPalindromeQuick(String s) {
        if (s == null || s.isEmpty()) {
            return null;
        }
        if (s.length() == 1) {
            // 只有一个字符
            return s;
        }
        // 表示从i到j的字串是否是回文串
        boolean[][] dp = new boolean[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            // 长度为1的子串都是回文串
            dp[i][i] = true;
        }
        // 最长回文字串的长度
        int maxLen = 1;
        // 最长回文字串的起始位置
        int startIndex = 0;
        // 从下往上求解
        for (int L = 2; L <= s.length(); L++) {
            // 相当于滑动窗口
            for (int i = 0; i < s.length(); i++) {
                int j = L + i - 1;
                if (j >= s.length()) {
                    // 超出边界了
                    break;
                }
                if (s.charAt(i) != s.charAt(j)) {
                    dp[i][j] = false;
                } else {
                    if (L <= 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }
                if (dp[i][j] && L > maxLen) {
                    // 更新值
                    maxLen = L;
                    startIndex = i;
                }
            }
        }
        return s.substring(startIndex, startIndex + maxLen);
    }

    /**
     * 最长公共子序列：给定两个字符串 text1 和 text2，返回这两个字符串的最长公共子序列的长度。如果不存在公共子序列 ，返回 0 。
     * 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
     * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
     * 两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。
     * 示例 ：
     * 输入：text1 = "abcde", text2 = "ace"
     * 输出：3
     * 解释：最长公共子序列是 "ace" ，它的长度为 3 。
     * 动态规划法
     */
    public static int longestCommonSubsequence(String text1, String text2) {
        if (text1 == null || text1.isEmpty() || text2 == null || text2.isEmpty()) {
            return 0;
        }
        int m = text1.length();
        int n = text2.length();
        // dp[i][j]表示text1从0到i 和 text2从0到j的最长公共子序列长度
        int[][] dp = new int[m + 1][n + 1];
        // 长度从1开始
        for (int i = 1; i <= m; i++) {
            char c1 = text1.charAt(i - 1);
            for (int j = 1; j <= n; j++) {
                char c2 = text2.charAt(j - 1);
                if (c1 == c2) {
                    // 这两个字符相同，是公共字符
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    // 这两个字符不同，取两项中的长度较大的一项
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }

}
