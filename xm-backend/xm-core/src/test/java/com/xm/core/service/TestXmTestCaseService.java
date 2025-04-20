package  com.xm.core.service;

import com.mdp.core.utils.BaseUtils;
import com.xm.core.entity.XmTestCase;
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
public class TestXmTestCaseService  {

	@Autowired
	XmTestCaseService xmTestCaseService;

	/**
	 * 新增一条数据
	 ***/
	@Test
	public void insert() {
		Map<String,Object> p=BaseUtils.map("id","2MEN","caseName","Ehv2","caseRemark","iLa3","testStep","Pqfn","expectResult","jT91","menuId","oTO6","menuName","y6sy","ctime",new Date("2023-10-03 4:17:31"),"ltime",new Date("2023-10-03 4:17:31"),"luserid","0A0R","lusername","K4Az","cbranchId","4hOF","moduleId","wAzg","moduleName","gZV1","caseStatus","r","cuserid","yHTk","cusername","fje6","productId","23Gx","verNum","7590","casedbId","k8zy","casedbName","7png","funcId","FQR2","funcName","oEZ3","funcPnames","aytj","preRemark","v1zG","caseType","G","cpriority","k","budgetWorkload",2553.11,"actWorkload",5053.63,"initWorkload",8045.95,"retest","o","pbranchId","S0dh","testType","y","autoStep","uec2");
		XmTestCase xmTestCase=BaseUtils.fromMap(p,XmTestCase.class);
		xmTestCaseService.save(xmTestCase);
		//Assert.assertEquals(1, result);
	}
	 
}
