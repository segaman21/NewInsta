package com.example.myapplication

import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 *
 *
 * [1, 2, 4, 5, 6, 8, 10]
 * [2, 3, 5, 7, 9, 10]
 *
 * MyIterator
 *
 *
 * составить функцию которая примет два этих input'а и найдёт все числа в первом множестве, которых нет во втором
 *
 * 1) [1, 2, 4, 5, 6, 8, 10]
 *    [2, 3, 5, 7, 9, 10]
 *
 * 2) []
 *    [1, 2]
 *
 * 3) [1, 2]
 *    []
 *
 * 4) [1, 2]
 *    [1, 2]
 *
 * 5) [ ]
 *    [ ]
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        val list1 = listOf(1, 2, 4, 5, 6, 8, 10)
        val list2 = listOf(2, 3, 5, 7, 9, 10)
        println(findUnique(list1.listIterator(), list2.listIterator()))

//        println(list1.subtract(list2))

    }

    private fun findUnique(list1: Iterator<Int>, list2: Iterator<Int>): List<Int> {
        val uniqueNumbers = mutableListOf<Int>()
        var num1: Int? = null
        var num2: Int? = null

        if (list1.hasNext()) {
            num1 = list1.next()
        }
        if (list2.hasNext()) {
            num2 = list2.next()
        }

        while (num1 != null) {
            if (num2 == null || num1 < num2) {
                uniqueNumbers.add(num1)
                num1 = if (list1.hasNext()) {
                    list1.next()
                } else {
                    null
                }
            } else if (num1 > num2) {
                num2 = if (list2.hasNext()) {
                    list2.next()
                } else {
                    null
                }
            } else {
                num1 = if (list1.hasNext()) {
                    list1.next()
                } else {
                    null
                }
                num2 = if (list2.hasNext()) {
                    list2.next()
                } else {
                    null
                }
            }
        }

        return uniqueNumbers
    }
}
