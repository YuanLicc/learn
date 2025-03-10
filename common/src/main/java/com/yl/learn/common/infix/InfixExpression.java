package com.yl.learn.common.infix;

import com.yl.learn.common.calculate.Calculator;
import com.yl.learn.common.expression.imp.CalculableExpression;

public class InfixExpression extends CalculableExpression {

    public InfixExpression(String expression, Calculator calculator) {
        super(expression, calculator);
    }
}
