package  com.xm.core.service;

import com.mdp.core.utils.BaseUtils;
import com.xm.core.entity.XmGroupStateHis;
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
public class TestXmGroupStateHisService  {

	@Autowired
	XmGroupStateHisService xmGroupStateHisService;

	/**
	 * 新增一条数据
	 ***/
	@Test
	public void insert() {
		Map<String,Object> p=BaseUtils.map("projectId","JB4Z","bizDate","Vas4","fileCnt",6721,"projectName","Cm0r","calcTime",new Date("2024-07-06 18:3:31"),"calcStatus","o","phaseCnt",6912,"phaseFinishCnt",2453,"needPayAt",6669.22,"finishPayAt",501.13,"needColAt",889.29,"finishColAt",9981.6,"riskCnt",5975,"riskFinishCnt",1960,"branchId","t5xI","branchName","14la","budgetNouserAt",7182.73,"budgetOuserAt",6392.04,"budgetIuserAt",604.85,"actUserAt",7148.83,"actIuserAt",4985.01,"actOuserAt",2244.83,"actNouserAt",2684.31,"finishRate",9656.57,"budgetWorkload",725.82,"budgetOuserWorkload",3919.52,"budgetIuserWorkload",2485.12,"estimateWorkload",8517.61,"actWorkload",7594.99,"projectStatus","OFe4","actOuserWorkload",7026,"actIuserWorkload",2166,"needPayCnt",1484,"finishPayCnt",4280,"finishPayUserCnt",1615,"needPayUserCnt",6700,"testCases",7102,"execCases",7232,"designCases",6828,"finishCases",877,"iterationCnt",9891,"productCnt",5203,"minStartTime",new Date("2024-07-06 18:3:31"),"maxEndTime",new Date("2024-07-06 18:3:31"),"menuCnt",8300,"menuFinishCnt",1684,"menuExecCnt",9272,"menuUnstartCnt",1667,"menuCloseCnt",2334,"taskCnt",9099,"taskUnstartCnt",5003,"taskExecCnt",4871,"taskFinishCnt",3696,"taskSetCnt",77,"taskOutCnt",7058,"taskCloseCnt",275,"bugCnt",2611,"closedBugs",8369,"resolvedBugs",5288,"activeBugs",226,"confirmedBugs",4111,"planWorkhours",6335.77,"planWorkerCnt",8275,"actWorkerCnt",3287,"budgetAt",7782.86,"actAt",2720.06,"groupId","DiBZ","groupName","Mfd7");
		XmGroupStateHis xmGroupStateHis=BaseUtils.fromMap(p,XmGroupStateHis.class);
		xmGroupStateHisService.save(xmGroupStateHis);
		//Assert.assertEquals(1, result);
	}
	 
}
