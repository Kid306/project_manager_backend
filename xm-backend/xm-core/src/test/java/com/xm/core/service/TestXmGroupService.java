package  com.xm.core.service;

import com.mdp.core.utils.BaseUtils;
import com.xm.core.entity.XmGroup;
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
public class TestXmGroupService  {

	@Autowired
	XmGroupService xmGroupService;

	/**
	 * 新增一条数据
	 ***/
	@Test
	public void insert() {
		Map<String,Object> p=BaseUtils.map("id","PS0R","groupName","zjya","projectId","q50f","pgTypeId","qIVN","pgTypeName","v2Cg","leaderUserid","uhyX","leaderUsername","OfYq","ctime",new Date("2023-11-10 16:33:3"),"ltime",new Date("2023-11-10 16:33:3"),"productId","c40j","branchId","SOEs","pgClass","X","pgroupId","p0dQ","lvl",1521,"pidPaths","J5w5","isTpl","e","assUserid","9LlN","assUsername","emuI","childrenCnt",1697,"userCnt",8957,"qxCode","MnDD","calcWorkload","E","ntype","K","crowBranchId","DYQO","crowBranchName","lMyS","isCrow","9");
		XmGroup xmGroup=BaseUtils.fromMap(p,XmGroup.class);
		xmGroupService.save(xmGroup);
		//Assert.assertEquals(1, result);
	}
	 
}
