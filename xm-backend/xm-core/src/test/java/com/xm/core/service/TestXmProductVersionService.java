package  com.xm.core.service;

import com.mdp.core.utils.BaseUtils;
import com.xm.core.entity.XmProductVersion;
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
public class TestXmProductVersionService  {

	@Autowired
	XmProductVersionService xmProductVersionService;

	/**
	 * 新增一条数据
	 ***/
	@Test
	public void insert() {
		Map<String,Object> p=BaseUtils.map("id","aRTl","remark","0nOi","productId","q8Fs","vstatus","c21u","vrate",2581,"ptime",new Date("2023-10-03 4:17:27"),"startTime",new Date("2023-10-03 4:17:27"),"endTime",new Date("2023-10-03 4:17:27"),"admUserid","ftp1","admUsername","C8xx","ctime",new Date("2023-10-03 4:17:27"),"name","n15Z");
		XmProductVersion xmProductVersion=BaseUtils.fromMap(p,XmProductVersion.class);
		xmProductVersionService.save(xmProductVersion);
		//Assert.assertEquals(1, result);
	}
	 
}
