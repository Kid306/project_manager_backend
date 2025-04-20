package com.mdp.core.query;


import com.mdp.core.utils.ObjectTools;

/**
 * 数据类型
 * @Author cyc
 * @Date 2023年02月14日
 */
public enum ColType {

    DATE("date"),
    DATETIME("datetime"),
    BOOLEAN("boolean"),
    STRING("string"),
    SHORT("short"),
    FLOAT("float"),
    LONG("long"),
    DOUBLE("double"),
    BIGDECIMAL("bigDecimal"),
    INT("int");

    private String value;

    ColType(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static ColType get(String value){
        if(ObjectTools.isEmpty(value)) {
            return null;
        }
        for(ColType val :values()){
            if (val.getValue().equalsIgnoreCase(value) ){
                return val;
            }
        }
        return  null;
    }
    public boolean eq(String value){
        return this.value.equalsIgnoreCase(value);
    }

    public boolean eq(ColType ql){
        return this.value.equalsIgnoreCase(ql.value);
    }
}
