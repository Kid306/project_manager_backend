package com.xm.core.ctrl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.entity.Result;
import com.mdp.core.query.QueryTools;
import com.mdp.core.utils.EntityUtils;
import com.mdp.core.utils.ObjectTools;
import com.mdp.core.utils.RequestUtils;
import com.mdp.msg.client.PushNotifyMsgService;
import com.mdp.safe.client.cache.DeptRedisCacheService;
import com.mdp.safe.client.entity.Dept;
import com.mdp.safe.client.entity.User;
import com.mdp.safe.client.utils.LoginUtils;
import com.mdp.sensitive.SensitiveWordService;
import com.mdp.swagger.ApiEntityParams;
import com.xm.core.entity.XmProduct;
import com.xm.core.entity.XmProductCopyVo;
import com.xm.core.entity.XmProductProjectLink;
import com.xm.core.entity.XmProject;
import com.xm.core.service.*;
import com.xm.core.vo.XmProductAddVo;
import io.swagger.annotations.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.*;
import java.util.stream.Collectors;

import static com.mdp.core.utils.BaseUtils.fromMap;
import static com.mdp.core.utils.BaseUtils.toMap;

/**
 * url编制采用rest风格,如对XM.xm_product 产品表的操作有增删改查,对应的url分别为:<br>
 *  新增: xm/xmProduct/add <br>
 *  查询: xm/xmProduct/list<br>
 *  模糊查询: xm/xmProduct/listKey<br>
 *  修改: xm/xmProduct/edit <br>
 *  删除: xm/xmProduct/del<br>
 *  批量删除: xm/xmProduct/batchDel<br>
 * 组织 com.qqkj  顶级模块 oa 大模块 xm 小模块 <br>
 * 实体 XmProduct 表 XM.xm_product 当前主键(包括多主键): id; 
 ***/
@RestController("xm.core.xmProductController")
@RequestMapping(value="/xm/core/xmProduct")
@Api(tags={"产品表操作接口"})
public class XmProductController {
	
	static Log logger=LogFactory.getLog(XmProductController.class);
	
	@Autowired
	private XmProductService xmProductService;

	@Autowired
	private XmProjectService projectService;

	@Autowired
	private XmGroupService groupService;


	@Autowired
	private XmRecordService xmRecordService;

	@Autowired
	PushNotifyMsgService notifyMsgService;

	@Autowired
	SensitiveWordService sensitiveWordService;

	@Autowired
	DeptRedisCacheService deptRedisCacheService;

	Map<String,Object> fieldsMap = toMap(new XmProduct());

	@Value("${mdp.platform-branch-id:platform-branch-001}")
	String platformBranchId="platform-branch-001";
	@Autowired
	XmProductStateService xmProductStateService;

	@ApiOperation( value = "查询产品表信息列表",notes="listXmProduct,条件之间是 and关系,模糊查询写法如 {studentName:'%才哥%'}")
	@ApiImplicitParams({  
		@ApiImplicitParam(name="id",value="菜单编号,主键",required=false),
		@ApiImplicitParam(name="productName",value="菜单名称",required=false),
		@ApiImplicitParam(name="branchId",value="机构号",required=false),
		@ApiImplicitParam(name="remark",value="备注",required=false),
		@ApiImplicitParam(name="pageSize",value="每页记录数",required=false),
		@ApiImplicitParam(name="pageNum",value="当前页码,从1开始",required=false),
		@ApiImplicitParam(name="total",value="总记录数,服务器端收到0时，会自动计算总记录数，如果上传>0的不自动计算",required=false),
		@ApiImplicitParam(name="orderBy",value="排序列 如性别、学生编号排序 orderBy = sex desc,student_id desc",required=false),
		@ApiImplicitParam(name="count",value="是否进行总条数计算,count=true|false",required=false) 
	})
	@ApiResponses({
		@ApiResponse(code = 200,response= XmProduct.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Result listXmProduct(@ApiIgnore @RequestParam Map<String,Object> params){

		RequestUtils.transformArray(params, "ids");
		IPage page=QueryTools.initPage(params);
		String id= (String) params.get("id");
		String queryScope= (String) params.get("queryScope");
		User user = LoginUtils.getCurrentUserInfo();
		if("branchId".equals(queryScope)){//如果是机构管理员，可以查询全机构数据，否则只能查自己相关的数据
			params.put("res.branchId",user.getBranchId());
		}else{
			params.remove("branchId");
		}
		params.put("linkBranchId",user.getBranchId());
		params.put("platformBranchId",platformBranchId);
		if(StringUtils.hasText(id)){
			queryScope="productId";
			params.put("queryScope",id);
		}
		if("branchId".equals(queryScope)&&!"1".equals(params.get("isTpl"))){//如果是机构管理员，可以查询全机构数据，否则只能查自己相关的数据
			if(!LoginUtils.isBranchAdmin(user.getBranchId())){
				return Result.error("no-qx-query-branch-scope-product","您不是机构管理员，不允许查询整个机构的产品");
			}
		}else if("createUserid".equals(queryScope)){
			params.put("createUserid",user.getUserid());
		}else if("pmUserid".equals(queryScope)){
			params.put("pmUserid",user.getUserid());
		}else if("assUserid".equals(queryScope)){
			params.put("assUserid",user.getUserid());
		}else if("admUserid".equals(queryScope)){
			params.put("admUserid",user.getUserid());
		}else if("compete".equals(queryScope)){
		}else if("productId".equals(queryScope)){
			if(!StringUtils.hasText(id)){
				return Result.error("产品编号id必输");
			}
		}else{
			if(!StringUtils.hasText(queryScope)){//如果是机构管理员，可以查询全机构数据，否则只能查自己相关的数据
				queryScope="compete";
				params.put("queryScope",queryScope);
			}
		}

		String linkProjectId= (String) params.get("linkProjectId");
		if(ObjectTools.isNotEmpty(linkProjectId)){
			XmProject xmProject=this.projectService.getProjectFromCache(linkProjectId);
			if("1".equals(xmProject.getIsTpl())){
				params.put("isTpl","1");
			}
		}
		QueryTools.alias(params,"productName res.productName");

		QueryWrapper<XmProduct> qw = QueryTools.initQueryWrapper(XmProduct.class , params);

		if(!StringUtils.isEmpty(user.getDeptid())){
			Dept dept=deptRedisCacheService.getDept(user.getDeptid());
			if(dept==null){
				params.put("myIdPath","");
			}else{
				params.put("myIdPath",dept.getIdPath());
			}
		}
		params.put("myDeptid",user.getDeptid());
		params.put("myUserid",user.getUserid());
		List<Map<String,Object>> datas =new ArrayList<>();
		if(params.containsKey("api")){
			datas = xmProductService.selectListMapByWhereWithState(page,qw,params);
		}else {
			datas = xmProductService.selectListMapByWhere(page,qw,params);
		}
		return Result.ok().setData(datas).setTotal(page.getTotal());
	}
	@ApiOperation( value = "查询产品表信息列表连同相关状态数据一起带出",notes="") 
	@ApiResponses({
		@ApiResponse(code = 200,response=XmProduct.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/listWithState",method=RequestMethod.GET)
	public Result listWithState(@ApiIgnore @RequestParam Map<String,Object> params){
		params.put("api","listWithState");
		return this.listXmProduct(params);
	}

	/***/
	@ApiOperation( value = "新增一条产品表信息",notes="addXmProduct,主键如果为空，后台自动生成")
	@ApiResponses({
			@ApiResponse(code = 200,response=XmProduct.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	//@HasQx(value = "xm_core_xmProduct_copyTo",name = "通过复制创建产品/战略规划等",moduleId = "xm-project",moduleName = "管理端-产品管理系统")
	@RequestMapping(value="/copyTo",method=RequestMethod.POST)
	public Result copyTo(@RequestBody XmProductCopyVo xmProduct) {

			User user= LoginUtils.getCurrentUserInfo();
			if( !StringUtils.hasText(xmProduct.getId())){
				return Result.error("id-0","请上送原产品编号参数id");
			}
			if( !StringUtils.hasText(xmProduct.getProductName())){
				return Result.error("productName-0","请上送新产品名称");
			}
			if(StringUtils.hasText(xmProduct.getCode())){
				XmProduct pq=new XmProduct();
				pq.setBranchId(user.getBranchId());
				pq.setCode(xmProduct.getCode());
				List<XmProduct> xmProductList=this.xmProductService.selectListByWhere(pq);
				if(xmProductList!=null && xmProductList.size()>0){
					return Result.error("code-exists","产品编码【"+xmProduct.getCode()+"】已存在，，请重新输入新的产品编码，如果为空，后台自动生成");
				}
			}

			XmProduct xmProductNew=xmProductService.copyTo(user,xmProduct);
			this.xmProductStateService.loadTasksToXmProductState(xmProductNew.getId());
			xmRecordService.addXmProductRecord(xmProductNew.getId(),"通过拷贝创建产品","拷贝产品【"+xmProduct.getId()+"】【"+xmProduct.getProductName()+"】,创建新的产品【"+xmProductNew.getId()+"】【"+xmProductNew.getProductName()+"】");
			
		return Result.ok();
		
	}
	
	/***/
	@ApiOperation( value = "新增一条产品表信息",notes="addXmProduct,主键如果为空，后台自动生成")
	@ApiResponses({
		@ApiResponse(code = 200,response=XmProduct.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	//@HasQx(value = "xm_core_xmProduct_add",name = "创建产品/战略规划等",moduleId = "xm-project",moduleName = "管理端-产品管理系统")
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Result addXmProduct(@RequestBody XmProductAddVo xmProduct) {

			User user=LoginUtils.getCurrentUserInfo();
			if(StringUtils.isEmpty(xmProduct.getCode())) {
				return Result.error("code-0","","产品代号不能为空");
			}else{
				 XmProduct xmProductQuery = new  XmProduct();
				xmProductQuery.setBranchId(user.getBranchId());
				xmProductQuery.setCode(xmProduct.getCode());
				if(xmProductService.countByWhere(xmProductQuery)>0){
					return Result.error("产品代号已存在，请修改再提交");

				}
			}
			if(xmProduct.getLinks()!=null && xmProduct.getLinks().size()>0){
				for (XmProductProjectLink link : xmProduct.getLinks()) {
					if(!StringUtils.hasText(link.getProjectId())) {
						return Result.error("projectId-0", "", "关联产品编号不能为空");
					}
				}
			}
			if(!StringUtils.hasText(xmProduct.getProductName())){
				return Result.error("productName-0","","产品名称不能为空");
			}

			Set<String> words=sensitiveWordService.getSensitiveWord(xmProduct.getProductName());
			if(words!=null && words.size()>0){
				return Result.error("name-sensitive-word","名字有敏感词"+words+",请修改后再提交");
			}
			words=sensitiveWordService.getSensitiveWord(xmProduct.getRemark());
			if(words!=null && words.size()>0){
				return Result.error("remark-sensitive-word","备注中有敏感词"+words+",请修改后再提交");
			}
			xmProduct.setPmUserid(user.getUserid());
			xmProduct.setPmUsername(user.getUsername());
			xmProduct.setId(this.xmProductService.createProductId(xmProduct.getCode()));
			xmProduct.setCtime(new Date());
			xmProduct.setLtime(new Date());
			if(!StringUtils.hasText(xmProduct.getIsTpl())){
				xmProduct.setIsTpl("0");
			}
			xmProduct.setBizFlowState("0");
			xmProduct.setPstatus("0");
			xmProduct.setDel("0");
			xmProduct.setLocked("0");
			if(xmProduct.getLinks()!=null && xmProduct.getLinks().size()>0){
				for (XmProductProjectLink link : xmProduct.getLinks()) {
					 link.setProductId(xmProduct.getId());
					 link.setCusername(user.getUsername());
					 link.setCuserid(user.getUserid());
					 link.setLinkStatus("1");
					 link.setCtime(new Date());
				}
			}
			xmProductService.addProduct(xmProduct);
			notifyMsgService.pushMsg(user,xmProduct.getPmUserid(),xmProduct.getPmUsername(),"您成为产品【"+xmProduct.getProductName()+"】的产品经理，请及时跟进。",null);
			if(StringUtils.hasText(xmProduct.getAssUserid()) && !xmProduct.getAssUserid().equals(xmProduct.getPmUserid())){
				notifyMsgService.pushMsg(user,xmProduct.getAssUserid(),xmProduct.getAssUsername(),"您成为产品【"+xmProduct.getProductName()+"】的副经理、助理，请及时跟进。",null);

			}
			if(StringUtils.hasText(xmProduct.getAdmUserid()) && !xmProduct.getAdmUserid().equals(xmProduct.getPmUserid())){
				notifyMsgService.pushMsg(user,xmProduct.getAdmUserid(),xmProduct.getAdmUsername(),"您成为产品【"+xmProduct.getProductName()+"】的产品总监，请及时跟进。",null);
			}
			xmRecordService.addXmProductRecord(xmProduct.getId(),"创建产品","创建产品【"+xmProduct.getId()+"】【"+xmProduct.getProductName()+"】");
			xmProductStateService.loadTasksToXmProductState(xmProduct.getId());
		return Result.ok().setData(xmProduct);
	}
	/***/
	@ApiOperation( value = "从回收站恢复产品",notes="unDelXmProduct,仅需要上传主键字段")
	@ApiResponses({
			@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}}")
	})
	//@HasQx(value = "xm_core_xmProduct_unDel",name = "从回收站恢复产品等",moduleId = "xm-project",moduleName = "管理端-产品管理系统")
	@RequestMapping(value="/unDel",method=RequestMethod.POST)
	public Result unDelXmProduct(@RequestBody XmProduct xmProduct){

			if(!StringUtils.hasText(xmProduct.getId())){
				return Result.error("id-0","","产品编号不能为空");
			}
			User user=LoginUtils.getCurrentUserInfo();
			XmProduct xmProductDb=xmProductService.getProductFromCache(xmProduct.getId());
			if(xmProductDb==null){
				return Result.error("data-0","产品已不存在");
			}
			if(!"1".equals(xmProductDb.getDel())){
				return Result.error("del-not-1","该产品不是已删除状态");
			}
			if(!user.getBranchId().equals(xmProductDb.getBranchId())){
				return Result.error("branchId-not-right","该产品不属于您所在的机构，不允许操作");
			}
			if(!groupService.checkUserIsProductAdm(xmProductDb,user.getUserid())){
				if(!LoginUtils.isBranchAdmin(xmProductDb.getBranchId())){
					return Result.error("pmUserid-not-right","您不是该产品产品管理人员，不允许操作");
				}
			}
			/**
			 if(!"1".equals(xmProductDb.getIsTpl())){
			 long menus=xmProductService.checkExistsMenu(xmProduct.getId());
			 if(menus>0) {
			 return Result.error("had-menus","该产品有"+menus+"个需求关联，不允许删除，请先解绑需求");

			 }
			 }
			 **/
			XmProduct xmProductDel=new XmProduct();
			xmProductDel.setId(xmProductDb.getId());
			xmProductDel.setDel("0");
			xmProductDel.setLtime(new Date());
			xmProductService.updateSomeFieldByPk(xmProductDel);
			xmProductService.clearCache(xmProduct.getId());
			xmRecordService.addXmProductRecord(xmProduct.getId(),"从回收站恢复产品","从回收站恢复产品产品【"+xmProductDb.getId()+"】【"+xmProductDb.getProductName()+"】");

		return Result.ok();
		
	}
	
	/***/
	@ApiOperation( value = "删除一条产品表信息",notes="delXmProduct,仅需要上传主键字段")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}}")
	})
	//@HasQx(value = "xm_core_xmProduct_del",name = "删除产品/战略规划等",moduleId = "xm-project",moduleName = "管理端-产品管理系统")
	@RequestMapping(value="/del",method=RequestMethod.POST)
	public Result delXmProduct(@RequestBody XmProduct xmProduct){

			if(!StringUtils.hasText(xmProduct.getId())){
				return Result.error("id-0","","产品编号不能为空");
			}
 			User user=LoginUtils.getCurrentUserInfo();
			 XmProduct xmProductDb=xmProductService.getProductFromCache(xmProduct.getId());
			 if(xmProductDb==null){
				return Result.error("data-0","产品已不存在");
			 }
			 if(!"0".equals(xmProductDb.getPstatus())&&!"3".equals(xmProductDb.getPstatus())){
				 return Result.error("pstatus-not-0-3","该产品不是初始、已关闭状态，不允许删除");
			 }
			 if(!user.getBranchId().equals(xmProductDb.getBranchId())){
				 return Result.error("branchId-not-right","该产品不属于您所在的机构，不允许删除");
			 }
			 if(!groupService.checkUserIsProductAdm(xmProductDb,user.getUserid())){
			 	if(!LoginUtils.isBranchAdmin(xmProductDb.getBranchId())){
					return Result.error("pmUserid-not-right","您不是该产品管理人员，不允许删除.若要强制删除，请联系产品管理人员或者机构管理员。");
				}
			 }
			 /**
			 if(!"1".equals(xmProductDb.getIsTpl())){
				 long menus=xmProductService.checkExistsMenu(xmProduct.getId());
				 if(menus>0) {
					 return Result.error("had-menus","该产品有"+menus+"个需求关联，不允许删除，请先解绑需求");

				 }
			 }
			  **/
			 XmProduct xmProductDel=new XmProduct();
			 xmProductDel.setId(xmProductDb.getId());
			 xmProductDel.setDel("1");
			xmProductDel.setLtime(new Date());
			 xmProductService.updateSomeFieldByPk(xmProductDel);
			xmProductService.clearCache(xmProduct.getId());
			xmRecordService.addXmProductRecord(xmProduct.getId(),"删除产品","删除产品【"+xmProductDb.getId()+"】【"+xmProductDb.getProductName()+"】");

 		return Result.ok();		
	}
	@ApiOperation( value = "批量修改某些字段",notes="")
	@ApiEntityParams( value = XmProduct.class, props={ }, remark = "产品", paramType = "body" )
	@ApiResponses({
			@ApiResponse(code = 200,response=XmProduct.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	@RequestMapping(value="/editSomeFields",method=RequestMethod.POST)
	public Result editSomeFields( @ApiIgnore @RequestBody Map<String,Object> xmProductMap) {

			List<String> ids= (List<String>) xmProductMap.get("$pks");
			if(ids==null || ids.size()==0){
				return Result.error("$pks-0","ids不能为空");
			}
			if( ids.size()>1){
				return Result.error("$pks-1","一次只能修改一个产品");
			}
			Set<String> fields=new HashSet<>();
			fields.add("id");
			for (String fieldName : xmProductMap.keySet()) {
				if(fields.contains(fieldName)){
					return Result.error(fieldName+"-no-edit",fieldName+"不允许修改");
				}
			}
			Set<String> fieldKey=xmProductMap.keySet().stream().filter(i-> fieldsMap.containsKey(i)).collect(Collectors.toSet());
			fieldKey=fieldKey.stream().filter(i->xmProductMap.get(i)!=null).collect(Collectors.toSet());

			if(fieldKey.size()<=0) {
				return Result.error("fieldKey-0","没有需要更新的字段");
			}
			XmProduct xmProduct = fromMap(xmProductMap,XmProduct.class);
			List<XmProduct> xmProductsDb=xmProductService.selectListByIds(ids);
			if(xmProductsDb==null ||xmProductsDb.size()==0){
				return Result.error("data-0","记录已不存在");
			}

			User user = LoginUtils.getCurrentUserInfo();
			XmProduct xmProductDb=xmProductsDb.get(0);
			if(!LoginUtils.isBranchAdmin(xmProductDb.getBranchId())  && !groupService.checkUserIsProductAdm(xmProductDb,user.getUserid())){
				return Result.error("noqx-all","无权限操作。产品管理人员、机构管理员有权限更新产品基础信息。");
			}
			if(xmProductMap.containsKey("assUserid")){
				String assUserid= (String) xmProductMap.get("assUserid");
				String assUsername= (String) xmProductMap.get("assUsername");
				if(StringUtils.hasText(assUserid)){
					if(!user.getUserid().equals(xmProductDb.getPmUserid()) && !user.getUserid().equals(xmProductDb.getAdmUserid())){
						return Result.error("noqx-pm","您无权限操作,产品经理、总监可以委任产品副经理。");
					}
				}
			}
			if(xmProductMap.containsKey("pmUserid")){
				String pmUserid= (String) xmProductMap.get("pmUserid");
				String pmUsername= (String) xmProductMap.get("pmUsername");
				if(StringUtils.hasText(pmUserid)){
					if(!user.getUserid().equals(xmProductDb.getAdmUserid())){
						return Result.error("noqx-adm","您无权限操作,产品总监可以委任产品经理。");
					}
				}
			}
			if(xmProductMap.containsKey("admUserid")){
				String admUserid= (String) xmProductMap.get("admUserid");
				String admUsername= (String) xmProductMap.get("admUsername");
				if(StringUtils.hasText(admUserid)){
					if(!LoginUtils.isBranchAdmin(xmProductDb.getBranchId()) && !user.getUserid().equals(xmProductDb.getAdmUserid())){
						return Result.error("noqx-adm","您无权限操作，产品总监、机构管理员可以委任产品总监。");
					}
				}
			}
			xmProductService.editSomeFields(xmProductMap);
			xmRecordService.addXmProductRecord(xmProductDb.getId(),"修改产品信息", ChangeLogService.getChangeLogsString(xmProductMap,xmProductDb));
			xmProductService.clearCache(xmProductDb.getId());
			if(StringUtils.hasText(xmProduct.getPmUserid()) && !xmProduct.getPmUserid().equals(xmProductDb.getPmUserid())){
				notifyMsgService.pushMsg(user,xmProduct.getPmUserid(),xmProduct.getPmUsername(),"您成为产品【"+xmProductDb.getProductName()+"】的产品经理，请及时跟进。",null);

			}
			if(StringUtils.hasText(xmProduct.getAssUserid()) && !xmProduct.getAssUserid().equals(xmProductDb.getAssUserid())){
				notifyMsgService.pushMsg(user,xmProduct.getAssUserid(),xmProduct.getAssUsername(),"您成为产品【"+xmProductDb.getProductName()+"】的副经理，请及时跟进。",null);

			}
			if(StringUtils.hasText(xmProduct.getAdmUserid()) && !xmProduct.getAdmUserid().equals(xmProductDb.getAdmUserid())){
				notifyMsgService.pushMsg(user,xmProduct.getAdmUserid(),xmProduct.getAdmUsername(),"您成为产品【"+xmProductDb.getProductName()+"】的产品总监，请及时跟进。",null);
			}
		return Result.ok();
		
	}
	
	/***/
	@ApiOperation( value = "创建产品代号",notes="createProductCode")
	@ApiResponses({
			@ApiResponse(code = 200,response=XmProduct.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	//@HasQx(value = "xm_core_xmProduct_createProductCode",name = "创建产品代号",moduleId = "xm-project",moduleName = "管理端-产品管理系统")
	@RequestMapping(value="/createProductCode",method=RequestMethod.POST)
	public Result createProductCode() {

			User user=LoginUtils.getCurrentUserInfo();
			String data=this.xmProductService.createProductCode(user.getBranchId());
			
		return Result.ok().setData(data);
		
	}


	/***/
	@ApiOperation( value = "根据主键修改一条产品表信息",notes="editXmProduct")
	@ApiResponses({
		@ApiResponse(code = 200,response=XmProduct.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	//@HasQx(value = "xm_core_xmProduct_edit",name = "修改产品/战略规划等基本信息",moduleId = "xm-project",moduleName = "管理端-产品管理系统")
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public Result editXmProduct(@RequestBody XmProduct xmProduct) {


			if(!StringUtils.hasText(xmProduct.getId())){
				return Result.error("id-0","","产品编号不能为空");
			}
			XmProduct xmProductDb=xmProductService.getProductFromCache(xmProduct.getId());
			if(xmProductDb==null){
				return Result.error("data-0","产品已不存在");
			}
			User user=LoginUtils.getCurrentUserInfo();
			if(!LoginUtils.isBranchAdmin(xmProductDb.getBranchId())){
				if(!groupService.checkUserIsProductAdm(xmProductDb,user.getUserid())){
					return Result.error("no-qx-0","您无权修改该产品。");
				}
			}

			xmProduct.setLtime(new Date());
			xmProductService.updateSomeFieldByPk(xmProduct);
			xmProductService.clearCache(xmProduct.getId());
			xmRecordService.addXmProductRecord(xmProduct.getId(),"修改产品","修改产品【"+xmProductDb.getId()+"】【"+xmProductDb.getProductName()+"】");

			if(StringUtils.hasText(xmProduct.getPmUserid()) && !xmProduct.getPmUserid().equals(xmProductDb.getPmUserid())){
				notifyMsgService.pushMsg(user,xmProduct.getPmUserid(),xmProduct.getPmUsername(),"您成为产品【"+xmProductDb.getProductName()+"】的产品经理，请及时跟进。",null);

			}
			if(StringUtils.hasText(xmProduct.getAssUserid()) && !xmProduct.getAssUserid().equals(xmProductDb.getAssUserid())){
				notifyMsgService.pushMsg(user,xmProduct.getAssUserid(),xmProduct.getAssUsername(),"您成为产品【"+xmProductDb.getProductName()+"】的副经理、助理，请及时跟进。",null);

			}
			if(StringUtils.hasText(xmProduct.getAdmUserid()) && !xmProduct.getAdmUserid().equals(xmProductDb.getAdmUserid()) ){
				notifyMsgService.pushMsg(user,xmProduct.getAdmUserid(),xmProduct.getAdmUsername(),"您成为产品【"+xmProductDb.getProductName()+"】的产品总监，请及时跟进。",null);
			}
		return Result.ok();
	}
	
	


}
