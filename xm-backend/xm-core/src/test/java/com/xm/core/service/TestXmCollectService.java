package  com.xm.core.service;

import com.mdp.core.utils.BaseUtils;
import com.xm.core.entity.XmCollect;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.Map;
/**
 * @author code-gen
 * @since 2023-10-3
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestXmCollectService  {

	@Autowired
	XmCollectService xmCollectService;

	/**
	 * 新增一条数据
	 ***/
	@Test
	public void insert() {
		Map<String,Object> p=BaseUtils.map("id","iJVx","name","DN6O","cuserid","7A1F","cusername","nFEd","ctime",new Date("2023-10-03 4:17:23"),"branchId","29Xn","deptid","9xl9");
		XmCollect xmCollect=BaseUtils.fromMap(p,XmCollect.class);
		xmCollectService.save(xmCollect);
		//Assert.assertEquals(1, result);
	}
	 
}
