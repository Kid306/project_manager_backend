package  com.mdp.arc.img.service;

import com.mdp.arc.img.entity.ImageCategory;
import com.mdp.core.utils.BaseUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;
/**
 * ImageCategoryService的测试案例
 * 组织 com.mdp<br>
 * 顶级模块 arc<br>
 * 大模块 img<br>
 * 小模块 <br>
 * 表 arc_image_category 图片分类<br>
 * 实体 ImageCategory<br>
 * 表是指数据库结构中的表,实体是指java类型中的实体类<br>
 * 当前实体所有属性名:<br>
 *	id,categoryName,branchId,pid,isPub;<br>
 * 当前表的所有字段名:<br>
 *	id,category_name,branch_id,pid,is_pub;<br>
 * 当前主键(包括多主键):<br>
 *	id;<br>
 ***/

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestImageCategoryService  {

	@Autowired
	ImageCategoryService imageCategoryService;

	/**
	 * 新增一条数据
	 ***/
	@Test
	public void insert() {
		Map<String,Object> p=BaseUtils.map("id","tGWT","categoryName","y8e1","branchId","6CfF","pid","Bs8L","isPub","wjmp");
		ImageCategory imageCategory=BaseUtils.fromMap(p,ImageCategory.class);
		imageCategoryService.save(imageCategory);
		//Assert.assertEquals(1, result);
	}
	 
}
