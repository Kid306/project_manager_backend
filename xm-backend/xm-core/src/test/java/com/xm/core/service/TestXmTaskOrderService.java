package  com.xm.core.service;

import com.mdp.core.utils.BaseUtils;
import com.xm.core.entity.XmTaskOrder;
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
public class TestXmTaskOrderService  {

	@Autowired
	XmTaskOrderService xmTaskOrderService;

	/**
	 * 新增一条数据
	 ***/
	@Test
	public void insert() {
		Map<String,Object> p=BaseUtils.map("ouserid","6o1W","obranchId","79BW","ostatus","9","ctime",new Date("2023-10-03 4:17:30"),"ltime",new Date("2023-10-03 4:17:30"),"payType","8","payStatus","n","payTime",new Date("2023-10-03 4:17:30"),"prepayId","SN39","id","Djkd","finalFee",2703.95,"othFee",5766.63,"originFee",6200.49,"payAt",4254.24,"payAuthId","2kk6","payOpenid","0PJ0","payUserid","Kxl5","payUsername","BvN9","discount",8814,"topFee",3544.04,"topStime",new Date("2023-10-03 4:17:30"),"topEtime",new Date("2023-10-03 4:17:30"),"hotFee",160.33,"hotStime",new Date("2023-10-03 4:17:30"),"hotEtime",new Date("2023-10-03 4:17:30"),"top","b","hot","M","crmSupFee",1983.22,"urgentFee",9335.1,"urgent","s","crmSup","c","efunds",1776.81,"estate","4","etoPlatTime",new Date("2023-10-03 4:17:30"),"etoDevTime",new Date("2023-10-03 4:17:30"),"ebackTime",new Date("2023-10-03 4:17:30"),"taskId","ZZ5t","topDays",7957,"hotDays",9540,"urgentDays",6818,"urgentStime",new Date("2023-10-03 4:17:30"),"urgentEtime",new Date("2023-10-03 4:17:30"),"calcStatus","m","calcTime",new Date("2023-10-03 4:17:30"),"oshare","h","shareFee",7290.43,"payId","Vhcq","tranId","c6BS","remark","dr19","name","j9s5","bizType","S","projectId","gsS7");
		XmTaskOrder xmTaskOrder=BaseUtils.fromMap(p,XmTaskOrder.class);
		xmTaskOrderService.save(xmTaskOrder);
		//Assert.assertEquals(1, result);
	}
	 
}
