package com.mdp.mq.queue;

/**
 * 队列消息压栈接口
 */
public interface Push {

	/**
	 * 将消息压入队列
	 * @param queueName
	 * @param value
	 * @return
	 */
	public Long leftPush(Object queueName,Object value);
}
