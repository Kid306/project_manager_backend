package com.mdp.core;

import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.toolkit.JdbcUtils;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

public class DsUtil {
    private static final Log logger = LogFactory.getLog(DsUtil.class);
    private static final Map<String, DbType> JDBC_DB_TYPE_CACHE_DS = new ConcurrentHashMap();

    public DsUtil() {
    }
    private static DynamicRoutingDataSource dataSourceService;

    public static DynamicRoutingDataSource getDataSourceService() {
        if(dataSourceService==null){
            dataSourceService=SpringUtils.getBean(DynamicRoutingDataSource.class);
        }
        return dataSourceService;
    }
    public static JdbcTemplate getJdbcTemplate(String ds){
        JdbcTemplate jdbcTemplate=new JdbcTemplate();
        jdbcTemplate.setDataSource(DsUtil.getDataSource(ds));
        return jdbcTemplate;
    }
    public static JdbcTemplate getJdbcTemplate(){
        JdbcTemplate jdbcTemplate=new JdbcTemplate();
        jdbcTemplate.setDataSource(DsUtil.getDataSource());
        return jdbcTemplate;
    }
    /**
     * 获取数据源
     * @return
     */
    public static DataSource getDataSource(){
        if(dataSourceService==null){
            dataSourceService= SpringUtils.getBean(DynamicRoutingDataSource.class);
        }
        return dataSourceService.determineDataSource();
    }
    /**
     * 获取数据源
     * @param ds
     * @return
     */
    public static DataSource getDataSource(String ds){
        if(dataSourceService==null){
            dataSourceService= SpringUtils.getBean(DynamicRoutingDataSource.class);
        }
        return dataSourceService.getDataSource(ds);
    }

    /**
     * 根据数据源名称获取数据库方言
     * @param ds
     * @return
     */
    public static DbType getDbTypeByDs(String ds) {
        DbType dbType=JDBC_DB_TYPE_CACHE_DS.get(ds);
        if(dbType!=null){
            return dbType;
        }
        String jdbcUrl= null;
        try {
            jdbcUrl = getDataSource(ds).getConnection().getMetaData().getURL();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        dbType= JdbcUtils.getDbType(jdbcUrl);
        if(dbType!=null){
            JDBC_DB_TYPE_CACHE_DS.put(ds,dbType);
        }
        return dbType;
    }

    public static boolean regexFind(String regex, CharSequence input) {
        return null == input ? false : Pattern.compile(regex).matcher(input).find();
    }

    public static boolean containsDataSource(String ds) {
        return getDataSourceService().getDataSources().containsKey(ds);
    }
}
