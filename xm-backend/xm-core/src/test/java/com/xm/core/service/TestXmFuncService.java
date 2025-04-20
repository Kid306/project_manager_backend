package  com.xm.core.service;

import com.mdp.core.utils.BaseUtils;
import com.xm.core.entity.XmFunc;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;
/**
 * @author code-gen
 * @since 2023-10-3
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestXmFuncService  {

	@Autowired
	XmFuncService xmFuncService;

	/**
	 * 新增一条数据
	 ***/
	@Test
	public void insert() {
		Map<String,Object> p=BaseUtils.map("id","foKe","name","nInn","pid","kCIh","pname","1Woo","pidPaths","X685","productId","jdoi","lvl",2532,"pbranchId","xfnQ");
		XmFunc xmFunc=BaseUtils.fromMap(p,XmFunc.class);
		xmFuncService.save(xmFunc);
		//Assert.assertEquals(1, result);
	}
	 
}
