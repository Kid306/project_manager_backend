package  com.xm.core.service;

import com.mdp.core.utils.BaseUtils;
import com.xm.core.entity.XmProductStateHis;
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
public class TestXmProductStateHisService  {

	@Autowired
	XmProductStateHisService xmProductStateHisService;

	/**
	 * 新增一条数据
	 ***/
	@Test
	public void insert() {
		Map<String,Object> p=BaseUtils.map("productId","qRs3","bizDate","eiPZ","fileCnt",5630,"productName","bX6n","calcTime",new Date("2023-10-03 4:17:26"),"calcStatus","7","phaseCnt",8091,"phaseFinishCnt",5120,"needPayAt",5174.02,"finishPayAt",5302.88,"needColAt",746.23,"finishColAt",3639.89,"riskCnt",3105,"riskFinishCnt",134,"branchId","83Rc","branchName","0YDf","budgetNouserAt",1288.33,"budgetOuserAt",9734.25,"budgetIuserAt",5704.07,"actUserAt",2634.2,"actIuserAt",4663.42,"actOuserAt",2682.41,"actNouserAt",6315.31,"finishRate",815.63,"budgetWorkload",1185.92,"budgetOuserWorkload",8156.57,"budgetIuserWorkload",39.48,"estimateWorkload",1981.91,"actWorkload",9865.35,"projectStatus","u3SF","actOuserWorkload",6844,"actIuserWorkload",1334,"needPayCnt",4865,"finishPayCnt",2446,"finishPayUserCnt",6252,"needPayUserCnt",934,"testCases",5790,"execCases",8070,"designCases",3222,"finishCases",3008,"iterationCnt",4432,"productCnt",1411,"minStartTime",new Date("2023-10-03 4:17:26"),"maxEndTime",new Date("2023-10-03 4:17:26"),"menuCnt",472,"menuFinishCnt",5741,"menuExecCnt",4005,"menuUnstartCnt",6906,"menuCloseCnt",444,"taskCnt",4700,"taskUnstartCnt",5774,"taskExecCnt",1587,"taskFinishCnt",6092,"taskSetCnt",4088,"taskOutCnt",3792,"taskCloseCnt",552,"bugCnt",3650,"closedBugs",3316,"resolvedBugs",4462,"activeBugs",2209,"confirmedBugs",4951,"planWorkhours",8613.23,"planWorkerCnt",3560,"actWorkerCnt",2740,"projectCnt",8053,"budgetAt",47.91,"actAt",7631.82);
		XmProductStateHis xmProductStateHis=BaseUtils.fromMap(p,XmProductStateHis.class);
		xmProductStateHisService.save(xmProductStateHis);
		//Assert.assertEquals(1, result);
	}
	 
}
