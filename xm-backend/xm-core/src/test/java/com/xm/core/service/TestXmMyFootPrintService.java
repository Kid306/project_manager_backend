package  com.xm.core.service;

import com.mdp.core.utils.BaseUtils;
import com.xm.core.entity.XmMyFootPrint;
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
public class TestXmMyFootPrintService  {

	@Autowired
	XmMyFootPrintService xmMyFootPrintService;

	/**
	 * 新增一条数据
	 ***/
	@Test
	public void insert() {
		Map<String,Object> p=BaseUtils.map("userid","dr6S","username","9a9f","bizId","4YUg","focusType","S","pbizId","eOXh","bizName","3xNI","pbizName","S19c","ftime",new Date("2023-10-03 4:17:26"),"ubranchId","5FxF","ltime",new Date("2023-10-03 4:17:26"));
		XmMyFootPrint xmMyFootPrint=BaseUtils.fromMap(p,XmMyFootPrint.class);
		xmMyFootPrintService.save(xmMyFootPrint);
		//Assert.assertEquals(1, result);
	}
	 
}
