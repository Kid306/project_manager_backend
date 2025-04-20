package  com.xm.core.service;

import java.util.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.mdp.core.utils.BaseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.xm.core.entity.XmKpi;
import org.springframework.boot.test.context.SpringBootTest;
/**
 * @author code-gen
 * @since 2025-4-4
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestXmKpiService {

	@Autowired
	XmKpiService xmKpiService;

	/**
	 * 新增一条数据
	 ***/
	@Test
	public void insert() {
		Map<String,Object> p=BaseUtils.map("projectId","7ktE","branchId","wVm0","kpiIndex","an5c","kpiName","763p","maxValue","96Ap","minValue","4p47","id","xU9G","score",6465,"scoreDate",new Date("2025-04-04 13:56:50"),"bizFlowState","4pXH","bizProcInstId","O3f6","kpiValue","EMdd","remark","Fdw2","calcType","D7jZ","nextCalcDate",new Date("2025-04-04 13:56:50"),"ktype","0eb7","kresult","23mV","taskId","vn3v","kstatus","3G26","kfile","t9TN","kclass","yAFH","kadmUserid","mSV3","kadmUsername","BInE","kselfUserid","pBdf","kselfUsername","LdWC","kselfTime",new Date("2025-04-04 13:56:50"),"kadmTime",new Date("2025-04-04 13:56:50"));
		XmKpi xmKpi=BaseUtils.fromMap(p, XmKpi.class);
		xmKpiService.save(xmKpi);
		//Assert.assertEquals(1, result);
	}
	 
}
