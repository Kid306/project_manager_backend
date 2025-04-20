package com.mdp.dev.dao;

import com.mdp.core.entity.DmField;
import com.mdp.core.entity.DmTable;
import com.mdp.dm.base.service.DataMetaBaseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestDbInfo {

	@Autowired
	DataMetaBaseService dataMetaService;

	@Test
	public void getTable(){
		DmTable tableInfos= dataMetaService.getTable("master","sys_user");
		Assert.isTrue(tableInfos!=null);
	}

	@Test
	public void getTableInfos(){
		List<DmTable> tableInfos= dataMetaService.getTableList("master","sys_user");
		Assert.isTrue(tableInfos.size()>0);
	}
	@Test
	public void getTableColumnInfos(){
		List<DmField> tableInfos= dataMetaService.getColumnsInfo("adm","sys_user");
		Assert.isTrue(tableInfos.size()>0);
	}

	@Test
	public void getTableColumnInfosFromCache(){
		List<DmField> tableInfos= dataMetaService.getColumnsInfoFirstCache("adm","sys_user");
		Assert.isTrue(tableInfos.size()>0);
	}


	@Test
	public void getTableColumnInfosSqlFromCache(){
		List<DmField> tableInfos= dataMetaService.getColumnsInfoBySqlFirstCache("adm","select * from sys_user");
		Assert.isTrue(tableInfos.size()>0);
	}
}
