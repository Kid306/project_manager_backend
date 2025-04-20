package  com.xm.core.service;

import com.mdp.core.utils.BaseUtils;
import com.xm.core.entity.XmMenuExchange;
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
public class TestXmMenuExchangeService  {

	@Autowired
	XmMenuExchangeService xmMenuExchangeService;

	/**
	 * 新增一条数据
	 ***/
	@Test
	public void insert() {
		Map<String,Object> p=BaseUtils.map("menuId","Ig8y","menuName","8DA5","productId","hBsj","remark","PpNb","id","i48m","pid","22we","cuserid","30lk","cusername","3WLb","ctime",new Date("2023-10-03 4:17:25"),"cbranchId","24Uh","adopt","t","adoptUserid","s2H0","adoptUsername","2sIr","adoptTime",new Date("2023-10-03 4:17:25"),"closed","z","puserid","ljsv","pusername","D8PP","premark","Bd4c","notifyUserids","e1i4","notifyChannels","qVk1","notifyUsernames","k2Fd","cuserHeadImg","q98F","replyType","L");
		XmMenuExchange xmMenuExchange=BaseUtils.fromMap(p,XmMenuExchange.class);
		xmMenuExchangeService.save(xmMenuExchange);
		//Assert.assertEquals(1, result);
	}
	 
}
