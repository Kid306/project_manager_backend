package com.mdp.arc.pub.ctrl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.arc.pub.entity.Category;
import com.mdp.arc.pub.service.CategoryQxService;
import com.mdp.arc.pub.service.CategoryService;
import com.mdp.arc.pub.vo.BatchChangeCategoryVo;
import com.mdp.core.entity.LangTips;
import com.mdp.core.entity.Result;
import com.mdp.core.err.BizException;
import com.mdp.core.query.QueryTools;
import com.mdp.core.service.BaseService;
import com.mdp.core.utils.NumberUtil;
import com.mdp.core.utils.ObjectTools;
import com.mdp.core.utils.RequestUtils;
import com.mdp.safe.client.entity.User;
import com.mdp.safe.client.utils.LoginUtils;
import com.mdp.swagger.ApiEntityParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.*;
import java.util.stream.Collectors;

/**
 ***/
@RestController("mdp.arc.categoryController")
@RequestMapping(value="/*/arc/pub/category")
public class CategoryController {
	
	static Log logger=LogFactory.getLog(CategoryController.class);
	
	@Autowired
	private CategoryService categoryService;

	@Autowired
	CategoryQxService qxService;

	@Value("${mdp.platform-branch-id:platform-branch-001}")
	String platformBranchId;
	
	/**
	 * 请求,如list
	 * 分页参数 {pageNum:1,pageSize:10,total:0}
	 * 根据条件查询数据对象列表,如果不上传分页参数，将不会分页。后台自动分页，无需编程
	 */
	@RequestMapping(value="/list")
	public Result listCategory(@RequestParam Map<String,Object> params ){
		 
		IPage page= QueryTools.initPage(params);

		User user=LoginUtils.getCurrentUserInfo();
		params.put("qxUserid",user.getUserid());
		params.put("qxBranchId",user.getBranchId());
		params.put("qxRoleids",LoginUtils.getMyRoleids());
		params.put("qxDeptids",user.getDeptids());
		QueryTools.alias(params,"* c.");
		RequestUtils.transformArray(params,"crelyIdList");
		RequestUtils.transformArray(params,"crelySidList");
		List<Map<String,Object>>	datas = categoryService.selectListMapByWhere(page,QueryTools.initQueryWrapper(Category.class,params).in("c.branch_id",user.getBranchId(),platformBranchId),params);	//列出Category列表
		return Result.ok().setData(datas).setTotal(page.getTotal()); 
	}
	/**
	 * 请求,如list/tree 
	 * 根据条件查询数据对象列表,如果不上传分页参数，将不会分页。后台自动分页，无需编程
	 */
	@RequestMapping(value="/list/tree")
	public Result listCategoryTree( @RequestParam Map<String,Object> params ){
		 return listCategory(params);
	}


	
	
	/**
	 * 新增一条数据
	 */
	@RequestMapping(value="/add")
	public Result addCategory(@RequestBody Category category) {

			if(StringUtils.isEmpty(category.getName())){
				return Result.error("name-null","分类名称不能为空");
			}
			if(StringUtils.isEmpty(category.getCategoryType())){
				return Result.error("categoryType-null","主题不能为空");
			}
			if(StringUtils.isEmpty(category.getId())){
				category.setId(this.categoryService.createKey("id"));
			}else{
				if(this.categoryService.countByWhere(new Category(category.getId()))>0){
					return Result.error("id-exists","分类编号已存在");
				}
			}
			User user=LoginUtils.getCurrentUserInfo();
			String pid=category.getPid();
			if(ObjectTools.isEmpty(pid)){
				category.setPid("0");
			}
			if(ObjectTools.isNotEmpty(pid) && !"0".equals(pid)){
				Category pcate=categoryService.getById(category.getPid());
				if(pcate==null){
					return Result.error("pcate-not-exists","上级分类不存在");
				}
				String pQxLvl=ObjectTools.isNotEmpty(pcate.getQxLvl())? pcate.getQxLvl() : "0";
				String cQxLvl=ObjectTools.isNotEmpty(category.getQxLvl())? category.getQxLvl() : "0";
				//如果上级的权限小于下级，置下级的权限=上级的权限
				if(NumberUtil.getInteger(pQxLvl,0) > NumberUtil.getInteger(cQxLvl,0)){
					category.setQxLvl(pQxLvl);
				}else{
					category.setQxLvl(cQxLvl);
				}
				if(pQxLvl.equals("2")){
					LangTips tips=categoryService.checkQx(pcate,"edit");
					if(!tips.isOk()){
						return Result.error(tips);
					}
				}
			}
			if(ObjectTools.isEmpty(category.getQxLvl())){
				category.setQxLvl("0");
			}

			String cbranchId=LoginUtils.getCurrentUserInfo().getBranchId();
			if(StringUtils.isEmpty(category.getBranchId())) {
				category.setBranchId(cbranchId);
			}
			category.setCuserid(user.getUserid());
			categoryService.insert(category);
			return Result.ok().setData(category);
	}
	
	
	/**
	 * 根据主键删除1条数据
	 */
	@RequestMapping(value="/del")
	public Result delCategory(@RequestBody Category category){
		

			Category delQ=new Category();
			delQ.setPid(category.getId());
			if(this.categoryService.countByWhere(delQ)>0){
				return Result.error("children-exists","还有子分类，不允许删除");
			}
			categoryService.deleteByPk(category);
		return Result.ok();
	}
	
	
	/**
	 * 根据主键修改一条数据
	 */
	@RequestMapping(value="/edit")
	public Result editCategory(@RequestBody Category category) {

			categoryService.updateSomeFieldByPk(category);
			if("0".equals(category.getPid())){
				categoryService.updatecategoryTypeByPid(category);
			}
			return Result.ok().setData(category);
	}

	@ApiOperation(value = "批量修改某些字段", notes = "")
	@ApiEntityParams(value = Category.class, props = {}, remark = "档案信息表", paramType = "body")
	@ApiResponses({
			@ApiResponse(code = 200, response = Category.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	@RequestMapping(value = "/editSomeFields", method = RequestMethod.POST)
	public Map<String, Object> editSomeFields(@ApiIgnore @RequestBody Map<String, Object> params) {
  		 int i= this.categoryService.editSomeFields(params);
  		 return Result.ok();
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
			if (ObjectTools.isEmpty(link.getPid()) || "0".equals(link.getPid()) ||"0".equals(link.getPid())) {
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


		List<Category> childCategorys = this.categoryService.selectListByIds(childIds.stream().collect(Collectors.toList()));
		List<Category> parentCategorys = new ArrayList<>();
		if (!pids.isEmpty()) {
			parentCategorys = this.categoryService.selectListByIds(pids.stream().collect(Collectors.toList()));

		}
		if (childCategorys == null || childCategorys.size() == 0) {
			return Result.error("data-0", "数据不存在");
		}

		// 过滤掉新旧上级一致的分类
		childCategorys = childCategorys.stream().filter(k -> !k.getPid().equals(newIdLinks.get(k.getId()))).collect(Collectors.toList());
		if (childCategorys == null || childCategorys.size() == 0) {
			return Result.error("id-link-same-0", "新旧上级一致，无须保存");
		}

		String categoryType = childCategorys.get(0).getCategoryType();
		if(ObjectTools.isEmpty(categoryType)){
			categoryType="";
		}
		Map<String, Category> categoryMap = new HashMap<>();
		for (Category category : childCategorys) {
			categoryMap.put(category.getId(), category);
			String oct=category.getCategoryType();
			if(ObjectTools.isEmpty(oct)){
				oct="";
			}
			// 进行是否同主题检测
			if (!categoryType.equals(oct)) {
				return Result.error("category-must-same-categoryType", "所有分类必须同主题，%s归属主题为%s,与其它分类归属不一致", category.getName(), category.getCategoryType());
			}
		}
		for (Category category : parentCategorys) {
			categoryMap.put(category.getId(), category);
			// 进行是否同主题检测
			String oct=category.getCategoryType();
			if(ObjectTools.isEmpty(oct)){
				oct="";
			}
			if (!categoryType.equals(oct)) {
				return Result.error("category-must-same-categoryType", "所有分类必须同主题，%s归属主题为%s,与其它分类归属不一致", category.getName(), category.getCategoryType());
			}
		}
 

		// 进行权限判断  
		if (!LoginUtils.isBranchAdmin(user.getBranchId())) {
			 
		}


		/**
		 * 按上级进行分组
		 */
		Map<String, List<Category>> xmMaps = new HashMap<>();
		for (Category category : childCategorys) {
			String newPid = newIdLinks.get(category.getId());
			Category parent = null;
			String pidPaths = null;
			if (ObjectTools.isEmpty(newPid) || "0".equals(newPid)||"0".equals(newPid)) {//向根迁移
				pidPaths = "0,";
			} else {
				parent = categoryMap.get(newPid);
				pidPaths = parent.getPaths();
			}

			List<Category> categorys = xmMaps.get(pidPaths);
			if (categorys == null) {
				categorys = new ArrayList<>();
				categorys.add(category);
			} else {
				categorys.add(category);
			}
			xmMaps.put(pidPaths, categorys);
		}

		// 分组后要进行降序排序，从底层往上更新
		List<String> pidPathsList = xmMaps.keySet().stream().sorted((x, y) -> {
			return y.split(",").length - x.split(",").length;
		}).collect(Collectors.toList());
		for (String pidPaths : pidPathsList) {
			List<Category> categorys = xmMaps.get(pidPaths);
			//上级为顶级的情况处理
			if ("0".equals(pidPaths) || "0,".equals(pidPaths) || ObjectTools.isEmpty(pidPaths)||"0,".equals(pidPaths)) {
				this.categoryService.batchChangeParent(categorys, null);
			} else {
				String[] parentIds = pidPaths.split(",");
				String parentId = parentIds[parentIds.length - 1];
				//必须重新查询,因为可能上一次循环已经更新了数据库中的pidPaths
				Category parentCategory = this.categoryService.getById(parentId);
				this.categoryService.batchChangeParent(categorys, parentCategory);
			}

		}
		return Result.ok();
	}

	/**
	 * 批量插入或者更新
	 */
	@RequestMapping(value="/batchSave")
	public Result batchSave(@RequestBody List<Category> categorys) {

			String cbranchId=LoginUtils.getCurrentUserInfo().getBranchId();
			if(categorys==null || categorys.size()<=0) {
				return Result.error("data-is-null","分类不能为空");
			}
			Map<String,Category> maps=new HashMap<>();
			for (Category category : categorys) {
				if(!StringUtils.hasText(category.getId())){
					return Result.error("id-is-null","分类编号不能为空");
				}
				if(!StringUtils.hasText(category.getPid())){
					return Result.error("pid-is-null","上级分类编号不能为空");
				}
				category.setBranchId(cbranchId);
				maps.put(category.getId(),category);
			}
			List<Category> addList=maps.values().stream().collect(Collectors.toList());
			if(addList!=null && addList.size()>0){
				for (Category c : addList) {
					if(!StringUtils.hasText(c.getPid())){
						c.setPid("0");
					}
				}
			}
			this.categoryService.batchSave(addList);
		return Result.ok();
	}

	/**
	 * 批量删除
	 */
	@RequestMapping(value="/batchDel")
	public Result batchDelCategory(@RequestBody List<Category> categorys) {

			String cbranchId=LoginUtils.getCurrentUserInfo().getBranchId();
			if(categorys==null || categorys.size()<=0) {
				return null;
			}
			for (Category c : categorys) { 
				if(cbranchId.equals(c.getBranchId())) {
					categorys.add(c);
				}
			}
			if(categorys.size()<=0) {
				return Result.error("没有当前登录机构的分类可以删除");
				
			}else {
				categoryService.batchDeleteByMyBranchId(categorys,cbranchId);
				return Result.ok("成功删除"+categorys.size()+"条记录。注意：只删除当前登录机构的记录");
			}
	} 
	
}
