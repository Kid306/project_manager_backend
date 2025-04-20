package  com.xm.core.service;

import com.mdp.core.utils.BaseUtils;
import com.xm.core.entity.XmQuestion;
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
public class TestXmQuestionService  {

	@Autowired
	XmQuestionService xmQuestionService;

	/**
	 * 新增一条数据
	 ***/
	@Test
	public void insert() {
		Map<String,Object> p=BaseUtils.map("id","XHiq","name","5sQf","projectId","ka1K","projectName","opm2","caseId","1hRZ","caseName","p0P7","endTime",new Date("2023-10-03 4:17:28"),"askUserid","6xth","askUsername","JJ11","handlerUserid","gWUe","handlerUsername","u0ZU","priority","kI61","solution","rNCO","description","30Qa","createUserid","QzuO","createUsername","Wsi5","createTime",new Date("2023-10-03 4:17:28"),"bugStatus","s","bizProcInstId","GH6r","bizFlowState","O","menuId","EwHn","menuName","63D2","budgetWorkload",4664.32,"budgetAt",4519.5,"actWorkload",5132.86,"actAt",4994.64,"expectResult","9m4G","opStep","oe04","currResult","rn9P","refRequire","JKqp","bugSeverity","q","bugType","V","tagIds","LNys","tagNames","0H38","urls","r9E5","ltime",new Date("2023-10-03 4:17:28"),"qtype","o","caseExecId","6UUj","remarks","MmXo","productId","ybaQ","repRate","2","verNum","Lss3","vpath","lRJz","pverNum","5YXS","bugReason","z","rate",6713,"initWorkload",5185.7,"taskOut","0","taskId","xz9b","funcId","1gOE","funcName","f55n","funcPnames","pPem","planId","sSDm","casedbId","9lFV","pbranchId","zB5F");
		XmQuestion xmQuestion=BaseUtils.fromMap(p,XmQuestion.class);
		xmQuestionService.save(xmQuestion);
		//Assert.assertEquals(1, result);
	}
	 
}
