// package com.mdp.biz.ctrl;
//
// import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
// import com.baomidou.mybatisplus.core.metadata.IPage;
// import com.mdp.biz.entity.Coop;
// import com.mdp.biz.service.CoopService;
// import com.mdp.core.entity.Result;
// import com.mdp.core.entity.Tips;
// import com.mdp.core.err.BizException;
// import com.mdp.core.query.QueryTools;
// import com.mdp.msg.client.PushNotifyMsgService;
// import com.mdp.safe.client.entity.User;
// import com.mdp.safe.client.utils.LoginUtils;
// import com.mdp.swagger.ApiEntityParams;
// import io.swagger.annotations.Api;
// import io.swagger.annotations.ApiOperation;
// import io.swagger.annotations.ApiResponse;
// import io.swagger.annotations.ApiResponses;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.util.StringUtils;
// import org.springframework.web.bind.annotation.*;
// import springfox.documentation.annotations.ApiIgnore;
//
// import java.util.*;
// import java.util.stream.Collectors;
//
// import static com.mdp.core.utils.BaseUtils.fromMap;
// import static com.mdp.core.utils.BaseUtils.toMap;
//
// /**
//  * url编制采用rest风格,如对biz_coop 商务合作申请的操作有增删改查,对应的url分别为:<br>
//  * 组织 com  顶级模块 mdp 大模块 biz 小模块 <br>
//  * 实体 Coop 表 biz_coop 当前主键(包括多主键): id;
//  ***/
// @RestController("mdp.biz.coopController")
// @RequestMapping(value="/*/biz/coop")
// @Api(tags={"商务合作申请操作接口"})
// public class CoopController {
//
// 	static Logger logger =LoggerFactory.getLogger(CoopController.class);
//
// 	@Autowired
// 	private CoopService coopService;
//
//
//
// 	@Autowired
// 	PushNotifyMsgService pushNotifyMsgService;
//
//
// 	Map<String,Object> fieldsMap = toMap(new Coop());
//
//
// 	@ApiOperation( value = "查询商务合作申请信息列表",notes=" ")
// 	@ApiResponses({
// 		@ApiResponse(code = 200,response=Coop.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
// 	})
// 	@RequestMapping(value="/list",method=RequestMethod.GET)
// 	public Result listCoop(  @ApiIgnore @RequestParam Map<String,Object>  coop){
//
// 		IPage page=QueryTools.initPage(coop);
// 		QueryWrapper<Coop> qw= QueryTools.initQueryWrapper(Coop.class,coop);
// 		List<Map<String,Object>>	coopList = coopService.selectListMapByWhere(page,qw,coop);
//
// 		return Result.ok().setData(coopList).setTotal(page.getTotal());
// 	}
//
//
//
// 	@ApiOperation( value = "新增一条商务合作申请信息",notes=" ")
// 	@ApiResponses({
// 		@ApiResponse(code = 200,response=Coop.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
// 	})
// 	@RequestMapping(value="/add",method=RequestMethod.POST)
// 	public Result addCoop(@RequestBody Coop coop) {
//
//
// 		try{
// 		    boolean createPk=false;
// 			if(!StringUtils.hasText(coop.getId())) {
// 			    createPk=true;
// 				coop.setId(coopService.createKey("id"));
// 			}
// 			if(createPk==false){
// 				if(coopService.selectOneObject(coop) !=null ){
// 					return Result.error("pk-exists","编号重复，请修改编号再提交");
// 				}
// 			}
// 			if(!StringUtils.hasText(coop.getEmail())){
// 				return Result.error("email-0","联系邮箱不能为空");
// 			}
// 			if(!StringUtils.hasText(coop.getPhoneno())){
// 				return Result.error("phoneno-0","联系电话不能为空");
// 			}
// 			if(!StringUtils.hasText(coop.getRemark())){
// 				return Result.error("remark-0","合作需求不能为空");
// 			}
// 			if(!LoginUtils.isGuest()){
// 				User user=LoginUtils.getCurrentUserInfo();
// 				coop.setBranchId(user.getBranchId());
// 				coop.setUserid(user.getUserid());
//  			}
// 			coop.setCtime(new Date());
// 			coop.setStatus("1");
// 			coopService.insert(coop);
// 			pushNotifyMsgService.pushMsg("platform-branch-001","superAdmin","平台管理员",coop.getUserid(),coop.getUsername(),coop.getUsername()+"提交商务合作申请："+coop.getRemark(),null);
// 			return Result.ok().setData(coop);
// 		}catch (BizException e) {
// 			logger.error("",e);
// 			return Result.error(e);
// 		}catch (Exception e) {
// 			logger.error("",e);
// 			return Result.error(e);
// 		}
// 	}
//
// 	@ApiOperation( value = "删除一条商务合作申请信息",notes=" ")
// 	@ApiResponses({
// 		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}}")
// 	})
// 	@RequestMapping(value="/del",method=RequestMethod.POST)
// 	public Result delCoop(@RequestBody Coop coop){
//
//
// 		try{
//             if(!StringUtils.hasText(coop.getId())) {
//                  return Result.error("pk-not-exists","请上送主键参数id");
//             }
//             Coop coopDb = coopService.selectOneObject(coop);
//             if( coopDb == null ){
//                 return Result.error("data-not-exists","数据不存在，无法删除");
//             }
// 			coopService.deleteByPk(coop);
//             return Result.ok();
// 		}catch (BizException e) {
// 			logger.error("",e);
// 			return Result.error(e);
// 		}catch (Exception e) {
// 			logger.error("",e);
// 			return Result.error(e);
// 		}
// 	}
//
// 	/**
// 	@ApiOperation( value = "根据主键修改一条商务合作申请信息",notes=" ")
// 	@ApiResponses({
// 		@ApiResponse(code = 200,response=Coop.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
// 	})
// 	@RequestMapping(value="/edit",method=RequestMethod.POST)
// 	public Result editCoop(@RequestBody Coop coop) {
//
//
// 		try{
//             if(!StringUtils.hasText(coop.getId())) {
//                  return Result.error("pk-not-exists","请上送主键参数id");
//             }
//             Coop coopDb = coopService.selectOneObject(coop);
//             if( coopDb == null ){
//                 return Result.error("data-not-exists","数据不存在，无法修改");
//             }
// 			coopService.updateSomeFieldByPk(coop);
// 			return Result.ok().setData(coop);
// 		}catch (BizException e) {
// 			logger.error("",e);
// 			return Result.error(e);
// 		}catch (Exception e) {
// 			logger.error("",e);
// 			return Result.error(e);
// 		}
// 	}
// 	*/
//
//     @ApiOperation( value = "批量修改某些字段",notes="")
//     @ApiEntityParams( value = Coop.class, props={ }, remark = "商务合作申请", paramType = "body" )
// 	@ApiResponses({
// 			@ApiResponse(code = 200,response=Coop.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
// 	})
// 	@RequestMapping(value="/editSomeFields",method=RequestMethod.POST)
// 	public Result editSomeFields( @ApiIgnore @RequestBody Map<String,Object> coopMap) {
//
//
// 		try{
//             List<String> ids= (List<String>) coopMap.get("$pks");
// 			if(ids==null || ids.size()==0){
// 				return Result.error("ids-0","ids不能为空");
// 			}
//
// 			Set<String> fields=new HashSet<>();
//             fields.add("id");
// 			for (String fieldName : coopMap.keySet()) {
// 				if(fields.contains(fieldName)){
// 					return Result.error(fieldName+"-no-edit",fieldName+"不允许修改");
// 				}
// 			}
// 			Set<String> fieldKey=coopMap.keySet().stream().filter(i-> fieldsMap.containsKey(i)).collect(Collectors.toSet());
// 			fieldKey=fieldKey.stream().filter(i->!StringUtils.isEmpty(coopMap.get(i) )).collect(Collectors.toSet());
//
// 			if(fieldKey.size()<=0) {
// 				return Result.error("fieldKey-0","没有需要更新的字段");
//  			}
// 			Coop coop = fromMap(coopMap,Coop.class);
// 			List<Coop> coopsDb=coopService.selectListByIds(ids);
// 			if(coopsDb==null ||coopsDb.size()==0){
// 				return Result.error("data-0","记录已不存在");
// 			}
// 			List<Coop> can=new ArrayList<>();
// 			List<Coop> no=new ArrayList<>();
// 			User user = LoginUtils.getCurrentUserInfo();
// 			for (Coop coopDb : coopsDb) {
// 				Tips tips2 = new Tips("检查通过");
// 				if(!tips2.isOk()){
// 				    no.add(coopDb);
// 				}else{
// 					can.add(coopDb);
// 				}
// 			}
// 			if(can.size()>0){
//                 coopMap.put("$pks",can.stream().map(i->i.getId()).collect(Collectors.toList()));
// 			    coopService.editSomeFields(coopMap);
// 			}
// 			List<String> msgs=new ArrayList<>();
// 			if(can.size()>0){
// 				msgs.add(String.format("成功更新以下%s条数据",can.size()));
// 			}
// 			if(no.size()>0){
// 				msgs.add(String.format("以下%s个数据无权限更新",no.size()));
// 			}
// 			if(can.size()>0){
// 				return Result.ok(msgs.stream().collect(Collectors.joining()));
// 			}else {
// 				return Result.error(msgs.stream().collect(Collectors.joining()));
// 			}
// 			//return Result.ok().setData(xmMenu);
// 		}catch (BizException e) {
// 			logger.error("",e);
// 			return Result.error(e);
// 		}catch (Exception e) {
// 			logger.error("",e);
// 			return Result.error(e);
// 		}
// 	}
//
// 	@ApiOperation( value = "根据主键列表批量删除商务合作申请信息",notes=" ")
// 	@ApiResponses({
// 		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
// 	})
// 	@RequestMapping(value="/batchDel",method=RequestMethod.POST)
// 	public Result batchDelCoop(@RequestBody List<Coop> coops) {
//
//
//         try{
//             if(coops.size()<=0){
//                 return Result.error("data-0","请上送待删除数据列表");
//             }
//              List<Coop> datasDb=coopService.selectListByIds(coops.stream().map(i-> i.getId() ).collect(Collectors.toList()));
//
//             List<Coop> can=new ArrayList<>();
//             List<Coop> no=new ArrayList<>();
//             for (Coop data : datasDb) {
//                 if(true){
//                     can.add(data);
//                 }else{
//                     no.add(data);
//                 }
//             }
//             List<String> msgs=new ArrayList<>();
//             if(can.size()>0){
//                 coopService.batchDelete(can);
//                 msgs.add(String.format("成功删除%s条数据.",can.size()));
//             }
//
//             if(no.size()>0){
//                 msgs.add(String.format("以下%s条数据不能删除.【%s】",no.size(),no.stream().map(i-> i.getId() ).collect(Collectors.joining(","))));
//             }
//             if(can.size()>0){
//                  return Result.ok(msgs.stream().collect(Collectors.joining()));
//             }else {
//                 return Result.error(msgs.stream().collect(Collectors.joining()));
//             }
//         }catch (BizException e) {
//             logger.error("",e);
//             return Result.error(e);
//         }catch (Exception e) {
//             logger.error("",e);
//             return Result.error(e);
//         }
// 	}
// }
