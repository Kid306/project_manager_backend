package  com.xm.core.service;

import com.mdp.core.utils.BaseUtils;
import com.xm.core.entity.XmIterationState;
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
public class TestXmIterationStateService  {

	@Autowired
	XmIterationStateService xmIterationStateService;

	/**
	 * 新增一条数据
	 ***/
	@Test
	public void insert() {
		Map<String,Object> p=BaseUtils.map("iterationId","6R8f","bizDate","RC9z","fileCnt",2858,"iterationName","sB5J","calcTime",new Date("2023-10-03 4:17:24"),"calcStatus","N","phaseCnt",4545,"phaseFinishCnt",5244,"needPayAt",7883.09,"finishPayAt",2024.35,"needColAt",7225.49,"finishColAt",9290.5,"riskCnt",9134,"riskFinishCnt",5585,"branchId","7ccP","branchName","aXca","budgetNouserAt",225.68,"budgetOuserAt",9148.51,"budgetIuserAt",2265.81,"actUserAt",7392.21,"actIuserAt",587.69,"actOuserAt",7975.22,"actNouserAt",3450.49,"finishRate",6648.87,"budgetWorkload",1188.18,"budgetOuserWorkload",7514.45,"budgetIuserWorkload",2813.82,"estimateWorkload",4584.48,"actWorkload",2950.43,"projectStatus","Fu7M","actOuserWorkload",9856,"actIuserWorkload",9490,"needPayCnt",7858,"finishPayCnt",3983,"finishPayUserCnt",8367,"needPayUserCnt",1228,"testCases",9315,"execCases",9038,"designCases",7701,"finishCases",6646,"iterationCnt",4076,"productCnt",33,"minStartTime",new Date("2023-10-03 4:17:24"),"maxEndTime",new Date("2023-10-03 4:17:24"),"menuCnt",1005,"menuFinishCnt",7948,"menuExecCnt",6345,"menuUnstartCnt",9917,"menuCloseCnt",5068,"taskCnt",4517,"taskUnstartCnt",9185,"taskExecCnt",1735,"taskFinishCnt",3778,"taskSetCnt",1309,"taskOutCnt",5919,"taskCloseCnt",292,"bugCnt",6927,"closedBugs",8190,"resolvedBugs",5552,"activeBugs",263,"confirmedBugs",611,"planWorkhours",1593.63,"planWorkerCnt",7994,"actWorkerCnt",2741,"projectCnt",329,"budgetAt",3243.83,"actAt",386.04);
		XmIterationState xmIterationState=BaseUtils.fromMap(p,XmIterationState.class);
		xmIterationStateService.save(xmIterationState);
		//Assert.assertEquals(1, result);
	}
	 
}
