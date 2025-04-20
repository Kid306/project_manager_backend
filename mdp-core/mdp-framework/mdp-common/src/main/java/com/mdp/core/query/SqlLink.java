package com.mdp.core.query;


import com.mdp.core.utils.ObjectTools;

/**
 * 连接符 and or
 * @Author cyc
 * @Date 2023年02月14日
 */
public enum SqlLink {

    AND("AND"),
    OR("OR");

    private String value;

    SqlLink(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static SqlLink get(String value){
        if(ObjectTools.isEmpty(value)) {
            return null;
        }
        for(SqlLink val :values()){
            if (val.getValue().equalsIgnoreCase(value) ){
                return val;
            }
        }
        return  null;
    }
    public boolean eq(String value){
        return this.value.equalsIgnoreCase(value);
    }

    public boolean eq(SqlLink ql){
        return this.value.equalsIgnoreCase(ql.value);
    }
}
