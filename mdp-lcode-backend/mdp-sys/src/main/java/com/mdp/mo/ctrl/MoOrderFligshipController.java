package com.mdp.mo.ctrl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.entity.Result;
import com.mdp.core.err.BizException;
import com.mdp.core.query.QueryTools;
import com.mdp.mo.entity.MoOrderFligship;
import com.mdp.mo.service.MoOrderFligshipService;
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

import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.mdp.core.utils.BaseUtils.toMap;


/**
 * url编制采用rest风格,如对mo_order_fligship mo_order_fligship的操作有增删改查,对应的url分别为:<br>
 *  新增: mo/moOrderFligship/add <br>
 *  查询: mo/moOrderFligship/list<br>
 *  模糊查询: mo/moOrderFligship/listKey<br>
 *  修改: mo/moOrderFligship/edit <br>
 *  删除: mo/moOrderFligship/del<br>
 *  批量删除: mo/moOrderFligship/batchDel<br>
 * 组织 com  顶级模块 mdp 大模块 mo 小模块 <br>
 * 实体 MoOrderFligship 表 mo_order_fligship 当前主键(包括多主键): id; 
 ***/
@RestController("mdp.mo.moOrderFligshipController")
@RequestMapping(value="/*/mo/moOrderFligship")
@Api(tags={"mo_order_fligship操作接口"})
public class MoOrderFligshipController {
	
	static Logger logger =LoggerFactory.getLogger(MoOrderFligshipController.class);
	
	@Autowired
	private MoOrderFligshipService moOrderFligshipService;
	 
	Map<String,Object> fieldsMap = toMap(new MoOrderFligship());

	@ApiOperation( value = "查询mo_order_fligship信息列表",notes=" ") 
	@ApiResponses({
		@ApiResponse(code = 200,response=MoOrderFligship.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Result listMoOrderFligship( @ApiIgnore @RequestParam Map<String,Object>  moOrderFligship){
		
		
		
		IPage page=QueryTools.initPage(moOrderFligship);
		QueryWrapper<MoOrderFligship> qw= QueryTools.initQueryWrapper(MoOrderFligship.class,moOrderFligship);
		List<Map<String,Object>>	moOrderFligshipList = moOrderFligshipService.selectListMapByWhere(page,qw,moOrderFligship);
		
		return Result.ok().setData(moOrderFligshipList);
	}

	@ApiOperation( value = "新增一条mo_order_fligship信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200,response=MoOrderFligship.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public Result addMoOrderFligship(@RequestBody MoOrderFligship moOrderFligship) {
		
		
		try{
		    boolean createPk=false;
			if(!StringUtils.hasText(moOrderFligship.getId())) {
			    createPk=true;
				moOrderFligship.setId(moOrderFligshipService.createKey("id"));
			}
			if(createPk==false){
                 if(moOrderFligshipService.selectOneObject(moOrderFligship) !=null ){
                    return Result.error("pk-exists","编号重复，请修改编号再提交");
                 }
            }
			User cuser = LoginUtils.getCurrentUserInfo();
			moOrderFligship.setCreateTime(new Date());
			moOrderFligship.setUpdateTime(new Date());
			moOrderFligship.setObranchId(cuser.getBranchId());
			moOrderFligship.setOuserid(cuser.getUserid());
			moOrderFligship.setOusername(cuser.getUsername());
			moOrderFligshipService.insert(moOrderFligship);
			return Result.ok().setData(moOrderFligship);
		}catch (BizException e) {
			logger.error("",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("",e);
			return Result.error(e);
		}   
	}

	
	/**
	@ApiOperation( value = "删除一条mo_order_fligship信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}}")
	}) 
	@RequestMapping(value="/del",method=RequestMethod.POST)
	public Result delMoOrderFligship(@RequestBody MoOrderFligship moOrderFligship){
		
		
		try{
            if(!StringUtils.hasText(moOrderFligship.getId())) {
                 return Result.error("pk-not-exists","请上送主键参数id");
            }
            MoOrderFligship moOrderFligshipDb = moOrderFligshipService.selectOneObject(moOrderFligship);
            if( moOrderFligshipDb == null ){
                return Result.error("data-not-exists","数据不存在，无法删除");
            }
			moOrderFligshipService.deleteByPk(moOrderFligship);
		}catch (BizException e) { 
			logger.error("",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("",e);
			return Result.error(e);
		}  
	}
	 */
	
	/**
	@ApiOperation( value = "根据主键修改一条mo_order_fligship信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200,response=MoOrderFligship.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public Result editMoOrderFligship(@RequestBody MoOrderFligship moOrderFligship) {
		
		
		try{
            if(!StringUtils.hasText(moOrderFligship.getId())) {
                 return Result.error("pk-not-exists","请上送主键参数id");
            }
            MoOrderFligship moOrderFligshipDb = moOrderFligshipService.selectOneObject(moOrderFligship);
            if( moOrderFligshipDb == null ){
                return Result.error("data-not-exists","数据不存在，无法修改");
            }
			moOrderFligshipService.updateSomeFieldByPk(moOrderFligship);
			return Result.ok().setData(moOrderFligship);
		}catch (BizException e) { 
			logger.error("",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("",e);
			return Result.error(e);
		}  
	}
	*/

	/**
    @ApiOperation( value = "批量修改某些字段",notes="")
	@ApiResponses({
			@ApiResponse(code = 200,response=MoOrderFligship.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	@RequestMapping(value="/editSomeFields",method=RequestMethod.POST)
	public Result editSomeFields(@RequestBody Map<String,Object> moOrderFligshipMap) {
		
		
		try{
            List<String> ids= (List<String>) moOrderFligshipMap.get("$pks");
			if(ids==null || ids.size()==0){
				return Result.error("ids-0","ids不能为空");
			}

			Set<String> fields=new HashSet<>();
            fields.add("id");
			for (String fieldName : moOrderFligshipMap.keySet()) {
				if(fields.contains(fieldName)){
					return Result.error(fieldName+"-no-edit",fieldName+"不允许修改");
				}
			}
			Set<String> fieldKey=moOrderFligshipMap.keySet().stream().filter(i-> fieldsMap.containsKey(i)).collect(Collectors.toSet());
			fieldKey=fieldKey.stream().filter(i->!StringUtils.isEmpty(moOrderFligshipMap.get(i) )).collect(Collectors.toSet());

			if(fieldKey.size()<=0) {
				return Result.error("fieldKey-0","没有需要更新的字段");
 			}
			MoOrderFligship moOrderFligship = fromMap(moOrderFligshipMap,MoOrderFligship.class);
			List<MoOrderFligship> moOrderFligshipsDb=moOrderFligshipService.selectListByIds(ids);
			if(moOrderFligshipsDb==null ||moOrderFligshipsDb.size()==0){
				return Result.error("data-0","记录已不存在");
			}
			List<MoOrderFligship> can=new ArrayList<>();
			List<MoOrderFligship> no=new ArrayList<>();
			User user = LoginUtils.getCurrentUserInfo();
			for (MoOrderFligship moOrderFligshipDb : moOrderFligshipsDb) {
				Tips tips2 = new Tips("检查通过"); 
				if(!tips2.isOk()){
				    no.add(moOrderFligshipDb); 
				}else{
					can.add(moOrderFligshipDb);
				}
			}
			if(can.size()>0){
                moOrderFligshipMap.put("$pks",can.stream().map(i->i.getId()).collect(Collectors.toList()));
			    moOrderFligshipService.editSomeFields(moOrderFligshipMap); 
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
	*/

	/**
	@ApiOperation( value = "根据主键列表批量删除mo_order_fligship信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	}) 
	@RequestMapping(value="/batchDel",method=RequestMethod.POST)
	public Result batchDelMoOrderFligship(@RequestBody List<MoOrderFligship> moOrderFligships) {
		
         
        try{ 
            if(moOrderFligships.size()<=0){
                return Result.error("data-0","请上送待删除数据列表");
            }
             List<MoOrderFligship> datasDb=moOrderFligshipService.selectListByIds(moOrderFligships.stream().map(i-> i.getId() ).collect(Collectors.toList()));

            List<MoOrderFligship> can=new ArrayList<>();
            List<MoOrderFligship> no=new ArrayList<>();
            for (MoOrderFligship data : datasDb) {
                if(true){
                    can.add(data);
                }else{
                    no.add(data);
                } 
            }
            List<String> msgs=new ArrayList<>();
            if(can.size()>0){
                moOrderFligshipService.batchDelete(moOrderFligships);
                msgs.add(String.format("成功删除%s条数据.",can.size()));
            }
    
            if(no.size()>0){ 
                msgs.add(String.format("以下%s条数据不能删除.【%s】",no.size(),no.stream().map(i-> i.getId() ).collect(Collectors.joining(","))));
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
        
        return Result.ok();
	} 
	*/
}
