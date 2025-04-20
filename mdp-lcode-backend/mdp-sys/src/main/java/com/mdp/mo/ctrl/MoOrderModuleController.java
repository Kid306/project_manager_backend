package com.mdp.mo.ctrl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.entity.Result;
import com.mdp.core.entity.Tips;
import com.mdp.core.err.BizException;
import com.mdp.core.query.QueryTools;
import com.mdp.mo.entity.MoOrderModule;
import com.mdp.mo.service.MoOrderModuleService;
import com.mdp.safe.client.entity.User;
import com.mdp.safe.client.utils.LoginUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.*;
import java.util.stream.Collectors;

import static com.mdp.core.utils.BaseUtils.*;


/**
 * url编制采用rest风格,如对mo_order_module 订单与模块关系表的操作有增删改查,对应的url分别为:<br>
 *  新增: mo/moOrderModule/add <br>
 *  查询: mo/moOrderModule/list<br>
 *  模糊查询: mo/moOrderModule/listKey<br>
 *  修改: mo/moOrderModule/edit <br>
 *  删除: mo/moOrderModule/del<br>
 *  批量删除: mo/moOrderModule/batchDel<br>
 * 组织 com  顶级模块 mdp 大模块 mo 小模块 <br>
 * 实体 MoOrderModule 表 mo_order_module 当前主键(包括多主键): order_id,module_id; 
 ***/
@RestController("mdp.mo.moOrderModuleController")
@RequestMapping(value="/*/mo/moOrderModule")
@Api(tags={"订单与模块关系表操作接口"})
public class MoOrderModuleController {
	
	static Logger logger =LoggerFactory.getLogger(MoOrderModuleController.class);
	
	@Autowired
	private MoOrderModuleService moOrderModuleService;
	 

	Map<String,Object> fieldsMap = toMap(new MoOrderModule());
 
	
	@ApiOperation( value = "查询订单与模块关系表信息列表",notes=" ") 
	@ApiResponses({
		@ApiResponse(code = 200,response=MoOrderModule.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Result listMoOrderModule( @ApiIgnore @RequestParam Map<String,Object>  moOrderModule){
		
		
		
		IPage page=QueryTools.initPage(moOrderModule);

		QueryWrapper<MoOrderModule> qw= QueryTools.initQueryWrapper(MoOrderModule.class,moOrderModule);
		List<Map<String,Object>>	moOrderModuleList = moOrderModuleService.selectListMapByWhere(page,qw,moOrderModule);
		
		return Result.ok().setData(moOrderModuleList).setTotal(page.getTotal());

	}
	
 
	

	@ApiOperation( value = "新增一条订单与模块关系表信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200,response=MoOrderModule.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Result addMoOrderModule(@RequestBody MoOrderModule moOrderModule) {
		
		
		try{
		    boolean createPk=false;
			if(!StringUtils.hasText(moOrderModule.getOrderId())) {
			    createPk=true;
				moOrderModule.setOrderId(moOrderModuleService.createKey("orderId"));
			}
			if(!StringUtils.hasText(moOrderModule.getModuleId())) {
			    createPk=true;
				moOrderModule.setModuleId(moOrderModuleService.createKey("moduleId"));
			}
			if(createPk==false){
                 if(moOrderModuleService.selectOneObject(moOrderModule) !=null ){
                    return Result.error("pk-exists","编号重复，请修改编号再提交");
                }
            }
			moOrderModuleService.insert(moOrderModule);
			return Result.ok().setData(moOrderModule);
		}catch (BizException e) { 
			logger.error("",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("",e);
			return Result.error(e);
		}  
	}

	@ApiOperation( value = "删除一条订单与模块关系表信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}}")
	}) 
	@RequestMapping(value="/del",method=RequestMethod.POST)
	public Result delMoOrderModule(@RequestBody MoOrderModule moOrderModule){
		
		
		try{
            if(!StringUtils.hasText(moOrderModule.getOrderId())) {
                 return Result.error("pk-not-exists","请上送主键参数orderId");
            }
            if(!StringUtils.hasText(moOrderModule.getModuleId())) {
                 return Result.error("pk-not-exists","请上送主键参数moduleId");
            }
            MoOrderModule moOrderModuleDb = moOrderModuleService.selectOneObject(moOrderModule);
            if( moOrderModuleDb == null ){
                return Result.error("data-not-exists","数据不存在，无法删除");
            }
			moOrderModuleService.deleteByPk(moOrderModule);
            return Result.ok();
		}catch (BizException e) { 
			logger.error("",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("",e);
			return Result.error(e);
		}  
	}

	@ApiOperation( value = "根据主键修改一条订单与模块关系表信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200,response=MoOrderModule.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public Result editMoOrderModule(@RequestBody MoOrderModule moOrderModule) {
		
		
		try{
            if(!StringUtils.hasText(moOrderModule.getOrderId())) {
                 return Result.error("pk-not-exists","请上送主键参数orderId");
            }
            if(!StringUtils.hasText(moOrderModule.getModuleId())) {
                 return Result.error("pk-not-exists","请上送主键参数moduleId");
            }
            MoOrderModule moOrderModuleDb = moOrderModuleService.selectOneObject(moOrderModule);
            if( moOrderModuleDb == null ){
                return Result.error("data-not-exists","数据不存在，无法修改");
            }
			moOrderModuleService.updateSomeFieldByPk(moOrderModule);
			return Result.ok().setData(moOrderModule);
		}catch (BizException e) { 
			logger.error("",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("",e);
			return Result.error(e);
		}  
	}

    @ApiOperation( value = "批量修改某些字段",notes="")
	@ApiResponses({
			@ApiResponse(code = 200,response=MoOrderModule.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	@RequestMapping(value="/editSomeFields",method=RequestMethod.POST)
	public Result editSomeFields(@RequestBody Map<String,Object> moOrderModuleMap) {
		
		
		try{
			List<Map<String,Object>> pkList= (List<Map<String,Object>>) moOrderModuleMap.get("$pks");
			if(pkList==null || pkList.size()==0){
				return Result.error("$pks-0","$pks不能为空");
			}

			Set<String> fields=new HashSet<>();
            fields.add("orderId");
            fields.add("moduleId");
			for (String fieldName : moOrderModuleMap.keySet()) {
				if(fields.contains(fieldName)){
					return Result.error(fieldName+"-no-edit",fieldName+"不允许修改");
				}
			}
			Set<String> fieldKey=moOrderModuleMap.keySet().stream().filter(i-> fieldsMap.containsKey(i)).collect(Collectors.toSet());
			fieldKey=fieldKey.stream().filter(i->!StringUtils.isEmpty(moOrderModuleMap.get(i) )).collect(Collectors.toSet());

			if(fieldKey.size()<=0) {
				return Result.error("fieldKey-0","没有需要更新的字段");
 			}
			MoOrderModule moOrderModule = fromMap(moOrderModuleMap,MoOrderModule.class);
			List<MoOrderModule> moOrderModulesDb=moOrderModuleService.selectListByIds(pkList);
			if(moOrderModulesDb==null ||moOrderModulesDb.size()==0){
				return Result.error("data-0","记录已不存在");
			}
			List<MoOrderModule> can=new ArrayList<>();
			List<MoOrderModule> no=new ArrayList<>();
			User user = LoginUtils.getCurrentUserInfo();
			for (MoOrderModule moOrderModuleDb : moOrderModulesDb) {
				Tips tips2 = new Tips("检查通过"); 
				if(!tips2.isOk()){
				    no.add(moOrderModuleDb); 
				}else{
					can.add(moOrderModuleDb);
				}
			}
			if(can.size()>0){
                moOrderModuleMap.put("$pks",can.stream().map(i->map( "orderId",i.getOrderId(),  "moduleId",i.getModuleId())).collect(Collectors.toList()));
			    moOrderModuleService.editSomeFields(moOrderModuleMap); 
			}
			List<String> msgs=new ArrayList<>();
			if(can.size()>0){
				msgs.add(String.format("成功更新以下%s条数据",can.size()));
			}
			if(no.size()>0){
				msgs.add(String.format("以下%s个数据无权限更新",no.size()));
			}
			if(can.size()>0){
				return Result.ok(msgs.stream().collect(Collectors.joining()));
			}else {
				return Result.error(msgs.stream().collect(Collectors.joining()));
			}
			//return Result.ok().setData(xmMenu);
		}catch (BizException e) {
			logger.error("",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("",e);
			return Result.error(e);
		}
	}

	@ApiOperation( value = "根据主键列表批量删除订单与模块关系表信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	}) 
	@RequestMapping(value="/batchDel",method=RequestMethod.POST)
	public Result batchDelMoOrderModule(@RequestBody List<MoOrderModule> moOrderModules) {
		
         
        try{ 
            if(moOrderModules.size()<=0){
                return Result.error("data-0","请上送待删除数据列表");
            }
             List<MoOrderModule> datasDb=moOrderModuleService.selectListByIds(moOrderModules.stream().map(i->map( "orderId",i.getOrderId() ,  "moduleId",i.getModuleId() )).collect(Collectors.toList()));

            List<MoOrderModule> can=new ArrayList<>();
            List<MoOrderModule> no=new ArrayList<>();
            for (MoOrderModule data : datasDb) {
                if(true){
                    can.add(data);
                }else{
                    no.add(data);
                } 
            }
            List<String> msgs=new ArrayList<>();
            if(can.size()>0){
                moOrderModuleService.batchDelete(moOrderModules);
                msgs.add(String.format("成功删除%s条数据.",can.size()));
            }
    
            if(no.size()>0){ 
                msgs.add(String.format("以下%s条数据不能删除.【%s】",no.size(),no.stream().map(i-> i.getOrderId() +" "+ i.getModuleId() ).collect(Collectors.joining(","))));
            }
            if(can.size()>0){
                 return Result.ok(msgs.stream().collect(Collectors.joining()));
            }else {
                return Result.error(msgs.stream().collect(Collectors.joining()));
            }
        }catch (BizException e) { 
            logger.error("",e);
            return Result.error(e);
        }catch (Exception e) {
            logger.error("",e);
            return Result.error(e);
        }
	} 

}
