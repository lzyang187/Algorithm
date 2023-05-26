package simple.string;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Simple {
    public static void main(String[] args) {
//        System.out.println(romanToInt("IX"));
//        System.out.println(longestCommonPrefix(new String[]{"flower", "flow", "flight"}));
//        System.out.println(addBinary("0", "0"));
//        System.out.println(addStrings("0", "0"));
//        System.out.println(lengthOfLastWord("a bb d   "));
//        System.out.println(isPalindrome("A man, a plan, a canal: Panama"));
//        System.out.println(convertToExcelTitle(53));
//        System.out.println(excelTitleToNumber("ZY"));
//        char[] chars = {'a', 'b', 'c'};
//        reverseString(chars);
//        for (int i = 0; i < chars.length; i++) {
//            System.out.print(chars[i]);
//        }
//        System.out.println(isPalindromeDigit(-121));
//        System.out.println(backspaceCompare("ab#cd", "ad#c"));
//        System.out.println(makeGood("aAbbbCcDd"));
//        System.out.println(isIsomorphic("badc", "baba"));
//        System.out.println(repeatedSubstringPattern("babbabbabbabbabbac"));
//        System.out.println(reverseWords(" "));
        System.out.println(reverseLeftWords("abcdefg", 2));
    }

    /**
     * 给定两个字符串形式的非负整数num1和num2，计算它们的和并同样以字符串形式返回。
     * 你不能使用任何內建的用于处理大整数的库（比如 BigInteger），也不能直接将输入的字符串转换为整数形式。
     * 考察的是大数加法
     */
    public static String addStrings(String num1, String num2) {
        if (num1 == null || num1.isEmpty()) {
            return num2;
        } else if (num2 == null || num2.isEmpty()) {
            return num1;
        }
        if (num1.length() > num2.length()) {
            // num2前面用0补齐
            StringBuilder sb = new StringBuilder(num2);
            for (int i = 0; i < (num1.length() - num2.length()); i++) {
                sb.insert(0, '0');
            }
            num2 = sb.toString();
        } else if (num2.length() > num1.length()) {
            // num1前面用0补齐
            StringBuilder sb = new StringBuilder(num1);
            for (int i = 0; i < (num2.length() - num1.length()); i++) {
                sb.insert(0, '0');
            }
            num1 = sb.toString();
        }
        StringBuilder sb = new StringBuilder();
        int last = 0;
        for (int i = num1.length() - 1; i >= 0; i--) {
            int digit1 = Character.digit(num1.charAt(i), 10);
            int digit2 = Character.digit(num2.charAt(i), 10);
            last = last + digit1 + digit2;
            // 进位
            if (last > 9) {
                sb.insert(0, last % 10);
                last = 1;
            } else {
                sb.insert(0, last);
                last = 0;
            }
        }
        if (last > 0) {
            sb.insert(0, last);
        }
        return sb.toString();
    }

    /**
     * 罗马数字转整数：罗马数字包含以下七种字符: I， V， X， L，C，D 和 M。
     */
    public static int romanToInt(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        int length = s.length();
        int count = 0;
        for (int i = 0; i < length - 1; i++) {
            int left = getRomanDigit(s.charAt(i));
            int right = getRomanDigit(s.charAt(i + 1));
            if (left < right) {
                count -= left;
            } else {
                count += left;
            }
        }
        count += getRomanDigit(s.charAt(length - 1));
        return count;
    }

    public static int getRomanDigit(char romanChar) {
        switch (romanChar) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                return 0;
        }
    }

    /**
     * 最长公共前缀：查找字符串数组中的最长公共前缀。如果不存在公共前缀，返回空字符串 ""。
     */
    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length <= 0) {
            return "";
        }
        char preChar;
        int preIndex = 0;
        int i = 0;
        while (true) {
            if (preIndex >= strs[0].length()) {
                if (preIndex <= 0) {
                    return "";
                }
                return strs[0].substring(0, preIndex);
            }
            preChar = strs[0].charAt(preIndex);
            for (i = 1; i < strs.length; i++) {
                if (preIndex >= strs[i].length()) {
                    return strs[0].substring(0, preIndex);
                }
                if (strs[i].charAt(preIndex) != preChar) {
                    return strs[0].substring(0, preIndex);
                }
            }
            preIndex++;
        }
    }

    /**
     * 二进制求和：给你两个二进制字符串 a 和 b ，以二进制字符串的形式返回它们的和。
     */
    public static String addBinary(String a, String b) {
        if (a == null || a.isEmpty()) {
            return b;
        } else if (b == null || b.isEmpty()) {
            return a;
        }
        // 用0补齐，使两个字符串长度一致
        if (a.length() > b.length()) {
            int range = a.length() - b.length();
            for (int i = 0; i < range; i++) {
                b = '0' + b;
            }
        } else if (b.length() > a.length()) {
            int range = b.length() - a.length();
            for (int i = 0; i < range; i++) {
                a = '0' + a;
            }
        }
        StringBuilder sb = new StringBuilder();
        int length = a.length();
        int last = 0;
        for (int i = length - 1; i >= 0; i--) {
            last = last + (a.charAt(i) - '0') + (b.charAt(i) - '0');
            if (last <= 1) {
                sb.append(last);
                last = 0;
            } else if (last == 2) {
                sb.append('0');
                last = 1;
            } else {
                sb.append('1');
                last = 1;
            }
        }
        if (last > 0) {
            sb.append(last);
        }
        return sb.reverse().toString();
    }

    /**
     * 最后一个单词的长度：给你一个字符串 s，由若干单词组成，单词前后用一些空格字符隔开。返回字符串中 最后一个 单词的长度。
     */
    public static int lengthOfLastWord(String s) {
        if (s == null || s.length() <= 0) {
            return 0;
        }
        s = s.trim();
        int i = s.lastIndexOf(" ");
        return s.length() - 1 - i;
    }

    /**
     * 验证回文串：如果在将所有大写字符转换为小写字符、并移除所有非字母数字字符之后，短语正着读和反着读都一样。则可以认为该短语是一个 回文串 。
     * 字母和数字都属于字母数字字符。给你一个字符串 s，如果它是 回文串 ，返回 true ；否则，返回 false 。
     */
    public static boolean isPalindrome(String s) {
        if (s == null || s.isEmpty()) {
            return false;
        }
        s = s.toLowerCase();
        StringBuilder sb = new StringBuilder();
        // 执行移除
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if ((c >= '0' && c <= '9') || (c >= 'a' && c <= 'z')) {
                sb.append(c);
            }
        }
        int left = 0;
        int right = sb.length() - 1;
        while (left < right) {
            if (sb.charAt(left) != sb.charAt(right)) {
                break;
            }
            left++;
            right--;
        }
        return right <= left;
    }

    /**
     * 回文数：给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。
     * 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
     */
    public static boolean isPalindromeDigit(int x) {
        String s = String.valueOf(x);
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
     * Excel表列名称：给你一个整数 columnNumber ，返回它在 Excel 表中相对应的列名称。
     */
    public static String convertToExcelTitle(int columnNumber) {
        if (columnNumber <= 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int d;
        do {
            d = columnNumber % 26;
            if (d == 0) {
                sb.append('Z');
                // 因为Z是26，所以需要-1
                columnNumber = columnNumber / 26 - 1;
            } else {
                sb.append((char) ('A' + d - 1));
                columnNumber /= 26;
            }
        } while ((columnNumber > 0));
        return sb.reverse().toString();
    }

    /**
     * Excel 表列序号：给你一个字符串 columnTitle ，表示 Excel 表格中的列名称。返回 该列名称对应的列序号 。
     */
    public static int excelTitleToNumber(String columnTitle) {
        if (columnTitle == null || columnTitle.isEmpty()) {
            return 0;
        }
        int pow = 1;
        int result = 0;
        for (int i = columnTitle.length() - 1; i >= 0; i--) {
            int c = columnTitle.charAt(i) - 'A' + 1;
            result = result + c * pow;
            pow *= 26;
        }
        return result;
    }

    /**
     * 反转字符串：编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 s 的形式给出。
     * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
     */
    public static void reverseString(char[] s) {
        if (s == null || s.length <= 1) {
            return;
        }
        char temp;
        int left = 0;
        int right = s.length - 1;
        while (left < right) {
            temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            left++;
            right--;
        }
    }


    /**
     * 比较含退格的字符串：给定 s 和 t 两个字符串，当它们分别被输入到空白的文本编辑器后，如果两者相等，返回 true 。# 代表退格字符。
     * 注意：如果对空文本输入退格字符，文本继续为空。
     */
    public static boolean backspaceCompare(String s, String t) {
        if (s == null || s.isEmpty()) {
            return t == null || t.isEmpty();
        }
        if (t == null || t.isEmpty()) {
            return false;
        }
        StringBuilder s1 = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '#') {
                if (s1.length() > 0) {
                    s1.delete(s1.length() - 1, s1.length());
                }
            } else {
                s1.append(s.charAt(i));
            }
        }
        StringBuilder s2 = new StringBuilder();
        for (int i = 0; i < t.length(); i++) {
            if (t.charAt(i) == '#') {
                if (s2.length() > 0) {
                    s2.delete(s2.length() - 1, s2.length());
                }
            } else {
                s2.append(t.charAt(i));
            }
        }
        return s1.toString().equals(s2.toString());
    }

    /**
     * 整理字符串：给你一个由大小写英文字母组成的字符串 s 。
     * 一个整理好的字符串中，两个相邻字符 s[i] 和 s[i+1]，其中 0<= i <= s.length-2 ，要满足如下条件:
     * 若 s[i] 是小写字符，则 s[i+1] 不可以是相同的大写字符。
     * 若 s[i] 是大写字符，则 s[i+1] 不可以是相同的小写字符。
     * 请你将字符串整理好，每次你都可以从字符串中选出满足上述条件的 两个相邻 字符并删除，直到字符串整理好为止。
     * 请返回整理好的 字符串 。题目保证在给出的约束条件下，测试样例对应的答案是唯一的。
     * 注意：空字符串也属于整理好的字符串，尽管其中没有任何字符。
     */
    public static String makeGood(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (sb.length() <= 0) {
                sb.append(s.charAt(i));
            } else {
                char last = sb.charAt(sb.length() - 1);
                if (Character.isLowerCase(last)) {
                    // 是小写字母
                    if ((s.charAt(i)) == (Character.toUpperCase(last))) {
                        sb.delete(sb.length() - 1, sb.length());
                    } else {
                        sb.append(s.charAt(i));
                    }
                } else {
                    // 是大写字母
                    if (Character.toLowerCase(last) == (s.charAt(i))) {
                        sb.delete(sb.length() - 1, sb.length());
                    } else {
                        sb.append(s.charAt(i));
                    }
                }
            }
        }
        return sb.toString();
    }

    /**
     * 同构字符串：给定两个字符串s和t，判断它们是否是同构的。
     * 如果s中的字符可以按某种映射关系替换得到t，那么这两个字符串是同构的。
     * 每个出现的字符都应当映射到另一个字符，同时不改变字符的顺序。不同字符不能映射到同一个字符上，相同字符只能映射到同一个字符上，字符可以映射到自己本身。
     */
    public static boolean isIsomorphic(String s, String t) {
        if (s == null || t == null) {
            throw new NullPointerException("参数为空");
        }
        if (s.length() != t.length()) {
            return false;
        }
        Map<Character, Character> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                if (map.get(s.charAt(i)) != t.charAt(i)) {
                    return false;
                }
            } else {
                if (map.containsValue(t.charAt(i))) {
                    // 说明不同字符映射到相同字符了
                    return false;
                }
                map.put(s.charAt(i), t.charAt(i));
            }
        }
        return true;
    }

    /**
     * 重复的子字符串：给定一个非空的字符串 s ，检查是否可以通过由它的一个子串重复多次构成。
     */
    public static boolean repeatedSubstringPattern(String s) {
        if (s == null || s.length() <= 0) {
            throw new NullPointerException("参数为空");
        }
        int len = 1;
        int mid = s.length() >>> 1;
        while (len <= mid) {
            String sub = s.substring(0, len);
            if (s.length() % sub.length() == 0) {
                // s的长度是sub长度的整数倍
                int i;
                for (i = len; i < s.length(); i += len) {
                    if (!sub.equals(s.substring(i, i + len))) {
                        break;
                    }
                }
                if (i == s.length()) {
                    return true;
                }
            }
            len++;
        }
        return false;
    }

    /**
     * 第一个只出现一次的字符：在字符串 s 中找出第一个只出现一次的字符。如果没有，返回一个单空格。 s 只包含小写字母。
     * 复杂度为O(n ^ 2)
     */
    public static char firstUniqChar(String s) {
        if (s == null || s.length() <= 0) {
            return ' ';
        }
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (s.substring(0, i).indexOf(c) < 0 && s.substring(i + 1).indexOf(c) < 0) {
                return c;
            }
        }
        return ' ';
    }

    /**
     * 第一个只出现一次的字符：在字符串 s 中找出第一个只出现一次的字符。如果没有，返回一个单空格。 s 只包含小写字母。
     */
    public static char firstUniqCharQuick(String s) {
        if (s == null || s.length() <= 0) {
            return ' ';
        }
        LinkedHashMap<Character, Integer> map = new LinkedHashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }
        for (Map.Entry<Character, Integer> characterIntegerEntry : map.entrySet()) {
            if (characterIntegerEntry.getValue() == 1) {
                return characterIntegerEntry.getKey();
            }
        }
        return ' ';
    }

    /**
     * 翻转单词顺序：输入一个英文句子，翻转句子中单词的顺序，但单词内字符的顺序不变。为简单起见，标点符号和普通字母一样处理。
     * 例如输入字符串"I am a student. "，则输出"student. a am I"。
     * 注意：输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括；如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
     */
    public static String reverseWords(String s) {
        if (s == null || s.length() <= 0) {
            return s;
        }
        String[] split = s.trim().split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = split.length - 1; i >= 0; i--) {
            if (split[i].trim().isEmpty()) {
                continue;
            }
            sb.append(split[i].trim());
            if (i > 0) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    /**
     * 左旋转字符串：字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。请定义一个函数实现字符串左旋转操作的功能。
     * 比如，输入字符串"abcdefg"和数字2，该函数将返回左旋转两位得到的结果"cdefgab"。
     */
    public static String reverseLeftWords(String s, int n) {
        if (s == null || s.length() <= n) {
            return s;
        }
        return s.substring(n) + s.substring(0, n);
    }

}
