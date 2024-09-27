package org.kiyotoko.calc4j.value

interface Value {
    operator fun plus(that: Value): Value {
        return when (that) {
            is Integer -> plus(that)
            is Fraction -> plus(that)
            is Real -> plus(that)
            is Variable -> plus(that)
            else -> throw InternalError()
        }
    }
    operator fun plus(that: Integer): Value
    operator fun plus(that: Fraction): Value
    operator fun plus(that: Real): Value
    operator fun plus(that: Variable): Value

    operator fun minus(that: Value): Value {
        return when (that) {
            is Integer -> minus(that)
            is Fraction -> minus(that)
            is Real -> minus(that)
            is Variable -> minus(that)
            else -> throw InternalError()
        }
    }
    operator fun minus(that: Integer): Value
    operator fun minus(that: Fraction): Value
    operator fun minus(that: Real): Value
    operator fun minus(that: Variable): Value

    operator fun times(that: Value): Value {
        return when (that) {
            is Integer -> times(that)
            is Fraction -> times(that)
            is Real -> times(that)
            is Variable -> times(that)
            else -> throw InternalError()
        }
    }
    operator fun times(that: Integer): Value
    operator fun times(that: Fraction): Value
    operator fun times(that: Real): Value
    operator fun times(that: Variable): Value

    operator fun div(that: Value): Value {
        return when (that) {
            is Integer -> div(that)
            is Fraction -> div(that)
            is Real -> div(that)
            is Variable -> div(that)
            else -> throw InternalError()
        }
    }
    operator fun div(that: Integer): Value
    operator fun div(that: Fraction): Value
    operator fun div(that: Real): Value
    operator fun div(that: Variable): Value

    fun value(): Number
    fun pretty(): String
}