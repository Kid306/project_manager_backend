package com.xm.core.ctrl;

import cn.hutool.core.collection.CollUtil;
import com.mdp.core.entity.LangTips;
import com.mdp.core.entity.Result;
import com.mdp.core.entity.Tips;
import com.mdp.core.utils.CollectionUtils;
import com.mdp.core.utils.NumberUtil;
import com.mdp.safe.client.entity.User;
import com.mdp.safe.client.utils.LoginUtils;
import com.mdp.swagger.ApiEntityParams;
import com.xm.core.entity.XmIteration;
import com.xm.core.entity.XmMenu;
import com.xm.core.entity.XmProduct;
import com.xm.core.service.*;
import com.xm.core.service.push.XmMenuPushMsgService;
import com.xm.core.vo.XmIterationMenuVo;
import io.swagger.annotations.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.*;
import java.util.stream.Collectors;

/**
 * url编制采用rest风格,如对XM.xm_iteration_menu 迭代定义的操作有增删改查,对应的url分别为:<br>
 *  新增: xm/xmIterationMenu/add <br>
 *  查询: xm/xmIterationMenu/list<br>
 *  模糊查询: xm/xmIterationMenu/listKey<br>
 *  修改: xm/xmIterationMenu/edit <br>
 *  删除: xm/xmIterationMenu/del<br>
 *  批量删除: xm/xmIterationMenu/batchDel<br>
 * 组织 com.qqkj  顶级模块 oa 大模块 xm 小模块 <br>
 * 实体 XmIterationMenu 表 XM.xm_iteration_menu 当前主键(包括多主键): id; 
 ***/
@RestController("xm.core.xmIterationMenuController")
@RequestMapping(value="/xm/core/xmIterationMenu")
@Api(tags={"迭代定义操作接口"})
public class XmIterationMenuController {
	
	static Log logger=LogFactory.getLog(XmIterationMenuController.class);
	

	
	@Autowired
    XmMenuPushMsgService xmMenuPushMsgService;

	@Autowired
	XmMenuService xmMenuService;

	@Autowired
	XmMenuOperQxService operQxService;

	@Autowired
	XmIterationService xmIterationService;


	@Autowired
    XmGroupService groupService;

	@Autowired
	XmProductQxService productQxService;

	@Autowired
	XmProductService xmProductService;

	@Autowired
	XmMenuController xmMenuController;


	@Autowired
	XmRecordService xmRecordService;

	@ApiOperation( value = "查询迭代定义信息列表",notes="listXmIterationMenu,条件之间是 and关系,模糊查询写法如 {studentName:'%才哥%'}")
	@ApiEntityParams(XmIterationMenuVo.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name="pageSize",value="每页记录数",required=false),
			@ApiImplicitParam(name="pageNum",value="当前页码,从1开始",required=false),
			@ApiImplicitParam(name="total",value="总记录数,服务器端收到0时，会自动计算总记录数，如果上传>0的不自动计算",required=false),
			@ApiImplicitParam(name="orderBy",value="排序列 如性别、学生编号排序 orderBy = sex desc,student_id desc",required=false),
			@ApiImplicitParam(name="count",value="是否进行总条数计算,count=true|false",required=false)
	})
	@ApiResponses({
			@ApiResponse(code = 200,response= Map.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Result listXmIterationMenu(@ApiIgnore @RequestParam Map<String,Object> params){
		 return xmMenuController.listWithState(params);
	}


	@ApiOperation( value = "删除一条迭代定义信息",notes="delXmIterationMenu,仅需要上传主键字段")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}}")
	}) 
	@RequestMapping(value="/del",method=RequestMethod.POST)
	public Result delXmIterationMenu(@RequestBody XmIterationMenuVo xmIterationMenus){ 
		
		return batchDelXmIterationMenu(xmIterationMenus); 
	}
	

	@ApiOperation( value = "根据主键列表批量删除迭代定义信息",notes="batchDelXmIterationMenu,仅需要上传主键字段")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	}) 
	@RequestMapping(value="/batchDel",method=RequestMethod.POST)
	public Result batchDelXmIterationMenu(@RequestBody XmIterationMenuVo xmIterationMenus) {

			User user= LoginUtils.getCurrentUserInfo();
			List<String> menuIds=xmIterationMenus.getMenuIds();
			if(menuIds==null || menuIds.size()==0){
				return Result.error("menuIds-0","用户故事编号不能为空");
			}
		XmIteration xmIteration=xmIterationService.getById(xmIterationMenus.getIterationId());
		if(xmIteration==null){
			return Result.error("iteration-not-exists","迭代已不存在");
		}

		XmProduct xmProductDb=xmProductService.getProductFromCache(xmIteration.getProductId());
		boolean headIsPm=groupService.checkUserIsProductAdm(xmProductDb,user.getUserid());
		// 迭代权限检测 1 检测产品赋予我的权限是否足够
		Tips tips=productQxService.checkProductQx(xmProductDb,3,user);
		Result.assertIsFalse(tips);

		// 迭代权限检测 2 检查我是不是迭代负责人或者迭代负责人的领导
		if(!user.getUserid().equals(xmIteration.getAdminUserid())){
			tips=productQxService.checkProductQxBatch(xmProductDb,3,user,xmIteration.getAdminUserid());
			Result.assertIsFalse(tips);
		}
		if(NumberUtil.getInteger(xmIteration.getIstatus())>=6){
			return Result.error("istatus-6-7","迭代当前为已完成/已关闭状态，不允许移出故事");
		}
			List<XmMenu> menus=xmMenuService.listByIds(xmIterationMenus.getMenuIds());
			if(menus==null || menus.size()==0){
				return Result.error("menus-0","故事不存在");
			}

			List<XmMenu> canOpList=menus;
 			List<XmMenu> notJoins=new ArrayList<>();
			List<XmMenu> status7=new ArrayList<>();
			List<XmMenu> canDels=new ArrayList<>();
			for (XmMenu menu : canOpList) {
				if(!StringUtils.hasText(menu.getIterationId())){
					notJoins.add(menu);
					continue;
				}
				if("7".equals(menu.getStatus())){
					status7.add(menu);
					continue;
				}
				canDels.add(menu);
			}
			List<XmMenu> noQx=new ArrayList<>();
			// 迭代权限检测 3 检测我是否能够操作需求列表
		Tips tips2=new Tips();
		if(!headIsPm){
			tips2=productQxService.checkProductQxBatch(xmProductDb,3,user,canDels.stream().map(k->k.getMmUserid()).collect(Collectors.toSet()).stream().toArray(String[]::new));
			if(!tips2.isOk()){
				if(tips2.getTipscode().equals("pdqx-scope-team-2")){
					Set<String> noQxUseridSet= (Set<String>) tips2.get("noQxUseridSet");
					noQx=canDels.stream().filter(k->noQxUseridSet.contains(k.getMmUserid())).collect(Collectors.toList());
					canDels=canDels.stream().filter(k->!noQxUseridSet.contains(k.getMmUserid())).collect(Collectors.toList());
				}else{
					return Result.error(LangTips.fromTips(tips2));
				}
			}
		}


			List<String> msgs=new ArrayList<>();
			if(canDels.size()>0){


				msgs.add("成功将"+canDels.size()+"个用户故事移出迭代");
				xmIterationMenus.setMenuIds(canDels.stream().map(i->i.getMenuId()).collect(Collectors.toList()));
				xmMenuService.batchUnIteration(xmIterationMenus);
				xmRecordService.addXmIterationRecord(xmIteration.getProductId(),xmIteration.getId(),"产品-迭代-用户故事移出迭代","将用户故事移出迭代。");
				xmRecordService.addXmMenuRecord(canDels,"产品-迭代-用户故事移出迭代","将用户故事移出迭代.");
				//this.xmMenuPushMsgService.pushMenuRelUsersMsg(user.getBranchId(), xmIterationMenu.getMenuId(), user.getUserid(), user.getUsername(), user.getUsername()+"将用户故事【"+xmIterationMenu.getMenuId()+"】加入迭代");
			}
			if(status7.size()>0){
				msgs.add("有"+status7.size()+"个用户故事状态为已上线,不能移出迭代。【"+status7.stream().map(i->i.getMenuName()).collect(Collectors.joining(","))+"】");
			}
			if(notJoins.size()>0){
				msgs.add("有"+notJoins.size()+"个用户故事未加入迭代，无需移出。【"+notJoins.stream().map(i->i.getMenuName()).collect(Collectors.joining(","))+"】");
			}

			if(noQx.size()>0){
				String noQxUsername=noQx.stream().map(k->k.getMmUsername()).collect(Collectors.toSet()).stream().collect(Collectors.joining(","));
				msgs.add("有"+noQx.size()+"个用户故事无权限操作,原因："+tips2.getMsg()+" 相关人员【"+noQxUsername+"】,相关故事【"+noQx.stream().map(i->i.getMenuName()).collect(Collectors.joining(","))+"】");
			}
			if(canDels.size()==0){
				return Result.error(msgs.stream().collect(Collectors.joining(" ")));
			}else {
				return Result.ok(msgs.stream().collect(Collectors.joining(" ")));
			}
		
	} 
	@RequestMapping(value="/batchAdd",method=RequestMethod.POST)
	public Result batchAddXmIterationMenu(@RequestBody XmIterationMenuVo xmIterationMenus) {

			User user=LoginUtils.getCurrentUserInfo();
			if(!StringUtils.hasText(xmIterationMenus.getIterationId())){
				return Result.error("iterationId-0","迭代编号不能为空");
			}
			List<String> menuIds=xmIterationMenus.getMenuIds();
			if(menuIds==null || menuIds.size()==0){
				return Result.error("menuIds-0","用户故事编号不能为空");
			}
		XmIteration xmIteration=xmIterationService.getById(xmIterationMenus.getIterationId());
		if(xmIteration==null){
			return Result.error("iteraion-not-exists","迭代不存在");
		}

		XmProduct xmProductDb=xmProductService.getProductFromCache(xmIteration.getProductId());
		// 迭代权限检测 1 检测产品赋予我的权限是否足够
		Tips tips=productQxService.checkProductQx(xmProductDb,3,user);
		Result.assertIsFalse(tips);
		boolean headIsPm=groupService.checkUserIsProductAdm(xmProductDb,user.getUserid());
		// 迭代权限检测 2 检查我是不是迭代负责人或者迭代负责人的领导
		if(!user.getUserid().equals(xmIteration.getAdminUserid())){
			tips=productQxService.checkProductQxBatch(xmProductDb,3,user,xmIteration.getAdminUserid());
			Result.assertIsFalse(tips);
		}

		if(NumberUtil.getInteger(xmIteration.getIstatus())>1){
			return Result.error("iteraion-istatus-gt1","迭代已过了需求评审阶段，不能再添加故事进入迭代");
		}
		List<XmMenu> menus=xmMenuService.listByIds(xmIterationMenus.getMenuIds());
			if(menus==null || menus.size()==0){
				return Result.error("data-0","故事不存在");
			}

			// 同产品检测
		List<XmMenu> notSameProductList=menus.stream().filter(k->!k.getProductId().equals(xmIteration.getProductId())).collect(Collectors.toList());
			if(notSameProductList.size()>0){
				String menuNames=notSameProductList.stream().map(k->k.getMenuName()).collect(Collectors.joining(","));
				return Result.error("menus-not-same-product","以下故事不属于产品【%s】，不能加入迭代【%s】，相关故事为【%s】", xmIteration.getProductName(),xmIteration.getIterationName(),menuNames);
			}




		List<XmMenu> canOpList=menus;
			List<XmMenu> hadJoin=new ArrayList<>();
			List<XmMenu> ntype1=new ArrayList<>();
			List<XmMenu> status789=new ArrayList<>();
			List<XmMenu> canAdds=new ArrayList<>();
			for (XmMenu menu : canOpList) {
				if(StringUtils.hasText(menu.getIterationId())){
					hadJoin.add(menu);
					continue;
				}
				if(!"3".equals(menu.getDclass())){
					ntype1.add(menu);
					continue;
				}
				if("7".equals(menu.getStatus())||"8".equals(menu.getStatus())||"9".equals(menu.getStatus())){
					status789.add(menu);
					continue;
				}
				canAdds.add(menu);
			}


		List<XmMenu> noQx=new ArrayList<>();
		// 迭代权限检测 3 检测我是否能够操作需求列表
		Tips tips2=new Tips();
		if(!headIsPm){
			tips2=productQxService.checkProductQxBatch(xmProductDb,3,user,canAdds.stream().map(k->k.getMmUserid()).collect(Collectors.toSet()).stream().toArray(String[]::new));
			if(!tips2.isOk()){
				if(tips2.getTipscode().equals("pdqx-scope-team-2")){
					Set<String> noQxUseridSet= (Set<String>) tips2.get("noQxUseridSet");
					noQx=canAdds.stream().filter(k->noQxUseridSet.contains(k.getMmUserid())).collect(Collectors.toList());
					canAdds=canAdds.stream().filter(k->!noQxUseridSet.contains(k.getMmUserid())).collect(Collectors.toList());
				}else{
					return Result.error(LangTips.fromTips(tips2));
				}
			}
		}


		List<String> msgs=new ArrayList<>();
			if(canAdds.size()>0){
				msgs.add("成功将"+canAdds.size()+"个用户故事加入迭代");
				xmIterationMenus.setIterationName(xmIteration.getIterationName());
				xmIterationMenus.setMenuIds(canAdds.stream().map(i->i.getMenuId()).collect(Collectors.toList()));
				xmMenuService.batchIteration(xmIterationMenus);
				xmRecordService.addXmMenuRecord(canAdds,"产品-迭代-用户故事加入迭代","将用户故事加入迭代.");
				xmRecordService.addXmIterationRecord(xmIteration.getProductId(),xmIteration.getId(),"产品-迭代-用户故事加入迭代","将用户故事加入迭代.");

				//this.xmMenuPushMsgService.pushMenuRelUsersMsg(user.getBranchId(), xmIterationMenu.getMenuId(), user.getUserid(), user.getUsername(), user.getUsername()+"将用户故事【"+xmIterationMenu.getMenuId()+"】加入迭代");
			}
			if(status789.size()>0){
				msgs.add("有"+status789.size()+"个用户故事状态为已上线、已下线、已删除状态，不能加入迭代。【"+status789.stream().map(i->i.getMenuName()).collect(Collectors.joining(","))+"】");
			}
			if(hadJoin.size()>0){
				msgs.add("有"+hadJoin.size()+"个用户故事已加入迭代，不能重复加入。【"+hadJoin.stream().map(i->i.getMenuName()).collect(Collectors.joining(","))+"】");
			}
			if(ntype1.size()>0){
				msgs.add("有"+ntype1.size()+"个为史诗/特性，不用加入迭代。【"+ntype1.stream().map(i->i.getMenuName()).collect(Collectors.joining(","))+"】");
			}


		if(noQx.size()>0){
			String noQxUsername=noQx.stream().map(k->k.getMmUsername()).collect(Collectors.toSet()).stream().collect(Collectors.joining(","));
			msgs.add("有"+noQx.size()+"个用户故事无权限操作,原因："+tips2.getMsg()+" 相关人员【"+noQxUsername+"】,相关故事【"+noQx.stream().map(i->i.getMenuName()).collect(Collectors.joining(","))+"】");
		}
			if(canAdds.size()==0){
				return Result.error(msgs.stream().collect(Collectors.joining(" ")));
			}else {
				return Result.ok(msgs.stream().collect(Collectors.joining(" ")));
			}
		
	} 
}
