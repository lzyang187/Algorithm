package main;

/**
 * 死锁的示例。两个线程两把锁，两个线程各拿一把锁然后互相等待对方释放另一把锁。
 */
public class DieLock {

    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (lock1) {
                System.out.println("thread1 get lock1");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock2) {
                    System.out.println("thread1 get lock2");
                }
            }
        }).start();
        new Thread(() -> {
            synchronized (lock2) {
                System.out.println("thread2 get lock2");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock1) {
                    System.out.println("thread2 get lock1");
                }
            }

        }).start();
    }
}
