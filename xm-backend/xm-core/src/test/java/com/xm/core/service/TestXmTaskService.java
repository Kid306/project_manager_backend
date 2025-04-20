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
import  com.xm.core.service.XmTaskService;
import  com.xm.core.entity.XmTask;
import org.springframework.boot.test.context.SpringBootTest;
/**
 * @author code-gen
 * @since 2025-4-4
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestXmTaskService  {

	@Autowired
	XmTaskService xmTaskService;

	/**
	 * 新增一条数据
	 ***/
	@Test
	public void insert() {
		Map<String,Object> p=BaseUtils.map("id","6bfU","name","wL3s","parentTaskid","KhaH","parentTaskname","Lrh1","projectId","pT9H","projectName","DnJx","level","A7lT","sortLevel","JN6C","executorUserid","7NYz","executorUsername","QFAf","preTaskid","oyXz","preTaskname","I56E","startTime",new Date("2025-04-04 13:56:50"),"endTime",new Date("2025-04-04 13:56:50"),"milestone","nY6K","description","W980","remarks","iNvG","createUserid","RujX","createUsername","E91r","createTime",new Date("2025-04-04 13:56:50"),"rate",6030,"budgetAt",5845.25,"budgetWorkload",9932.74,"actAt",7217.79,"actWorkload",307.35,"taskState","x","taskType","ZLmb","taskClass","u","toTaskCenter","Q","actStartTime",new Date("2025-04-04 13:56:50"),"actEndTime",new Date("2025-04-04 13:56:50"),"bizProcInstId","oL26","bizFlowState","b","phaseId","d7zN","phaseName","PS6D","taskSkillNames","18Z7","exeUsernames","FScy","taskSkillIds","fcAd","exeUserids","FaND","taskOut","D","planType","UVgc","settleSchemel","zja7","menuId","XJyO","menuName","6uT5","productId","79Ts","cbranchId","CI5y","cdeptid","FlMx","tagIds","IBUn","tagNames","A86Y","ntype","H","childrenCnt",6324,"ltime",new Date("2025-04-04 13:56:50"),"pidPaths","cHYw","lvl",3625,"isTpl","b","keyPath","b","uniInnerPrice",7992.37,"uniOutPrice",186.02,"calcType","2","ptype","N","wtype","1","bctrl","4","initWorkload",7856.34,"shareFee",4272,"oshare","j","crowd","G","browseUsers",9584,"execUsers",4323,"cityId","o94x","cityName","XUM7","regionType","Q","browseTimes",2169,"capaLvls","dgRJ","tranMode","M","supRequires","o98g","hot","t","top","t","urgent","j","crmSup","8","bidStep","z","interestLvls","38KH","filePaths","LF4P","estate","P","efunds",824.5259,"etoPlatTime",new Date("2025-04-04 13:56:50"),"etoDevTime",new Date("2025-04-04 13:56:50"),"ebackTime",new Date("2025-04-04 13:56:50"),"topStime",new Date("2025-04-04 13:56:50"),"topEtime",new Date("2025-04-04 13:56:50"),"hotStime",new Date("2025-04-04 13:56:50"),"hotEtime",new Date("2025-04-04 13:56:50"),"urgentStime",new Date("2025-04-04 13:56:50"),"urgentEtime",new Date("2025-04-04 13:56:50"),"quoteFinalAt",1000.41,"provinceId","02r0","provinceName","pcBD","areaId","QsD1","areaName","xQ9o","status","5","bidEtime",new Date("2025-04-04 13:56:50"),"serviceId","oBMU","creditId","Pn1u","pbranchId","n0VW","subTaskCnt",5067,"subPlanCnt",3904,"weight",7268,"initRate",9910,"mactWorkload",4476.73,"sumActWorkload",5194.66,"sonSumMactWorkload",2631.08,"sonSumInitWorkload",2985.89,"openLvl","2rly","openValid","Zt84","validStatus","W");
		XmTask xmTask=BaseUtils.fromMap(p,XmTask.class);
		xmTaskService.save(xmTask);
		//Assert.assertEquals(1, result);
	}
	 
}
