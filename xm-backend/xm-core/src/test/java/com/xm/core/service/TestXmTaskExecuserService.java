package  com.xm.core.service;

import com.mdp.core.utils.BaseUtils;
import com.xm.core.entity.XmTaskExecuser;
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
public class TestXmTaskExecuserService  {

	@Autowired
	XmTaskExecuserService xmTaskExecuserService;

	/**
	 * 新增一条数据
	 ***/
	@Test
	public void insert() {
		Map<String,Object> p=BaseUtils.map("createTime",new Date("2023-10-03 4:17:30"),"taskId","0N0w","userid","9G98","startTime",new Date("2023-10-03 4:17:30"),"endTime",new Date("2023-10-03 4:17:30"),"status","00oj","remarks","3Q3j","createUserid","dc0s","createUsername","hv3t","username","6TLR","matchScore",946.6,"quoteWeekday",2506.47,"quoteAmount",2997.47,"quoteTime",new Date("2023-10-03 4:17:30"),"projectId","xp73","phaseId","Rz9B","skillRemark","hb5a","quoteWorkload",4275.03,"quoteStartTime",new Date("2023-10-03 4:17:30"),"quoteEndTime",new Date("2023-10-03 4:17:30"),"branchId","z7KT","phaseName","iB1H","taskName","JM2n","distUserid","VAsi","distUsername","nMDi","execUserBranchId","I1lW","shareKey","a69J","sfeeRate",1775,"sfee",7721.16,"provinceId","Xa10","provinceName","pWuz","cityId","463n","cityName","DXwR","areaId","2b47","areaName","3jNE","gradeId","oxF5","guardId","B4CU","ilvlId","ZlFb","creditId","WsV7","ctotalBids",7763,"srvTimes",3324,"cmonthExp",6890.04,"cmonthBids",4139,"bidDirect","H","skillIds","c1gY","skillNames","1ZoX","upRate",645,"adjustScore",241,"finalScore",5202,"adjustRemark","P8KI","csixBids",7471,"csixExp",9971.28,"csixAt",5087);
		XmTaskExecuser xmTaskExecuser=BaseUtils.fromMap(p,XmTaskExecuser.class);
		xmTaskExecuserService.save(xmTaskExecuser);
		//Assert.assertEquals(1, result);
	}
	 
}
