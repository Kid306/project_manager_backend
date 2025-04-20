package  com.xm.core.service;

import com.mdp.core.utils.BaseUtils;
import com.xm.core.entity.XmWorkload;
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
public class TestXmWorkloadService  {

	@Autowired
	XmWorkloadService xmWorkloadService;

	/**
	 * 新增一条数据
	 ***/
	@Test
	public void insert() {
		Map<String,Object> p=BaseUtils.map("userid","BPnJ","username","Z7cN","ctime",new Date("2023-10-03 4:17:31"),"taskId","0oEg","cuserid","6wdC","bizDate","NT2H","wstatus","0","remark","41HQ","ttype","aSwd","id","297M","stime",new Date("2023-10-03 4:17:31"),"sstatus","x","workload",6103.38,"rworkload",5431.71,"cusername","RYtl","projectId","D6nQ","branchId","Q32n","ubranchId","fht1","sbillId","e59r","detailId","njJe","menuId","igWX","productId","JBl0","caseId","j08w","planId","42q2","bugId","Xyz4","bizType","M","bizName","m9f1","funcId","oCax");
		XmWorkload xmWorkload=BaseUtils.fromMap(p,XmWorkload.class);
		xmWorkloadService.save(xmWorkload);
		//Assert.assertEquals(1, result);
	}
	 
}
