package  com.xm.core.service;

import com.mdp.core.utils.BaseUtils;
import com.xm.core.entity.XmProductState;
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
public class TestXmProductStateService  {

	@Autowired
	XmProductStateService xmProductStateService;

	/**
	 * 新增一条数据
	 ***/
	@Test
	public void insert() {
		Map<String,Object> p=BaseUtils.map("productId","WPNW","bizDate","QzJR","fileCnt",1265,"productName","P14X","calcTime",new Date("2023-10-03 4:17:26"),"calcStatus","n","phaseCnt",3152,"phaseFinishCnt",1075,"needPayAt",9520.67,"finishPayAt",3094.83,"needColAt",6378.79,"finishColAt",5610.07,"riskCnt",8301,"riskFinishCnt",4614,"branchId","TJTo","branchName","i1td","budgetNouserAt",6283,"budgetOuserAt",2600.91,"budgetIuserAt",5747.49,"actUserAt",329.71,"actIuserAt",3796.59,"actOuserAt",3991.79,"actNouserAt",7733.54,"finishRate",6531.66,"budgetWorkload",4504.94,"budgetOuserWorkload",4552.08,"budgetIuserWorkload",872.74,"estimateWorkload",5418.48,"actWorkload",8963.96,"projectStatus","W5XW","actOuserWorkload",3893,"actIuserWorkload",5408,"needPayCnt",1801,"finishPayCnt",1586,"finishPayUserCnt",4170,"needPayUserCnt",1078,"testCases",7848,"execCases",443,"designCases",1671,"finishCases",6402,"iterationCnt",9538,"productCnt",9729,"minStartTime",new Date("2023-10-03 4:17:26"),"maxEndTime",new Date("2023-10-03 4:17:26"),"menuCnt",1980,"menuFinishCnt",7212,"menuExecCnt",2307,"menuUnstartCnt",763,"menuCloseCnt",8603,"taskCnt",7743,"taskUnstartCnt",1800,"taskExecCnt",3298,"taskFinishCnt",49,"taskSetCnt",7166,"taskOutCnt",8330,"taskCloseCnt",8783,"bugCnt",3140,"closedBugs",542,"resolvedBugs",1483,"activeBugs",4720,"confirmedBugs",6880,"planWorkhours",4381.92,"planWorkerCnt",9436,"actWorkerCnt",2538,"projectCnt",2376,"budgetAt",1850.26,"actAt",6059.91);
		XmProductState xmProductState=BaseUtils.fromMap(p,XmProductState.class);
		xmProductStateService.save(xmProductState);
		//Assert.assertEquals(1, result);
	}
	 
}
