/*
 * MIT License
 *
 * Copyright (c) 2023 Karl Zschiebsch
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.kiyotoko.calc4j.math

import kotlin.math.abs
import kotlin.math.sqrt

fun solve(variables: Array<DoubleArray>, values: DoubleArray): DoubleArray {
    val size = values.size
    for (k in 0 until size) {
        /* find pivot row */
        var max = k
        for (i in k + 1 until size) if (abs(variables[i][k]) > abs(variables[max][k])) max = i

        /* swap row in A matrix */
        val temp = variables[k]
        variables[k] = variables[max]
        variables[max] = temp

        /* swap corresponding values in constants matrix */
        val t = values[k]
        values[k] = values[max]
        values[max] = t

        /* pivot within A and B */
        for (i in k + 1 until size) {
            val factor = variables[i][k] / variables[k][k]
            values[i] -= factor * values[k]
            for (j in k until size) variables[i][j] -= factor * variables[k][j]
        }
    }

    /* back substitution */
    val solution = DoubleArray(size)
    for (i in size - 1 downTo 0) {
        var sum = 0.0
        for (j in i + 1 until size) sum += variables[i][j] * solution[j]
        solution[i] = (values[i] - sum) / variables[i][i]
    }
    return solution
}

/**
 * Solves an equation in the form of:
 *
 *
 * `
 * ax^2 + bx + c = 0
` *
 *
 * @param a the factor a
 * @param b the factor b
 * @param c the constant c
 * @return an array containing all possible different solutions for x
 */
fun solve(a: Double, b: Double, c: Double): DoubleArray {
    if (a == 0.0) return (if (c == 0.0) doubleArrayOf(c) else doubleArrayOf())
    val l = -b / (2 * a)
    val r = sqrt(b * b - 4 * a * c) / (2 * a)
    if (java.lang.Double.isNaN(r)) return doubleArrayOf()
    if (r == 0.0) return doubleArrayOf(l)
    return doubleArrayOf(l + r, l - r)
}

/**
 * Solves an equation in the form of:
 *
 *
 * `
 * x^2 + px + q = 0
` *
 *
 * @param p the factor p
 * @param q the constant q
 * @return an array containing all possible different solutions for x
 */
fun solve(p: Double, q: Double): DoubleArray {
    val l = -p / 2
    val r = sqrt(p * p / 4 - q)
    if (java.lang.Double.isNaN(r)) return doubleArrayOf()
    if (r == 0.0) return doubleArrayOf(l)
    return doubleArrayOf(l + r, l - r)
}

