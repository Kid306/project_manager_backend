package com.mdp.mq.sp;

public interface Subscriber {

	void  receiveMessage(String channelName,Object  message);
}
