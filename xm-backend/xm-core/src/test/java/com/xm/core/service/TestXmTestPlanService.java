package  com.xm.core.service;

import com.mdp.core.utils.BaseUtils;
import com.xm.core.entity.XmTestPlan;
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
public class TestXmTestPlanService  {

	@Autowired
	XmTestPlanService xmTestPlanService;

	/**
	 * 新增一条数据
	 ***/
	@Test
	public void insert() {
		Map<String,Object> p=BaseUtils.map("id","T08j","name","Yl1v","casedbId","53a1","casedbName","X090","projectId","yo58","projectName","7W6v","cuserid","S9J5","cusername","Gxyu","ctime",new Date("2023-10-03 4:17:31"),"stime",new Date("2023-10-03 4:17:31"),"etime",new Date("2023-10-03 4:17:31"),"status","9","tcode","c","totalCases",6597,"okCases",2861,"errCases",7445,"igCases",5337,"blCases",8461,"productId","4XC8","productName","mBTp","flowState","E","bugCnt",8414,"closedBugs",5405,"resolvedBugs",3016,"activeBugs",2175,"confirmedBugs",6812,"menus",9544,"funcs",9700,"budgetWorkload",3832.26,"actWorkload",4493.65,"summaryRemark","GXGH","cbranchId","n1aB","toTestCases",1916,"pbranchId","eBel","ptype","5");
		XmTestPlan xmTestPlan=BaseUtils.fromMap(p,XmTestPlan.class);
		xmTestPlanService.save(xmTestPlan);
		//Assert.assertEquals(1, result);
	}
	 
}
