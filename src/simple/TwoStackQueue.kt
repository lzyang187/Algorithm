package simple

import java.util.*

/**
 * 两个栈实现一个队列
 */
class TwoStackQueue {

    private val mStack1 = Stack<Int>()
    private val mStack2 = Stack<Int>()

    fun appendTail(value: Int) {
        mStack1.push(value)
    }

    fun deleteHead(): Int {
        if (mStack2.empty()) {
            if (mStack1.empty()) {
                return -1
            } else {
                for (i in 0 until mStack1.size) {
                    mStack2.push(mStack1.pop())
                }
                return mStack2.pop()
            }
        } else {
            return mStack2.pop()
        }

    }
}