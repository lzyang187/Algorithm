package main

import java.util.*

/**
 * 两个队列实现一个栈
 */
class TwoQueueStack {

    private val mQueue1 = LinkedList<Int>()
    private val mQueue2 = LinkedList<Int>()

    fun push(x: Int) {
        if (mQueue1.isEmpty()) {
            mQueue2.offer(x)
        } else {
            mQueue1.offer(x)
        }
    }

    fun pop(): Int? {
        if (mQueue1.isEmpty()) {
            if (mQueue2.isEmpty()) {
                return null
            } else {
                for (i in 0 until mQueue2.size - 1) {
                    mQueue1.offer(mQueue2.poll())
                }
                return mQueue2.poll()
            }
        } else {
            for (i in 0 until mQueue1.size - 1) {
                mQueue2.offer(mQueue1.poll())
            }
            return mQueue1.poll()
        }
    }

    fun peek(): Int? {
        if (mQueue1.isEmpty()) {
            if (mQueue2.isEmpty()) {
                return null
            } else {
                for (i in 0 until mQueue2.size - 1) {
                    mQueue1.offer(mQueue2.poll())
                }
                val top = mQueue2.poll()
                mQueue1.offer(top)
                return top
            }
        } else {
            for (i in 0 until mQueue1.size - 1) {
                mQueue2.offer(mQueue1.poll())
            }
            val top = mQueue1.poll()
            mQueue2.offer(top)
            return top
        }
    }

    fun isEmpty(): Boolean {
        return mQueue1.isEmpty() && mQueue2.isEmpty()
    }
}