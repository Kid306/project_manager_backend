package com.mdp.mq.queue;
import java.util.List;

public interface Pop {

	/**
	 * 将最新一条消息弹出
	 * @param queueName 队列名称
	 * @return
	 * @param <T>
	 */
	default  <T> T  rightPop(Object queueName){
		return this.rightPop(queueName,10000L);
	}
	/*
	 * 毫秒
	 */
	default Long timeOut() {
		return (long) 1000;
	}

	/**
	 * 将最新一条消息弹出
	 * @param queueName 队列名称
	 * @param millseconds 超时时间
	 * @return
	 * @param <T>
	 */
	<T> T  rightPop(Object queueName,Long millseconds);

	Long  size(Object queueName);

	/**
	 * 获取区间内的消息
	 * @param queueName 队列名称
	 * @param start 开始索引，从0开始
	 * @param end 结束索引
	 * @return
	 * @param <T>
	 */
	<T> List<T> range(Object queueName,long start,long end);
}
