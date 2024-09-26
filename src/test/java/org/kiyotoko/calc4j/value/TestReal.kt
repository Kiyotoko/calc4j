package org.kiyotoko.calc4j.value

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class TestReal {
    private val a = Real(10.0)
    private val b = Fraction(5L, 2L)
    private val c = Real(-5.0)
    
    @Test
    fun add() {
        Assertions.assertEquals(Real(12.5), a + b)
        Assertions.assertEquals(Real(5.0), a + c)
        Assertions.assertNotEquals(Real(1.0), a + b + c)
    }

    @Test
    fun sub() {
        Assertions.assertEquals(Real(7.5), a - b)
        Assertions.assertEquals(Real(15.0), a - c)
        Assertions.assertNotEquals(Real(1.0), a - b - c)
    }

    @Test
    fun mul() {
        Assertions.assertEquals(Real(25.0), a * b)
        Assertions.assertEquals(Real(-50.0), a * c)
        Assertions.assertNotEquals(Real(1.0), a * b * c)
    }

    @Test
    fun div() {
        Assertions.assertEquals(Real(4.0), a / b)
        Assertions.assertEquals(Real(-2.0), a / c)
        Assertions.assertNotEquals(Real(1.0), a / b / c)
    }
}