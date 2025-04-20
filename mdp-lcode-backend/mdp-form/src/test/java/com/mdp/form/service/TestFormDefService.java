package  com.mdp.form.service;

import com.mdp.core.utils.BaseUtils;
import com.mdp.form.entity.FormDef;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;
/**
 * @author code-gen
 * @since 2024-4-26
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestFormDefService  {

	@Autowired
	FormDefService formDefService;

	/**
	 * 新增一条数据
	 ***/
	@Test
	public void insert() {
		Map<String,Object> p=BaseUtils.map("id","lpke","tableName","ZS1k","ds","mrFp","formName","lb16","userid","UypE","deptid","ytKp","formType","e","tpl","1","bizType","efj5","ctime","k1E1","branchId","Ea5E","categoryId","euh8","tagIds","4ft5","tagNames","NGfL","username","l5ay","deptName","Ovra","dataType","X","option","V0Xx","funcs","H8kU","rule","PqY4","formQx","43x3","flowState","x","flowTime","TRbj","flowUserid","96cU","flowStart","EL4q","flowStartTime","V43e");
		FormDef formDef=BaseUtils.fromMap(p,FormDef.class);
		formDefService.save(formDef);
		//Assert.assertEquals(1, result);
	}
	 
}
