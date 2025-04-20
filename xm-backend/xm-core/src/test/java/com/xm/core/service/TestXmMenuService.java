package  com.xm.core.service;

import com.mdp.core.utils.BaseUtils;
import com.xm.core.entity.XmMenu;
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
public class TestXmMenuService  {

	@Autowired
	XmMenuService xmMenuService;

	/**
	 * 新增一条数据
	 ***/
	@Test
	public void insert() {
		Map<String,Object> p=BaseUtils.map("startTime",new Date("2023-10-03 4:17:25"),"menuId","y1Pp","menuName","6HQC","pmenuId","BW4t","productId","uY57","remark","FA59","status","S","online","7","demandUrl","jqhT","codeUrl","6cpo","designUrl","OoEF","docUrl","R22m","helpUrl","jzFA","operDocUrl","ioO6","seqNo","O1aV","mmUserid","DxH5","mmUsername","9432","ctime",new Date("2023-10-03 4:17:25"),"ntype","5","sinceVersion","awky","childrenCnt",1881,"ltime",new Date("2023-10-03 4:17:25"),"tagIds","91j2","tagNames","8gN7","pidPaths","R1cv","lvl",1899,"isTpl","j","phaseId","bH52","iterationId","21D5","source","h","proposerId","878p","proposerName","0J0p","dlvl","h","dtype","0","priority","K","dclass","A","iterationName","S74W","endTime",new Date("2023-10-03 4:17:25"),"funcId","1S17","funcName","09qK","comments",6532,"ups",3426,"readNum",3320,"pbranchId","26MH");
		XmMenu xmMenu=BaseUtils.fromMap(p,XmMenu.class);
		xmMenuService.save(xmMenu);
		//Assert.assertEquals(1, result);
	}
	 
}
