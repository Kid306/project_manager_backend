package  com.mdp.sys.service;

import com.mdp.core.utils.BaseUtils;
import com.mdp.sys.entity.NotifyMsg;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.Map;
/**
 * @author code-gen
 * @since 2023-9-29
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestNotifyMsgService  {

	@Autowired
	NotifyMsgService notifyMsgService;

	/**
	 * 新增一条数据
	 ***/
	@Test
	public void insert() {
		Map<String,Object> p=BaseUtils.map("id","DXTM","sendUserid","5sMV","sendUsername","AcjP","operTime",new Date("2023-09-29 22:18:12"),"objType","SF4J","msg","BMrM","gloNo","fKZg","branchId","gb2k","ip","vKk6","bizId","JymL","pbizId","5PqD","bizName","QpBa","toUserid","YgNu","toUsername","KCr0","hadRead","i","url","0GYC");
		NotifyMsg notifyMsg=BaseUtils.fromMap(p,NotifyMsg.class);
		notifyMsgService.save(notifyMsg);
		//Assert.assertEquals(1, result);
	}
	 
}
