package com.mdp.mq.sp;

import com.mdp.mq.enums.SpTopic;

public interface SubscriberProvider  {

	
	default public void receiveMessage(String channelName,Object message) {

	}

	public default String getTopicName(){
		return SpTopic.defTopic.name();
	}



}
