package  com.xm.core.service;

import com.mdp.core.utils.BaseUtils;
import com.xm.core.entity.XmMenuState;
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
public class TestXmMenuStateService  {

	@Autowired
	XmMenuStateService xmMenuStateService;

	/**
	 * 新增一条数据
	 ***/
	@Test
	public void insert() {
		Map<String,Object> p=BaseUtils.map("menuId","sWUM","finishRate",7810.91,"menuStatus","z","ctime",new Date("2023-10-03 4:17:25"),"calcTime",new Date("2023-10-03 4:17:25"),"menuName","JzG1","planWorkhours",8260.48,"planWorkerCnt",3911.78,"closedBugs",1694,"activeBugs",3975,"confirmedBugs",5831,"resolvedBugs",966,"productId","18uK","testCases",4895,"execCases",2073,"designCases",9167,"finishCases",7605,"bizDate","MoHA","bugCnt",8294,"taskCnt",3230,"taskUnstartCnt",6784,"taskExecCnt",6001,"taskFinishCnt",8434,"taskSetCnt",1965,"taskOutCnt",9542,"taskCloseCnt",4391,"budgetNouserAt",3074.23,"budgetOuserAt",5417.66,"budgetIuserAt",3395.14,"actUserAt",4296.5,"actIuserAt",763.51,"actOuserAt",7041.79,"actNouserAt",9651.39,"budgetWorkload",9752.61,"budgetOuserWorkload",6279.35,"budgetIuserWorkload",6602.24,"actWorkload",220.95,"actOuserWorkload",3783.96,"actIuserWorkload",1668.46,"estimateWorkload",6523.64,"budgetAt",563.11,"actAt",8782.04,"minStartTime",new Date("2023-10-03 4:17:25"),"maxEndTime",new Date("2023-10-03 4:17:25"),"productCnt",190,"iterationCnt",8822,"projectCnt",7165);
		XmMenuState xmMenuState=BaseUtils.fromMap(p,XmMenuState.class);
		xmMenuStateService.save(xmMenuState);
		//Assert.assertEquals(1, result);
	}
	 
}
