package com.mdp.sys.ctrl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.entity.Result;
import com.mdp.core.err.BizException;
import com.mdp.core.query.QueryTools;
import com.mdp.safe.client.entity.User;
import com.mdp.safe.client.utils.LoginUtils;
import com.mdp.swagger.ApiEntityParams;
import com.mdp.sys.entity.BranchSvr;
import com.mdp.sys.service.BranchSvrService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.*;
import java.util.stream.Collectors;

import static com.mdp.core.utils.BaseUtils.fromMap;
import static com.mdp.core.utils.BaseUtils.toMap;

/**
 * url编制采用rest风格,如对sys_branch_svr 企业服务列表的操作有增删改查,对应的url分别为:<br>
 * 组织 com  顶级模块 mdp 大模块 sys 小模块 <br>
 * 实体 BranchSvr 表 sys_branch_svr 当前主键(包括多主键): id; 
 ***/
@RestController("mdp.sys.branchSvrController")
@RequestMapping(value="/*/sys/branchSvr")
@Api(tags={"企业服务列表操作接口"})
public class BranchSvrController {
	
	static Logger logger =LoggerFactory.getLogger(BranchSvrController.class);
	
	@Autowired
	private BranchSvrService branchSvrService;
	 

	Map<String,Object> fieldsMap = toMap(new BranchSvr());
 
	
	@ApiOperation( value = "查询企业服务列表信息列表",notes=" ")
	@ApiEntityParams( BranchSvr.class )
	@ApiImplicitParams({
			@ApiImplicitParam(name="pageSize",value="每页大小，默认20条",required=false),
			@ApiImplicitParam(name="pageNum",value="当前页码,从1开始",required=false),
			@ApiImplicitParam(name="total",value="总记录数,服务器端收到0时，会自动计算总记录数，如果上传>0的不自动计算",required=false),
			@ApiImplicitParam(name="count",value="是否计算总记录条数，如果count=true,则计算计算总条数，如果count=false 则不计算",required=false),
			@ApiImplicitParam(name="orderBy",value="排序列 如性别、学生编号排序 orderBy = sex desc,student desc",required=false),
 	})
	@ApiResponses({
		@ApiResponse(code = 200,response=BranchSvr.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Result listBranchSvr(  @ApiIgnore @RequestParam Map<String,Object>  params){
		
		
		
		IPage page=QueryTools.initPage(params);
		QueryWrapper<BranchSvr> qw= QueryTools.initQueryWrapper(BranchSvr.class,params);
		List<Map<String,Object>>	branchSvrList = branchSvrService.selectListMapByWhere(page,qw,params);
		
		return Result.ok().setData(branchSvrList).setTotal(page.getTotal());

	}
	

	@ApiOperation( value = "新增一条企业服务列表信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200,response=BranchSvr.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Result addBranchSvr(@RequestBody BranchSvr branchSvr) {
		
		
		try{
		    boolean createPk=false;
			if(!StringUtils.hasText(branchSvr.getId())) {
			    createPk=true;
				branchSvr.setId(branchSvrService.createKey("id"));
			}
			if(createPk==false){
                 if(branchSvrService.selectOneObject(branchSvr) !=null ){
                    return Result.error("pk-exists","编号重复，请修改编号再提交");
                }
            }
			if(!LoginUtils.isBranchAdmin()){
				return Result.error("branchAdm","您不是机构管理员，不允许添加机构服务");
			}
			User user=LoginUtils.getCurrentUserInfo();
			branchSvr.setBranchId(user.getBranchId());
			branchSvrService.insert(branchSvr);
			return Result.ok().setData(branchSvr);
		}catch (BizException e) { 
			logger.error("",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("",e);
			return Result.error(e);
		}  
	}

	@ApiOperation( value = "删除一条企业服务列表信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}}")
	}) 
	@RequestMapping(value="/del",method=RequestMethod.POST)
	public Result delBranchSvr(@RequestBody BranchSvr branchSvr){
		
		
		try{
            if(!StringUtils.hasText(branchSvr.getId())) {
                 return Result.error("pk-not-exists","请上送主键参数id");
            }

			if(!LoginUtils.isBranchAdmin()){
				return Result.error("branchAdm","您不是机构管理员，不允许删除机构服务");
			}
            BranchSvr branchSvrDb = branchSvrService.selectOneObject(branchSvr);
            if( branchSvrDb == null ){
                return Result.error("data-not-exists","数据不存在，无法删除");
            }
			branchSvrService.deleteByPk(branchSvr);
            return Result.ok();
		}catch (BizException e) { 
			logger.error("",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("",e);
			return Result.error(e);
		}  
	}

	@ApiOperation( value = "根据主键修改一条企业服务列表信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200,response=BranchSvr.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public Result editBranchSvr(@RequestBody BranchSvr branchSvr) {
		
		
		try{
            if(!StringUtils.hasText(branchSvr.getId())) {
                 return Result.error("pk-not-exists","请上送主键参数id");
            }

			if(!LoginUtils.isBranchAdmin()){
				return Result.error("branchAdm","您不是机构管理员，不允许修改机构服务");
			}
			BranchSvr branchSvrDb = branchSvrService.selectOneObject(branchSvr);
            if( branchSvrDb == null ){
                return Result.error("data-not-exists","数据不存在，无法修改");
            }
			branchSvrService.updateSomeFieldByPk(branchSvr);
			return Result.ok().setData(branchSvr);
		}catch (BizException e) { 
			logger.error("",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("",e);
			return Result.error(e);
		}  
	}

    @ApiOperation( value = "批量修改某些字段",notes="")
    @ApiEntityParams( value = BranchSvr.class, props={ }, remark = "企业服务列表", paramType = "body" )
	@ApiResponses({
			@ApiResponse(code = 200,response=BranchSvr.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	@RequestMapping(value="/editSomeFields",method=RequestMethod.POST)
	public Result editSomeFields( @ApiIgnore @RequestBody Map<String,Object> branchSvrMap) {
		
		
		try{
            List<String> ids= (List<String>) branchSvrMap.get("$pks");
			if(ids==null || ids.size()==0){
				return Result.error("ids-0","ids不能为空");
			}

			Set<String> fields=new HashSet<>();
            fields.add("id");
			for (String fieldName : branchSvrMap.keySet()) {
				if(fields.contains(fieldName)){
					return Result.error(fieldName+"-no-edit",fieldName+"不允许修改");
				}
			}
			Set<String> fieldKey=branchSvrMap.keySet().stream().filter(i-> fieldsMap.containsKey(i)).collect(Collectors.toSet());
			fieldKey=fieldKey.stream().filter(i->!StringUtils.isEmpty(branchSvrMap.get(i) )).collect(Collectors.toSet());

			if(fieldKey.size()<=0) {
				return Result.error("fieldKey-0","没有需要更新的字段");
 			}
			BranchSvr branchSvr = fromMap(branchSvrMap,BranchSvr.class);
			List<BranchSvr> branchSvrsDb=branchSvrService.selectListByIds(ids);
			if(branchSvrsDb==null ||branchSvrsDb.size()==0){
				return Result.error("data-0","记录已不存在");
			}
			List<BranchSvr> can=new ArrayList<>();
			List<BranchSvr> no=new ArrayList<>();
			User user = LoginUtils.getCurrentUserInfo();
			for (BranchSvr branchSvrDb : branchSvrsDb) {
				if(!LoginUtils.isBranchAdmin(branchSvrDb.getBranchId())){
				    no.add(branchSvrDb); 
				}else{
					can.add(branchSvrDb);
				}
			}
			if(can.size()>0){
                branchSvrMap.put("$pks",can.stream().map(i->i.getId()).collect(Collectors.toList()));
			    branchSvrService.editSomeFields(branchSvrMap); 
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

	@ApiOperation( value = "根据主键列表批量删除企业服务列表信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	}) 
	@RequestMapping(value="/batchDel",method=RequestMethod.POST)
	public Result batchDelBranchSvr(@RequestBody List<BranchSvr> branchSvrs) {
		
         
        try{ 
            if(branchSvrs.size()<=0){
                return Result.error("data-0","请上送待删除数据列表");
            }
             List<BranchSvr> datasDb=branchSvrService.selectListByIds(branchSvrs.stream().map(i-> i.getId() ).collect(Collectors.toList()));

            List<BranchSvr> can=new ArrayList<>();
            List<BranchSvr> no=new ArrayList<>();
            for (BranchSvr data : datasDb) {
                if(LoginUtils.isBranchAdmin(data.getBranchId())){
                    can.add(data);
                }else{
                    no.add(data);
                } 
            }
            List<String> msgs=new ArrayList<>();
            if(can.size()>0){
                branchSvrService.batchDelete(can);
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
	} 

}
