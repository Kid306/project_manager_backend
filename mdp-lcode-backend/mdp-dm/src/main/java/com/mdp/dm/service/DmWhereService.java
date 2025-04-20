package com.mdp.dm.service;

import com.mdp.core.entity.DmField;
import com.mdp.dm.base.service.DataMetaBaseService;
import com.mdp.dm.tools.DmQxUtil;
import com.mdp.dm.tools.WhereCallback;
import com.mdp.safe.client.entity.User;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.StringValue;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.select.PlainSelect;

import java.util.List;

/**
 * 不可以单例，因为要注入数据源及用户信息
 */
public class DmWhereService implements WhereCallback {
    DataMetaBaseService dataMetaService;
    String dataSource;
    User user;

    public DmWhereService(DataMetaBaseService dataMetaBaseService, String dataSource, User user){
        this.dataMetaService=dataMetaBaseService;
        this.dataSource=dataSource;
        this.user=user;
    }

    @Override
    public void setCondition(PlainSelect plainSelect, Table table) {
        List<DmField> dmFields=dataMetaService.getColumnsInfoFirstCache(this.dataSource,table.getName());
        DmField dmField=DmQxUtil.getBranchField(dmFields);
        if(dmField==null){//如果没用机构字段，略过
            return;
        }
        // 创建一个新的默认条件，例如：a IS NOT NULL
        Expression defaultCondition = new EqualsTo(
                new Column(table, dmField.getColumnName()),
                new StringValue("'"+user.getBranchId()+"'")
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
