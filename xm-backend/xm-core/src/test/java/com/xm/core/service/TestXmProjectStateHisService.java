package  com.xm.core.service;

import com.mdp.core.utils.BaseUtils;
import com.xm.core.entity.XmProjectStateHis;
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
public class TestXmProjectStateHisService  {

	@Autowired
	XmProjectStateHisService xmProjectStateHisService;

	/**
	 * 新增一条数据
	 ***/
	@Test
	public void insert() {
		Map<String,Object> p=BaseUtils.map("projectId","jdZG","bizDate","KBaw","fileCnt",4704,"projectName","8Zr3","calcTime",new Date("2023-10-03 4:17:28"),"calcStatus","A","phaseCnt",3152,"phaseFinishCnt",6195,"needPayAt",2919.16,"finishPayAt",4148.22,"needColAt",4401.06,"finishColAt",4974.34,"riskCnt",3019,"riskFinishCnt",9704,"branchId","3MS5","branchName","JwU8","budgetNouserAt",447.06,"budgetOuserAt",1357.77,"budgetIuserAt",1319.77,"actUserAt",7347.33,"actIuserAt",189.92,"actOuserAt",3045.71,"actNouserAt",6445.92,"finishRate",4464.77,"budgetWorkload",8728.29,"budgetOuserWorkload",6599.19,"budgetIuserWorkload",6219.88,"estimateWorkload",24.61,"actWorkload",9553.01,"projectStatus","GqsF","actOuserWorkload",3075,"actIuserWorkload",7527,"needPayCnt",9754,"finishPayCnt",6320,"finishPayUserCnt",25,"needPayUserCnt",2183,"testCases",9117,"execCases",1967,"designCases",9493,"finishCases",2436,"iterationCnt",1554,"productCnt",6876,"minStartTime",new Date("2023-10-03 4:17:28"),"maxEndTime",new Date("2023-10-03 4:17:28"),"menuCnt",2560,"menuFinishCnt",2669,"menuExecCnt",3013,"menuUnstartCnt",4129,"menuCloseCnt",5184,"taskCnt",4562,"taskUnstartCnt",9810,"taskExecCnt",8162,"taskFinishCnt",6077,"taskSetCnt",5652,"taskOutCnt",757,"taskCloseCnt",1008,"bugCnt",2296,"closedBugs",7556,"resolvedBugs",8125,"activeBugs",4925,"confirmedBugs",9610,"planWorkhours",1553.45,"planWorkerCnt",1466,"actWorkerCnt",6586,"budgetAt",7070.29,"actAt",1674.9);
		XmProjectStateHis xmProjectStateHis=BaseUtils.fromMap(p,XmProjectStateHis.class);
		xmProjectStateHisService.save(xmProjectStateHis);
		//Assert.assertEquals(1, result);
	}
	 
}
