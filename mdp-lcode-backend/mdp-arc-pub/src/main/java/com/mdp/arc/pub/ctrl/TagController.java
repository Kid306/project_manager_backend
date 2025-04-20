package com.mdp.arc.pub.ctrl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.arc.cache.TagCacheService;
import com.mdp.arc.pub.entity.Tag;
import com.mdp.arc.pub.entity.TagCategory;
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
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
/**
 * url编制采用rest风格,如对ARC.arc_tag arc_tag的操作有增删改查,对应的url分别为:<br>
 *  新增: arc/tag/add <br>
 *  查询: arc/tag/list<br>
 *  模糊查询: arc/tag/listKey<br>
 *  修改: arc/tag/edit <br>
 *  删除: arc/tag/del<br>
 *  批量删除: arc/tag/batchDel<br>
 * 组织 com.qqkj  顶级模块 mdp 大模块 arc 小模块 <br>
 * 实体 Tag 表 ARC.arc_tag 当前主键(包括多主键): id; 
 ***/
@RestController("mdp.arc.tagController")
@RequestMapping(value="/*/arc/tag")
@Api(tags={"arc_tag操作接口"})
public class TagController {
	
	static Log logger=LogFactory.getLog(TagController.class);
	
	@Autowired
	private TagService tagService;
	 

	@Autowired
    TagCacheService tagCacheService;
 
	
	@ApiOperation( value = "查询arc_tag信息列表",notes="listTag,条件之间是 and关系,模糊查询写法如 {studentName:'%才哥%'}")
	@ApiImplicitParams({  
		@ApiImplicitParam(name="id",value="标签编号,主键",required=false),
		@ApiImplicitParam(name="tagName",value="标签名",required=false),
		@ApiImplicitParam(name="branchId",value="机构号",required=false),
		@ApiImplicitParam(name="shopId",value="商户编号",required=false),
		@ApiImplicitParam(name="categoryId",value="标签分组",required=false),
		@ApiImplicitParam(name="pageSize",value="每页记录数",required=false),
		@ApiImplicitParam(name="currentPage",value="当前页码,从1开始",required=false),
		@ApiImplicitParam(name="total",value="总记录数,服务器端收到0时，会自动计算总记录数，如果上传>0的不自动计算",required=false),
		@ApiImplicitParam(name="orderFields",value="排序列 如性别、学生编号排序 ['sex','studentId']",required=false),
		@ApiImplicitParam(name="orderDirs",value="排序方式,与orderFields对应，升序 asc,降序desc 如 性别 升序、学生编号降序 ['asc','desc']",required=false) 
	})
	@ApiResponses({
		@ApiResponse(code = 200,response= Tag.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},pageInfo:{total:总记录数},data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Result listTag(@RequestParam Map<String,Object> params ){
		 
		RequestUtils.transformArray(params, "ids");
		IPage page= QueryTools.initPage(params);
		User user=LoginUtils.getCurrentUserInfo();
		params.put("branchId",user.getBranchId());
		List<Map<String,Object>>	datas = tagService.selectListMapByWhere(page,QueryTools.initQueryWrapper(Tag.class,params),params);	//列出Tag列表
		
		return Result.ok().setData(datas);
	}
	
 
	 
	@RequestMapping(value="/getAllTag",method=RequestMethod.GET)
	public Result getAllTag(@RequestParam Map<String,Object> params) {
		User user=LoginUtils.getCurrentUserInfo();
		List<Map<String,Object>> datas=tagService.getAllTag(user.getBranchId());;
		params.put("branchId",user.getBranchId());
		return Result.ok().setData(datas).setTotal(datas==null?0:datas.size());
	} 
	
	@RequestMapping(value="/deleteTagCategory",method=RequestMethod.POST)
	public Result deleteTagCategory(@RequestBody TagCategory tagCategory) {
		
		
		try{
			User user=LoginUtils.getCurrentUserInfo();
			tagCategory.setBranchId(user.getBranchId());
			if("1".equals(tagCategory.getIsPub())) {//如果添加的是公共标签需要检查是否有权限
				if(!LoginUtils.hasAnyRoles("superAdmin","platformAdmin")) {
					return Result.error("tag-add-no-role-001", "isPub", "只有平台管理员和超级管理员可以添加公共标签");
				};
			}
			int i=tagService.deleteTagCategory(tagCategory);
			if(i>0 && "1".equals(tagCategory.getIsPub())) {//如果添加的是公共标签需要同步更新缓存
				tagCacheService.removePubTabs();
			}else {
				tagCacheService.removeNotPubTabs(tagCategory.getBranchId());
			}
		}catch (Exception e) {
			logger.error("",e);
			return Result.error(e.getMessage());
		}  
		return Result.ok();
	}
	
	/**
	 * 添加之前先查询 如果有就不能添加
	 * */
	@ApiOperation( value = "新增一条pub_tag信息",notes="addTag,主键如果为空，后台自动生成")
	@ApiResponses({
		@ApiResponse(code = 200,response=Tag.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Result addTag(@RequestBody Tag tag) {
		
		
		try{

			User user=LoginUtils.getCurrentUserInfo();
			tag.setBranchId(user.getBranchId());
			Tag query=new Tag();
			query.setBranchId(user.getBranchId());
			query.setTagName(tag.getTagName());
		  	List<Tag>	tagList = tagService.selectListByWhere(query);	//列出Tag列表
			if(!CollectionUtils.isEmpty(tagList)) {
				throw new BizException("该标签名字已经存在了，不能添加");
			}
			String id=tagService.createTagId(user.getBranchId());
			tag.setId(id);
			if(StringUtils.isEmpty(tag.getIsPub())) {
				tag.setIsPub("0");
			}
			if("1".equals(tag.getIsPub())) {//如果添加的是公共标签需要检查是否有权限
				if(!LoginUtils.hasAnyRoles("superAdmin","platformAdmin")) {
					return Result.error("tag-add-no-role-001", "isPub", "只有平台管理员和超级管理员可以添加公共标签");
				};
			}
				tag.setBranchId(user.getBranchId());
				int i=tagService.insert(tag);
				if(i>0 && "1".equals(tag.getIsPub())) {//如果添加的是公共标签需要同步更新缓存
					tagCacheService.removePubTabs();
				}else {
					tagCacheService.removeNotPubTabs(tag.getBranchId());
				}
			return Result.ok().setData(tag);
		}catch (BizException e) {
			logger.error("",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("",e);
			return Result.error(e.getMessage());

		}
	}
	 
	/***/
	@ApiOperation( value = "删除一条arc_tag信息",notes="delTag,仅需要上传主键字段")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}}")
	}) 
	@RequestMapping(value="/del",method=RequestMethod.POST)
	public Result delTag(@RequestBody Tag tag){
		
		
		try{
			User user=LoginUtils.getCurrentUserInfo();
			tag.setBranchId(user.getBranchId());
			if("1".equals(tag.getIsPub())) {//如果添加的是公共标签需要检查是否有权限
				if(!LoginUtils.hasAnyRoles("superAdmin","platformAdmin")) {
					return Result.error("tag-add-no-role-001", "isPub", "只有平台管理员和超级管理员可以添加公共标签");
				};
			}
				int i=tagService.deleteByPk(tag);
				if(i>0 && "1".equals(tag.getIsPub())) {//如果添加的是公共标签需要同步更新缓存
					tagCacheService.removePubTabs();
				}else {
					tagCacheService.removeNotPubTabs(tag.getBranchId());
				}
		}catch (BizException e) {
		logger.error("",e);
		return Result.error(e);
	}catch (Exception e) {
		logger.error("",e);
		return Result.error(e.getMessage());

	}  
		return Result.ok();
	}
	 
	
	/**
	@ApiOperation( value = "根据主键修改一条arc_tag信息",notes="editTag")
	@ApiResponses({
		@ApiResponse(code = 200,response=Tag.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public Result editTag(@RequestBody Tag tag) {
		
		
		try{
			tagService.updateByPk(tag);
			return Result.ok().setData(tag);
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
	@ApiOperation( value = "根据主键列表批量删除arc_tag信息",notes="batchDelTag,仅需要上传主键字段")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	}) 
	@RequestMapping(value="/batchDel",method=RequestMethod.POST)
	public Result batchDelTag(@RequestBody List<Tag> tags) {
		
		
		try{ 
			tagService.batchDelete(tags);
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
