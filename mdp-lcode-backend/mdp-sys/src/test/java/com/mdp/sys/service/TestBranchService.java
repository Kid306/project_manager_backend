package  com.mdp.sys.service;

import com.mdp.core.utils.BaseUtils;
import com.mdp.sys.entity.Branch;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.Map;
/**
 * @author code-gen
 * @since 2023-9-24
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestBranchService  {

	@Autowired
	BranchService branchService;

	/**
	 * 新增一条数据
	 ***/
	@Test
	public void insert() {
		Map<String,Object> p=BaseUtils.map("id","8512","branchName","gLnB","enabled","6","industryCategory","ktvK","cuserid","995m","cdate",new Date("2023-09-24 14:52:49"),"cusername","SBlR","lphoneNo","SLdL","emaill","LFRr","bizProcInstId","Cv7o","bizFlowState","Z","pbranchId","0k4g","admUserid","hGA9","admUsername","2kt4","lusername","E5CY","luserid","7zuB","address","zPkB","btype","R","imgUrl","73kU","bcode","K98V","blicense","yaVH","legalPerson","031v","regCapital",3018.86,"remark","WbH4","validLvls","To3o","brelyType","qV3i","brelyId","J7sU","brelyStype","NX7B","brelySid","dP0n","claimStatus","P");
		Branch branch=BaseUtils.fromMap(p,Branch.class);
		branchService.save(branch);
		//Assert.assertEquals(1, result);
	}
	 
}
