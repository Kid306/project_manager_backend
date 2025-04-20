package com.mdp;


import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.TenantLineInnerInterceptor;
import com.mdp.mybatis.MapWrapperFactory;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@MapperScan(value={"com.**.mapper.**"})
@Configuration
public class MybatisAutoConfiguration {
	
	Logger log= LoggerFactory.getLogger(MybatisAutoConfiguration.class);
	/**
	 * tenant_id 字段名
	 */
	private static final String TENANT_FIELD_NAME = "tenant_id";
	/**
	 * 哪些表需要做多租户 表需要添加一个字段 tenant_id
	 */
	private static final List<String> tenantTable = new ArrayList<String>();

	static {
		tenantTable.add("demo");

//        //角色、菜单、部门
//        tenantTable.add("sys_role");
//        tenantTable.add("sys_permission");
//        tenantTable.add("sys_depart");
	}


	@Bean
	public MybatisPlusInterceptor mybatisPlusInterceptor() {
		MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
		// 先 add TenantLineInnerInterceptor 再 add PaginationInnerInterceptor
		interceptor.addInnerInterceptor(new TenantLineInnerInterceptor(new TenantLineHandler() {
			@Override
			public Expression getTenantId() {
				String tenant_id =tenantTable.get(0);
				return new LongValue(tenant_id);
			}

			@Override
			public String getTenantIdColumn(){
				return TENANT_FIELD_NAME;
			}

			// 返回 true 表示不走租户逻辑
			@Override
			public boolean ignoreTable(String tableName) {
				for(String temp: tenantTable){
					if(temp.equalsIgnoreCase(tableName)){
						return false;
					}
				}
				return true;
			}
		}));
		interceptor.addInnerInterceptor(new PaginationInnerInterceptor());
		return interceptor;
	}

	@Bean
	MapWrapperFactory mapWrapperFactory(){
		return new MapWrapperFactory();
	}

	@Bean
	public ConfigurationCustomizer configurationCustomizer(){
		ConfigurationCustomizer c=new ConfigurationCustomizer() {
			public void customize(MybatisConfiguration configuration) {
				configuration.setObjectWrapperFactory(mapWrapperFactory());
			}
		};
		return c;
	}
}
