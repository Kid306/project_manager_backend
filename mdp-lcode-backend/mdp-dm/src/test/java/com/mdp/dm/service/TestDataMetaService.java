package com.mdp.dm.service;

import com.mdp.core.entity.DmField;
import com.mdp.dm.base.service.DataMetaCacheBaseService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author code-gen
 * @since 2024-4-25
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestDataMetaService {

	@Autowired
	DmDataMetaService dataMetaService;

	@Autowired
	DataMetaCacheBaseService dataMetaCacheService;

	/**
	 * 新增一条数据
	 ***/
	@Test
	public void all_test() {
		dataMetaCacheService.putFields("master","sys_user",null);

		List<DmField> fields= dataMetaService.getColumnsInfoFirstCache("master","sys_user");
 		Assert.assertEquals(true, fields.size()>1);
		boolean hadPk=fields.stream().filter(k->k.isPk).findAny().isPresent();
		Assert.assertEquals(true,hadPk);
		dataMetaCacheService.putFields("master","select * from sys_user",null);

		fields=dataMetaService.getColumnsInfoBySqlFirstCache("master","select * from sys_user");
		Assert.assertEquals(true, fields.size()>1);

		boolean hadPk2=fields.stream().filter(k->k.isPk).findAny().isPresent();
		Assert.assertEquals(false,hadPk2);

	}
	@Test
	public void getPksTest() {
		List<DmField> fields= dataMetaService.getColumnsInfoFirstCache("master","sys_user");
		boolean hadPk=fields.stream().filter(k->k.isPk).findAny().isPresent();
		Assert.assertEquals(true,hadPk);
		//List<String> pks=dataMetaService.getPrimaryKeys("master","select * from sys_user");
		//Assert.assertEquals(true,pks.size()>0);

	}
}
