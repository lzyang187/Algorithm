package main.ctrip;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 三个线程分别打印A、B、C，要求按顺序打印，不能错乱，循环打印。
 */
public class ThreeThreadPrintABC {

    private static int currentThread = 1; // 1 for A, 2 for B, 3 for C

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Condition conditionA = lock.newCondition();
        Condition conditionB = lock.newCondition();
        Condition conditionC = lock.newCondition();


        Thread threadA = new Thread(() -> {
            while (true) {
                lock.lock();
                System.out.println("threadA lock");
                try {
                    while (currentThread != 1) {
                        System.out.println("threadA before await");
                        // 调用 await() 后，线程会释放锁，并进入WAITING状态，直到被唤醒。
                        conditionA.await();
                        // 当线程被唤醒后，它会重新尝试获取锁，只有在成功获取锁后才会继续执行。
                        System.out.println("threadA after await");
                    }
                    System.out.println("A");
                    currentThread = 2;
                    // 调用 signal() 前，线程也必须持有锁。调用 signal() 不会释放锁，只是通知一个等待的线程可以准备运行。
                    conditionB.signal();
                    // 被唤醒的线程会从 await() 返回
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    lock.unlock();
                    System.out.println("threadA unlock");
                }
            }
        });

        Thread threadB = new Thread(() -> {
            while (true) {
                lock.lock();
                System.out.println("threadB lock");
                try {
                    while (currentThread != 2) {
                        conditionB.await();
                        System.out.println("threadB await");
                    }
                    System.out.println("B");
                    currentThread = 3;
                    conditionC.signal();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    lock.unlock();
                    System.out.println("threadB unlock");
                }
            }
        });

        Thread threadC = new Thread(() -> {
            while (true) {
                lock.lock();
                System.out.println("threadC lock");
                try {
                    while (currentThread != 3) {
                        conditionC.await();
                        System.out.println("threadC await");
                    }
                    System.out.println("C");
                    currentThread = 1;
                    conditionA.signal();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    lock.unlock();
                    System.out.println("threadC unlock");
                }
            }
        });

        threadA.start();
        threadB.start();
        threadC.start();
    }
}
