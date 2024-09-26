package org.kiyotoko.calc4j.function

import org.kiyotoko.calc4j.value.Value

@FunctionalInterface
fun interface Signature {
    fun signature(): Value
}