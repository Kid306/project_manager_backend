package com.mdp.arc.img.ctrl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.arc.img.entity.ImageCategory;
import com.mdp.arc.img.service.ImageCategoryService;
import com.mdp.arc.img.vo.BatchChangeCategoryVo;
import com.mdp.core.entity.Result;
import com.mdp.core.err.BizException;
import com.mdp.core.query.QueryTools;
import com.mdp.core.utils.ObjectTools;
import com.mdp.safe.client.entity.User;
import com.mdp.safe.client.utils.LoginUtils;
import com.mdp.swagger.ApiEntityParams;
import io.swagger.annotations.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.*;

/**
 * url编制采用rest风格,如对ARC.arc_image_category 图片分类的操作有增删改查,对应的url分别为:<br>
 *  新增: arc/imageCategory/add <br>
 *  查询: arc/imageCategory/list<br>
 *  模糊查询: arc/imageCategory/listKey<br>
 *  修改: arc/imageCategory/edit <br>
 *  删除: arc/imageCategory/del<br>
 *  批量删除: arc/imageCategory/batchDel<br>
 * 组织 com.qqkj  顶级模块 mdp 大模块 arc 小模块 <br>
 * 实体 ImageCategory 表 ARC.arc_image_category 当前主键(包括多主键): id; 
 ***/
@RestController("mdp.arc.imageCategoryController")
@RequestMapping(value="/*/arc/img/imageCategory")
@Api(tags={"图片分类操作接口"})
public class ImageCategoryController {
	
	static Log logger=LogFactory.getLog(ImageCategoryController.class);
	
	@Autowired
	private ImageCategoryService imageCategoryService;

	 
		 
	
	@ApiOperation( value = "查询图片分类信息列表",notes="listImageCategory,条件之间是 and关系,模糊查询写法如 {studentName:'%才哥%'}")
	@ApiImplicitParams({  
		@ApiImplicitParam(name="id",value="主键,主键",required=false),
		@ApiImplicitParam(name="categoryName",value="分类名称",required=false),
		@ApiImplicitParam(name="branchId",value="机构编号",required=false),
		@ApiImplicitParam(name="pid",value="上一级",required=false),
		@ApiImplicitParam(name="isPub",value="是否是公共分类的图片 1是0 否",required=false),
		@ApiImplicitParam(name="pageSize",value="每页记录数",required=false),
		@ApiImplicitParam(name="pageNum",value="当前页码,从1开始",required=false),
		@ApiImplicitParam(name="total",value="总记录数,服务器端收到0时，会自动计算总记录数，如果上传>0的不自动计算",required=false),
		@ApiImplicitParam(name="orderFields",value="排序列 如性别、学生编号排序 ['sex','studentId']",required=false),
		@ApiImplicitParam(name="orderDirs",value="排序方式,与orderFields对应，升序 asc,降序desc 如 性别 升序、学生编号降序 ['asc','desc']",required=false) 
	})
	@ApiResponses({
		@ApiResponse(code = 200,response=ImageCategory.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},pageInfo:{total:总记录数},data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Result listImageCategory(@RequestParam Map<String,Object> params ){
		 
		IPage page= QueryTools.initPage(params);
		List<Map<String,Object>>	datas = imageCategoryService.selectListMapByWhere(page,QueryTools.initQueryWrapper(ImageCategory.class,params),params);	//列出ImageCategory列表
		
		return Result.ok().setData(datas).setTotal(page.getTotal());
	}
	
    /**
     * 请求,如list/tree
     * 根据条件查询数据对象列表,如果不上传分页参数，将不会分页。后台自动分页，无需编程
     */
    @RequestMapping(value = "/list/trees")
    public Map<String, Object> listCategoryTree(@RequestParam Map<String, Object> category) {
		return this.listImageCategory(category);
	}

 
	
	/***/
	@ApiOperation( value = "新增一条图片分类信息",notes="addImageCategory,主键如果为空，后台自动生成")
	@ApiResponses({
		@ApiResponse(code = 200,response=ImageCategory.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Result addImageCategory(@RequestBody ImageCategory imageCategory) {
		
		
		try{
			if(StringUtils.hasText(imageCategory.getId())){
				if(this.imageCategoryService.countByWhere(new ImageCategory(imageCategory.getId()))>0){
					return Result.error("id-is-exists","该分类编号已存在");
				}
			}else{
				imageCategory.setId(imageCategoryService.createKey("id"));
			}
			if(ObjectTools.isEmpty(imageCategory.getPid())){
				imageCategory.setPid("0");
			}
			imageCategoryService.insert(imageCategory);
			return Result.ok().setData(imageCategory);
		}catch (BizException e) { 
			logger.error("执行异常",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("执行异常",e);
			return Result.error(e.getMessage());
		}
	}

	/**
	 * 批量修改上下级
	 */
	@RequestMapping(value="/batchChangeParent")
	public Result batchChangeParent(@RequestBody List<BatchChangeCategoryVo> idLinks) {

		User user = LoginUtils.getCurrentUserInfo();
		if (idLinks == null || idLinks.size() == 0) {
			return Result.error("分类列表不能为空");

		}
		Set<String> childIds = new HashSet<>();
		Set<String> pids = new HashSet<>();
		Map<String, String> newIdLinks = new HashMap<>();
		for (BatchChangeCategoryVo link : idLinks) {
			if (ObjectTools.isEmpty(link.getPid()) || "0".equals(link.getPid()) || "0".equals(link.getPid())) {
				link.setPid("0");
			} else {
				pids.add(link.getPid());
			}

			if (ObjectTools.isEmpty(link.getId())) {
				return Result.error("分类编号不能为空");
			}
			childIds.add(link.getId());
			newIdLinks.put(link.getId(), link.getPid());
		}
		this.imageCategoryService.batchChangeParent(idLinks);
		return Result.ok();
	}
	
	/***/
	@ApiOperation( value = "删除一条图片分类信息",notes="delImageCategory,仅需要上传主键字段")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}}")
	}) 
	@RequestMapping(value="/del",method=RequestMethod.POST)
	public Result delImageCategory(@RequestBody ImageCategory imageCategory){
		
		
		try{
			ImageCategory imageCategoryQuery=new ImageCategory();
			imageCategoryQuery.setPid(imageCategory.getId());
			Long children=this.imageCategoryService.countByWhere(imageCategoryQuery);
			if(children>0){
				return Result.error("have-children","还有子分类，不能删除，请先删除子分类");
			}
			imageCategoryService.deleteByPk(imageCategory);
		}catch (BizException e) { 
			logger.error("执行异常",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("执行异常",e);
			return Result.error(e.getMessage());
		}  
		return Result.ok();
	}
	 
	
	/***/
	@ApiOperation( value = "根据主键修改一条图片分类信息",notes="editImageCategory")
	@ApiResponses({
		@ApiResponse(code = 200,response=ImageCategory.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public Result editImageCategory(@RequestBody ImageCategory imageCategory) {
		
		
		try{
			imageCategoryService.updateByPk(imageCategory);
			return Result.ok().setData(imageCategory);
		}catch (BizException e) { 
			logger.error("执行异常",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("执行异常",e);
			return Result.error(e.getMessage());
		}
	}

	@ApiOperation(value = "批量修改某些字段", notes = "")
	@ApiEntityParams(value = ImageCategory.class, props = {}, remark = "档案信息表", paramType = "body")
	@ApiResponses({
			@ApiResponse(code = 200, response = ImageCategory.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	@RequestMapping(value = "/editSomeFields", method = RequestMethod.POST)
	public Map<String, Object> editSomeFields(@ApiIgnore @RequestBody Map<String, Object> params) {
		int i= this.imageCategoryService.editSomeFields(params);
		return Result.ok();
	}
	

	@ApiOperation( value = "根据主键列表批量删除图片分类信息",notes="batchDelImageCategory,仅需要上传主键字段")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	}) 
	@RequestMapping(value="/batchDel",method=RequestMethod.POST)
	public Result batchDelImageCategory(@RequestBody List<ImageCategory> imageCategorys) {
		
		
		try{ 
			imageCategoryService.batchDelete(imageCategorys);
		}catch (BizException e) { 
			logger.error("执行异常",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("执行异常",e);
			return Result.error(e.getMessage());
		}  
		return Result.ok();
	}
}
