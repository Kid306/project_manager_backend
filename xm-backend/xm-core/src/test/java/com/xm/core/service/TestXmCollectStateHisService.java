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
import  com.xm.core.service.XmCollectStateHisService;
import  com.xm.core.entity.XmCollectStateHis;
import org.springframework.boot.test.context.SpringBootTest;
/**
 * @author code-gen
 * @since 2025-3-22
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestXmCollectStateHisService  {

	@Autowired
	XmCollectStateHisService xmCollectStateHisService;

	/**
	 * 新增一条数据
	 ***/
	@Test
	public void insert() {
		Map<String,Object> p=BaseUtils.map("collectId","9nMT","bizDate","KA2Q","fileCnt",2068,"collectName","ZWcP","calcTime",new Date("2025-03-22 9:11:41"),"calcStatus","w","phaseCnt",6085,"phaseFinishCnt",5420,"needPayAt",3507.68,"finishPayAt",5413.8,"needColAt",6912.71,"finishColAt",624.23,"riskCnt",90,"riskFinishCnt",9316,"branchId","2e5U","branchName","lOLu","budgetNouserAt",8047.8,"budgetOuserAt",7015.4,"budgetIuserAt",6379.79,"actUserAt",7622.6,"actIuserAt",5707.45,"actOuserAt",3649.85,"actNouserAt",9168.36,"finishRate",8394.88,"budgetWorkload",7955.65,"budgetOuserWorkload",8151.07,"budgetIuserWorkload",3594.22,"estimateWorkload",775.26,"actWorkload",3261.2,"projectStatus","O9yY","actOuserWorkload",918,"actIuserWorkload",5531,"needPayCnt",3835,"finishPayCnt",8180,"finishPayUserCnt",166,"needPayUserCnt",1245,"testCases",7813,"execCases",477,"designCases",6014,"finishCases",5917,"iterationCnt",8414,"productCnt",7450,"minStartTime",new Date("2025-03-22 9:11:41"),"maxEndTime",new Date("2025-03-22 9:11:41"),"menuCnt",6569,"menuFinishCnt",5762,"menuExecCnt",9563,"menuUnstartCnt",446,"menuCloseCnt",7339,"taskCnt",4781,"taskUnstartCnt",5732,"taskExecCnt",6212,"taskFinishCnt",9410,"taskSetCnt",5454,"taskOutCnt",2775,"taskCloseCnt",9825,"bugCnt",8861,"closedBugs",5853,"resolvedBugs",5931,"activeBugs",5438,"confirmedBugs",6457,"planWorkhours",3946.44,"planWorkerCnt",5712,"actWorkerCnt",7218,"budgetAt",286.49,"actAt",827.36,"initWorkload",2026.74,"initRate",3902,"mactWorkload",4414.64,"sumActWorkload",7257.04,"projectCnt",1095);
		XmCollectStateHis xmCollectStateHis=BaseUtils.fromMap(p,XmCollectStateHis.class);
		xmCollectStateHisService.save(xmCollectStateHis);
		//Assert.assertEquals(1, result);
	}
	 
}
