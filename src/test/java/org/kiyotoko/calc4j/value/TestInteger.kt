package org.kiyotoko.calc4j.value

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class TestInteger {

    @Test
    fun add() {
        val a = Integer(43L)
        val b = Integer(12L)
        val c = Integer(-25L)

        Assertions.assertEquals(Integer(55L), a + b)
        Assertions.assertEquals(Integer(18L), a + c)
        Assertions.assertNotEquals(Integer(2L), a + b + c)
    }

    @Test
    fun sub() {
        val a = Integer(43L)
        val b = Integer(12L)
        val c = Integer(-25L)

        Assertions.assertEquals(Integer(31L), a - b)
        Assertions.assertEquals(Integer(68L), a - c)
        Assertions.assertNotEquals(Integer(2L), a - b - c)
    }

    @Test
    fun mul() {
        val a = Integer(2L)
        val b = Integer(4L)
        val c = Integer(5L)

        Assertions.assertEquals(Integer(8L), a * b)
        Assertions.assertEquals(Integer(10L), a * c)
        Assertions.assertNotEquals(Integer(2L), a * b * c)
    }

    @Test
    fun div() {
        val a = Integer(2L)
        val b = Integer(4L)
        val c = Integer(5L)

        Assertions.assertEquals(Fraction(1L, 2L), a / b)
        Assertions.assertEquals(Fraction(2L, 5L), a / c)
        Assertions.assertNotEquals(Integer(2L), a / b / c)
    }
}