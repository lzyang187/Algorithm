package simple.string;

public class Simple {
    public static void main(String[] args) {

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
        char[] num1Chars = new char[num1.length()];
        for (int i = 0; i < num1.length(); i++) {
            num1Chars[i] = num1.charAt(num1.length() - 1 - i);
        }
        char[] num2Chars = new char[num2.length()];
        for (int i = 0; i < num2.length(); i++) {
            num2Chars[i] = num2.charAt(num2.length() - 1 - i);
        }
        // 结果数组的最大长度是较大整数的位数+1
        int maxSize = Math.max(num1Chars.length, num2Chars.length);
        int[] results = new int[maxSize + 1];
        for (int i = 0; i < maxSize; i++) {
            int result = results[i];
            if (i < num1Chars.length) {
                // 将字符转换为对应的int，'9' 转换为9
                result += (num1Chars[i] - '0');
            }
            if (i < num2Chars.length) {
                result += (num2Chars[i] - '0');
            }
            // 进位
            if (result > 9) {
                results[i + 1] = 1;
                result = result % 10;
            }
            results[i] = result;
        }
        StringBuilder sb = new StringBuilder();
        boolean flag = true;
        for (int i = results.length - 1; i >= 0; i--) {
            if (flag && results[i] == 0) {
                continue;
            }
            flag = false;
            sb.append(results[i]);
        }
        if (sb.length() == 0) {
            return "0";
        }
        return sb.toString();
    }
}
