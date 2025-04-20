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
import  com.xm.core.service.XmProjectService;
import  com.xm.core.entity.XmProject;
import org.springframework.boot.test.context.SpringBootTest;
/**
 * @author code-gen
 * @since 2025-4-4
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestXmProjectService  {

	@Autowired
	XmProjectService xmProjectService;

	/**
	 * 新增一条数据
	 ***/
	@Test
	public void insert() {
		Map<String,Object> p=BaseUtils.map("id","HjLX","code","7Vy1","name","MX9a","xmType","0P6i","startTime",new Date("2025-04-04 13:56:50"),"endTime",new Date("2025-04-04 13:56:50"),"urgent","3vzg","priority","W2er","description","yccp","createUserid","69kD","createUsername","6ZL4","createTime",new Date("2025-04-04 13:56:50"),"assess","Ffk8","assessRemarks","XN4y","status","4p2H","branchId","5B4M","planTotalCost",7999.62,"bizProcInstId","sUuN","bizFlowState","I","planNouserAt",6411.96,"planIuserAt",1155.48,"planOuserAt",4127.55,"locked","S","baseTime",new Date("2025-04-04 13:56:50"),"baseRemark","O4q4","baselineId","z4Tk","planWorkload",8987.81,"totalReceivables",4511.56,"budgetMarginRate",6712.29,"contractAmt",8725.3,"planIuserPrice",3434.78,"planOuserPrice",6659.83,"planOuserCnt",7147,"planIuserCnt",7540,"planWorkingHours",7966,"taxRate",4726.83,"planIuserWorkload",4475.78,"planOuserWorkload",11.89,"fromTplId","lDFy","budgetCtrl","b","deptid","d01n","showOut","2","isTpl","9","pmUserid","psnJ","pmUsername","NS7d","assUserid","1dJ9","assUsername","g5CI","admUserid","M517","admUsername","n9UC","budgetEarly","Z","phaseActCtrl","n","del","I","ltime",new Date("2025-04-04 13:56:50"),"ostatus","8","workType","zeYz","wtype","8","earlyAmt",2302.8,"maxTaskAmt",7970.21,"menuLink","Q","phaseLink","D","tplType","O","qxCode","M9W0","deptidPath","oM97","tagIds","aCYA","currPhase","TXdV","openKpi","un6P","kpiStatus","3NHf","actStartTime",new Date("2025-04-04 13:56:50"),"actEndTime",new Date("2025-04-04 13:56:50"));
		XmProject xmProject=BaseUtils.fromMap(p,XmProject.class);
		xmProjectService.save(xmProject);
		//Assert.assertEquals(1, result);
	}
	 
}
