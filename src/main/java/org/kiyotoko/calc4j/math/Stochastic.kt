package org.kiyotoko.calc4j.math

import kotlin.math.pow

fun factorial(n: Long, start: Long = 1): Double {
    var i: Long
    if (n <= 16) {
        var r = start
        i = start + 1
        while (i < start + n) {
            r *= i
            i++
        }
        return r.toDouble()
    }
    i = n / 2
    return factorial(i, start) * factorial(n - i, start + i)
}

fun binomial(n: Int, k: Int): Double {
    return factorial(n.toLong()) / (factorial(k.toLong()) * factorial((n - k).toLong()))
}

fun combU(n: Int, k: Int): Double {
    return binomial(n, k)
}

fun combA(n: Int, k: Int): Double {
    return binomial(n + k - 1, k)
}

fun bernoulliPdf(p: Double, n: Int, k: Int): Double {
    return binomial(n, k) * p.pow(k.toDouble()) * (1 - p).pow((n - k).toDouble())
}

fun bernoulliCdf(p: Double, n: Int, min: Int, max: Int): Double {
    var sum = 0.0
    for (i in min..max) {
        sum += bernoulliPdf(p, n, i)
    }
    return sum
}

fun minimalAttempts(p: Double, min: Double, n: Int): Int {
    var sum = bernoulliPdf(p, n, 0)
    var k = 0
    while (sum < min) {
        sum += bernoulliPdf(p, n, ++k)
    }
    return k
}
