package  com.xm.core.service;

import com.mdp.core.utils.BaseUtils;
import com.xm.core.entity.XmBudgetRecord;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.Map;
/**
 * @author code-gen
 * @since 2024-7-6
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestXmBudgetRecordService  {

	@Autowired
	XmBudgetRecordService xmBudgetRecordService;

	/**
	 * 新增一条数据
	 ***/
	@Test
	public void insert() {
		Map<String,Object> p=BaseUtils.map("projectId","V8PJ","userid","eSA6","budgetAt",1105.75,"id","r0N3","remark","sUNg","username","wD4l","subjectId","lJEd","bizSdate",new Date("2024-07-06 18:3:31"),"bizEdate",new Date("2024-07-06 18:3:31"),"bizMonth","VSc7","instId","F6H0","bizFlowState","8","costType","0","subjectName","8uyx","branchId","34Pm","ubranchId","8ls7","pid","yVm6","taskId","v1c9");
		XmBudgetRecord xmBudgetRecord=BaseUtils.fromMap(p,XmBudgetRecord.class);
		xmBudgetRecordService.save(xmBudgetRecord);
		//Assert.assertEquals(1, result);
	}
	 
}
