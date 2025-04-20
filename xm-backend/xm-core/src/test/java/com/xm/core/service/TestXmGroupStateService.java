package  com.xm.core.service;

import com.mdp.core.utils.BaseUtils;
import com.xm.core.entity.XmGroupState;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.Map;
/**
 * @author code-gen
 * @since 2023-11-10
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestXmGroupStateService  {

	@Autowired
	XmGroupStateService xmGroupStateService;

	/**
	 * 新增一条数据
	 ***/
	@Test
	public void insert() {
		Map<String,Object> p=BaseUtils.map("planStartTime",new Date("2023-11-10 16:33:3"),"planEndTime",new Date("2023-11-10 16:33:3"),"actStartTime",new Date("2023-11-10 16:33:3"),"actEndTime",new Date("2023-11-10 16:33:3"),"planWorkload",1073.08,"actWorkload",7946.42,"planCostAmount",2655.46,"actCostAmount",2094.76,"finishRate",3413.5,"demandRate",7640.24,"designRate",9242.96,"devRate",8315.16,"uatRate",3971.94,"sitRate",2932.46,"ctime",new Date("2023-11-10 16:33:3"),"calcTime",new Date("2023-11-10 16:33:3"),"planWorkhours",756.86,"planWorkerCnt",6995.62,"closedBugs",939,"activeBugs",7671,"confirmedBugs",9137,"resolvedBugs",5801,"testCases",9705,"execCases",915,"designCases",1329,"finishCases",3450,"iterationCnt",1548,"taskCnt",7039,"finishTaskCnt",901,"bizDate","n1xJ","bugCnt",1553,"groupId","ki4Q","projectId","refF","projectName","8r4q","groupName","qmay");
		XmGroupState xmGroupState=BaseUtils.fromMap(p,XmGroupState.class);
		xmGroupStateService.save(xmGroupState);
		//Assert.assertEquals(1, result);
	}
	 
}
