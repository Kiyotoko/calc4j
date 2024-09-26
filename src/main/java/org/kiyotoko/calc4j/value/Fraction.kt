package org.kiyotoko.calc4j.value

import java.math.BigDecimal
import java.math.MathContext

data class Fraction(private val numerator: Integer, private val denominator: Integer): Value {

    constructor(numerator: Long, denominator: Long) : this(Integer(numerator), Integer(denominator))

    override fun plus(that: Fraction): Value {
        return Fraction(numerator * that.denominator + denominator * that.numerator, denominator * that.denominator).shortened()
    }

    override fun plus(that: Real): Value {
        return Real(value() + that.value())
    }

    override fun plus(that: Integer): Value {
        return Fraction(numerator + denominator * that, denominator).shortened()
    }

    override fun minus(that: Fraction): Value {
        return Fraction(numerator * that.denominator - denominator * that.numerator, denominator * that.denominator).shortened()
    }

    override fun minus(that: Real): Real {
        return Real(value() - that.value())
    }

    override fun minus(that: Integer): Value {
        return Fraction(numerator - denominator * that, denominator).shortened()
    }

    override fun times(that: Fraction): Value {
        val down = numerator.value().gcd(that.denominator.value())
        val up = denominator.value().gcd(that.numerator.value())
        val numerator = (numerator.value() / down) * (that.numerator.value() / up)
        val denominator = (denominator.value() / up) * (that.denominator.value() / down)

        return Fraction(Integer(numerator), Integer(denominator)).shortened()
    }

    override fun times(that: Real): Real {
        return Real(value() * that.value())
    }

    override fun times(that: Integer): Value {
        return Fraction(numerator * that, denominator).shortened()
    }

    override fun div(that: Fraction): Value {
        return times(that.invert())
    }

    override fun div(that: Real): Real {
        return Real(value().divide(that.value(), MathContext.DECIMAL128))
    }

    override fun div(that: Integer): Value {
        return Fraction(numerator, denominator * that).shortened()
    }

    override fun value(): BigDecimal {
        return BigDecimal(numerator.value()).divide(BigDecimal(denominator.value()), MathContext.DECIMAL128)
    }

    override fun pretty(): String {
        return "${numerator.pretty()}/${denominator.pretty()}"
    }

    fun shortened(): Value {
        val gcd = numerator.value().gcd(denominator.value())
        if (denominator.value() == gcd) return Integer(numerator.value() / gcd)
        return Fraction(Integer(numerator.value() / gcd), Integer(denominator.value() / gcd))
    }

    fun invert(): Fraction {
        return Fraction(denominator, numerator)
    }

    fun getNumerator(): Integer {
        return numerator
    }

    fun getDenominator(): Integer {
        return denominator
    }
}
