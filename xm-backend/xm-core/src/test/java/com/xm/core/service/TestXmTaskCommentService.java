package  com.xm.core.service;

import com.mdp.core.utils.BaseUtils;
import com.xm.core.entity.XmTaskComment;
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
public class TestXmTaskCommentService  {

	@Autowired
	XmTaskCommentService xmTaskCommentService;

	/**
	 * 新增一条数据
	 ***/
	@Test
	public void insert() {
		Map<String,Object> p=BaseUtils.map("id","2h5K","userid","632q","username","Dw2z","star","W","cdate",new Date("2023-10-03 4:17:29"),"taskId","N0YL","pid","7PsD","ups",3437,"isShow","k","toUserid","6Nd6","toUsername","2KOG","lvl","Y","context","x6Hr","branchId","Tt5u","ip","CB25","cityId","keI3","cityName","Z7la","status","N","childNums",6873);
		XmTaskComment xmTaskComment=BaseUtils.fromMap(p,XmTaskComment.class);
		xmTaskCommentService.save(xmTaskComment);
		//Assert.assertEquals(1, result);
	}
	 
}
