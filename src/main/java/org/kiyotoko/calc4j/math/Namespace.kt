package org.kiyotoko.calc4j.math

import org.kiyotoko.calc4j.function
import org.kiyotoko.calc4j.value.Real
import kotlin.math.*

typealias Namespace = MutableMap<String, Any>

val namespace: Namespace = HashMap(buildMap {
    put("e", Real(Math.E))
    put("pi", Real(Math.PI))

    put("sin", function(1) {
        arguments -> Real(sin(arguments[0].value().toDouble()))
    })
    put("asin", function(1) {
            args -> Real(asin(args[0].value().toDouble()))
    })
    put("cos", function(1) {
            arguments -> Real(cos(arguments[0].value().toDouble()))
    })
    put("acos", function(1) {
            args -> Real(acos(args[0].value().toDouble()))
    })
    put("tan", function(1) {
            arguments -> Real(tan(arguments[0].value().toDouble()))
    })
    put("atan", function(1) {
            args -> Real(atan(args[0].value().toDouble()))
    })
    put("ln", function(1) {
            args -> Real(ln(args[0].value().toDouble()))
    })
    put("exp", function(1) {
            args -> Real(exp(args[0].value().toDouble()))
    })
    put("pow", function(2) {
            args -> Real(args[0].value().toDouble().pow(args[1].value().toDouble()))
    })
    put("log", function(2) {
            args -> Real(log(args[0].value().toDouble(), args[1].value().toDouble()))
    })
    put("hypot", function(2) {
        args -> Real(hypot(args[0].value().toDouble(), args[1].value().toDouble()))
    })
    put("sqrt", function(1) {
            args -> Real(sqrt(args[0].value().toDouble()))
    })
})


