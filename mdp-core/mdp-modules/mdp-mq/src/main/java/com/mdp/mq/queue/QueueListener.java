package com.mdp.mq.queue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class QueueListener<T> {

	Logger logger= LoggerFactory.getLogger(QueueListener.class);
	ExecutorService e = Executors.newFixedThreadPool(3);

	@Autowired
	Pop pop;
	
	public abstract Object getQueueKey();
	
	public abstract void handleMessage(T message);
	
	/*
	 * 毫秒
	 */
	public Long timeOut() {
		return (long) 1000000;
	}

	public void popMessage() {
		e.execute(new Runnable() {
			public void run() {
				while(true) {
					try {
						T  message=(T) pop.rightPop(getQueueKey(), timeOut());

						if(message==null||"".equals(message)||"null".equals(message)){
							//防止循环过快，如果拿到时null，说明没有消息来了，暂停一会
							Thread.sleep(timeOut());
							continue;
						}

						handleMessage(message);
					} catch (Exception e) {
                        try {
                            Thread.sleep(timeOut());
                        } catch (InterruptedException ex) {
                        }
                        logger.error("队列消息监听异常",e);
					}
				}

			}
		});
	}
}
