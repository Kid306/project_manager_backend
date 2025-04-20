package  com.xm.core.service;

import com.mdp.core.utils.BaseUtils;
import com.xm.core.entity.XmGroupUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.Map;
/**
 * @author code-gen
 * @since 2023-11-10
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestXmGroupUserService  {

	@Autowired
	XmGroupUserService xmGroupUserService;

	/**
	 * 新增一条数据
	 ***/
	@Test
	public void insert() {
		Map<String,Object> p=BaseUtils.map("joinTime",new Date("2023-11-10 16:33:3"),"groupId","i297","userid","ztct","username","FNbW","outTime",new Date("2023-11-10 16:33:3"),"status","f","obranchId","1zPT","isPri","F","seqNo",5179,"projectId","Y968","productId","80g5","pgClass","G","obranchName","R9eR");
		XmGroupUser xmGroupUser=BaseUtils.fromMap(p,XmGroupUser.class);
		xmGroupUserService.save(xmGroupUser);
		//Assert.assertEquals(1, result);
	}
	 
}
