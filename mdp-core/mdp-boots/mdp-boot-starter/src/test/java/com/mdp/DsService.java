package com.mdp;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
@Service
public class DsService {

	@Autowired
	DsSubService dsSubService;
	
	@Transactional
    public  void selectDs1(Map parameter) {

	}
	public void selectDsSub(){
		dsSubService.selectDs2(new HashMap());
	}

	@DS("ds1")
	public void selectMy(){
		this.selectDs1(new HashMap());
	}
}
