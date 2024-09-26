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
 *
 */
package org.kiyotoko.calc4j

import org.antlr.v4.runtime.ANTLRInputStream
import org.antlr.v4.runtime.CharStream
import org.antlr.v4.runtime.CommonTokenStream
import org.kiyotoko.calc4j.parser.CalcErrorListener
import org.kiyotoko.calc4j.parser.CalcLexer
import org.kiyotoko.calc4j.parser.CalcParseListener
import org.kiyotoko.calc4j.parser.CalcParser
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.util.*

class Calculator @JvmOverloads constructor(
    private val input: InputStream = System.`in`,
    private val output: OutputStream = System.out
) {
    @Throws(IOException::class)
    fun runAndServe() {
        Scanner(input).use { scanner ->
            while (true) {
                output.write(">>> ".toByteArray())
                val line = scanner.nextLine()
                if (line == "exit") break

                val stream: CharStream = ANTLRInputStream(line)
                val lexer = CalcLexer(stream)
                val tokens = CommonTokenStream(lexer)
                val listener = CalcParseListener()
                val parser = CalcParser(tokens)

                parser.addParseListener(listener)
                parser.addErrorListener(CalcErrorListener())

                try {
                    parser.root()
                    val result = listener.result()
                    if (result != null) output.write((result.pretty() + '\n').toByteArray())
                } catch (e: Exception) {
                    output.write(("${e::class.simpleName} -> ${e.message}\n").toByteArray())
                }
            }
        }
    }
}
