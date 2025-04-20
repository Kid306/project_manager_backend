package com.mdp.sys.ctrl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.entity.Result;
import com.mdp.core.query.QueryTools;
import com.mdp.swagger.ApiEntityParams;
import com.mdp.sys.entity.Guard;
import com.mdp.sys.service.GuardService;
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
 * url编制采用rest风格,如对sys_guard 服务保障定义表的操作有增删改查,对应的url分别为:<br>
 * 组织 com  顶级模块 mdp 大模块 sys 小模块 <br>
 * 实体 Guard 表 sys_guard 当前主键(包括多主键): id; 
 ***/
@RestController("mdp.sys.guardController")
@RequestMapping(value="/*/sys/guard")
@Api(tags={"服务保障定义表操作接口"})
public class GuardController {
	
	static Logger logger =LoggerFactory.getLogger(GuardController.class);
	
	@Autowired
	private GuardService guardService;
	 

	Map<String,Object> fieldsMap = toMap(new Guard());
 
	
	@ApiOperation( value = "查询服务保障定义表信息列表",notes=" ")
	@ApiEntityParams( Guard.class )
	@ApiImplicitParams({
			@ApiImplicitParam(name="pageSize",value="每页大小，默认20条",required=false),
			@ApiImplicitParam(name="pageNum",value="当前页码,从1开始",required=false),
			@ApiImplicitParam(name="total",value="总记录数,服务器端收到0时，会自动计算总记录数，如果上传>0的不自动计算",required=false),
			@ApiImplicitParam(name="count",value="是否计算总记录条数，如果count=true,则计算计算总条数，如果count=false 则不计算",required=false),
			@ApiImplicitParam(name="orderBy",value="排序列 如性别、学生编号排序 orderBy = sex desc,student desc",required=false),
 	})
	@ApiResponses({
		@ApiResponse(code = 200,response=Guard.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Result listGuard( @ApiIgnore @RequestParam Map<String,Object>  params){
		
		
		
		IPage page=QueryTools.initPage(params);
		QueryWrapper<Guard> qw= QueryTools.initQueryWrapper(Guard.class,params);
		List<Map<String,Object>>	guardList = guardService.selectListMapByWhere(page,qw,params);
		
		return Result.ok().setData(guardList).setTotal(page.getTotal());

	}
	
 
	
	/**
	@ApiOperation( value = "新增一条服务保障定义表信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200,response=Guard.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Result addGuard(@RequestBody Guard guard) {
		
		
		try{
		    boolean createPk=false;
			if(!StringUtils.hasText(guard.getId())) {
			    createPk=true;
				guard.setId(guardService.createKey("id"));
			}
			if(createPk==false){
                 if(guardService.selectOneObject(guard) !=null ){
                    return Result.error("pk-exists","编号重复，请修改编号再提交");
                }
            }
			guardService.insert(guard);
			return Result.ok().setData(guard);
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
	@ApiOperation( value = "删除一条服务保障定义表信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}}")
	}) 
	@RequestMapping(value="/del",method=RequestMethod.POST)
	public Result delGuard(@RequestBody Guard guard){
		
		
		try{
            if(!StringUtils.hasText(guard.getId())) {
                 return Result.error("pk-not-exists","请上送主键参数id");
            }
            Guard guardDb = guardService.selectOneObject(guard);
            if( guardDb == null ){
                return Result.error("data-not-exists","数据不存在，无法删除");
            }
			guardService.deleteByPk(guard);
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
	@ApiOperation( value = "根据主键修改一条服务保障定义表信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200,response=Guard.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public Result editGuard(@RequestBody Guard guard) {
		
		
		try{
            if(!StringUtils.hasText(guard.getId())) {
                 return Result.error("pk-not-exists","请上送主键参数id");
            }
            Guard guardDb = guardService.selectOneObject(guard);
            if( guardDb == null ){
                return Result.error("data-not-exists","数据不存在，无法修改");
            }
			guardService.updateSomeFieldByPk(guard);
			return Result.ok().setData(guard);
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
    @ApiEntityParams( value = Guard.class, props={ }, remark = "服务保障定义表", paramType = "body" )
	@ApiResponses({
			@ApiResponse(code = 200,response=Guard.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	@RequestMapping(value="/editSomeFields",method=RequestMethod.POST)
	public Result editSomeFields( @ApiIgnore @RequestBody Map<String,Object> guardMap) {
		
		
		try{
            List<String> ids= (List<String>) guardMap.get("$pks");
			if(ids==null || ids.size()==0){
				return Result.error("ids-0","ids不能为空");
			}

			Set<String> fields=new HashSet<>();
            fields.add("id");
			for (String fieldName : guardMap.keySet()) {
				if(fields.contains(fieldName)){
					return Result.error(fieldName+"-no-edit",fieldName+"不允许修改");
				}
			}
			Set<String> fieldKey=guardMap.keySet().stream().filter(i-> fieldsMap.containsKey(i)).collect(Collectors.toSet());
			fieldKey=fieldKey.stream().filter(i->!StringUtils.isEmpty(guardMap.get(i) )).collect(Collectors.toSet());

			if(fieldKey.size()<=0) {
				return Result.error("fieldKey-0","没有需要更新的字段");
 			}
			Guard guard = fromMap(guardMap,Guard.class);
			List<Guard> guardsDb=guardService.selectListByIds(ids);
			if(guardsDb==null ||guardsDb.size()==0){
				return Result.error("data-0","记录已不存在");
			}
			List<Guard> can=new ArrayList<>();
			List<Guard> no=new ArrayList<>();
			User user = LoginUtils.getCurrentUserInfo();
			for (Guard guardDb : guardsDb) {
				Tips tips2 = new Tips("检查通过"); 
				if(!tips2.isOk()){
				    no.add(guardDb); 
				}else{
					can.add(guardDb);
				}
			}
			if(can.size()>0){
                guardMap.put("$pks",can.stream().map(i->i.getId()).collect(Collectors.toList()));
			    guardService.editSomeFields(guardMap); 
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
	@ApiOperation( value = "根据主键列表批量删除服务保障定义表信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	}) 
	@RequestMapping(value="/batchDel",method=RequestMethod.POST)
	public Result batchDelGuard(@RequestBody List<Guard> guards) {
		
         
        try{ 
            if(guards.size()<=0){
                return Result.error("data-0","请上送待删除数据列表");
            }
             List<Guard> datasDb=guardService.selectListByIds(guards.stream().map(i-> i.getId() ).collect(Collectors.toList()));

            List<Guard> can=new ArrayList<>();
            List<Guard> no=new ArrayList<>();
            for (Guard data : datasDb) {
                if(true){
                    can.add(data);
                }else{
                    no.add(data);
                } 
            }
            List<String> msgs=new ArrayList<>();
            if(can.size()>0){
                guardService.batchDelete(can);
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
