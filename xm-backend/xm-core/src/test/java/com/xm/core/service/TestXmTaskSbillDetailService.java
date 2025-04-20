package  com.xm.core.service;

import com.mdp.core.utils.BaseUtils;
import com.xm.core.entity.XmTaskSbillDetail;
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
public class TestXmTaskSbillDetailService  {

	@Autowired
	XmTaskSbillDetailService xmTaskSbillDetailService;

	/**
	 * 新增一条数据
	 ***/
	@Test
	public void insert() {
		Map<String,Object> p=BaseUtils.map("userid","TOuQ","username","e2kf","ctime",new Date("2023-10-03 7:17:15"),"taskId","QMRq","bizDate","jHA5","remark","Q6wj","id","BgXf","sbillId","Ur45","stime",new Date("2023-10-03 7:17:15"),"sstatus","V","amt",2618.82,"samt",6745,"workload",7633.98,"projectId","lDyP","sworkload",4535.83,"bizMonth","lL7f","budgetAt",8680.07,"budgetWorkload",1531.03,"initWorkload",6080.77,"quoteAt",2909.25,"quoteWorkload",1860,"sschemel","u","uniPrice",3740.49,"qendTime",new Date("2023-10-03 7:17:15"),"qstartTime",new Date("2023-10-03 7:17:15"),"actEndTime",new Date("2023-10-03 7:17:15"),"actStartTime",new Date("2023-10-03 7:17:15"),"oshare","3","shareFee",9084,"sfee",762.88,"sfeeRate",7114,"cpId","ivdI","cpName","31k6","cpType","x","distUserid","G5ie","distUsername","OorQ","shareKey","8c11","taskOut","P","crowd","d","othFee",8728.23,"feeRemark","0t6k","tactAt",0,"taskName","J3G6","subjectId","","subjectName","vm1Y","branchId","DzPi");
		XmTaskSbillDetail xmTaskSbillDetail=BaseUtils.fromMap(p,XmTaskSbillDetail.class);
		xmTaskSbillDetailService.save(xmTaskSbillDetail);
		//Assert.assertEquals(1, result);
	}
	 
}
