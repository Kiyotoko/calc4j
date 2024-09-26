package org.kiyotoko.calc4j.function

import org.kiyotoko.calc4j.value.Value

@FunctionalInterface
fun interface Function {
    fun  evaluate(vararg arguments: Value): Value
}
