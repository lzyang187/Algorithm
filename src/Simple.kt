fun main() {
    println(reverse(-120))
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