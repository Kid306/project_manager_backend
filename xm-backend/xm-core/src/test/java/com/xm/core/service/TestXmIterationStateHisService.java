package  com.xm.core.service;

import com.mdp.core.utils.BaseUtils;
import com.xm.core.entity.XmIterationStateHis;
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
public class TestXmIterationStateHisService  {

	@Autowired
	XmIterationStateHisService xmIterationStateHisService;

	/**
	 * 新增一条数据
	 ***/
	@Test
	public void insert() {
		Map<String,Object> p=BaseUtils.map("iterationId","07jR","bizDate","acVL","fileCnt",7791,"iterationName","874q","calcTime",new Date("2023-10-03 4:17:24"),"calcStatus","6","phaseCnt",9753,"phaseFinishCnt",9484,"needPayAt",3361.35,"finishPayAt",4407.57,"needColAt",7380.47,"finishColAt",6122.7,"riskCnt",6671,"riskFinishCnt",4310,"branchId","7FP6","branchName","r3dt","budgetNouserAt",7684.28,"budgetOuserAt",2683.13,"budgetIuserAt",222.22,"actUserAt",1198.66,"actIuserAt",9178.83,"actOuserAt",572.75,"actNouserAt",1785.83,"finishRate",1814.29,"budgetWorkload",9660.52,"budgetOuserWorkload",8039.09,"budgetIuserWorkload",4730.2,"estimateWorkload",1189.78,"actWorkload",1831.32,"projectStatus","B3JI","actOuserWorkload",2368,"actIuserWorkload",6512,"needPayCnt",9187,"finishPayCnt",1680,"finishPayUserCnt",4955,"needPayUserCnt",5093,"testCases",2429,"execCases",5068,"designCases",7006,"finishCases",8396,"iterationCnt",193,"productCnt",4152,"minStartTime",new Date("2023-10-03 4:17:24"),"maxEndTime",new Date("2023-10-03 4:17:24"),"menuCnt",6774,"menuFinishCnt",9694,"menuExecCnt",958,"menuUnstartCnt",4052,"menuCloseCnt",6788,"taskCnt",1367,"taskUnstartCnt",7722,"taskExecCnt",3669,"taskFinishCnt",5944,"taskSetCnt",8610,"taskOutCnt",6082,"taskCloseCnt",6352,"bugCnt",4188,"closedBugs",7482,"resolvedBugs",1549,"activeBugs",6846,"confirmedBugs",1835,"planWorkhours",648.72,"planWorkerCnt",2949,"actWorkerCnt",2000,"projectCnt",2061,"budgetAt",9136.08,"actAt",3820.47);
		XmIterationStateHis xmIterationStateHis=BaseUtils.fromMap(p,XmIterationStateHis.class);
		xmIterationStateHisService.save(xmIterationStateHis);
		//Assert.assertEquals(1, result);
	}
	 
}
