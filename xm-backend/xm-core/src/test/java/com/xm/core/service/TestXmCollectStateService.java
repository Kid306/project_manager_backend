package  com.xm.core.service;

import java.util.*;
import java.text.SimpleDateFormat;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.mdp.core.utils.BaseUtils;
import org.springframework.beans.factory.annotation.Autowired; 
import  com.xm.core.service.XmCollectStateService;
import  com.xm.core.entity.XmCollectState;
import org.springframework.boot.test.context.SpringBootTest;
/**
 * @author code-gen
 * @since 2025-3-22
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestXmCollectStateService  {

	@Autowired
	XmCollectStateService xmCollectStateService;

	/**
	 * 新增一条数据
	 ***/
	@Test
	public void insert() {
		Map<String,Object> p=BaseUtils.map("collectId","3q1J","bizDate","Bp32","fileCnt",1123,"collectName","NX55","calcTime",new Date("2025-03-22 9:11:41"),"calcStatus","x","phaseCnt",5687,"phaseFinishCnt",8322,"needPayAt",1833.7,"finishPayAt",7155.58,"needColAt",2484.03,"finishColAt",9370.91,"riskCnt",3007,"riskFinishCnt",1061,"branchId","f7b1","branchName","VTVD","budgetNouserAt",6559.28,"budgetOuserAt",1767.33,"budgetIuserAt",307,"actUserAt",1784.7,"actIuserAt",1410.91,"actOuserAt",4345.66,"actNouserAt",4818.85,"finishRate",1660.52,"budgetWorkload",8093.22,"budgetOuserWorkload",7914.22,"budgetIuserWorkload",3633.42,"estimateWorkload",7749.54,"actWorkload",3739.21,"projectStatus","8UVM","actOuserWorkload",2140,"actIuserWorkload",8910,"needPayCnt",5049,"finishPayCnt",8731,"finishPayUserCnt",7839,"needPayUserCnt",9421,"testCases",7848,"execCases",5652,"designCases",4541,"finishCases",8412,"iterationCnt",4693,"productCnt",7114,"minStartTime",new Date("2025-03-22 9:11:41"),"maxEndTime",new Date("2025-03-22 9:11:41"),"menuCnt",7223,"menuFinishCnt",3378,"menuExecCnt",6587,"menuUnstartCnt",9409,"menuCloseCnt",6890,"taskCnt",4965,"taskUnstartCnt",1202,"taskExecCnt",5746,"taskFinishCnt",2442,"taskSetCnt",1903,"taskOutCnt",7418,"taskCloseCnt",6605,"bugCnt",5851,"closedBugs",6009,"resolvedBugs",7412,"activeBugs",6626,"confirmedBugs",1637,"planWorkhours",4529.93,"planWorkerCnt",3761,"actWorkerCnt",6015,"budgetAt",4019.57,"actAt",8537.1,"initWorkload",5046,"initRate",9225,"mactWorkload",6738.94,"sumActWorkload",9270.51,"projectCnt",1004);
		XmCollectState xmCollectState=BaseUtils.fromMap(p,XmCollectState.class);
		xmCollectStateService.save(xmCollectState);
		//Assert.assertEquals(1, result);
	}
	 
}
