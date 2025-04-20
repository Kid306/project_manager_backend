package com.mdp.dm.tools;

import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.StringValue;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.select.PlainSelect;

public interface WhereCallback {

    default void setCondition(PlainSelect plainSelect, Table table){

        // 创建一个新的默认条件，例如：a IS NOT NULL
        Expression defaultCondition = new EqualsTo(
                new StringValue("'default'"),
                new StringValue("'condition'")
        );

        // 将默认条件注入到WHERE子句中
        if (plainSelect.getWhere() != null) {
            // 如果已经有条件，使用AND连接
            Expression newCondition = new AndExpression(plainSelect.getWhere(), defaultCondition);
            plainSelect.setWhere(newCondition);
        } else {
            // 如果没有条件，直接设置
            plainSelect.setWhere(defaultCondition);
        }
    }
}
