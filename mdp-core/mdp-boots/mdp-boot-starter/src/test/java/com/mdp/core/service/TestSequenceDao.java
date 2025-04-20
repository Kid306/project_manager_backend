package com.mdp.core.service;

import com.mdp.core.dao.SequenceDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestSequenceDao {
	
	@Autowired
	SequenceDao sequenceDao;
	
	@Test
	public void testSequenceDao(){ 
		sequenceDao.generate("test11111", "测试");
	}
	@Test
	public void testSequenceDao2(){ 
		String seq=sequenceDao.generate("test2222", "测试",0,"2","xx","ff",new Date());
		System.out.println(seq);
	}
	@Test
	public void testSequenceDao3(){ 
		String seq=sequenceDao.generate("test3333", "测试",10,"4","xx","ff",new Date());
		System.out.println(seq);
	}
}
