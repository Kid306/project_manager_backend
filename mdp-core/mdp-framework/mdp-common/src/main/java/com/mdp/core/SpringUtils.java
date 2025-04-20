package com.mdp.core;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author chenyc 2016-11-03 19:36
 * @apiNote spring上下文工具类，用于普通类调用springIOC中的对象
 */
@Component
public class SpringUtils implements ApplicationContextAware {
	private static ApplicationContext applicationContext = null;

 
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		if (SpringUtils.applicationContext == null) {
			SpringUtils.applicationContext = applicationContext;
		}
	}
 
	/**
	 * @apiNote 获取applicationContext
	 * @author chenyc 2016/11/3 
	 */
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}
 
	/**
	 * @apiNote 通过name获取 Bean.
	 * @author 
	 */
	public static <T> T getBean(String name) {
		return (T)getApplicationContext().getBean(name);
	}
 
	/**
	 * @apiNote 通过class获取Bean.
	 * @author 
	 */
	public static <T> T getBean(Class<T> clazz) {
		return getApplicationContext().getBean(clazz);
	}

	public static <T> Map<String, T> getBeansOfType(Class<T> clazz){
		return getApplicationContext().getBeansOfType(clazz);
	}
 
	/**
	 * @apiNote 通过name, 以及Clazz返回指定的Bean
	 * @author 
	 */
	public static <T> T getBean(String name, Class<T> clazz) {
		return getApplicationContext().getBean(name, clazz);
	}
}