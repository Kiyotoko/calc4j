package org.kiyotoko.calc4j.value

import org.kiyotoko.calc4j.function.Signature
import org.kiyotoko.calc4j.math.namespace

data class Variable(val signature: Signature): Value {
    constructor(name: String) : this({
        val present = namespace[name]?: throw IllegalAccessException()
        if (present is Value) {
            present
        } else throw InternalError()
    })

    override fun plus(that: Integer): Value {
        return Variable { signature.signature() + that }
    }

    override fun plus(that: Fraction): Value {
        return Variable { signature.signature() + that }
    }

    override fun plus(that: Real): Value {
        return Variable { signature.signature() + that }
    }

    override fun minus(that: Integer): Value {
        return Variable { signature.signature() - that }
    }

    override fun minus(that: Fraction): Value {
        return Variable { signature.signature() - that }
    }

    override fun minus(that: Real): Value {
        return Variable { signature.signature() - that }
    }

    override fun times(that: Integer): Value {
        return Variable { signature.signature() * that }
    }

    override fun times(that: Fraction): Value {
        return Variable { signature.signature() * that }
    }

    override fun times(that: Real): Value {
        return Variable { signature.signature() * that }
    }

    override fun div(that: Integer): Value {
        return Variable { signature.signature() / that }
    }

    override fun div(that: Fraction): Value {
        return Variable { signature.signature() / that }
    }

    override fun div(that: Real): Value {
        return Variable { signature.signature() / that }
    }

    override fun value(): Number {
        return signature.signature().value()
    }

    override fun pretty(): String {
        return signature.signature().pretty()
    }
}
