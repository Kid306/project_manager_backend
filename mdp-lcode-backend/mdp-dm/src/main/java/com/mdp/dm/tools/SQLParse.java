package com.mdp.dm.tools;

import com.mdp.core.entity.LangTips;
import com.mdp.core.err.BizException;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.create.table.CreateTable;
import net.sf.jsqlparser.statement.delete.Delete;
import net.sf.jsqlparser.statement.drop.Drop;
import net.sf.jsqlparser.statement.insert.Insert;
import net.sf.jsqlparser.statement.select.*;
import net.sf.jsqlparser.statement.update.Update;
import net.sf.jsqlparser.util.TablesNamesFinder;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;

public class SQLParse {

    /**
     * 解析任意sql
     * @param sql
     * @return
     */
      public static Statement parseSql(String sql){
          Statement statement = null;
          try {
              statement = CCJSqlParserUtil.parse(sql);
              return statement;
          } catch (JSQLParserException e) {
              throw new BizException(LangTips.errMsg("sql-parse-err","sql解析错误，错误原因：%s",e.getMessage()));
          }

      }

    /**
     * 是否为select
     * @param statement
     * @return
     */
      public static Select getSelect(Statement statement){
          if(statement instanceof Select){
              return (Select) statement;
          }else{
              throw new BizException("not-select-statement","不是查询sql");
          }
      }

      public static String getSelectStr(Select select){
          PlainSelect plainSelect= (PlainSelect) select.getSelectBody();
            return "";
      }

      public static DLType getDLType(Statement statement){
          if(statement instanceof Select){
              return DLType.SELECT;
          }else if(statement instanceof Insert){
              return DLType.INSERT;
          }else if(statement instanceof Update){
              return DLType.UPDATE;
          }else if(statement instanceof Delete){
              return DLType.DELETE;
          }else if(statement instanceof CreateTable){
              return DLType.CREATE;
          }else if(statement instanceof Drop){
              return DLType.DROP;
          }else{
              return null;
          }
      }

    /**
     * 获取查询、drop 等sql中的表名
     * @param statement
     * @return
     */
    public static List<String> getTableNames(Statement statement){
          TablesNamesFinder tablesNamesFinder = new TablesNamesFinder();
          List<String> tableList = tablesNamesFinder.getTableList(statement);
          return tableList;
      }



    public static void injectWhere(PlainSelect select, WhereCallback whereService){
        PlainSelect plain =select;
        FromItem fromItem=plain.getFromItem();
        if(fromItem instanceof Table){
            Table table = (Table) (fromItem);
            whereService.setCondition(select,table);
        }else if(fromItem instanceof SubSelect){
            SubSelect subSelect = (SubSelect) (fromItem);
            injectWhere((PlainSelect) subSelect.getSelectBody(),whereService);
        }
        List<Join> joins = plain.getJoins();
        if (CollectionUtils.isNotEmpty(joins)) {
            for (Join join : joins) {
                FromItem rightItem = join.getRightItem();
                if (rightItem instanceof Table) {
                    Table table = (Table) (rightItem);
                    whereService.setCondition(plain,table);
                } else if (rightItem instanceof SubSelect) {
                    SubSelect subSelect = (SubSelect) (rightItem);
                     injectWhere((PlainSelect) subSelect.getSelectBody(),whereService);

                }
            }
        }

    }

    /**
     * 获得sql的增、改表结构的操作的表名及删除数据的表格
     * insert、update、delete、create 四种操作语句的表格
     * @param statement
     * @return
     */
    public static String getCIUDTableName(Statement statement){
        if(statement instanceof Insert){
            Insert insert=(Insert) statement;
            return insert.getTable().getName();
        }else if(statement instanceof Update){
            Update update=(Update) statement;
            return update.getTable().getName();
        }else if(statement instanceof Delete){
            Delete delete=(Delete) statement;
              return delete.getTable().getName();
        }else if(statement instanceof CreateTable){
            CreateTable createTable=(CreateTable) statement;
            return createTable.getTable().getName();
        }else{
            throw new BizException("not-sup-statement","不支持的sql类型");
        }
      }

}

