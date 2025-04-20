package com.mdp.arc.pub.ctrl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.arc.cache.TagCacheService;
import com.mdp.arc.pub.entity.TagCategory;
import com.mdp.arc.pub.service.TagCategoryService;
import com.mdp.arc.pub.service.TagService;
import com.mdp.core.entity.Result;
import com.mdp.core.err.BizException;
import com.mdp.core.query.QueryTools;
import com.mdp.core.utils.RequestUtils;
import com.mdp.safe.client.entity.User;
import com.mdp.safe.client.utils.LoginUtils;
import io.swagger.annotations.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
/**
 * url编制采用rest风格,如对ARC.arc_tag_category arc_tag_category的操作有增删改查,对应的url分别为:<br>
 *  新增: arc/tagCategory/add <br>
 *  查询: arc/tagCategory/list<br>
 *  模糊查询: arc/tagCategory/listKey<br>
 *  修改: arc/tagCategory/edit <br>
 *  删除: arc/tagCategory/del<br>
 *  批量删除: arc/tagCategory/batchDel<br>
 * 组织 com.qqkj  顶级模块 mdp 大模块 arc 小模块 <br>
 * 实体 TagCategory 表 ARC.arc_tag_category 当前主键(包括多主键): id; 
 ***/
@RestController("mdp.arc.tagCategoryController")
@RequestMapping(value="/*/arc/tagCategory")
@Api(tags={"arc_tag_category操作接口"})
public class TagCategoryController {
	
	static Log logger=LogFactory.getLog(TagCategoryController.class);
	
	@Autowired
	private TagCategoryService tagCategoryService;
	 

	@Autowired
	TagCacheService tagCacheService;
	

	@Autowired
	private TagService tagService;
 
	
	@ApiOperation( value = "查询arc_tag_category信息列表",notes="listTagCategory,条件之间是 and关系,模糊查询写法如 {studentName:'%才哥%'}")
	@ApiImplicitParams({  
		@ApiImplicitParam(name="id",value="分组编号,主键",required=false),
		@ApiImplicitParam(name="branchId",value="机构号",required=false),
		@ApiImplicitParam(name="shopId",value="商户编号",required=false),
		@ApiImplicitParam(name="categoryName",value="分组名称",required=false),
		@ApiImplicitParam(name="pageSize",value="每页记录数",required=false),
		@ApiImplicitParam(name="currentPage",value="当前页码,从1开始",required=false),
		@ApiImplicitParam(name="total",value="总记录数,服务器端收到0时，会自动计算总记录数，如果上传>0的不自动计算",required=false),
		@ApiImplicitParam(name="orderFields",value="排序列 如性别、学生编号排序 ['sex','studentId']",required=false),
		@ApiImplicitParam(name="orderDirs",value="排序方式,与orderFields对应，升序 asc,降序desc 如 性别 升序、学生编号降序 ['asc','desc']",required=false) 
	})
	@ApiResponses({
		@ApiResponse(code = 200,response=TagCategory.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},pageInfo:{total:总记录数},data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Result listTagCategory(@RequestParam Map<String,Object> params ){
		 
		RequestUtils.transformArray(params, "ids");
		IPage page= QueryTools.initPage(params);
		List<Map<String,Object>>	datas = tagCategoryService.selectListMapByWhere(page,QueryTools.initQueryWrapper(TagCategory.class,params),params);	//列出TagCategory列表
		
		return Result.ok().setData(datas).setTotal(page.getTotal());
	}
	
 
	
	/***/
	@ApiOperation( value = "新增一条arc_tag_category信息",notes="addTagCategory,主键如果为空，后台自动生成")
	@ApiResponses({
		@ApiResponse(code = 200,response=TagCategory.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Result addTagCategory(@RequestBody TagCategory tagCategory) {
		
		
		try{
			User user=LoginUtils.getCurrentUserInfo();
			tagCategory.setBranchId(user.getBranchId());
			TagCategory tagCategoryQuery = new  TagCategory();
			tagCategoryQuery.setCategoryName(tagCategory.getCategoryName());
			tagCategoryQuery.setBranchId(tagCategory.getBranchId());
			if(tagCategoryService.countByWhere(tagCategoryQuery)>0){
				return Result.error("名字已存在，不允许再添加");
			}
			tagCategory.setId(this.tagCategoryService.createTagCategoryId(user.getBranchId()));
			if(StringUtils.isEmpty(tagCategory.getIsPub())) {
				tagCategory.setIsPub("0");
			}
			if("1".equals(tagCategory.getIsPub())) {//如果添加的是公共标签需要检查是否有权限
				if(!LoginUtils.hasAnyRoles("superAdmin","platformAdmin")) {
					return Result.error("tag-add-no-role-001", "isPub", "只有平台管理员和超级管理员可以添加公共标签");
				};
			}
				int i=tagCategoryService.insert(tagCategory);
				if(i>0 && "1".equals(tagCategory.getIsPub())) {//如果添加的是公共标签需要同步更新缓存 
					  tagCacheService.removePubTabs();
				}else {
					tagCacheService.removeNotPubTabs(tagCategory.getBranchId());
				}
			
			return Result.ok().setData(tagCategory);
		}catch (BizException e) {
			logger.error("",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("",e);
			return Result.error(e.getMessage());

		}
	}
	
	
	/**
	@ApiOperation( value = "删除一条arc_tag_category信息",notes="delTagCategory,仅需要上传主键字段")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}}")
	}) 
	@RequestMapping(value="/del",method=RequestMethod.POST)
	public Result delTagCategory(@RequestBody TagCategory tagCategory){
		
		
		try{
			tagCategoryService.deleteByPk(tagCategory);
		}catch (BizException e) {
		logger.error("",e);
		return Result.error(e);
	}catch (Exception e) {
		logger.error("",e);
		return Result.error(e.getMessage());

	}  
		return Result.ok();
	}
	 */
	
	/**
	@ApiOperation( value = "根据主键修改一条arc_tag_category信息",notes="editTagCategory")
	@ApiResponses({
		@ApiResponse(code = 200,response=TagCategory.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public Result editTagCategory(@RequestBody TagCategory tagCategory) {
		
		
		try{
			tagCategoryService.updateByPk(tagCategory);
			return Result.ok().setData(tagCategory);
		}catch (BizException e) {
		logger.error("",e);
		return Result.error(e);
	}catch (Exception e) {
		logger.error("",e);
		return Result.error(e.getMessage());

	}  
		return Result.ok();
	}
	*/
	

	
	/**
	@ApiOperation( value = "根据主键列表批量删除arc_tag_category信息",notes="batchDelTagCategory,仅需要上传主键字段")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	}) 
	@RequestMapping(value="/batchDel",method=RequestMethod.POST)
	public Result batchDelTagCategory(@RequestBody List<TagCategory> tagCategorys) {
		
		
		try{ 
			tagCategoryService.batchDelete(tagCategorys);
		}catch (BizException e) {
		logger.error("",e);
		return Result.error(e);
	}catch (Exception e) {
		logger.error("",e);
		return Result.error(e.getMessage());

	}  
		return Result.ok();
	} 
	*/
}
