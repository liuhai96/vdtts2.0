package com.lsjbc.vdtts.utils;

import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * @Description: el解析
 * @Author:
 * @Date: 2020/6/8 9:24
 */

public class ElParser {
    private static ExpressionParser expressionParser = new SpelExpressionParser();

    private StandardEvaluationContext evalCtx;

    private ElParser(StandardEvaluationContext evalCtx) {
        this.evalCtx = (evalCtx != null ? evalCtx : new StandardEvaluationContext());
    }

    public static ElParser getInstance() {
        return new ElParser(new StandardEvaluationContext());
    }

    public static ElParser getInstance(StandardEvaluationContext evalCtx) {
        return new ElParser(evalCtx);
    }

    public ElParser setVar(String name, Object value) {
        if (value != null) {
            evalCtx.setVariable(name, value);
        }
        return this;
    }

    public <T> T eval(String expr, Class<T> returnClazz) {
        if (expr == null || expr.trim().length() == 0) {
            return null;
        }

        return expressionParser.parseExpression(expr).getValue(evalCtx, returnClazz);
    }
}
