package com.mdp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestDataSource {

	@Autowired
	DsService dsService;

	@Autowired
	DsAllService dsAllService;

	@Autowired
	DsSubService dsSubService;
	
	@Test
	public void testDataSource1(){
		Map<String,Object> p=new HashMap<String,Object>();
		dsService.selectDs1(p);

		dsAllService.selectDs1(p);

		dsSubService.selectDs1(p);
		dsSubService.selectDs2(p);
		dsService.selectDsSub();
	}


	@Test
	public void testDataSource2(){
		dsService.selectDsSub();
	}

	@Test
	public void testDataSource3(){
		dsService.selectMy();
	}
}
