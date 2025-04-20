package  com.mdp.dm.service;

import com.mdp.core.utils.BaseUtils;
import com.mdp.dm.entity.DmModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.Map;
/**
 * @author code-gen
 * @since 2024-5-4
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestModelService  {

	@Autowired
	DmModelService modelService;

	/**
	 * 新增一条数据
	 ***/
	@Test
	public void insert() {
		Map<String,Object> p=BaseUtils.map("id","C9JX","name","8dKj","branchId","28hg","cuserid","q710","ctime",new Date("2024-05-04 12:37:48"));
		DmModel model=BaseUtils.fromMap(p, DmModel.class);
		modelService.save(model);
		//Assert.assertEquals(1, result);
	}
	 
}
