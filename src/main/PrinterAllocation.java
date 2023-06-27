package main;

import java.util.Arrays;

/**
 * 20个打印任务分配给3台打印机时间最优
 */
public class PrinterAllocation {
    public static void main(String[] args) {
        int[] fileTimes = {10, 5, 8, 12, 6, 7, 9, 11, 15, 13, 4, 3, 14, 16, 20, 18, 17, 19, 2, 1};
        int[] printerTimes = new int[3];
        Arrays.sort(fileTimes);
        for (int i = fileTimes.length - 1; i >= 0; i--) {
            int minIndex = getMinIndex(printerTimes);
            printerTimes[minIndex] += fileTimes[i];
        }
        System.out.println("Printer 1 time: " + printerTimes[0]);
        System.out.println("Printer 2 time: " + printerTimes[1]);
        System.out.println("Printer 3 time: " + printerTimes[2]);
    }

    private static int getMinIndex(int[] arr) {
        int minIndex = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[minIndex]) {
                minIndex = i;
            }
        }
        return minIndex;
    }
}
