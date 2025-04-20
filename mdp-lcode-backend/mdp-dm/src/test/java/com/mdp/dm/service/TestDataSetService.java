package  com.mdp.dm.service;

import com.mdp.core.utils.BaseUtils;
import com.mdp.dm.entity.DmDataSet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.Map;
/**
 * @author code-gen
 * @since 2024-4-25
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestDataSetService  {

	@Autowired
	DmDataSetService dataSetService;

	/**
	 * 新增一条数据
	 ***/
	@Test
	public void insert() {
		Map<String,Object> p=BaseUtils.map("id","V5x5","dsSql","NApC","dataSource","xVD0","dataModel","Cbk3","title","FR82","cuserid","ns94","cbranchId","T9v6","ctime",new Date("2024-04-25 22:47:10"),"euserid","j1ZK","etime",new Date("2024-04-25 22:47:10"),"idTitleLinks","BS2r");
		DmDataSet dataSet=BaseUtils.fromMap(p, DmDataSet.class);
		dataSetService.save(dataSet);
		//Assert.assertEquals(1, result);
	}
	 
}
