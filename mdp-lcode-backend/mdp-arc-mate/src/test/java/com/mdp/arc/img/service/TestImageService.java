package  com.mdp.arc.img.service;

import com.mdp.arc.img.entity.Image;
import com.mdp.core.utils.BaseUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.Map;
/**
 * ImageService的测试案例
 * 组织 com.mdp<br>
 * 顶级模块 arc<br>
 * 大模块 img<br>
 * 小模块 <br>
 * 表 arc_image 图片素材库<br>
 * 实体 Image<br>
 * 表是指数据库结构中的表,实体是指java类型中的实体类<br>
 * 当前实体所有属性名:<br>
 *	id,name,url,relativePath,fileSuffix,rootPath,createDate,fileSize,deptid,tag,remark,categoryId,storageName,urlPrefix,isOutUrl,outUrl,branchId;<br>
 * 当前表的所有字段名:<br>
 *	id,name,url,relative_path,file_suffix,root_path,create_date,file_size,deptid,tag,remark,category_id,storage_name,url_prefix,is_out_url,out_url,branch_id;<br>
 * 当前主键(包括多主键):<br>
 *	id;<br>
 ***/

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestImageService  {

	@Autowired
	ImageService imageService;

	/**
	 * 新增一条数据
	 ***/
	@Test
	public void insert() {
		Map<String,Object> p=BaseUtils.map("id","exjf","name","rH3O","url","qdly","relativePath","cwh5","fileSuffix","lH5O","rootPath","q7Gk","createDate",new Date("2023-08-27 18:27:13"),"fileSize",2900,"deptid","l6Wo","tag","pvOk","remark","7b19","categoryId","ncDc","storageName","K2Z4","urlPrefix","tBZ3","isOutUrl","5","outUrl","iAfM","branchId","Vf7R");
		Image image=BaseUtils.fromMap(p,Image.class);
		imageService.save(image);
		//Assert.assertEquals(1, result);
	}
	 
}
