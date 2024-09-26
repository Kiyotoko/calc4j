package org.kiyotoko.calc4j

import org.kiyotoko.calc4j.function.Function
import org.kiyotoko.calc4j.math.namespace
import org.kiyotoko.calc4j.value.Value
import org.kiyotoko.calc4j.value.Variable
import java.util.Deque

fun<T> Deque<T>.clean(i: Int): List<T> {
    val collection = ArrayList<T>(i)
    for (k in 1..i) {
        collection.add(pollLast())
    }
    return collection
}

fun call(name: String, arguments: List<Value>): Variable {
    return Variable {
        val present = namespace[name] ?: throw IllegalAccessException()
        if (present is Function) {
            present.evaluate(*arguments.toTypedArray())
        } else throw InternalError()
    }
}

fun function(i: Int, f: Function): Function {
    return Function {
        arguments -> if (arguments.size != i) throw IndexOutOfBoundsException("Got ${arguments.size} arguments, but expected $i") else f.evaluate(*arguments)
    }
}