package  com.xm.core.service;

import com.mdp.core.utils.BaseUtils;
import com.xm.core.entity.XmProductProjectLink;
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
public class TestXmProductProjectLinkService  {

	@Autowired
	XmProductProjectLinkService xmProductProjectLinkService;

	/**
	 * 新增一条数据
	 ***/
	@Test
	public void insert() {
		Map<String,Object> p=BaseUtils.map("projectId","6AtD","productId","ZjWU","ctime",new Date("2023-10-03 4:17:26"),"cuserid","cdCS","cusername","7oSo","linkStatus","l","seq",784);
		XmProductProjectLink xmProductProjectLink=BaseUtils.fromMap(p,XmProductProjectLink.class);
		xmProductProjectLinkService.save(xmProductProjectLink);
		//Assert.assertEquals(1, result);
	}
	 
}
