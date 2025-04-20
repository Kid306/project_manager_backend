
package com.mdp.core;

import org.apache.ibatis.type.JdbcType;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.*;
import java.util.Calendar;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public  class JdbcTypeMapping {
    private static final ConcurrentHashMap<Class<?>, String> JAVA2SQL = new ConcurrentHashMap();
    private static final ConcurrentHashMap<String, Class<?>> SQL2JAVA = new ConcurrentHashMap();


    public static String javaType2JdbcTypeName(Class<?> javaType){
        return JAVA2SQL.getOrDefault(javaType,JdbcType.VARCHAR.name());
    }
    public static Class<?> jdbcType2javaType(String jdbcTypeName){
        return SQL2JAVA.getOrDefault(jdbcTypeName,String.class);
    }
    public static Class<?> jdbcType2javaType(JdbcType jdbcType){
        return SQL2JAVA.getOrDefault(jdbcType.name(),String.class);
    }

    static {
        JAVA2SQL.put(BigDecimal.class, JdbcType.NUMERIC.name());
        JAVA2SQL.put(BigInteger.class, JdbcType.BIGINT.name());
        JAVA2SQL.put(Boolean.TYPE, JdbcType.BOOLEAN.name());
        JAVA2SQL.put(Boolean.class, JdbcType.BOOLEAN.name());
        JAVA2SQL.put(byte[].class, JdbcType.VARBINARY.name());
        JAVA2SQL.put(Byte.TYPE, JdbcType.TINYINT.name());
        JAVA2SQL.put(Byte.class, JdbcType.TINYINT.name());
        JAVA2SQL.put(Calendar.class, JdbcType.TIMESTAMP.name());
        JAVA2SQL.put(Date.class, JdbcType.DATE.name());
        JAVA2SQL.put(java.util.Date.class, JdbcType.TIMESTAMP.name());
        JAVA2SQL.put(Double.TYPE, JdbcType.DOUBLE.name());
        JAVA2SQL.put(Double.class, JdbcType.DOUBLE.name());
        JAVA2SQL.put(Float.TYPE, JdbcType.REAL.name());
        JAVA2SQL.put(Float.class, JdbcType.REAL.name());
        JAVA2SQL.put(Integer.TYPE, JdbcType.INTEGER.name());
        JAVA2SQL.put(Integer.class, JdbcType.INTEGER.name());
        JAVA2SQL.put(LocalDate.class, JdbcType.DATE.name());
        JAVA2SQL.put(LocalDateTime.class, JdbcType.TIMESTAMP.name());
        JAVA2SQL.put(LocalTime.class, JdbcType.TIME.name());
        JAVA2SQL.put(Long.TYPE, JdbcType.BIGINT.name());
        JAVA2SQL.put(Long.class, JdbcType.BIGINT.name());
        JAVA2SQL.put(OffsetDateTime.class, JdbcType.TIMESTAMP_WITH_TIMEZONE.name());
        JAVA2SQL.put(OffsetTime.class, JdbcType.TIME_WITH_TIMEZONE.name());
        JAVA2SQL.put(Short.class, JdbcType.SMALLINT.name());
        JAVA2SQL.put(String.class, JdbcType.VARCHAR.name());
        JAVA2SQL.put(Time.class, JdbcType.TIME.name());
        JAVA2SQL.put(Timestamp.class, JdbcType.TIMESTAMP.name());
        JAVA2SQL.put(URL.class, JdbcType.DATALINK.name());

        JAVA2SQL.put(java.util.Date.class, "DATETIME");

        for (Map.Entry<Class<?>, String> entry : JAVA2SQL.entrySet()) {
            SQL2JAVA.put(entry.getValue(),entry.getKey());
        }
        SQL2JAVA.put(JdbcType.TIMESTAMP.name(),java.util.Date.class);
        SQL2JAVA.put(JdbcType.DATE.name(),java.util.Date.class);
        SQL2JAVA.put(JdbcType.TIME.name(),LocalTime.class);
        SQL2JAVA.put("DATETIME", java.util.Date.class);
        SQL2JAVA.put("INT",Integer.class);
        SQL2JAVA.put("DECIMAL",BigDecimal.class);
    }


    public static void main(String[] args) {
        for (Map.Entry<Class<?>, String> java2sql : JAVA2SQL.entrySet()) {
            System.out.println("java class.getName:"+java2sql.getKey().getName()+"---> sql:"+java2sql.getValue());
            System.out.println("java class.getSimpleName:"+java2sql.getKey().getSimpleName()+"---> sql:"+java2sql.getValue());

        }
        String n=javaType2JdbcTypeName(boolean.class);
        System.out.println(n);

        for (Map.Entry<String, Class<?>> sql2java : SQL2JAVA.entrySet()) {
            System.out.println("sql:"+sql2java.getKey()+"---> java class.getName:"+sql2java.getValue().getName());
            System.out.println("sql:"+sql2java.getKey()+"---> java class.getSimpleName:"+sql2java.getValue().getSimpleName());

        }
    }
}
