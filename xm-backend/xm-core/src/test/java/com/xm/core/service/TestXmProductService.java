package  com.xm.core.service;

import com.mdp.core.utils.BaseUtils;
import com.xm.core.entity.XmProduct;
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
public class TestXmProductService  {

	@Autowired
	XmProductService xmProductService;

	/**
	 * 新增一条数据
	 ***/
	@Test
	public void insert() {
		Map<String,Object> p=BaseUtils.map("id","vzjR","productName","938f","branchId","y2kI","remark","cVeE","version","pLOD","pmUserid","dCPE","pmUsername","Hzd0","ctime",new Date("2023-10-03 4:17:26"),"deptid","xo3j","pstatus","L","startTime",new Date("2023-10-03 4:17:26"),"endTime",new Date("2023-10-03 4:17:26"),"deptName","Xt03","admUserid","dEK1","admUsername","V8qA","assUserid","9GY8","assUsername","3Aqj","bizProcInstId","AyU9","bizFlowState","Q","isTpl","p","baselineId","h7J8","baseTime",new Date("2023-10-03 4:17:26"),"code","ZFr0","pbudgetWorkload",6311.05,"pbudgetAmount",6005.47,"pmenuBudgetWorkload",2824.89,"pmenuBudgetAmount",2178.87,"budgetCtrl","y","phaseBudgetCtrl","W","phaseActCtrl","w","locked","y","del","e","ltime",new Date("2023-10-03 4:17:26"),"qxCode","3pEH");
		XmProduct xmProduct=BaseUtils.fromMap(p,XmProduct.class);
		xmProductService.save(xmProduct);
		//Assert.assertEquals(1, result);
	}
	 
}
