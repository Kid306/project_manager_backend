package com.mdp.core.query;


import com.mdp.core.utils.ObjectTools;

import java.util.Locale;

/**
 * 操作符
 * @Author cyc
 * @Date 2023年02月14日
 */
public enum SqlOper {

    /**
     * 必须把 >= 放在 >之前，否则先匹配了>
     */
    GE(">="),
    GT(">"),
    LE("<="),
    LT("<"),
    NE("!="),
    EQ("="),
    IS_NOT_NULL("$IS NOT NULL"),
    NOT_IN("$NOT IN"),
    IS_NULL("$IS NULL"),
    IN("$IN"),
    BETWEEN("$BETWEEN"),
    LIKE_LEFT("$LIKE LEFT"),
    LIKE_RIGHT("$LIKE RIGHT"),
    LIKE("$LIKE"),
    JAVA("$JAVA"),//如果操作符是java,则sqlVal填java类及方法
    SQL("$SQL");
 
    private String value;

    SqlOper(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


	public static SqlOper get(String value){
        if(ObjectTools.isEmpty(value)){
            return null;
        }
    	String trimValue=value.trim().toUpperCase(Locale.ROOT);

        if(trimValue.startsWith("*") && trimValue.endsWith("*")){
            return LIKE;
        }
        if(trimValue.startsWith("*")){
            return LIKE_LEFT;
        }
        if(trimValue.endsWith("*")){
            return LIKE_RIGHT;
        }
        for(SqlOper val :values()){
            if (val.getValue().equalsIgnoreCase(trimValue) ){
                return val;
            }
            if(trimValue.startsWith(val.getValue())){
                return val;
            }
        }
        return  null;
    }

    public boolean eq(String value){
        return this.value.equalsIgnoreCase(value);
    }
    public boolean eq(SqlOper qo){
        return this.value.equalsIgnoreCase(qo.value);
    }
}
