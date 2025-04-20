package com.mdp.sys.ctrl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.entity.Result;
import com.mdp.core.query.QueryTools;
import com.mdp.swagger.ApiEntityParams;
import com.mdp.sys.entity.GuardDamage;
import com.mdp.sys.service.GuardDamageService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Map;

import static com.mdp.core.utils.BaseUtils.toMap;

/**
 * url编制采用rest风格,如对sys_guard_damage 保障金赔付表的操作有增删改查,对应的url分别为:<br>
 * 组织 com  顶级模块 mdp 大模块 sys 小模块 <br>
 * 实体 GuardDamage 表 sys_guard_damage 当前主键(包括多主键): id; 
 ***/
@RestController("mdp.sys.guardDamageController")
@RequestMapping(value="/*/sys/guardDamage")
@Api(tags={"保障金赔付表操作接口"})
public class GuardDamageController {
	
	static Logger logger =LoggerFactory.getLogger(GuardDamageController.class);
	
	@Autowired
	private GuardDamageService guardDamageService;
	 

	Map<String,Object> fieldsMap = toMap(new GuardDamage());
 
	
	@ApiOperation( value = "查询保障金赔付表信息列表",notes=" ")
	@ApiEntityParams( GuardDamage.class )
	@ApiImplicitParams({
			@ApiImplicitParam(name="pageSize",value="每页大小，默认20条",required=false),
			@ApiImplicitParam(name="pageNum",value="当前页码,从1开始",required=false),
			@ApiImplicitParam(name="total",value="总记录数,服务器端收到0时，会自动计算总记录数，如果上传>0的不自动计算",required=false),
			@ApiImplicitParam(name="count",value="是否计算总记录条数，如果count=true,则计算计算总条数，如果count=false 则不计算",required=false),
			@ApiImplicitParam(name="orderBy",value="排序列 如性别、学生编号排序 orderBy = sex desc,student desc",required=false),
 	})
	@ApiResponses({
		@ApiResponse(code = 200,response=GuardDamage.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Result listGuardDamage( @ApiIgnore @RequestParam Map<String,Object> params){
		
		
		
		IPage page=QueryTools.initPage(params);
		QueryWrapper<GuardDamage> qw= QueryTools.initQueryWrapper(GuardDamage.class,params);
		List<Map<String,Object>>	guardDamageList = guardDamageService.selectListMapByWhere(page,qw,params);
		
		return Result.ok().setData(guardDamageList).setTotal(page.getTotal());

	}
	
 
	
	/**
	@ApiOperation( value = "新增一条保障金赔付表信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200,response=GuardDamage.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Result addGuardDamage(@RequestBody GuardDamage guardDamage) {
		
		
		try{
		    boolean createPk=false;
			if(!StringUtils.hasText(guardDamage.getId())) {
			    createPk=true;
				guardDamage.setId(guardDamageService.createKey("id"));
			}
			if(createPk==false){
                 if(guardDamageService.selectOneObject(guardDamage) !=null ){
                    return Result.error("pk-exists","编号重复，请修改编号再提交");
                }
            }
			guardDamageService.insert(guardDamage);
			return Result.ok().setData(guardDamage);
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
	@ApiOperation( value = "删除一条保障金赔付表信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}}")
	}) 
	@RequestMapping(value="/del",method=RequestMethod.POST)
	public Result delGuardDamage(@RequestBody GuardDamage guardDamage){
		
		
		try{
            if(!StringUtils.hasText(guardDamage.getId())) {
                 return Result.error("pk-not-exists","请上送主键参数id");
            }
            GuardDamage guardDamageDb = guardDamageService.selectOneObject(guardDamage);
            if( guardDamageDb == null ){
                return Result.error("data-not-exists","数据不存在，无法删除");
            }
			guardDamageService.deleteByPk(guardDamage);
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
	@ApiOperation( value = "根据主键修改一条保障金赔付表信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200,response=GuardDamage.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public Result editGuardDamage(@RequestBody GuardDamage guardDamage) {
		
		
		try{
            if(!StringUtils.hasText(guardDamage.getId())) {
                 return Result.error("pk-not-exists","请上送主键参数id");
            }
            GuardDamage guardDamageDb = guardDamageService.selectOneObject(guardDamage);
            if( guardDamageDb == null ){
                return Result.error("data-not-exists","数据不存在，无法修改");
            }
			guardDamageService.updateSomeFieldByPk(guardDamage);
			return Result.ok().setData(guardDamage);
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
    @ApiEntityParams( value = GuardDamage.class, props={ }, remark = "保障金赔付表", paramType = "body" )
	@ApiResponses({
			@ApiResponse(code = 200,response=GuardDamage.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	@RequestMapping(value="/editSomeFields",method=RequestMethod.POST)
	public Result editSomeFields( @ApiIgnore @RequestBody Map<String,Object> guardDamageMap) {
		
		
		try{
            List<String> ids= (List<String>) guardDamageMap.get("$pks");
			if(ids==null || ids.size()==0){
				return Result.error("ids-0","ids不能为空");
			}

			Set<String> fields=new HashSet<>();
            fields.add("id");
			for (String fieldName : guardDamageMap.keySet()) {
				if(fields.contains(fieldName)){
					return Result.error(fieldName+"-no-edit",fieldName+"不允许修改");
				}
			}
			Set<String> fieldKey=guardDamageMap.keySet().stream().filter(i-> fieldsMap.containsKey(i)).collect(Collectors.toSet());
			fieldKey=fieldKey.stream().filter(i->!StringUtils.isEmpty(guardDamageMap.get(i) )).collect(Collectors.toSet());

			if(fieldKey.size()<=0) {
				return Result.error("fieldKey-0","没有需要更新的字段");
 			}
			GuardDamage guardDamage = fromMap(guardDamageMap,GuardDamage.class);
			List<GuardDamage> guardDamagesDb=guardDamageService.selectListByIds(ids);
			if(guardDamagesDb==null ||guardDamagesDb.size()==0){
				return Result.error("data-0","记录已不存在");
			}
			List<GuardDamage> can=new ArrayList<>();
			List<GuardDamage> no=new ArrayList<>();
			User user = LoginUtils.getCurrentUserInfo();
			for (GuardDamage guardDamageDb : guardDamagesDb) {
				Tips tips2 = new Tips("检查通过"); 
				if(!tips2.isOk()){
				    no.add(guardDamageDb); 
				}else{
					can.add(guardDamageDb);
				}
			}
			if(can.size()>0){
                guardDamageMap.put("$pks",can.stream().map(i->i.getId()).collect(Collectors.toList()));
			    guardDamageService.editSomeFields(guardDamageMap); 
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
	@ApiOperation( value = "根据主键列表批量删除保障金赔付表信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	}) 
	@RequestMapping(value="/batchDel",method=RequestMethod.POST)
	public Result batchDelGuardDamage(@RequestBody List<GuardDamage> guardDamages) {
		
         
        try{ 
            if(guardDamages.size()<=0){
                return Result.error("data-0","请上送待删除数据列表");
            }
             List<GuardDamage> datasDb=guardDamageService.selectListByIds(guardDamages.stream().map(i-> i.getId() ).collect(Collectors.toList()));

            List<GuardDamage> can=new ArrayList<>();
            List<GuardDamage> no=new ArrayList<>();
            for (GuardDamage data : datasDb) {
                if(true){
                    can.add(data);
                }else{
                    no.add(data);
                } 
            }
            List<String> msgs=new ArrayList<>();
            if(can.size()>0){
                guardDamageService.batchDelete(can);
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
