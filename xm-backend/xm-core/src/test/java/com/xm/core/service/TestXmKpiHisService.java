package  com.xm.core.service;

import java.util.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.mdp.core.utils.BaseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.xm.core.entity.XmKpiHis;
import org.springframework.boot.test.context.SpringBootTest;
/**
 * @author code-gen
 * @since 2025-4-4
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestXmKpiHisService {

	@Autowired
	XmKpiHisService xmKpiHisService;

	/**
	 * 新增一条数据
	 ***/
	@Test
	public void insert() {
		Map<String,Object> p=BaseUtils.map("projectId","4ijj","branchId","h55P","kpiIndex","QmTM","kpiName","J33L","maxValue","AI98","minValue","9OwM","id","R2rv","score",924,"scoreDate",new Date("2025-04-04 13:56:50"),"bizFlowState","ht8r","bizProcInstId","rxI9","kpiValue","d9KF","remark","XxgP","calcType","8AiB","nextCalcDate",new Date("2025-04-04 13:56:50"),"ktype","Doif","kresult","M1xN","taskId","8p27","kstatus","3jUP","kfile","7lp0","kclass","TdGp","kadmUserid","ivyt","kadmUsername","LTHF","kselfUserid","iJtF","kselfUsername","7SBX","kselfTime",new Date("2025-04-04 13:56:50"),"kadmTime",new Date("2025-04-04 13:56:50"));
		XmKpiHis xmKpiHis=BaseUtils.fromMap(p, XmKpiHis.class);
		xmKpiHisService.save(xmKpiHis);
		//Assert.assertEquals(1, result);
	}
	 
}
