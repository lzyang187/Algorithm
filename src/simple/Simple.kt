package simple

import java.util.*

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
    println(stack.top())
    println(stack.pop())
    println(stack.top())
    println(stack.pop())
    println(stack.empty())
}


/**
 * 两数之和：
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出和为目标值 target  的那两个整数，
 * 并返回它们的数组下标
 */
fun twoSum(nums: IntArray?, target: Int): IntArray {
    val result = IntArray(2)
    if (nums != null) {
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
    }
    return result
}

/**
 * 整数反转：
 * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
 * 如果反转后整数超过 32 位的有符号整数的范围 [−231,  231 − 1] ，就返回 0。
 * 假设环境不允许存储 64 位整数（有符号或无符号）。
 */

fun reverse(x: Int): Int {
    val str = x.toString()
    val split = str.split("")
    if (split.isNullOrEmpty()) {
        return 0
    }
    val filter = split.filter {
        it != ""
    }
    val sb = StringBuilder()
    for (i in filter.size - 1 downTo 1) {
        if (filter[i] == "0" && sb.isEmpty()) {
            continue
        }
        sb.append(filter[i])
    }
    val result: String = if (filter[0] == "-") {
        ("-$sb")
    } else {
        sb.append(filter[0]).toString()
    }
    return result.toIntOrNull() ?: 0
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
 * 二维数组中的查找
 * 在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
 * 请完成一个高效的函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数
 */
fun findNumberIn2DArray(matrix: Array<IntArray>, target: Int): Boolean {
    if (matrix.isNullOrEmpty()) {
        return false
    }
    if (!matrix.first().isNotEmpty()) {
        return false
    }
    val mutableArray = mutableListOf<MutableList<Int>>()
    matrix.forEach {
        val subArray = mutableListOf<Int>()
        for (i in 0 until it.size) {
            subArray.add(it[i])
        }
        mutableArray.add(subArray)
    }
    return realFindNumberIn2DArray(mutableArray, target)
}

fun realFindNumberIn2DArray(matrix: MutableList<MutableList<Int>>, target: Int): Boolean {
    if (matrix.isNullOrEmpty()) {
        return false
    }
    if (!matrix.first().isNotEmpty()) {
        return false
    }
    // 找到第一行最后一个元素
    val rightTop = matrix.first().last()
    if (rightTop == target) {
        return true
    } else if (rightTop > target) {
        // 去掉最后一列
        matrix.forEach {
            it.removeAt(it.size - 1)
        }
        return realFindNumberIn2DArray(matrix, target)
    } else {
        // 去掉第一行
        matrix.removeAt(0)
        return realFindNumberIn2DArray(matrix, target)
    }
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

