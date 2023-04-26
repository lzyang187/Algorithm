package simple.string;

public class Medium {
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("dvdf"));
    }

    /**
     * 无重复字符的最长子串：给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
     */
    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() <= 0) {
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

}
