package  com.xm.core.service;

import com.mdp.core.utils.BaseUtils;
import com.xm.core.entity.XmRptConfig;
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
public class TestXmRptConfigService  {

	@Autowired
	XmRptConfigService xmRptConfigService;

	/**
	 * 新增一条数据
	 ***/
	@Test
	public void insert() {
		Map<String,Object> p=BaseUtils.map("bizId","7m3P","id","h5pB","name","0J8r","cuserid","FZ9m","cusername","q5qG","ctime",new Date("2023-10-03 4:17:28"),"cbranchId","971v","cfg","QW42","bizType","s");
		XmRptConfig xmRptConfig=BaseUtils.fromMap(p,XmRptConfig.class);
		xmRptConfigService.save(xmRptConfig);
		//Assert.assertEquals(1, result);
	}
	 
}
