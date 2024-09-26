package org.kiyotoko.calc4j.value

import java.math.BigDecimal
import java.math.MathContext

data class Real(private val value: BigDecimal): Value {
    constructor(value: Double): this(BigDecimal(value))

    constructor(value: String): this(BigDecimal(value))

    override fun plus(that: Integer): Real {
        return Real(value + BigDecimal(that.value()))
    }

    override fun plus(that: Fraction): Real {
        return Real(value + that.value())
    }

    override fun plus(that: Real): Real {
        return Real(value + that.value)
    }

    override fun minus(that: Integer): Real {
        return Real(value - BigDecimal(that.value()))
    }

    override fun minus(that: Fraction): Real {
        return Real(value - that.value())
    }

    override fun minus(that: Real): Real {
        return Real(value - that.value)
    }

    override fun times(that: Integer): Real {
        return Real(value * BigDecimal(that.value()))
    }

    override fun times(that: Fraction): Real {
        return Real(value * that.value())
    }

    override fun times(that: Real): Real {
        return Real(value * that.value)
    }

    override fun div(that: Integer): Real {
        return Real(value.divide(BigDecimal(that.value()), MathContext.DECIMAL128))
    }

    override fun div(that: Fraction): Real {
        return Real(value.divide(that.value(), MathContext.DECIMAL128))
    }

    override fun div(that: Real): Real {
        return Real(value.divide(that.value, MathContext.DECIMAL128))
    }

    override fun equals(other: Any?): Boolean {
        return when (other) {
            is Real -> other.value
            is Fraction -> other.value()
            is Value -> other.value().toDouble().toBigDecimal()
            is Number -> other.toDouble().toBigDecimal()
            else -> return false
        }.compareTo(value) == 0
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }

    override fun value(): BigDecimal {
        return value
    }

    override fun pretty(): String {
        return value.toString()
    }
}
