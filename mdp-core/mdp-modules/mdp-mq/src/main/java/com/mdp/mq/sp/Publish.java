package com.mdp.mq.sp;

public interface Publish {
	public void  push(String subject,Object  message);
}
