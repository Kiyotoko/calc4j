package org.kiyotoko.calc4j.value

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class TestFraction {
    private val a = Fraction(1L, 2L)
    private val b = Fraction(1L, 3L)
    private val c = Integer(2L)

    @Test
    fun add() {
        Assertions.assertEquals(Fraction(5L, 6L), a + b)
        Assertions.assertEquals(Fraction(5L, 2L), a + c)
        Assertions.assertNotEquals(Integer(2L), a + b + c)
    }

    @Test
    fun sub() {
        Assertions.assertEquals(Fraction(1L, 6L), a - b)
        Assertions.assertEquals(Fraction(-3L, 2L), a - c)
        Assertions.assertNotEquals(Integer(2L), a - b - c)
    }

    @Test
    fun mul() {
        Assertions.assertEquals(Fraction(1L, 6L), a * b)
        Assertions.assertEquals(Integer(1L), a * c)
        Assertions.assertNotEquals(Integer(2L), a * b * c)
    }

    @Test
    fun div() {
        Assertions.assertEquals(Fraction(3L, 2L), a / b)
        Assertions.assertEquals(Fraction(1L, 4L), a / c)
        Assertions.assertNotEquals(Integer(2L), a / b / c)
    }

    @Test
    fun value() {
        Assertions.assertEquals(Real(0.333333).value().toFloat(), b.value().toFloat(), 0.01f)
        Assertions.assertEquals(0.5.toBigDecimal(), a.value())
    }
}
