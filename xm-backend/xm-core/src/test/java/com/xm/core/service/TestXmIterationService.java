package  com.xm.core.service;

import com.mdp.core.utils.BaseUtils;
import com.xm.core.entity.XmIteration;
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
public class TestXmIterationService  {

	@Autowired
	XmIterationService xmIterationService;

	/**
	 * 新增一条数据
	 ***/
	@Test
	public void insert() {
		Map<String,Object> p=BaseUtils.map("id","S1GM","branchId","S7Rv","iterationName","BwuJ","startTime",new Date("2023-10-03 4:17:24"),"endTime",new Date("2023-10-03 4:17:24"),"onlineTime",new Date("2023-10-03 4:17:24"),"pid","O8I9","adminUserid","pxXe","adminUsername","89Vt","ctime",new Date("2023-10-03 4:17:24"),"budgetCost",9075.46,"budgetWorkload",1834.61,"seqNo","KILJ","istatus","Y","cuserid","Zu5n","cusername","T443","remark","lXSO","iphase","csr3","isTpl","i","productId","CBOb","productName","iF9K");
		XmIteration xmIteration=BaseUtils.fromMap(p,XmIteration.class);
		xmIterationService.save(xmIteration);
		//Assert.assertEquals(1, result);
	}
	 
}
