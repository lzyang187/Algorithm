fun main() {
    val a = intArrayOf(1, 2, 3)
    twoSum(a, 5)
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