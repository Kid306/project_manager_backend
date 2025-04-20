package  com.xm.core.service;

import com.mdp.core.utils.BaseUtils;
import com.xm.core.entity.XmTaskBidOrder;
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
public class TestXmTaskBidOrderService  {

	@Autowired
	XmTaskBidOrderService xmTaskBidOrderService;

	/**
	 * 新增一条数据
	 ***/
	@Test
	public void insert() {
		Map<String,Object> p=BaseUtils.map("id","7EOv","ouserid","YAGA","obranchId","G1Gx","ostatus","2","ctime",new Date("2023-10-03 4:17:29"),"ltime",new Date("2023-10-03 4:17:29"),"payType","8","payStatus","6","payTime",new Date("2023-10-03 4:17:29"),"prepayId","1nrq","finalFee",4026.48,"othFee",3564.24,"originFee",7519.49,"payAt",3989.34,"payOpenid","cZtZ","payUserid","c125","payUsername","093r","taskId","AYC6","calcStatus","u","calcTime",new Date("2023-10-03 4:17:29"),"payId","1fv9","tranId","U5nV","remark","TOjD","name","m0rn","bizType","N","projectId","tKeI","otype","3","taskBudgetAt",1237);
		XmTaskBidOrder xmTaskBidOrder=BaseUtils.fromMap(p,XmTaskBidOrder.class);
		xmTaskBidOrderService.save(xmTaskBidOrder);
		//Assert.assertEquals(1, result);
	}
	 
}
