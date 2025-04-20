package  com.xm.core.service;

import com.mdp.core.utils.BaseUtils;
import com.xm.core.entity.XmRecord;
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
public class TestXmRecordService  {

	@Autowired
	XmRecordService xmRecordService;

	/**
	 * 新增一条数据
	 ***/
	@Test
	public void insert() {
		Map<String,Object> p=BaseUtils.map("id","haCM","projectId","Olea","operUserid","Fpi8","operUsername","zMv8","operTime",new Date("2023-10-03 4:17:28"),"objType","a2hW","action","PbF5","oldValue","FVP4","newValue","4a4a","remarks","4mFq","gloNo","gSk8","branchId","rA6d","ip","YWBx","bizId","khTF","pbizId","U8k4","productId","E0JC","bizName","AmDn");
		XmRecord xmRecord=BaseUtils.fromMap(p,XmRecord.class);
		xmRecordService.save(xmRecord);
		//Assert.assertEquals(1, result);
	}
	 
}
