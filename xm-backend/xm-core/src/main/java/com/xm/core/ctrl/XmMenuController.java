package com.xm.core.ctrl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.entity.LangTips;
import com.mdp.core.entity.Result;
import com.mdp.core.entity.Tips;
import com.mdp.core.query.QueryTools;
import com.mdp.core.utils.*;
import com.mdp.core.valid.ValidUtil;
import com.mdp.msg.client.PushNotifyMsgService;
import com.mdp.safe.client.entity.User;
import com.mdp.safe.client.utils.LoginUtils;
import com.mdp.sensitive.SensitiveWordService;
import com.mdp.swagger.ApiEntityParams;
import com.xm.core.PubTool;
import com.xm.core.entity.*;
import com.xm.core.queue.XmMenuSumParentsPushService;
import com.xm.core.service.*;
import com.xm.core.vo.BatchAddXmMenusVo;
import com.xm.core.vo.BatchChangeParentMenuVo;
import com.xm.core.vo.ImportMenuByTplVo;
import io.swagger.annotations.*;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.*;
import java.util.stream.Collectors;

import static com.mdp.core.utils.BaseUtils.map;

/**
 * url编制采用rest风格,如对XM.xm_menu 项目菜单表的操作有增删改查,对应的url分别为:<br>
 *  新增: xm/xmMenu/add <br>
 *  查询: xm/xmMenu/list<br>
 *  模糊查询: xm/xmMenu/listKey<br>
 *  修改: xm/xmMenu/edit <br>
 *  删除: xm/xmMenu/del<br>
 *  批量删除: xm/xmMenu/batchDel<br>
 * 组织 com.qqkj  顶级模块 oa 大模块 xm 小模块 <br>
 * 实体 XmMenu 表 XM.xm_menu 当前主键(包括多主键): menu_id; 
 ***/
@RestController("xm.core.xmMenuController")
@RequestMapping(value="/xm/core/xmMenu")
@Api(tags={"项目菜单表操作接口"})
public class XmMenuController {

	static Log logger = LogFactory.getLog(XmMenuController.class);

	@Autowired
	private XmMenuService xmMenuService;


	@Autowired
	private XmTaskService xmTaskService;


	@Autowired
	private XmRecordService xmRecordService;

	@Autowired
	private XmGroupService groupService;


	@Autowired
	private XmProductQxService productQxService;

	@Autowired
	private XmProjectService xmProjectService;


	@Autowired
	private XmProductService productService;

	@Autowired
	XmMenuSumParentsPushService pushService;


	@Autowired
	PushNotifyMsgService notifyMsgService;

	@Autowired
	SensitiveWordService sensitiveWordService;


	Map<String, Object> fieldsMap = BaseUtils.toMap(new XmMenu());

	@ApiOperation(value = "查询项目菜单表信息列表", notes = "listXmMenu,条件之间是 and关系,模糊查询写法如 {studentName:'%才哥%'}")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "menuId", value = "菜单编号,主键", required = false),
			@ApiImplicitParam(name = "menuName", value = "菜单名称", required = false),
			@ApiImplicitParam(name = "pmenuId", value = "上级菜单", required = false),
			@ApiImplicitParam(name = "productId", value = "归属产品编号", required = false),
			@ApiImplicitParam(name = "pageSize", value = "每页记录数", required = false),
			@ApiImplicitParam(name = "pageNum", value = "当前页码,从1开始", required = false),
			@ApiImplicitParam(name = "total", value = "总记录数,服务器端收到0时，会自动计算总记录数，如果上传>0的不自动计算", required = false),
			@ApiImplicitParam(name = "orderBy", value = "排序列 如性别、学生编号排序 orderBy = sex desc,student_id desc", required = false),
			@ApiImplicitParam(name = "count", value = "是否进行总条数计算,count=true|false", required = false)
	})
	@ApiResponses({
			@ApiResponse(code = 200, response = XmMenu.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Result listXmMenu(@ApiIgnore @RequestParam Map<String, Object> params) {

		RequestUtils.transformArray(params, "menuIds");
		RequestUtils.transformArray(params, "tagIdList");
		RequestUtils.transformArray(params, "dclasss");
		IPage page = QueryTools.initPage(params);
		User user=LoginUtils.getCurrentUserInfo();
		this.paramsInit(params);
		QueryTools.alias(params, "productId res.productId", "menuId res.menuId", "menuName res.menuName");

		String linkProjectId= (String) params.get("linkProjectId");
		String productId = (String) params.get("productId");
		if(ObjectTools.isNotEmpty(linkProjectId)){
			XmProject xmProject=this.xmProjectService.getProjectFromCache(linkProjectId);
			if(xmProject==null){
				return Result.error("project-not-exists","项目不存在");

				//不是全网公开
			}else if(!"9".equals(xmProject.getShowOut())){
				params.put("pbranchId",user.getBranchId());//限定只能查自己机构的数据
			}
		}else if(ObjectTools.isNotEmpty(productId)){
			XmProduct xmProduct=this.productService.getProductFromCache(productId);
			if(xmProduct==null){
				return Result.error("project-not-exists","项目不存在");

				//不是全网公开的模板
			}else if(!"9".equals(xmProduct.getShowOut())){
				params.put("pbranchId",user.getBranchId());//限定只能查自己机构的数据
			}
		}else{
			params.put("pbranchId",user.getBranchId());//限定只能查自己机构的数据
		}
		QueryWrapper<XmMenu> qw = QueryTools.initQueryWrapper(XmMenu.class, params);
		List<Map<String, Object>> datas = xmMenuService.selectListMapByWhere(page, qw, params);

		return Result.ok("query-ok", "查询成功").setData(datas).setTotal(page.getTotal());    //列出XmMenu列表

	}
	@ApiOperation( value = "演进分析数据查询",notes="deriveList,条件之间是 and关系,模糊查询写法如 {studentName:'%才哥%'}")
	@ApiEntityParams(value = XmTask.class,props = {"id"})
	@ApiResponses({
			@ApiResponse(code = 200,response= XmTask.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/deriveList",method=RequestMethod.GET)
	public Result deriveList(@ApiIgnore @RequestParam Map<String,Object> params){


		String menuId=(String) params.get("menuId");
		if(!StringUtils.hasText(menuId)){
			return Result.error("需求编号menuId必传");
		}
		List<XmMenu> menus=xmMenuService.deriveList(menuId);
		return Result.ok().setData(menus).setTotal(menus.size());

	}
	public void paramsInit(Map<String, Object> xmMenu) {

	}

	@RequestMapping(value = "/listWithState", method = RequestMethod.GET)
	public Result listWithState(@ApiIgnore @RequestParam Map<String, Object> params) {
		User user=LoginUtils.getCurrentUserInfo();
		RequestUtils.transformArray(params, "menuIds");
		RequestUtils.transformArray(params, "tagIdList");
		RequestUtils.transformArray(params, "dclasss");
		IPage page = QueryTools.initPage(params);
		this.paramsInit(params);
		QueryTools.alias(params, "* res.");

		String linkProjectId= (String) params.get("linkProjectId");
		String productId = (String) params.get("productId");
		if(ObjectTools.isNotEmpty(linkProjectId)){
			XmProject xmProject=this.xmProjectService.getProjectFromCache(linkProjectId);
			if(xmProject==null){
				return Result.error("project-not-exists","项目不存在");

				//不是全网公开
			}else if(!"9".equals(xmProject.getShowOut())){
				params.put("pbranchId",user.getBranchId());//限定只能查自己机构的数据
			}
		}else if(ObjectTools.isNotEmpty(productId)){
			XmProduct xmProduct=this.productService.getProductFromCache(productId);
			if(xmProduct==null){
				return Result.error("project-not-exists","项目不存在");

				//不是全网公开的模板
			}else if(!"9".equals(xmProduct.getShowOut())){
				params.put("pbranchId",user.getBranchId());//限定只能查自己机构的数据
			}
		}else{
			params.put("pbranchId",user.getBranchId());//限定只能查自己机构的数据
		}
		QueryWrapper<XmMenu> qw = QueryTools.initQueryWrapper(XmMenu.class, params);
		this.paramsInit(params);
		List<Map<String, Object>> datas = xmMenuService.selectListMapByWhereWithState(page, qw, params);    //列出XmMenu列表

		return Result.ok("query-ok", "查询成功").setData(datas).setTotal(page.getTotal());    //列出XmMenu列表


	}

	@RequestMapping(value = "/listWithPlan", method = RequestMethod.GET)
	public Result listWithPlan(@ApiIgnore @RequestParam Map<String, Object> params) {
		return this.listWithState(params);
	}

	@RequestMapping(value = "/getXmMenuAttDist", method = RequestMethod.GET)
	public Result getXmMenuAttDist(@ApiIgnore @RequestParam Map<String, Object> params) {
		this.paramsInit(params);
		IPage page = QueryTools.initPage(params);
		QueryWrapper<XmMenu> qw = QueryTools.initQueryWrapper(XmMenu.class, params);
		List<Map<String, Object>> datas = this.xmMenuService.getXmMenuAttDist(page, qw, params);
		return Result.ok("ok", "成功").setData(datas).setTotal(page.getTotal());
	}

	@RequestMapping(value = "/getXmMenuAgeDist", method = RequestMethod.GET)
	public Result getXmMenuAgeDist(@ApiIgnore @RequestParam Map<String, Object> params) {
		this.paramsInit(params);
		IPage page = QueryTools.initPage(params);
		QueryWrapper<XmMenu> qw = QueryTools.initQueryWrapper(XmMenu.class, params);
		List<Map<String, Object>> datas = this.xmMenuService.getXmMenuAgeDist(page, qw, params);
		return Result.ok("ok", "成功").setData(datas).setTotal(datas.size());
	}

	@RequestMapping(value = "/getXmMenuSort", method = RequestMethod.GET)
	public Result getXmMenuSort(@ApiIgnore @RequestParam Map<String, Object> params) {

		IPage page = QueryTools.initPage(params);
		this.paramsInit(params);
		QueryWrapper<XmMenu> qw = QueryTools.initQueryWrapper(XmMenu.class, params);
		List<Map<String, Object>> datas = this.xmMenuService.getXmMenuSort(page, qw, params);
		return Result.ok().setData(datas).setTotal(page.getTotal());
	}

	/***/
	@ApiOperation(value = "新增一条项目菜单表信息", notes = "addXmMenu,主键如果为空，后台自动生成")
	@ApiResponses({
			@ApiResponse(code = 200, response = XmMenu.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	//@HasQx(value = "xm_core_xmMenu_add",name = "新增用户需求",moduleId = "xm-project",moduleName = "管理端-项目管理系统")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public Result addXmMenu(@RequestBody XmMenu xmMenu) {

		if (StringUtils.isEmpty(xmMenu.getMenuId())) {
			xmMenu.setMenuId(xmMenuService.createKey("menuId"));
		} else {
			XmMenu xmMenuQuery = new XmMenu(xmMenu.getMenuId());
			if (xmMenuService.countByWhere(xmMenuQuery) > 0) {
				return Result.error("编号重复，请修改编号再提交");

			}
		}
		Set<String> words = sensitiveWordService.getSensitiveWord(xmMenu.getMenuName());
		if (words != null && words.size() > 0) {
			return Result.error("name-sensitive-word", "名字有敏感词" + words + ",请修改后再提交");
		}
		words = sensitiveWordService.getSensitiveWord(xmMenu.getRemark());
		if (words != null && words.size() > 0) {
			return Result.error("remark-sensitive-word", "备注中有敏感词" + words + ",请修改后再提交");
		}
		if (!StringUtils.hasText(xmMenu.getMenuName())) {
			return Result.error("menuName-0", "需求名称不能为空");
		}

		if (!StringUtils.hasText(xmMenu.getProductId())) {
			return Result.error("productId-0", "需求归属产品不能为空");
		}
		User user = LoginUtils.getCurrentUserInfo();
		if (StringUtils.isEmpty(xmMenu.getMmUserid())) {
			xmMenu.setMmUserid(user.getUserid());
			xmMenu.setMmUsername(user.getUsername());
		}
		if (!StringUtils.hasText(xmMenu.getPmenuId()) || "0".equals(xmMenu.getPmenuId())) {
			if (!"1".equals(xmMenu.getDclass())) {
				return Result.error("dclass-not-1", "一级需求目录只能是史诗");
			}
		}

		XmProduct xmProduct = productService.getProductFromCache(xmMenu.getProductId());
		if (xmProduct == null) {
			return Result.error("data-0", "产品已不存在");
		}
		Tips tips = productQxService.checkProductQx(xmProduct, 2, user);
		Result.assertIsFalse(tips);
		if (StringUtils.hasText(xmMenu.getMmUserid()) && !xmMenu.getMmUserid().equals(user.getUserid())) {
			tips = productQxService.checkProductQx(xmProduct, 2, user, xmMenu.getMmUserid(), xmMenu.getMmUsername(), null);
			Result.assertIsFalse(tips);
		}


		xmMenuService.parentIdPathsCalcBeforeSave(xmMenu);
		xmMenu.setStatus("0");
		xmMenu.setChildrenCnt(0);
		xmMenu.setPbranchId(xmProduct.getBranchId());

		if (!StringUtils.hasText(xmMenu.getProposerId())) {
			xmMenu.setProposerId(user.getUserid());
			xmMenu.setProposerName(user.getUsername());
		}
		xmMenu.setCtime(new Date());
		xmMenu.setLtime(new Date());
		if (xmMenu.getStartTime() == null) {
			xmMenu.setStartTime(xmMenu.getCtime());
		}
		if (xmMenu.getEndTime() == null) {
			xmMenu.setEndTime(DateUtils.addDays(xmMenu.getCtime(), 14));
		}
		if (ObjectTools.isEmpty(xmMenu.getPmenuId())) {
			xmMenu.setPmenuId("0");
		}
		xmMenuService.insert(xmMenu);
		notifyMsgService.pushMsg(user, xmMenu.getMmUserid(), xmMenu.getMmUsername(), "您成为需求【" + xmMenu.getMenuName() + "】的负责人，请跟进需求！", null);

		xmRecordService.addXmMenuRecord(xmMenu.getProductId(), xmMenu.getMenuId(),xmMenu.getIterationId(), "需求-新增", "新增需求【" + xmMenu.getMenuName()+"】");
		return Result.ok().setData(xmMenu);
	}


	/***/
	@ApiOperation(value = "删除一条项目菜单表信息", notes = "delXmMenu,仅需要上传主键字段")
	@ApiResponses({
			@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}}")
	})
	//@HasQx(value = "xm_core_xmMenu_del",name = "删除用户需求",moduleId = "xm-project",moduleName = "管理端-项目管理系统")
	@RequestMapping(value = "/del", method = RequestMethod.POST)
	public Result delXmMenu(@RequestBody XmMenu xmMenu) {

		User user = LoginUtils.getCurrentUserInfo();
		XmTask xmTask = new XmTask();
		if (StringUtils.isEmpty(xmMenu.getMenuId())) {
			return Result.error("menuId-0", "需求编号不能为空");
		}
		xmTask.setMenuId(xmMenu.getMenuId());
		long taskCount = xmTaskService.countByWhere(xmTask);
		if (taskCount > 0) {
			return Result.error("存在" + taskCount + "个任务关联该需求，不允许删除");
		} else {
			XmMenu xmMenuDb = this.xmMenuService.selectOneById(xmMenu.getMenuId());
			if (xmMenuDb == null) {
				return Result.error("data-0", "该需求已不存在");
			}
			XmMenu xmMenuCount = new XmMenu();
			xmMenuCount.setPmenuId(xmMenu.getMenuId());
			long childrenCnt = this.xmMenuService.countByWhere(xmMenuCount);
			if (childrenCnt > 0) {
				return Result.error("childrenCnt-1", "存在" + childrenCnt + "个子需求，不允许删除,请先删除子需求");
			}
			XmProduct xmProduct = productService.getProductFromCache(xmMenuDb.getProductId());
			if (xmProduct == null) {
				return Result.error("product-data-0", "产品已不存在");
			}
			if (!groupService.checkUserIsProductAdm(xmProduct, user.getUserid())) {
				Tips tips = productQxService.checkProductQx(xmProduct, 2, user, xmMenuDb.getMmUserid(), xmMenuDb.getMmUsername(), null);
				Result.assertIsFalse(tips);
			}

			xmMenuService.removeById(xmMenu);
			xmRecordService.addXmMenuRecord(xmMenuDb.getProductId(), xmMenu.getMenuId(),xmMenuDb.getIterationId(), "需求-删除", "删除需求【" + xmMenuDb.getMenuName()+"】");

		}
		return Result.ok();
	}

	public void initFormAdd(XmMenu m){
		// 需求负责人设为导入者
	}

	/***/
	@ApiOperation(value = "根据主键修改一条项目菜单表信息", notes = "editXmMenu")
	@ApiResponses({
			@ApiResponse(code = 200, response = XmMenu.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	//@HasQx(value = "xm_core_xmMenu_editSomeFields",name = "修改用户需求中的某些字段",moduleId = "xm-project",moduleName = "管理端-项目管理系统")
	@RequestMapping(value = "/editSomeFields", method = RequestMethod.POST)
	public Result editSomeFields(@RequestBody Map<String, Object> xmMenuMap) {

		User user = LoginUtils.getCurrentUserInfo();
		List<String> menuIds = (List<String>) xmMenuMap.get("$pks");

		if (menuIds == null || menuIds.size() == 0) {
			return Result.error("$pks-0", "$pks不能为空");
		}

		Set<String> fields = new HashSet<>();
		fields.add("childrenCnt");
//		fields.add("ntype");
		fields.add("pidPaths");
		fields.add("pmenuId");
		fields.add("pbranchId");
		for (String fieldName : xmMenuMap.keySet()) {
			if (fields.contains(fieldName)) {
				return Result.error(fieldName + "-no-edit", fieldName + "不允许修改");
			}
		}

		Set<String> fieldKey = xmMenuMap.keySet().stream().filter(i -> fieldsMap.containsKey(i)).collect(Collectors.toSet());
		fieldKey = fieldKey.stream().filter(i -> !StringUtils.isEmpty(xmMenuMap.get(i))).collect(Collectors.toSet());
		if (fieldKey.size() <= 0 && !xmMenuMap.containsKey("initWorkload")) {
			return Result.error("fieldKey-0", "没有需要更新的字段");
		}

		XmMenu xmMenu = BaseUtils.fromMap(xmMenuMap, XmMenu.class);
		List<XmMenu> xmMenusDb = this.xmMenuService.selectListByIds(menuIds);
		if (xmMenusDb == null || xmMenusDb.size() == 0) {
			return Result.error("menus-0", "需求均已不存在");
		}
		XmMenu xmMenuDb = xmMenusDb.get(0);
		if (xmMenusDb.stream().filter(k -> !xmMenuDb.getProductId().equals(k.getProductId())).findAny().isPresent()) {
			return Result.error("no-same-productId", "批量操作只能在同一个产品进行。");
		}

		XmProduct xmProduct = productService.getProductFromCache(xmMenuDb.getProductId());
		if (xmProduct == null) {
			return Result.error("product-data-0", "产品已不存在");
		}

		Tips tips = productQxService.checkProductQx(xmProduct, 2, user);
		Result.assertIsFalse(tips);
		if (xmMenuMap.containsKey("mmUserid")) {
			String mmUserid = (String) xmMenuMap.get("mmUserid");
			String mmUsername = (String) xmMenuMap.get("mmUsername");
			if (!user.getUserid().equals(mmUserid)) {
				tips = productQxService.checkProductQx(xmProduct, 2, user, mmUserid, mmUsername, null);
				Result.assertIsFalse(tips);
			}
		}
		List<XmMenu> canOper = new ArrayList<>();
		List<XmMenu> noOper = new ArrayList<>();
		Map<String, Tips> noOperTips = new HashMap<>();
		if (groupService.checkUserIsProductAdm(xmProduct, user.getUserid())) {
			canOper.addAll(xmMenusDb);
		} else {
			for (XmMenu xm : xmMenusDb) {
				tips = productQxService.checkProductQx(xmProduct, 2, user, xm.getMmUserid(), xm.getMmUsername(), null);
				if (tips.isOk()) {
					canOper.add(xm);
				} else {
					noOper.add(xm);
					noOperTips.put(tips.getMsg(), tips);
				}
			}
		}
		List<XmMenu> canOper2=new ArrayList<>();
		Map<String,Tips> noDclassTips=new HashMap<>();
		// 如果是更改需求类型，有子节点不允许转成故事，
		if(xmMenuMap.containsKey("dclass")){
			int dclass=NumberUtil.getInteger(xmMenuMap.get("dclass"));
			for (XmMenu menu : canOper) {
				int mdclass=NumberUtil.getInteger(menu.getDclass());
				if(dclass>2 && mdclass<3 && menu.getChildrenCnt()>0){
					Tips tips1= LangTips.errMsg("dclass-err-1","【%s】有子节点，不允许变更为【故事】",menu.getMenuName());
					noDclassTips.put(tips1.getMsg(),tips1);
				}else if(dclass==1 && ObjectTools.isNotEmpty(menu.getPmenuId()) && !"0".equals(menu.getPmenuId())){
					Tips tips1= LangTips.errMsg("dclass-err-2","【%s】有上级，不允许变更为【史诗】",menu.getMenuName());
					noDclassTips.put(tips1.getMsg(),tips1);
				}else{
					canOper2.add(menu);
				}
			}
			if(dclass>=3){
				xmMenuMap.put("ntype","0");
			}else{
				xmMenuMap.put("ntype","1");
			}
			canOper=canOper2;
		}

		if (canOper.size() > 0) {
			String status = (String) xmMenuMap.get("status");
			if (status != null) {
				if ("3".equals(status) || "2".equals(status)) {//关闭缺陷就把结束时间定死
					xmMenuMap.put("endTime", new Date());
				}
			}
			xmMenuMap.put("ltime", new Date());
			xmMenuMap.put("$pks", canOper.stream().map(k -> k.getMenuId()).collect(Collectors.toList()));
			xmMenuService.editSomeFieldsExt(xmMenuMap,canOper);
			if (xmMenuMap.containsKey("mmUserid")) {
				for (XmMenu menu : canOper) {
					notifyMsgService.pushMsg(user, xmMenu.getMmUserid(), xmMenu.getMmUsername(), "您成为需求【" + menu.getMenuName() + "】的负责人，请跟进需求！", null);
				}

			}

			xmMenuMap.remove("ltime");
			xmMenuMap.remove("$pks");
			for (XmMenu canDb : canOper) {
				String remarks= ChangeLogService.getChangeLogsString(xmMenuMap,canDb);
				xmRecordService.addXmMenuRecord(canDb.getProductId(), canDb.getMenuId(),canDb.getIterationId(), "需求-修改", "修改需求: " + remarks);
			}
		}

		List<String> msgs = new ArrayList<>();
		if (canOper.size() > 0) {
			msgs.add(String.format("修改了%s个需求。", canOper.size()));
		}
		if (noOper.size() > 0) {
			msgs.add(String.format("其中%s个需求，无权限修改。原因【%s】", noOper.size(), noOperTips.keySet().stream().collect(Collectors.joining(";"))));
		}

		if(noDclassTips.keySet().size()>0){
			for (String msg : noDclassTips.keySet()) {
				msgs.add(msg);
			}
		}
		if (canOper.size() > 0) {
			return Result.ok(msgs.stream().collect(Collectors.joining()));
		} else {
			return Result.error(msgs.stream().collect(Collectors.joining()));
		}

	}


	/***/
	@ApiOperation(value = "根据主键列表批量删除项目菜单表信息", notes = "batchDelXmMenu,仅需要上传主键字段")
	@ApiResponses({
			@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	})
	//@HasQx(value = "xm_core_xmMenu_batchDel",name = "批量删除用户需求",moduleId = "xm-project",moduleName = "管理端-项目管理系统")
	@RequestMapping(value = "/batchDel", method = RequestMethod.POST)
	public Result batchDelXmMenu(@RequestBody List<XmMenu> xmMenus) {

		User user = LoginUtils.getCurrentUserInfo();
		List<String> hasChildMenus = new ArrayList<>();
		List<XmMenu> canDelList = new ArrayList<>();
		List<String> menuIds = xmMenus.stream().map(k -> k.getMenuId()).collect(Collectors.toSet()).stream().collect(Collectors.toList());
		if (menuIds == null || menuIds.size() <= 0) {
			return Result.error("menuIds-0", "需求编号不能为空");
		}
		List<XmMenu> xmMenusDb = this.xmMenuService.selectListByIdsWithsChildrenCnt(menuIds);
		if (xmMenusDb == null || xmMenusDb.size() == 0) {
			return Result.error("menus-0", "需求均已不存在");
		}
		XmMenu xmMenuDb = xmMenusDb.get(0);
		if (xmMenusDb.stream().filter(k -> !xmMenuDb.getProductId().equals(k.getProductId())).findAny().isPresent()) {
			return Result.error("no-same-productId", "批量操作只能在同一个产品进行。");
		}

		List<XmMenu> canOper = new ArrayList<>();
		List<XmMenu> noOper = new ArrayList<>();
		Map<String, Tips> noOperTips = new HashMap<>();

		XmProduct xmProduct = productService.getProductFromCache(xmMenuDb.getProductId());
		if (xmProduct == null) {
			return Result.error("product-data-0", "产品已不存在");
		}
		if (groupService.checkUserIsProductAdm(xmProduct, user.getUserid())) {
			canOper.addAll(xmMenusDb);
		} else {
			for (XmMenu xm : xmMenusDb) {
				Tips tips = productQxService.checkProductQx(xmProduct, 2, user, xm.getMmUserid(), xm.getMmUsername(), null);
				if (tips.isOk()) {
					canOper.add(xm);
				} else {
					noOper.add(xm);
					noOperTips.put(tips.getMsg(), tips);
				}
			}
		}


		if (canOper.size() > 0) {
			for (XmMenu xmMenu : canOper) {
				boolean canDel = this.xmMenuService.checkCanDelAllChild(xmMenu, canOper);
				if (canDel) {
					canDelList.add(xmMenu);
				} else {
					hasChildMenus.add(xmMenu.getMenuName());
				}
			}
			if (canDelList.size() > 0) {
				xmMenuService.doBatchDelete(canDelList);
			}
			for (XmMenu canDb : canDelList) {
				xmRecordService.addXmMenuRecord(canDb.getProductId(), canDb.getMenuId(),canDb.getIterationId(), "需求-删除", "删除产品需求【" + canDb.getMenuName()+"】");
			}
		}


		List<String> msgs = new ArrayList<>();
		if (canDelList.size() > 0) {
			msgs.add(String.format("删除了%s个需求。", canDelList.size()));
		}
		if (noOper.size() > 0) {
			msgs.add(String.format("其中%s个需求，无权限删除。原因【%s】", noOper.size(), noOperTips.keySet().stream().collect(Collectors.joining(";"))));
		}
		if (hasChildMenus.size() > 0) {
			msgs.add(String.format("其中%s个需求，存在子需求，不能删除。分别是【%s】", hasChildMenus.size(), hasChildMenus.stream().collect(Collectors.joining(","))));
		}
		if (canDelList.size() > 0) {
			return Result.ok(msgs.stream().collect(Collectors.joining()));
		} else {
			return Result.error(msgs.stream().collect(Collectors.joining()));
		}

	}

	//@HasQx(value = "xm_core_xmMenu_batchAdd",name = "批量新增用户需求",moduleId = "xm-project",moduleName = "管理端-项目管理系统")
	@RequestMapping(value = "/batchImportFromTpl", method = RequestMethod.POST)
	public Result batchImportFromTpl(@RequestBody ImportMenuByTplVo tpl) {
		List<XmMenu> xmMenus=tpl.getXmMenus();
		User user=LoginUtils.getCurrentUserInfo();
		if("some".equals(tpl.getSourceType()) && (xmMenus==null || xmMenus.size()==0)){
			return Result.error("需求列表不能为空");
		}
		if("all".equals(tpl.getSourceType()) && ObjectTools.isEmpty(tpl.getSourceProductId())){
			return Result.error("sourceProductId-0","请上送源产品编号");
		}
		if(!StringUtils.hasText(tpl.getProductId())){
			return Result.error("productId-0","请上送目标产品编号");
		}

		String productId=tpl.getProductId();
		XmProduct xmProduct=productService.getProductFromCache(productId); 
		Tips tips1=productQxService.checkProductQx(xmProduct,2,user);
		Result.assertIsFalse(tips1);
		if("all".equals(tpl.getSourceType())){
			xmMenus=this.xmMenuService.list(new LambdaQueryWrapper<XmMenu>().eq(XmMenu::getProductId,tpl.getSourceProductId()));
			if(xmMenus.size()==0){
				return Result.error("xmMenus-data-0","产品【%s】没有需求项可导入",tpl.getSourceProductId());
			}
		}
		for (XmMenu xmMenu : xmMenus) {
			if("3".equals(xmMenu.getDclass())) {
				xmMenu.setNtype("0");
			}else {
				xmMenu.setNtype("1");
			}
		}

		XmMenu parent;
		if(ObjectTools.isNotEmpty(tpl.getPmenuId()) && !"0".equals(tpl.getPmenuId())){
			parent=this.xmMenuService.getById(tpl.getPmenuId());
			if(parent==null){
				return Result.error("parent-menu-data-0","上级【%s】不存在",tpl.getPmenuId());
			}
			if("0".equals(parent.getNtype())){
				return Result.error("ntype-0","不能导入需求故事中。【%s】属于故事，不允许下挂任何子孙节点",parent.getMenuName());
			}
		} else {
			parent = null;
		}
		Map<String,String> newIdMap=new HashMap<>();
		if(StringUtils.hasText(tpl.getPmenuId())){
			newIdMap.put(tpl.getPmenuId(),tpl.getPmenuId());
		}else{
			tpl.setPmenuId("0");

			newIdMap.put(tpl.getPmenuId(),tpl.getPmenuId());
		}
		for (XmMenu xmMenu : xmMenus) {
			newIdMap.put(xmMenu.getMenuId(),this.xmMenuService.createKey("menuId"));
		}
		for (XmMenu g : xmMenus) {
			g.setMenuId(newIdMap.get(g.getMenuId()));
			if(StringUtils.hasText(g.getPmenuId())){
				if(newIdMap.containsKey(g.getPmenuId())){
					g.setPmenuId(newIdMap.get(g.getPmenuId()));
				}else{
					g.setPmenuId(tpl.getPmenuId());
				}
			}else{
				g.setPmenuId(tpl.getPmenuId());
			}
			g.setPbranchId(xmProduct.getBranchId());

			g.setProductId(productId);

			//前置处理
			if(ObjectTools.isNotEmpty(g.getPhaseId())){
				String[] preMenuIds=g.getPhaseId().split(",");
				List<String> newPres=new ArrayList<>();
				for (String preMenuId : preMenuIds) {
					if(newIdMap.containsKey(preMenuId)){
						newPres.add(newIdMap.get(preMenuId));
					}
				}
				if(newPres.size()>0){
					g.setPhaseId(CollectionUtil.join(newPres,","));
				}
			}
		}
		/**
		 * 计算子节点数量
		 */
		//故事,如果有子节点，自动升级为特性
		for (XmMenu xmMenu : xmMenus) {
			List<XmMenu> childs=xmMenus.stream().filter(k->xmMenu.getMenuId().equals(k.getPmenuId())).collect(Collectors.toList());
			if(childs.size()>0){
				xmMenu.setChildrenCnt(childs.size());
				xmMenu.setNtype("1");
				if(!("1".equals(xmMenu.getDclass()) ||"2".equals(xmMenu.getDclass()))){
					xmMenu.setDclass("2");
				}
			}else{
				if("3".equals(xmMenu.getDclass())){
					xmMenu.setNtype("0");
				}else if("1".equals(xmMenu.getDclass()) ||"2".equals(xmMenu.getDclass())){
					xmMenu.setNtype("1");
				}else{
					if("0".equals(xmMenu.getNtype())){
						xmMenu.setDclass("3");
					}else if("1".equals(xmMenu.getNtype())){
						xmMenu.setDclass("2");
					}
				}
			}
		}

		for (XmMenu xmMenu : xmMenus) {
			xmMenu.setSubStoryCnt(xmMenus.stream().filter(k->xmMenu.getMenuId().equals(k.getPmenuId()) && "0".equals(k.getNtype())).collect(Collectors.toList()).size());
			xmMenu.setSubEfCnt(xmMenus.stream().filter(k->xmMenu.getMenuId().equals(k.getPmenuId()) && "1".equals(k.getNtype())).collect(Collectors.toList()).size());

			xmMenu.setChildrenCnt(xmMenu.getSubStoryCnt()+xmMenu.getSubEfCnt());
		}

		xmMenuService.parentIdPathsCalcBeforeSave(xmMenus);

		//排序号生成 按##.##.##格式生成
		PubTool.initMenusSeqNo(parent,xmMenus);

		// 日期处理
		for (XmMenu xmMenu : xmMenus) {
			if(xmMenu.getStartTime()!=null){
				xmMenu.setStartTime(DateUtil.offsetDay(xmMenu.getStartTime(),tpl.getDays()));
			}
			if(xmMenu.getEndTime()!=null){
				xmMenu.setEndTime(DateUtil.offsetDay(xmMenu.getEndTime(),tpl.getDays()));
			}
		}
		xmMenuService.saveBatch(xmMenus);
		if(parent!=null){
			// 设置默认值，防止空指针异常
			parent.setChildrenCnt(NumberUtil.getInteger(parent.getChildrenCnt(),0));
			parent.setSubEfCnt(NumberUtil.getInteger(parent.getSubEfCnt(),0));
			parent.setSubStoryCnt(NumberUtil.getInteger(parent.getSubStoryCnt(),0));
			List<XmMenu> childs=xmMenus.stream().filter(k->parent.getMenuId().equals(k.getPmenuId())).collect(Collectors.toList());
			if(childs.size()>0){
				parent.setChildrenCnt(childs.size()+NumberUtil.getInteger(parent.getChildrenCnt(),0));
				parent.setNtype("1");
				// 计算子计划数目
				List<XmMenu> planChilds=childs.stream().filter(k->"1".equals(k.getNtype())).collect(Collectors.toList());
				parent.setSubEfCnt(planChilds.size()+NumberUtil.getInteger(parent.getSubEfCnt(),0));
				parent.setSubStoryCnt(parent.getChildrenCnt()-NumberUtil.getInteger(parent.getSubEfCnt(),0));
			}
		}
		if(parent!=null){
			this.xmMenuService.lambdaUpdate().set(XmMenu::getChildrenCnt,parent.getChildrenCnt()).set(XmMenu::getSubEfCnt,parent.getSubEfCnt()).set(XmMenu::getSubStoryCnt,parent.getSubStoryCnt()).eq(XmMenu::getMenuId,parent.getMenuId()).update();
		}
		for (XmMenu t : xmMenus) {

			if(!user.getUserid().equals(t.getMmUserid()) && !"0".equals(t.getStatus())) {
				notifyMsgService.pushMsg(user.getUserid(), t.getMmUserid(),  "您成为需求【" + t.getMenuName() + "】的负责人，请注意跟进。","");
			}
		}
		xmRecordService.addXmMenuRecord(xmMenus, "需求-批量模板导入", "从需求模板导入");
		 return Result.ok();
	}

	//@HasQx(value = "xm_core_xmMenu_batchAdd",name = "批量新增用户需求",moduleId = "xm-project",moduleName = "管理端-项目管理系统")
	@RequestMapping(value = "/batchAdd", method = RequestMethod.POST)
	public Result batchAddXmMenu(@RequestBody BatchAddXmMenusVo vo) {
		List<XmMenu> xmMenus=vo.getXmMenus();
		if ( xmMenus==null || xmMenus.size() == 0 ) {
			return Result.error("没有数据可以新增，请上送数据");
		}
		User user = LoginUtils.getCurrentUserInfo();
 		if(ObjectTools.isEmpty(vo.getProductId())){
			return Result.error("productId-required","产品编号不能为空");
		}
		// 进行权限检查，我是不是产品经理、需求人员
		XmProduct xmProduct=this.productService.getProductFromCache(vo.getProductId());
		if(xmProduct==null){
			return Result.error("product-data-0","产品已不存在");
		}
		if (!groupService.checkUserIsProductAdm(xmProduct, user.getUserid())) {
			Tips tips = productQxService.checkProductQx(xmProduct, 2, user, user.getUserid(), user.getUsername(), null);
			Result.assertIsFalse(tips);
		}
		if(ObjectTools.isEmpty(vo.getPmenuId())){
			vo.setPmenuId("0");
		}
		// 导入前检测
		for (XmMenu m : xmMenus) {
			//名称检测
			if(ObjectTools.isEmpty(m.getMenuName())){
				return Result.error("xm-menu-menuName-required","名称不能为空" );
			}
		}

		// 必要数据特殊处理
		for (XmMenu m : xmMenus) {
			m.setProductId(vo.getProductId());
			// 需求负责人设为导入者
			m.setMmUserid(user.getUserid());
			m.setMmUsername(user.getUsername());
			m.setIterationId(null);
			m.setIterationName(null);
			m.setFuncId(null);
			m.setFuncName(null);
			m.setCtime(new Date());
			m.setStartTime(new Date());
			m.setEndTime(null);
			m.setLtime(new Date());
		}

		// 进行主键重新生成
		Map<String,String> oldNewIdMap=new HashMap<>();
		for (XmMenu menu : xmMenus) {
			String newId=oldNewIdMap.get(menu.getMenuId());
			if(ObjectTools.isEmpty(newId)){
				newId=this.xmMenuService.createKey("menuId");
				oldNewIdMap.put(menu.getMenuId(),newId);
			}
			menu.setMenuId(newId);

			// 上级主键处理
			if(ObjectTools.isEmpty(menu.getPmenuId())||"0".equals(menu.getPmenuId())){
				menu.setPmenuId("0");
			}else{
				String newPmenuId=oldNewIdMap.get(menu.getPmenuId());
				if(ObjectTools.isEmpty(newPmenuId)){
					newPmenuId=this.xmMenuService.createKey("menuId");
					oldNewIdMap.put(menu.getPmenuId(),newPmenuId);
				}
				menu.setPmenuId(newPmenuId);
			}

		}


		// 找出所有没有上级的需求,没有上级的应该把其上级设置为新的上级
		List<XmMenu> noExistsList = xmMenus.stream().filter(i -> !xmMenus.stream().filter(k -> k.getMenuId().equals(i.getPmenuId())).findAny().isPresent()).collect(Collectors.toList());
		for (XmMenu xmMenu : noExistsList) {
			xmMenu.setPmenuId(vo.getPmenuId());
		}


		// 重新计算级别、全路径等
		this.xmMenuService.parentIdPathsCalcBeforeSave(xmMenus);

		// 保存
		this.xmMenuService.saveBatch(xmMenus);


		return Result.ok();
	}


	@ApiOperation(value = "批量修改需求的上级", notes = "batchChangeParentMenu,仅需要上传主键字段")
	@ApiResponses({
			@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	})
	//@HasQx(value = "xm_core_xmMenu_batchChangeParentMenu",name = "批量修改需求的上级",moduleId = "xm-project",moduleName = "管理端-项目管理系统")
	@RequestMapping(value = "/batchChangeParentMenu", method = RequestMethod.POST)
	public Result batchChangeParentMenu(@RequestBody List<BatchChangeParentMenuVo> xmMenusVo) {

		User user = LoginUtils.getCurrentUserInfo();
		if (xmMenusVo == null || xmMenusVo.size() == 0) {
			return Result.error("需求列表不能为空");

		}
		Set<String> childIds = new HashSet<>();
		Set<String> pmenuIds = new HashSet<>();
		Map<String, String> newIdLinks = new HashMap<>();
		for (BatchChangeParentMenuVo link : xmMenusVo) {
			if (ObjectTools.isEmpty(link.getPmenuId()) || "0".equals(link.getPmenuId())) {
				link.setPmenuId("0");
			} else {
				pmenuIds.add(link.getPmenuId());
			}

			if (ObjectTools.isEmpty(link.getMenuId())) {
				return Result.error("需求编号不能为空");
			}
			childIds.add(link.getMenuId());
			newIdLinks.put(link.getMenuId(), link.getPmenuId());
		}


		List<XmMenu> childMenus = this.xmMenuService.selectListByIds(childIds.stream().collect(Collectors.toList()));
		List<XmMenu> parentMenus = new ArrayList<>();
		if (!pmenuIds.isEmpty()) {
			parentMenus = this.xmMenuService.selectListByIds(pmenuIds.stream().collect(Collectors.toList()));

		}
		if (childMenus == null || childMenus.size() == 0) {
			return Result.error("data-0", "数据不存在");
		}

		// 过滤掉新旧上级一致的需求
		childMenus = childMenus.stream().filter(k -> !k.getPmenuId().equals(newIdLinks.get(k.getMenuId()))).collect(Collectors.toList());
		if (childMenus == null || childMenus.size() == 0) {
			return Result.error("id-link-same-0", "新旧上级一致，无须保存");
		}

		String productId = childMenus.get(0).getProductId();
		Map<String, XmMenu> xmMenuMap = new HashMap<>();
		for (XmMenu xmMenu : childMenus) {
			xmMenuMap.put(xmMenu.getMenuId(), xmMenu);
			// 进行是否同项目检测
			if (!productId.equals(xmMenu.getProductId())) {
				return Result.error("menu-must-same-project", "所有需求必须同产品，%s归属产品为%s,与其它需求归属不一致", xmMenu.getMenuName(), xmMenu.getProductId());
			}
		}
		for (XmMenu xmMenu : parentMenus) {
			xmMenuMap.put(xmMenu.getMenuId(), xmMenu);
			// 进行是否同项目检测
			if (!productId.equals(xmMenu.getProductId())) {
				return Result.error("menu-must-same-project", "所有需求必须同产品，%s归属产品为%s,与其它需求归属不一致", xmMenu.getMenuName(), xmMenu.getProductId());
			}
		}
		// 不允许计划向需求迁移
		for (String pmenuId : pmenuIds) {
			XmMenu xmMenu = xmMenuMap.get(pmenuId);
			if ("3".equals(xmMenu.getDclass())) {
				return Result.error("plan-join-menu-err-01", "%s为用户故事，不允许下挂史诗/特性/故事等", xmMenu.getMenuName());
			}
		}

		// 进行权限判断 0.机构管理员不受权限约束， 1.项目经理、副经理有权限调整整个项目的需求，2. 组长有权限调整组内需求
		if (!LoginUtils.isBranchAdmin(user.getBranchId())) {
			boolean isProductAdm = this.groupService.checkUserIsProductAdm(productId, user.getUserid());
			if (!isProductAdm) {
				for (XmMenu xmMenu : xmMenuMap.values()) {
					// 允许调整组内需求
					Tips tips2 = this.groupService.checkIsProductAdmOrTeamHeadOrAss(user, xmMenu.getMmUserid(), productId);
					if ("not-head".equals(tips2.getTipscode())) {
						return Result.error("not-user-head-01", "您不是需求【%s】,负责人【%s】的组长/经理，无权限修改其归属", xmMenu.getMenuName(), xmMenu.getMmUsername());
					}
					Result.assertIsFalse(tips2);
					// 不允许调整非组内需求
					XmMenu tardgetMenu = xmMenuMap.get(newIdLinks.get(xmMenu.getMenuId()));
					tips2 = this.groupService.checkIsProductAdmOrTeamHeadOrAss(user, tardgetMenu.getMmUserid(), productId);
					if ("not-head".equals(tips2.getTipscode())) {
						return Result.error("not-user-head-02", "您不是需求【%s】,负责人【%s】的组长/经理，无权限将【%s】挂接上去", tardgetMenu.getMenuName(), tardgetMenu.getMmUsername(), xmMenu.getMenuName());
					}
					Result.assertIsFalse(tips2);
				}

			}
		}


		/**
		 * 按上级进行分组
		 */
		Map<String, List<XmMenu>> xmMaps = new HashMap<>();
		for (XmMenu xmMenu : childMenus) {
			String newPid = newIdLinks.get(xmMenu.getMenuId());
			XmMenu parent = null;
			String pidPaths = null;
			if (ObjectTools.isEmpty(newPid) || "0".equals(newPid)) {//向根迁移
				pidPaths = "0,";
			} else {
				parent = xmMenuMap.get(newPid);
				pidPaths = parent.getPidPaths();
			}

			List<XmMenu> menus = xmMaps.get(pidPaths);
			if (menus == null) {
				menus = new ArrayList<>();
				menus.add(xmMenu);
			} else {
				menus.add(xmMenu);
			}
			xmMaps.put(pidPaths, menus);
		}

		// 分组后要进行降序排序，从底层往上更新
		List<String> pidPathsList = xmMaps.keySet().stream().sorted((x, y) -> {
			return y.split(",").length - x.split(",").length;
		}).collect(Collectors.toList());
		for (String pidPaths : pidPathsList) {
			List<XmMenu> menus = xmMaps.get(pidPaths);
			//上级为顶级的情况处理
			if ("0".equals(pidPaths) || "0,".equals(pidPaths) || ObjectTools.isEmpty(pidPaths)) {
				this.xmMenuService.batchChangeParent(menus, null);
				for (XmMenu menu : menus) {
					this.xmRecordService.addXmMenuRecord(menu.getProductId(),menu.getMenuId(),menu.getIterationId(),"需求-批量修改上级",String.format("修改上级：新值【%s %s】，旧值【%s】","0","顶级",menu.getPmenuId()));
				}
			} else {
				String[] parentMenuIds = pidPaths.split(",");
				String parentMenuId = parentMenuIds[parentMenuIds.length - 1];
				//必须重新查询,因为可能上一次循环已经更新了数据库中的pidPaths
				XmMenu parentMenu = this.xmMenuService.getById(parentMenuId);
				this.xmMenuService.batchChangeParent(menus, parentMenu);
				for (XmMenu menu : menus) {
					this.xmRecordService.addXmMenuRecord(menu.getProductId(),menu.getMenuId(),menu.getIterationId(),"需求-批量修改上级",String.format("修改上级：新值【%s %s】，旧值【%s】",parentMenu.getMenuId(),parentMenu.getMenuName(),menu.getPmenuId()));
				}
			}

		}

		// 执行汇总等善后工作 所有的新上级、原上级需要重新计算统计数据
		Set<String> pidSet=childMenus.stream().map(k->k.getPmenuId()).collect(Collectors.toSet());
		pidSet.addAll(pmenuIds);
		List<XmMenu> xmMenusDb=this.xmMenuService.listByIds(pidSet);
   		this.xmMenuService.batchSumParents(xmMenusDb);
		return Result.ok();
	}

	@ApiOperation( value = "需求编号-根据主键查询一条数据",notes=" ")
	@ApiResponses({
			@ApiResponse(code = 200,response= XmMenu.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	@RequestMapping(value="/queryById",method=RequestMethod.GET)
	public Result queryById(Map<String,Object> params) {
		ValidUtil.isRequired("menuId","故事编号",params.get("menuId"));
		IPage page = QueryTools.initPage(params);
		QueryWrapper<XmMenu> qw = QueryTools.initQueryWrapper(XmMenu.class, params);
		List<Map<String, Object>> datas = xmMenuService.selectListMapByWhereWithState(page, qw, params);
		if(datas!=null && datas.size()>0){
			return Result.ok().setData(datas.get(0));
		}else{
			return Result.error("data-0","无数据");
		}
	}

}
