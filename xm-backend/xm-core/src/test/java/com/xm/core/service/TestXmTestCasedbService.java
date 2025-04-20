package  com.xm.core.service;

import com.mdp.core.utils.BaseUtils;
import com.xm.core.entity.XmTestCasedb;
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
public class TestXmTestCasedbService  {

	@Autowired
	XmTestCasedbService xmTestCasedbService;

	/**
	 * 新增一条数据
	 ***/
	@Test
	public void insert() {
		Map<String,Object> p=BaseUtils.map("id","mRsO","name","RFpv","cuserid","nY63","cusername","JJmF","ctime",new Date("2023-10-03 4:17:31"),"cbranchId","bP4w","productId","TOQ0","productName","vq1l","totalCases",4265,"okCases",845,"errCases",549,"igCases",1706,"blCases",7312,"bugCnt",9848,"closedBugs",720,"resolvedBugs",7706,"activeBugs",3808,"confirmedBugs",2517,"testPlans",6056,"menus",6203,"funcs",7954,"status","y","budgetWorkload",2252.03,"actWorkload",2055.25,"pbranchId","y67j");
		XmTestCasedb xmTestCasedb=BaseUtils.fromMap(p,XmTestCasedb.class);
		xmTestCasedbService.save(xmTestCasedb);
		//Assert.assertEquals(1, result);
	}
	 
}
