package  com.xm.core.service;

import com.mdp.core.utils.BaseUtils;
import com.xm.core.entity.XmTaskSbill;
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
public class TestXmTaskSbillService  {

	@Autowired
	XmTaskSbillService xmTaskSbillService;

	/**
	 * 新增一条数据
	 ***/
	@Test
	public void insert() {
		Map<String,Object> p=BaseUtils.map("id","dS4e","title","JzLu","amt",4011.19,"ctime",new Date("2023-10-03 4:17:30"),"cuserid","x9jT","cusername","yloz","remark","GkkG","branchId","8Gx0","deptid","z1Ww","cpId","OwGk","cpName","Fv6Z","workload",8442.4,"bizMonth","n1Ts","bizDate","1hHU","bizFlowState","DZrQ","bizProcInstId","3x5L","ltime",new Date("2023-10-03 4:17:30"),"status","b","fmsg","XoVy","projectId","F75K","projectName","8i8Q","userCnt",4597,"cpType","7");
		XmTaskSbill xmTaskSbill=BaseUtils.fromMap(p,XmTaskSbill.class);
		xmTaskSbillService.save(xmTaskSbill);
		//Assert.assertEquals(1, result);
	}
	 
}
