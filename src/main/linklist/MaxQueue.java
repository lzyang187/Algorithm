package main.linklist;

import java.util.Collections;
import java.util.LinkedList;

/**
 * 队列的最大值：请定义一个队列并实现函数 max_value 得到队列里的最大值，要求函数max_value、push_back 和 pop_front 的均摊时间复杂度都是O(1)。
 * 若队列为空，pop_front 和 max_value需要返回 -1
 */
public class MaxQueue {

    private final LinkedList<Integer> mDataQueue;
    private final LinkedList<Integer> mMaxQueue;

    public MaxQueue() {
        mDataQueue = new LinkedList<>();
        mMaxQueue = new LinkedList<>();
    }

    public int max_value() {
        Integer peekFirst = mMaxQueue.peekFirst();
        return peekFirst != null ? peekFirst : -1;
    }

    public void push_back(int value) {
        mDataQueue.offer(value);
        if (mMaxQueue.isEmpty()) {
            mMaxQueue.offer(value);
        } else {
            if (value > max_value()) {
                // 前面所有值都改为value
                Collections.fill(mMaxQueue, value);
            } else {
                // 找到第一个比value小的值，其及之后的值都改为value
                for (int i = 1; i < mMaxQueue.size(); i++) {
                    if (mMaxQueue.get(i) < value) {
                        mMaxQueue.set(i, value);
                    }
                }
            }
            mMaxQueue.offer(value);
        }
    }

    public int pop_front() {
        if (mDataQueue.isEmpty()) {
            return -1;
        }
        mMaxQueue.pop();
        return mDataQueue.pop();
    }
}
