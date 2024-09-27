package org.kiyotoko.calc4j.value

import java.math.BigDecimal
import java.math.BigInteger
import java.math.MathContext

data class Integer(private val value: BigInteger): Value {

    constructor(value: Long): this(BigInteger.valueOf(value))

    constructor(value: String): this(BigInteger(value))

    override fun plus(that: Integer): Integer {
        return Integer(this.value + that.value)
    }

    override fun plus(that: Fraction): Fraction {
        return Fraction(this * that.getDenominator() + that.getNumerator(), that.getDenominator())
    }

    override fun plus(that: Real): Real {
        return Real(BigDecimal(value) + that.value())
    }

    override fun plus(that: Variable): Value {
        return Variable { this + that.signature.signature() }
    }

    override fun minus(that: Integer): Integer {
        return Integer(this.value - that.value)
    }

    override fun minus(that: Fraction): Fraction {
        return Fraction(this * that.getDenominator() - that.getNumerator(), that.getDenominator())
    }

    override fun minus(that: Real): Value {
        return Real(BigDecimal(value) - that.value())
    }

    override fun minus(that: Variable): Value {
        return Variable { this - that.signature.signature() }
    }

    override fun times(that: Integer): Integer {
        return Integer(value * that.value)
    }

    override fun times(that: Fraction): Value {
        return Fraction(this * that.getNumerator(), that.getDenominator()).shortened()
    }

    override fun times(that: Real): Real {
        return Real(BigDecimal(value) * that.value())
    }

    override fun times(that: Variable): Value {
        return Variable { this * that.signature.signature() }
    }

    override fun div(that: Integer): Value {
        return Fraction(this, that).shortened()
    }

    override fun div(that: Fraction): Value {
        return times(that.invert())
    }

    override fun div(that: Real): Value {
        return Real(BigDecimal(value).divide(that.value(), MathContext.DECIMAL128))
    }

    override fun div(that: Variable): Value {
        return Variable { this / that.signature.signature() }
    }

    operator fun compareTo(that: Integer): Int {
        return value.compareTo(that.value)
    }

    override fun value(): BigInteger {
        return value
    }

    override fun pretty(): String {
        return value.toString()
    }
}