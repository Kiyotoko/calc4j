package org.kiyotoko.calc4j.parser

import org.kiyotoko.calc4j.clean
import org.kiyotoko.calc4j.call
import org.kiyotoko.calc4j.value.Value
import org.kiyotoko.calc4j.value.Integer
import org.kiyotoko.calc4j.value.Real
import org.kiyotoko.calc4j.value.Variable
import org.kiyotoko.calc4j.math.namespace
import java.util.*

class CalcParseListener : CalcBaseListener() {
    private val values: Deque<Value> = ArrayDeque()

    override fun exitNumber(ctx: CalcParser.NumberContext) {
        if (ctx.NUMBER() == null) throw InternalError()

        val text = ctx.NUMBER().text
        val number = if (text.contains('.')) Real(text) else Integer(text)
        values.add(if (ctx.SUB() != null) Integer(-1L) * number else number)
    }

    override fun exitCall(ctx: CalcParser.CallContext) {
        val name = ctx.NAME().text
        if (ctx.arguments() != null) {
            val count = ctx.arguments().childCount / 2 + 1 // Ignore commas
            values.add(call(name, values.clean(count)))
        } else {
            values.add(call(name, emptyList()))
        }
    }

    override fun exitExpr(ctx: CalcParser.ExprContext) {
        if (ctx.NAME() != null) {
            values.add(Variable(ctx.NAME().text))
        } else if (ctx.number() != null) {
            // Ignore, will be caught by exitCall()
        } else if (ctx.ADD() != null) {
            val right = values.pollLast()
            val left = values.pollLast()

            checkForNull(left, right)
            values.add(left + right)
        } else if (ctx.SUB() != null) {
            val right = values.pollLast()
            val left = values.pollLast()

            checkForNull(left, right)
            values.add(left - right)
        } else if (ctx.MUL() != null) {
            val right = values.pollLast()
            val left = values.pollLast()

            checkForNull(left, right)
            values.add(left * right)
        } else if (ctx.DIV() != null) {
            val right = values.pollLast()
            val left = values.pollLast()

            checkForNull(left, right)
            values.add(left / right)
        } else {
            // Ignoring expression, as it should be additional brackets.
            // Safety: make sure that this is the only possibility to reach this block.
        }
    }

    override fun exitDefinition(ctx: CalcParser.DefinitionContext) {
        if (ctx.NAME() != null) {
            namespace[ctx.NAME().text] = values.peek()
        } else {
            System.err.println("Did not expect empty name")
        }
    }

    fun result(): Value? {
        return values.peek()
    }

    companion object {
        @JvmStatic
        fun checkForNull(vararg objs: Any?) {
            for (o in objs) {
                if (o == null) throw ParserException("Expected value")
            }
        }
    }
}
