package com.mdp.ds.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.springframework.stereotype.Service;

import java.util.Map;
@Service
public class DsService {
 
	
	@DS("ds1")
	public  void selectDs1(Map parameter) {
		
		//List<Map<String,Object>> lm= super.selectList("Ds.selectTest",parameter);
	}
}
