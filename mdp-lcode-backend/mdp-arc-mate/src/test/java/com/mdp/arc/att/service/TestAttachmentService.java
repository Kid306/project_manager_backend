package  com.mdp.arc.att.service;

import com.mdp.arc.att.entity.Attachment;
import com.mdp.core.utils.BaseUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.Map;
/**
 * @author code-gen
 * @since 2023-9-1
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestAttachmentService  {

	@Autowired
	AttachmentService attachmentService;

	/**
	 * 新增一条数据
	 ***/
	@Test
	public void insert() {
		Map<String,Object> p=BaseUtils.map("id","24l5","name","7hGS","url","2CJ9","relativePath","IWOW","fileSuffix","iFZn","cdnUrl","MP6F","isImg","L","archiveId","hDER","isCdn","G","rootPath","z0xW","createDate",new Date("2023-09-01 1:31:3"),"canDown","h","canDel","6","canRead","b","bizId","dD0d","remark","GjPF","storeName","9PBZ","fileSize",6960,"branchId","I4xP","deptid","FSis","archiveType","V","categoryId","9IL7");
		Attachment attachment=BaseUtils.fromMap(p,Attachment.class);
		attachmentService.save(attachment);
		//Assert.assertEquals(1, result);
	}
	 
}
