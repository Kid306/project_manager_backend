package  com.xm.core.service;

import com.mdp.core.utils.BaseUtils;
import com.xm.core.entity.XmTestPlanCase;
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
public class TestXmTestPlanCaseService  {

	@Autowired
	XmTestPlanCaseService xmTestPlanCaseService;

	/**
	 * 新增一条数据
	 ***/
	@Test
	public void insert() {
		Map<String,Object> p=BaseUtils.map("bugs",6055,"execUserid","DQJ8","caseId","13gn","ltime",new Date("2023-10-03 4:17:31"),"ctime",new Date("2023-10-03 4:17:31"),"execStatus","1","execUsername","9w8T","priority","Y","remark","0JEi","testStep","36V2","planId","D05r","projectId","20o2","budgetWorkload",9508.89,"actWorkload",6428.48,"initWorkload",8696.54,"execDate","0KDl","execType","t","productId","Utf3");
		XmTestPlanCase xmTestPlanCase=BaseUtils.fromMap(p,XmTestPlanCase.class);
		xmTestPlanCaseService.save(xmTestPlanCase);
		//Assert.assertEquals(1, result);
	}
	 
}
