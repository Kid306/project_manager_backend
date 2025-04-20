package  com.xm.core.service;

import com.mdp.core.utils.BaseUtils;
import com.xm.core.entity.XmCostRecord;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.Map;
/**
 * @author code-gen
 * @since 2024-7-6
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestXmCostRecordService  {

	@Autowired
	XmCostRecordService xmCostRecordService;

	/**
	 * 新增一条数据
	 ***/
	@Test
	public void insert() {
		Map<String,Object> p=BaseUtils.map("projectId","3chj","userid","6ZzX","ctime",new Date("2024-07-06 18:3:31"),"sendTime",new Date("2024-07-06 18:3:31"),"username","1Vsy","projectName","y3vo","remark","ox1F","id","nMw7","taskId","FmT2","taskName","OZdA","subjectId","x4US","bizSdate",new Date("2024-07-06 18:3:31"),"bizEdate",new Date("2024-07-06 18:3:31"),"actAt",6637.45,"costType","6","bizMonth","2Jko","bizDate","ZJl8","subjectName","EL62","ubranchId","fpSo","branchId","Hs3a","instId","OW10","bizFlowState","Q","refSbillId","iVJ0","refSbillSubId","6ZDx");
		XmCostRecord xmCostRecord=BaseUtils.fromMap(p,XmCostRecord.class);
		xmCostRecordService.save(xmCostRecord);
		//Assert.assertEquals(1, result);
	}
	 
}
