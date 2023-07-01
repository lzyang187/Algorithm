package main.linklist;

import java.util.ArrayList;

public class Hard {
    public static void main(String[] args) {
        System.out.println(lastRemaining(5, 3));
    }

    /**
     * 圆圈中最后剩下的数字：0,1,···,n-1这n个数字排成一个圆圈，从数字0开始，每次从这个圆圈里删除第m个数字（删除后从下一个数字开始计数）。
     * 求出这个圆圈里剩下的最后一个数字。
     * 例如，0、1、2、3、4这5个数字组成一个圆圈，从数字0开始每次删除第3个数字，则删除的前4个数字依次是2、0、4、1，因此最后剩下的数字是3。
     */
    public static int lastRemaining(int n, int m) {
        if (n <= 0 || m <= 0) {
            throw new IllegalArgumentException("参数不合法");
        }
        ArrayList<Integer> queue = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            queue.add(i);
        }
        int idx = 0;
        while (n > 1) {
            idx = (idx + m - 1) % n;
            queue.remove(idx);
            n--;
        }
        return queue.get(0);
    }

}
