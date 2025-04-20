package  com.xm.core.service;

import com.mdp.core.utils.BaseUtils;
import com.xm.core.entity.XmQuestionHandle;
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
public class TestXmQuestionHandleService  {

	@Autowired
	XmQuestionHandleService xmQuestionHandleService;

	/**
	 * 新增一条数据
	 ***/
	@Test
	public void insert() {
		Map<String,Object> p=BaseUtils.map("id","I3XN","handlerUserid","iAHD","handlerUsername","vuL9","handleSolution","fcSg","receiptMessage","5veY","receiptTime",new Date("2023-10-03 4:17:28"),"handleStatus","l7h2","bizProcInstId","1rAv","bizFlowState","A","questionId","eP6Y","lastUpdateTime",new Date("2023-10-03 4:17:28"),"createTime",new Date("2023-10-03 4:17:28"),"actWorkload",3397.55,"actCostAmount",8278.53,"urls","8517","targetUserid","iBnH","targetUsername","u0nK");
		XmQuestionHandle xmQuestionHandle=BaseUtils.fromMap(p,XmQuestionHandle.class);
		xmQuestionHandleService.save(xmQuestionHandle);
		//Assert.assertEquals(1, result);
	}
	 
}
