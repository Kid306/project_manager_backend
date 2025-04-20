package  com.xm.core.service;

import com.mdp.core.utils.BaseUtils;
import com.xm.core.entity.XmBranchState;
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
public class TestXmBranchStateService  {

	@Autowired
	XmBranchStateService xmBranchStateService;

	/**
	 * 新增一条数据
	 ***/
	@Test
	public void insert() {
		Map<String,Object> p=BaseUtils.map("projectCnt",8475,"bizDate","8ldp","fileCnt",5538,"calcTime",new Date("2023-10-03 4:17:21"),"calcStatus","K","phaseCnt",4781,"phaseFinishCnt",9692,"needPayAt",8425.92,"finishPayAt",9956.4,"needColAt",3078.71,"finishColAt",8442.96,"riskCnt",8532,"riskFinishCnt",6165,"branchId","ma53","branchName","FbsN","budgetNouserAt",8246.65,"budgetOuserAt",3973.88,"budgetIuserAt",1757.07,"actUserAt",4201.06,"actIuserAt",7624.29,"actOuserAt",2401.39,"actNouserAt",7796.22,"finishRate",9293.34,"budgetWorkload",1547.81,"budgetOuserWorkload",7572.81,"budgetIuserWorkload",3008.18,"estimateWorkload",2714.41,"actWorkload",8035.12,"actOuserWorkload",212,"actIuserWorkload",3315,"needPayCnt",5896,"finishPayCnt",6529,"finishPayUserCnt",3608,"needPayUserCnt",9151,"testCases",8016,"execCases",3268,"designCases",9642,"finishCases",1392,"iterationCnt",1697,"productCnt",8639,"minStartTime",new Date("2023-10-03 4:17:21"),"maxEndTime",new Date("2023-10-03 4:17:21"),"menuCnt",3614,"menuFinishCnt",6652,"menuExecCnt",821,"menuUnstartCnt",4582,"menuCloseCnt",2920,"taskCnt",1501,"taskUnstartCnt",379,"taskExecCnt",1153,"taskFinishCnt",9024,"taskSetCnt",4093,"taskOutCnt",2873,"taskCloseCnt",1203,"bugCnt",8888,"closedBugs",8746,"resolvedBugs",9151,"activeBugs",2603,"confirmedBugs",8483,"planWorkhours",6719.72,"planWorkerCnt",5342,"actWorkerCnt",824,"budgetAt",6872.37,"actAt",9293.72,"productBudgetWorkload",5002.85,"productActWorkload",1615.55);
		XmBranchState xmBranchState=BaseUtils.fromMap(p,XmBranchState.class);
		xmBranchStateService.save(xmBranchState);
		//Assert.assertEquals(1, result);
	}
	 
}
