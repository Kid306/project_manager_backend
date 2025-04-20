package com.mdp.safe.client.service;

import com.mdp.mq.queue.Push;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class MdpLoginQueueService {
	
	@Autowired
    Push push;

	
	public String queueKey="MdpLoginQueue";
	
	public void pushLoginRecord(Map<String,Object> loginRecord) {
		push.leftPush(queueKey, loginRecord);
	}
}
