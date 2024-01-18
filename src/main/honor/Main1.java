package main.honor;

import java.util.*;

/**
 * 明日之星投票选举
 */
public class Main1 {
    public static final String ERROR = "error.0001";

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String votes = in.next();
        String star = star(votes);
        System.out.println(star);
    }

    public static String star(String votes) {
        if (votes == null || votes.isEmpty()) {
            return ERROR;
        }
        try {
            String[] split = votes.split(",");
            if (split.length == 0) {
                return ERROR;
            }
            HashMap<String, Integer> map = new HashMap<>();
            for (String s : split) {
                if (s.isEmpty()) {
                    return ERROR;
                }
                // 判断大小写
                for (int i = 0; i < s.length(); i++) {
                    char c = s.charAt(i);
                    if (i == 0) {
                        if (c > 'Z' || c < 'A') {
                            return ERROR;
                        }
                    } else {
                        if (c > 'z' || c < 'a') {
                            return ERROR;
                        }
                    }
                }
                if (map.containsKey(s)) {
                    Integer value = map.get(s);
                    map.put(s, ++value);
                } else {
                    map.put(s, 1);
                }
            }
            int maxValue = 0;
            List<String> list = new ArrayList<>();
            for (String s : map.keySet()) {
                if (map.get(s) > maxValue) {
                    maxValue = map.get(s);
                    list.clear();
                    list.add(s);
                } else if (map.get(s) == maxValue) {
                    list.add(s);
                }
            }
            if (list.size() > 1) {
                Collections.sort(list);
            }
            return list.get(0);
        } catch (Exception e) {
            return ERROR;
        }
    }
}
