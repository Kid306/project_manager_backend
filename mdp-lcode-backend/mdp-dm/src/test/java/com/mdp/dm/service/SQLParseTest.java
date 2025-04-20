package com.mdp.dm.service;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.Function;
import net.sf.jsqlparser.expression.StringValue;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import net.sf.jsqlparser.expression.operators.relational.NamedExpressionList;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.create.table.CreateTable;
import net.sf.jsqlparser.statement.delete.Delete;
import net.sf.jsqlparser.statement.insert.Insert;
import net.sf.jsqlparser.statement.select.*;
import net.sf.jsqlparser.statement.update.Update;
import net.sf.jsqlparser.statement.values.ValuesStatement;
import net.sf.jsqlparser.util.TablesNamesFinder;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;
import java.util.Objects;
 
public class SQLParseTest {
    static String SQL1_1_1 = "select t1.f1,t1.f2,t2.id,count(*) from  (select * from table2 where userid=?) t3 left join table t1 left join table1 t2  where t1.id='12121' or (t1.id between 1 and 3 and t1.id>'22112') group by t.f1 order by t.f1 desc,tf2 asc limit 1,20";

    static String SQL1 = "select t1.f1,t1.f2,t2.id,count(*) from table t1 left join table1 t2 right join (select * from table2) t3 where t1.id='12121' or (t1.id between 1 and 3 and t1.id>'22112') group by t.f1 order by t.f1 desc,tf2 asc limit 1,20";
    static String SQL2 = "insert into table(f1,f2) values (1,2)";
    static String SQL2_1 = "insert into table(f1,f2) (select f1,f2 from table1)";
    static String SQL3 = "update table set f1=2,f2=3 where f1=1212";
    static String SQL3_1 = "insert into table(f1,f2) (select f1,f2 from table1)";
    static String SQL4_1 = "delete from table where 1=1";
    static String SQL_5 = "create table table_name2 as select * from table_name1 t1 where t1.id = '333'";
    static String SQL5_1 = "CREATE TABLE `gen_table` (\n" +
            "  `table_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',\n" +
            "  `table_name` varchar(200) DEFAULT '' COMMENT '表名称',\n" +
            "  `table_comment` varchar(500) DEFAULT '' COMMENT '表描述',\n" +
            "  `sub_table_name` varchar(64) DEFAULT NULL COMMENT '关联子表的表名',\n" +
            "  `sub_table_fk_name` varchar(64) DEFAULT NULL COMMENT '子表关联的外键名',\n" +
            "  `class_name` varchar(100) DEFAULT '' COMMENT '实体类名称',\n" +
            "  `tpl_category` varchar(200) DEFAULT 'crud' COMMENT '使用的模板（crud单表操作 tree树表操作 sub主子表操作）',\n" +
            "  `package_name` varchar(100) DEFAULT NULL COMMENT '生成包路径',\n" +
            "  `module_name` varchar(30) DEFAULT NULL COMMENT '生成模块名',\n" +
            "  `business_name` varchar(30) DEFAULT NULL COMMENT '生成业务名',\n" +
            "  `function_name` varchar(50) DEFAULT NULL COMMENT '生成功能名',\n" +
            "  `function_author` varchar(50) DEFAULT NULL COMMENT '生成功能作者',\n" +
            "  `gen_type` char(1) DEFAULT '0' COMMENT '生成代码方式（0zip压缩包 1自定义路径）',\n" +
            "  `gen_path` varchar(200) DEFAULT '/' COMMENT '生成路径（不填默认项目路径）',\n" +
            "  `options` varchar(1000) DEFAULT NULL COMMENT '其它生成选项',\n" +
            "  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',\n" +
            "  `create_time` datetime DEFAULT NULL COMMENT '创建时间',\n" +
            "  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',\n" +
            "  `update_time` datetime DEFAULT NULL COMMENT '更新时间',\n" +
            "  `remark` varchar(500) DEFAULT NULL COMMENT '备注',\n" +
            "  PRIMARY KEY (`table_id`)\n" +
            ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='代码生成业务表';";
    static String SQL1_1 = "select `t2a_cust_c`.`CUST_ID` AS `CUST_ID`,`t2a_cust_c`.`CUST_NAME` AS `CUST_NAME`,`t2a_cust_c`.`CUST_EN_NAME` AS `CUST_EN_NAME`,`t2a_cust_c`.`CUST_STS` AS `cust_sts`,`t2a_cust_c`.`CUST_TYPE` AS `CUST_TYPE`,`t2a_cust_c`.`CERT_TYPE` AS `cert_type`,`t2a_cust_c`.`CERT_NO` AS `CERT_NO`,`t2a_cust_c`.`ORG_ID` AS `ORG_ID`,`t2a_cust_c`.`BIZ_SCOPE` AS `BIZ_SCOPE`,'' AS `NATION_CD`,NULL AS `INCOME_AMT`,`t2a_cust_c`.`CREATE_DT` AS `CREATE_DT`,'' AS `IS_STAFF`,`t2a_cust_c`.`IS_FREE_TRADE` AS `IS_FREE_TRADE`,`t2a_cust_c`.`CUST_NAT` AS `CUST_NAT`,`t2a_cust_c`.`PBC_INDUS` AS `pbc_indus`,'' AS `pbc_ocp`,`t2a_cust_c`.`CERT_INVALID_DT` AS `CERT_INVALID_DT` from `t2a_cust_c` union all select `t2a_cust_i`.`CUST_ID` AS `CUST_ID`,`t2a_cust_i`.`CUST_NAME` AS `CUST_NAME`,`t2a_cust_i`.`CUST_EN_NAME` AS `CUST_EN_NAME`,`t2a_cust_i`.`CUST_STS` AS `cust_sts`,`t2a_cust_i`.`CUST_TYPE` AS `CUST_TYPE`,`t2a_cust_i`.`CERT_TYPE` AS `cert_type`,`t2a_cust_i`.`CERT_NO` AS `CERT_NO`,`t2a_cust_i`.`ORG_ID` AS `ORG_ID`,'' AS `BIZ_SCOPE`,`t2a_cust_i`.`NATION_CD` AS `NATION_CD`,`t2a_cust_i`.`INCOME_AMT` AS `INCOME_AMT`,`t2a_cust_i`.`CREATE_DT` AS `CREATE_DT`,`t2a_cust_i`.`IS_STAFF` AS `IS_STAFF`,`t2a_cust_i`.`IS_FREE_TRADE` AS `IS_FREE_TRADE`,`t2a_cust_i`.`CUST_NAT` AS `CUST_NAT`,'' AS `pbc_indus`,`t2a_cust_i`.`PBC_OCP` AS `pbc_ocp`,`t2a_cust_i`.`CERT_INVALID_DT` AS `CERT_INVALID_DT` from `t2a_cust_i`";
 
    public static void main(String[] args) {
 //       testSimpleSelectSQL(SQL1_1_1);
//        testSimpleInsertSQL(SQL2);
//        testSimpleInsertSQL(SQL2_1);
//        testSimpleUpdateSQL(SQL3);
//        testSimpleDeleteSQL(SQL4_1);
//        testSimpleCreateSQL(SQL5_1);

        injectWhere();
    }
 
    private static void testSimpleCreateSQL(String SQL_5) {
        try {
            Statement statement = CCJSqlParserUtil.parse(SQL_5);
            if (statement instanceof CreateTable) {
                Table table = ((CreateTable) statement).getTable();
                System.out.println(table);
                Select select = ((CreateTable) statement).getSelect();
                if (select != null){
                    String s = select.toString();
                    testSimpleSelectSQL(s);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 
    //解析SQL
    public static void testSimpleSelectSQL(String SQL1) {
        System.out.println("=================测试查询==================");
        try {
            Select select = (Select) CCJSqlParserUtil.parse(SQL1);
            TablesNamesFinder tablesNamesFinder = new TablesNamesFinder();
            List<String> tableList = tablesNamesFinder.getTableList(select);
            // 获取到查询SQL中的所有表名,下面的逻辑是对SQL的细致拆分
            System.out.println("表名:" + tableList);
            if (select.getSelectBody() instanceof PlainSelect) { // 普通查询
                // 复杂SQL会多次调用此处方法，所以抽出作为公共类使用
               getSelectMsg((PlainSelect) select.getSelectBody());
            }else if (select.getSelectBody() instanceof WithItem){ // WITH语句
 
            }else if (select.getSelectBody() instanceof SetOperationList){ // INTERSECT、EXCEPT、MINUS、UNION语句
               SetOperationList setOperationList =  (SetOperationList)select.getSelectBody();
                List<SelectBody> selects = setOperationList.getSelects();
                for (int i = 0; i < selects.size(); i++) {
                    // 此处又是符合普通SQL的拆解逻辑
                    getSelectMsg((PlainSelect)selects.get(i));
                }
            }else if (select.getSelectBody() instanceof ValuesStatement){ // VALUES语句
 
            }
        } catch (JSQLParserException e) {
            e.printStackTrace();
        }
        System.out.println("=================测试查询==================");
    }
 
    public static void testSimpleInsertSQL(String SQL) {
        System.out.println("=================测试插入SQL==================");
        System.out.println("测试SQL:" + SQL);
        try {
            Insert insert = (Insert) CCJSqlParserUtil.parse(SQL);
            System.out.println("插入的表" + insert.getTable());
            System.out.println("插入的列" + insert.getColumns());
            if (Objects.nonNull(insert.getSelect())) {
                SelectBody selectBody = insert.getSelect().getSelectBody();
                System.out.println("来自：" + selectBody);
            } else {
                System.out.println("普通插入");
                System.out.println("插入的值" + insert.getItemsList());
            }
 
        } catch (JSQLParserException e) {
            e.printStackTrace();
        }
 
        System.out.println("=================测试插入SQL==================");
    }
 
    public static void testSimpleUpdateSQL(String SQL) {
        System.out.println("=================测试更新SQL==================");
        System.out.println("测试SQL:" + SQL);
        try {
            Update update = (Update) CCJSqlParserUtil.parse(SQL);
            System.out.println("更新的表" + update.getTable());
            System.out.println("更新的列" + update.getColumns());
            System.out.println("更新的值" + update.getExpressions());
            System.out.println("条件" + update.getWhere());
        } catch (JSQLParserException e) {
            e.printStackTrace();
        }
 
        System.out.println("=================测试更新SQL==================");
    }
 
    public static void testSimpleDeleteSQL(String SQL) {
        System.out.println("=================测试删除SQL==================");
        System.out.println("测试SQL:" + SQL);
        try {
            Delete delete = (Delete) CCJSqlParserUtil.parse(SQL);
            System.out.println("删除的表" + delete.getTable());
            System.out.println("条件的列" + delete.getWhere());
        } catch (JSQLParserException e) {
            e.printStackTrace();
        }
 
        System.out.println("=================测试删除SQL==================");
    }
 
    public static String joinTypeStr(Join join) {
        if (join.isLeft()) {
            return "左连接";
        }
        if (join.isRight()) {
            return "左连接";
        }
        if (join.isFull()) {
            return "全连接";
        }
        if (join.isCross()) {
            return "交叉连接";
        }
        return null;
    }
 
    public static void getSelectMsg(PlainSelect select){
        PlainSelect plain = (PlainSelect) select;
        List<Join> joins = plain.getJoins();
        if (CollectionUtils.isNotEmpty(joins)) {
            for (Join join : joins) {
                FromItem rightItem = join.getRightItem();
                if (rightItem instanceof Table) {
                    Table table = (Table) (rightItem);
                    System.out.println("连接类型:" + joinTypeStr(join) + "         表：" + table.getName() + "           别名：" + table.getAlias());
                } else if (rightItem instanceof SubSelect) {
                    SubSelect subSelect = (SubSelect) (rightItem);
                    System.out.println("连接类型:" + joinTypeStr(join) + "         子查询：" + subSelect.getSelectBody() + "           别名：" + rightItem.getAlias());
                }
            }
        }
        List<SelectItem> selectItems = plain.getSelectItems();
        for (SelectItem selectItem : selectItems) {
            if (selectItem instanceof AllColumns) {
                System.out.println("获取的是表中的全部列:  * ");
                continue;
            }
            SelectExpressionItem selectExpressionItem = (SelectExpressionItem) selectItem;
            Expression expression = selectExpressionItem.getExpression();
            //判断表达式是否是函数
            if (expression instanceof Function) {
                Function function = (Function) expression;
                NamedExpressionList namedParameters = function.getNamedParameters();
                if (namedParameters != null) {
                    List<Expression> expressions = namedParameters.getExpressions();
                    System.out.println(expressions);
                }
                System.out.println("函数：" + ((Function) expression).getName());
                boolean allColumns = function.isAllColumns();
                System.out.println("传入的是全部列：" + allColumns);
                //判断表达式是否是列
            } else if (expression instanceof Column) {
                System.out.println("查询值：" + ((Column) expression).getColumnName());
            }
        }
        FromItem fromItem=plain.getFromItem();
       System.out.println("表名别名:" + fromItem.getAlias().getName());
        Expression where = plain.getWhere();
        if (where != null) {
            System.out.println("条件:" + where);
        }
 
        //排序
        List<OrderByElement> orderByElements = plain.getOrderByElements();
        if (Objects.nonNull(orderByElements)) {
            for (OrderByElement orderByElement : orderByElements) {
                Expression expression = orderByElement.getExpression();
                if (expression instanceof Column) {
                    Column column = (Column) (expression);
                    System.out.println("排序字段:" + column.getColumnName() + "," + (orderByElement.isAsc() ? "正序" : "倒序"));
                }
            }
        }
 
 
        //获取分组
        GroupByElement groupBy = plain.getGroupBy();
        if (Objects.nonNull(groupBy)) {
            List<Expression> groupByExpressions = groupBy.getGroupByExpressions();
            for (Expression groupByExpression : groupByExpressions) {
                if (groupByExpression instanceof Column) {
                    Column column = (Column) (groupByExpression);
                    System.out.println("分组字段:" + column.getColumnName());
                }
            }
        }
 
        //分页
        Limit limit = plain.getLimit();
        if (Objects.nonNull(limit)) {
            System.out.println("行:" + limit.getRowCount());
            System.out.println("偏移量:" + limit.getOffset());
        }
    }

        public static void injectWhere( ) {
            try {
                String sql = SQL1_1_1;
                Select selectStatement = (Select) CCJSqlParserUtil.parse(sql);
                injectWhere2((PlainSelect) selectStatement.getSelectBody());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        public static void setWhere(PlainSelect plainSelect,Table table){

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

            // 打印修改后的SQL
            System.out.println(plainSelect.toString());
        }



    public static void injectWhere2(PlainSelect select){
        PlainSelect plain = (PlainSelect) select;
        FromItem fromItem=plain.getFromItem();
        if(fromItem instanceof Table){
            Table table = (Table) (fromItem);
            System.out.println("表名:       表：" + table.getName() + "           别名：" + table.getAlias());
            //需要进行默认条件注入
            System.out.println("!!!!!!!需要进行默认条件注入!!!!!!!");
            setWhere(select,table);
        }else if(fromItem instanceof SubSelect){
            SubSelect subSelect = (SubSelect) (fromItem);
            injectWhere2((PlainSelect) subSelect.getSelectBody());
        }
        List<Join> joins = plain.getJoins();
        if (CollectionUtils.isNotEmpty(joins)) {
            for (Join join : joins) {
                FromItem rightItem = join.getRightItem();
                if (rightItem instanceof Table) {
                    Table table = (Table) (rightItem);
                    System.out.println("连接类型:" + joinTypeStr(join) + "         表：" + table.getName() + "           别名：" + table.getAlias());
                    System.out.println("!!!!!!!需要进行默认条件注入!!!!!!!");
                    setWhere(plain,table);
                } else if (rightItem instanceof SubSelect) {
                    SubSelect subSelect = (SubSelect) (rightItem);
                    System.out.println("连接类型:" + joinTypeStr(join) + "         子查询：" + subSelect.getSelectBody() + "           别名：" + rightItem.getAlias());
                    injectWhere2((PlainSelect) subSelect.getSelectBody());

                }
            }
        }

    }
}

