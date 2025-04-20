package  com.xm.core.service;

import com.mdp.core.utils.BaseUtils;
import com.xm.core.entity.XmBranchTaskTypeState;
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
public class TestXmBranchTaskTypeStateService  {

	@Autowired
	XmBranchTaskTypeStateService xmBranchTaskTypeStateService;

	/**
	 * 新增一条数据
	 ***/
	@Test
	public void insert() {
		Map<String,Object> p=BaseUtils.map("taskType","6tKa","planWorkload",6428.86,"planAmount",8012.11,"actWorkload",1320.98,"actAmount",779.92,"branchId","SQLt","bizDate","QEcS","calcTime",new Date("2023-10-03 4:17:22"),"planOuserAt",4528.77,"planIuserAt",9844.72,"actOutUserAt",3703.82,"actInnerUserAt",244.71,"planOuserWorkload",2357.77,"planIuserWorkload",5890.05,"actOuserWorkload",8860.91,"actIuserWorkload",6819.87,"planNouserAt",3397.37,"actNouserAt",2211.84,"id","MK51","branchName","ty39");
		XmBranchTaskTypeState xmBranchTaskTypeState=BaseUtils.fromMap(p,XmBranchTaskTypeState.class);
		xmBranchTaskTypeStateService.save(xmBranchTaskTypeState);
		//Assert.assertEquals(1, result);
	}
	 
}
