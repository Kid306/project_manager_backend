package  com.mdp.meta.service;

import com.mdp.core.utils.BaseUtils;
import com.mdp.meta.entity.ItemOption;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;
/**
 * ItemOptionService的测试案例
 * 组织 com<br>
 * 顶级模块 mdp<br>
 * 大模块 meta<br>
 * 小模块 <br>
 * 表 meta_item_option 字典选项值<br>
 * 实体 ItemOption<br>
 * 表是指数据库结构中的表,实体是指java类型中的实体类<br>
 * 当前实体所有属性名:<br>
 *	id,name,relyTypes,relyIds,relySubTypes,relySubIds,color,unTargets,disabled,itemId,seqOrder;<br>
 * 当前表的所有字段名:<br>
 *	id,name,rely_types,rely_ids,rely_sub_types,rely_sub_ids,color,un_targets,disabled,item_id,seq_order;<br>
 * 当前主键(包括多主键):<br>
 *	id,item_id;<br>
 ***/

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestItemOptionService  {

	@Autowired
	ItemOptionService itemOptionService;

	/**
	 * 新增一条数据
	 ***/
	@Test
	public void insert() {
		Map<String,Object> p=BaseUtils.map("id","y73G","name","OYn2","relyTypes","wCPW","relyIds","Ogrf","relySubTypes","0c87","relySubIds","8Gyz","color","Hipn","unTargets","d4L0","disabled","u","itemId","3L6R","seqOrder",4155);
		ItemOption itemOption=BaseUtils.fromMap(p,ItemOption.class);
		itemOptionService.save(itemOption);
		//Assert.assertEquals(1, result);
	}
	 
}
