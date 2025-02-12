package com.vimalraj.todoapplication.learning

import kotlin.time.measureTimedValue

object ProblemSet {

    val vowels = "aeiouAEIOU"
    val myStr = "Ice cream"

    //5, 2, 3, 4, 1), 8
    // Time complex O(n)
    // Space Complex O(n)
    fun twoSum(nums: IntArray, target: Int): IntArray {
        val selectedVowels = myStr.filter { it in vowels }
        println("selectedVowels $selectedVowels")
        val mystrChar = myStr.toCharArray()
        mystrChar[2] = '1'
        val myStr = String(mystrChar)
        println("myStr $myStr")

        val map = HashMap<Int, Int>()
        for (i in nums.indices) {
            val current = nums[i]
            val x = target - current
            if (map.containsKey(x)) {
                return intArrayOf(map[x] ?: 0, i)
            }
            map[current] = i
        }
        return intArrayOf(0)
    }

    // Time complex O(n^2)
    // Space Complex O(1)
    fun twoSumTimeComplex(nums: IntArray, target: Int): IntArray {
        var temp: Int
        val array = IntArray(2)
        for (i in nums.indices) {
            for (j in i + 1 until nums.size) {
                temp = nums[i] + nums[j]
                if (temp == target) {
                    array[0] = i
                    array[1] = j
                }
            }
        }
        return array
    }


    fun isPalindrome(x: Int): Boolean {
        var num = x
        var sum = 0

        while (num > 0) {
            val z = num % 10
            sum = sum * 10 + z
            num /= 10
        }
        return sum == x
    }

    fun containsDuplicate(nums: IntArray): Boolean {
        val s = HashSet<Int>()
        for (num in nums) {
            if (s.contains(num)) return true;
            s.add(num)
        }
        return false;
    }


    fun romanToInt(s: String): Int {
        val romanMap = mapOf(
            "I" to 1,
            "V" to 5,
            "X" to 10,
            "L" to 50,
            "C" to 100,
            "D" to 500,
            "M" to 1000,
        )

        var sum = 0

        val modifiedStr = s
            .replace("IV", "IIII")
            .replace("XV", "VIIII")

            .replace("XL", "XXXX")
            .replace("CL", "LXXXX")

            .replace("CD", "CCCC")
            .replace("MD", "DCCCC")

        for (char in modifiedStr) {
            sum += romanMap.getValue(char.toString())
        }

        return sum
    }


    fun largestNumberInArray() {
        val timedValue = measureTimedValue {
            val array = intArrayOf(11, 22, 99, 32, 14, 34, 5)
            var temp = array[0]

            for (i in array.indices) {
                if (temp < array[i]) {
                    temp = array[i]
                }
            }
            println("Temp: $temp")
        }
        println("Execution time: ${timedValue.duration.inWholeMilliseconds} ")
    }
}

fun String?.isNotNullAndEmpty(): Boolean {
    return this != null && this != ""
}