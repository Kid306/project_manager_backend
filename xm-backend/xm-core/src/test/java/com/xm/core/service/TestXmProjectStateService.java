package  com.xm.core.service;

import com.mdp.core.utils.BaseUtils;
import com.xm.core.entity.XmProjectState;
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
public class TestXmProjectStateService  {

	@Autowired
	XmProjectStateService xmProjectStateService;

	/**
	 * 新增一条数据
	 ***/
	@Test
	public void insert() {
		Map<String,Object> p=BaseUtils.map("projectId","4Pz2","bizDate","9XJ2","fileCnt",3664,"projectName","k4k7","calcTime",new Date("2023-10-03 4:17:27"),"calcStatus","j","phaseCnt",8494,"phaseFinishCnt",8014,"needPayAt",838.08,"finishPayAt",9589.48,"needColAt",6729.04,"finishColAt",6922.66,"riskCnt",9896,"riskFinishCnt",8682,"branchId","utPn","branchName","3tY4","budgetNouserAt",3387.87,"budgetOuserAt",3095.46,"budgetIuserAt",4996.33,"actUserAt",7644.37,"actIuserAt",2381.97,"actOuserAt",6917.08,"actNouserAt",8567.97,"finishRate",3480.28,"budgetWorkload",7708.89,"budgetOuserWorkload",3781.99,"budgetIuserWorkload",4011.36,"estimateWorkload",177.77,"actWorkload",1491.73,"projectStatus","ID37","actOuserWorkload",3341,"actIuserWorkload",8892,"needPayCnt",3623,"finishPayCnt",8052,"finishPayUserCnt",6266,"needPayUserCnt",8864,"testCases",3901,"execCases",8391,"designCases",4329,"finishCases",7375,"iterationCnt",9428,"productCnt",8524,"minStartTime",new Date("2023-10-03 4:17:27"),"maxEndTime",new Date("2023-10-03 4:17:27"),"menuCnt",7797,"menuFinishCnt",5042,"menuExecCnt",6713,"menuUnstartCnt",9213,"menuCloseCnt",4750,"taskCnt",3589,"taskUnstartCnt",387,"taskExecCnt",3318,"taskFinishCnt",8662,"taskSetCnt",6145,"taskOutCnt",7769,"taskCloseCnt",4064,"bugCnt",8836,"closedBugs",6158,"resolvedBugs",9031,"activeBugs",9393,"confirmedBugs",8137,"planWorkhours",2681.14,"planWorkerCnt",3389,"actWorkerCnt",849,"budgetAt",4579,"actAt",8651.13);
		XmProjectState xmProjectState=BaseUtils.fromMap(p,XmProjectState.class);
		xmProjectStateService.save(xmProjectState);
		//Assert.assertEquals(1, result);
	}
	 
}
