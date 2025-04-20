package  com.mdp.sys.service;

import com.mdp.core.utils.BaseUtils;
import com.mdp.sys.entity.Dept;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.Map;
/**
 * @author code-gen
 * @since 2023-9-23
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestDeptService  {

	@Autowired
	DeptService deptService;

	/**
	 * 新增一条数据
	 ***/
	@Test
	public void insert() {
		Map<String,Object> p=BaseUtils.map("deptid","3SGr","deptName","LkHq","pdeptid","4528","deptType","73Ku","state","EmRL","manager","wFOG","leader","CLKf","shortName","I3uq","displayDeptid","TpIN","orgType","kqyT","managerName","ZCN8","leaderName","UfS6","branchId","ep9H","levelType","F83N","idPath","Vj6a","bizProcInstId","5460","bizFlowState","9","ltime",new Date("2023-09-23 20:59:15"),"isCbCenter","2","cpaType","v2Z7","cpaBranchId","B2LC","relyType","mbL2","relyId","AEHu","relyStype","j91X","relySid","Kr32");
		Dept dept=BaseUtils.fromMap(p,Dept.class);
		deptService.save(dept);
		//Assert.assertEquals(1, result);
	}
	 
}
