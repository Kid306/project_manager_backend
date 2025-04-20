package com.mdp.mq.queue;

import com.mdp.mq.enums.SpTopic;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestSp {

 @Autowired
    Push push;
 @Test
 public void pushMsg(){
     push.leftPush(SpTopic.defTopic.name(),"测试队列推送");
 }
}

