package com.mdp.ds.service;

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
	MallDSService mallDSService;
	
	@Test
	public void testDataSource1(){
		Map<String,Object> p=new HashMap<String,Object>();
		dsService.selectDs1(p);

		mallDSService.selectDs1(p);
	}
}
