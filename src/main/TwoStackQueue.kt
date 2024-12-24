package main

import java.util.*

/**
 * 两个栈实现一个队列
 */
class TwoStackQueue {

    private val mStack1 = Stack<Int>()
    private val mStack2 = Stack<Int>()

    fun offer(value: Int) {
        mStack1.push(value)
    }

    fun poll(): Int? {
        if (mStack2.empty()) {
            if (mStack1.empty()) {
                return null
            } else {
                // 一次全部出栈
                while (mStack1.isNotEmpty()) {
                    mStack2.push(mStack1.pop())
                }
            }
        }
        return mStack2.pop()
    }

    fun isEmpty(): Boolean {
        return mStack1.isEmpty() && mStack2.isEmpty()
    }

}