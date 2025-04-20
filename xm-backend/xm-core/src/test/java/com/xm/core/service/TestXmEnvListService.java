package  com.xm.core.service;

import com.mdp.core.utils.BaseUtils;
import com.xm.core.entity.XmEnvList;
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
public class TestXmEnvListService  {

	@Autowired
	XmEnvListService xmEnvListService;

	/**
	 * 新增一条数据
	 ***/
	@Test
	public void insert() {
		Map<String,Object> p=BaseUtils.map("id","T2b5","remark","FIh3","ipAddress","7329","port","iFpD","branchId","HrMk","accessUserid","7Vn3","accessPassword","U3wb","accessUrl","5o23","supplier","84KN","webIpAddress","7oL0","webPort","XoqK","createUserid","9X6Z","createUsername","vg1Z","createTime",new Date("2023-10-03 4:17:24"),"envState","8","startTime",new Date("2023-10-03 4:17:24"),"endTime",new Date("2023-10-03 4:17:24"),"feeAmount",7848.44,"feeRule","x5LM","projectId","O2yw","readQx","0","writeQx","h","ltime",new Date("2023-10-03 4:17:24"),"luserid","9Fml","lusername","Z9jz","name","CxrY");
		XmEnvList xmEnvList=BaseUtils.fromMap(p,XmEnvList.class);
		xmEnvListService.save(xmEnvList);
		//Assert.assertEquals(1, result);
	}
	 
}
