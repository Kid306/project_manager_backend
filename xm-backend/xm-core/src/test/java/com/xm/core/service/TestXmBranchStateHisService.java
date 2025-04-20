package  com.xm.core.service;

import com.mdp.core.utils.BaseUtils;
import com.xm.core.entity.XmBranchStateHis;
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
public class TestXmBranchStateHisService  {

	@Autowired
	XmBranchStateHisService xmBranchStateHisService;

	/**
	 * 新增一条数据
	 ***/
	@Test
	public void insert() {
		Map<String,Object> p=BaseUtils.map("projectCnt",8692,"bizDate","86E6","fileCnt",663,"calcTime",new Date("2023-10-03 4:17:22"),"calcStatus","W","phaseCnt",3440,"phaseFinishCnt",9131,"needPayAt",6130.67,"finishPayAt",6416.3,"needColAt",3401.25,"finishColAt",6529.4,"riskCnt",8835,"riskFinishCnt",6708,"branchId","ldpo","branchName","PgDj","budgetNouserAt",254.49,"budgetOuserAt",3935.45,"budgetIuserAt",5812.81,"actUserAt",7670,"actIuserAt",5454.49,"actOuserAt",5721.76,"actNouserAt",6537.72,"finishRate",4836.17,"budgetWorkload",6832.31,"budgetOuserWorkload",5801.95,"budgetIuserWorkload",8037.53,"estimateWorkload",9314.76,"actWorkload",2974.97,"actOuserWorkload",3666,"actIuserWorkload",9084,"needPayCnt",2470,"finishPayCnt",2783,"finishPayUserCnt",3919,"needPayUserCnt",2757,"testCases",9842,"execCases",3765,"designCases",901,"finishCases",998,"iterationCnt",3071,"productCnt",4124,"minStartTime",new Date("2023-10-03 4:17:22"),"maxEndTime",new Date("2023-10-03 4:17:22"),"menuCnt",1647,"menuFinishCnt",8688,"menuExecCnt",221,"menuUnstartCnt",4964,"menuCloseCnt",9977,"taskCnt",3099,"taskUnstartCnt",5156,"taskExecCnt",721,"taskFinishCnt",6258,"taskSetCnt",4688,"taskOutCnt",5991,"taskCloseCnt",7820,"bugCnt",7364,"closedBugs",8111,"resolvedBugs",3743,"activeBugs",8310,"confirmedBugs",7410,"planWorkhours",7375.98,"planWorkerCnt",392,"actWorkerCnt",9717,"budgetAt",7700.2,"actAt",2272.24,"productBudgetWorkload",1990.33,"productActWorkload",4819.04);
		XmBranchStateHis xmBranchStateHis=BaseUtils.fromMap(p,XmBranchStateHis.class);
		xmBranchStateHisService.save(xmBranchStateHis);
		//Assert.assertEquals(1, result);
	}
	 
}
