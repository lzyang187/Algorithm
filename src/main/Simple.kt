package main

fun main() {
//    println(simple.reverse(-120))
//    val queue = TwoStackQueue()
//    println(queue.deleteHead())
//    queue.appendTail(1)
//    queue.appendTail(2)
//    println(queue.deleteHead())
//    println(queue.deleteHead())
//    println(queue.deleteHead())
    val stack = TwoQueueStack()
    stack.push(1)
    stack.push(2)
    println(stack.peek())
    println(stack.pop())
    println(stack.peek())
    println(stack.pop())
    println(stack.isEmpty())

    println(reverse(-4236469))
}


/**
 * 两数之和：
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出和为目标值 target  的那两个整数，
 * 并返回它们的数组下标
 */
fun twoSum(nums: IntArray?, target: Int): IntArray? {
    if (nums == null || nums.size <= 1) {
        return null
    }
    val result = IntArray(2)
    for (i in 0 until nums.size - 1) {
        for (j in i + 1 until nums.size) {
            if (nums[i] + nums[j] == target) {
                result[0] = i
                result[1] = j
                println(result[0])
                println(result[1])
                return result
            }
        }
    }
    return result
}

/**
 * 整数反转：
 * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
 * 如果反转后整数超过 32 位的有符号整数的范围[−2^31, 2^(31− 1)] ，就返回 0。
 * 假设环境不允许存储 64 位整数（有符号或无符号）。
 */

fun reverse(x: Int): Int {
    if (x in -9..9) {
        return x
    }
    var result = 0L
    var temp = x
    while (temp != 0) {
        result = result * 10 + temp % 10
        temp /= 10
    }
    // 判断是否越界
    if (result > Int.MAX_VALUE || result < Int.MIN_VALUE) {
        return 0
    }
    return result.toInt()
}

/**
 * 找出数组中重复的数字。
 * 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。
 * 数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字
 */
fun findRepeatNumber(nums: IntArray): Int {
    if (nums.isNotEmpty()) {
        val map = HashMap<Int, Int>()
        nums.forEach {
            if (map.containsKey(it)) {
                return it
            } else {
                map[it] = 0
            }
        }
    }
    return -1
}

/**
 * 替换空格
 * 请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
 */
fun replaceSpace(s: String): String {
    if (s.isEmpty()) {
        return s
    }
    return s.replace(" ", "%20")
}

