package com.mdp.sys.ctrl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.entity.Result;
import com.mdp.core.entity.Tips;
import com.mdp.core.err.BizException;
import com.mdp.core.query.QueryTools;
import com.mdp.core.utils.NumberUtil;
import com.mdp.safe.client.entity.User;
import com.mdp.safe.client.utils.LoginUtils;
import com.mdp.sys.entity.BranchInterests;
import com.mdp.sys.service.BranchInterestsService;
import com.mdp.sys.service.BranchService;
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

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static com.mdp.core.utils.BaseUtils.fromMap;
import static com.mdp.core.utils.BaseUtils.toMap;

/**
 * url编制采用rest风格,如对sys_branch_interests 机构权益关联表的操作有增删改查,对应的url分别为:<br>
 *  新增: sys/branchInterests/add <br>
 *  查询: sys/branchInterests/list<br>
 *  模糊查询: sys/branchInterests/listKey<br>
 *  修改: sys/branchInterests/edit <br>
 *  删除: sys/branchInterests/del<br>
 *  批量删除: sys/branchInterests/batchDel<br>
 * 组织 com  顶级模块 mdp 大模块 sys 小模块 <br>
 * 实体 BranchInterests 表 sys_branch_interests 当前主键(包括多主键): branch_id; 
 ***/
@RestController("mdp.sys.branchInterestsController")
@RequestMapping(value="/*/sys/branchInterests")
@Api(tags={"机构权益关联表操作接口"})
public class BranchInterestsController {
	
	static Logger logger =LoggerFactory.getLogger(BranchInterestsController.class);
	
	@Autowired
	private BranchInterestsService branchInterestsService;
	@Autowired
	private BranchService branchService;
	 

	Map<String,Object> fieldsMap = toMap(new BranchInterests());
 
	
	@ApiOperation( value = "查询机构权益关联表信息列表",notes=" ") 
	@ApiResponses({
		@ApiResponse(code = 200,response=BranchInterests.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Result listBranchInterests( @ApiIgnore @RequestParam Map<String,Object>  params){
		
		
		
		IPage page=QueryTools.initPage(params);
		QueryWrapper<BranchInterests> qw= QueryTools.initQueryWrapper(BranchInterests.class,params);
		List<Map<String,Object>>	branchInterestsList = branchInterestsService.selectListMapByWhere(page,qw,params);
		
		return Result.ok().setData(branchInterestsList).setTotal(page.getTotal());

	}

	@ApiOperation( value = "查询机构权益明细",notes=" ")
	@ApiResponses({
			@ApiResponse(code = 200,response=BranchInterests.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/detail",method=RequestMethod.GET)
	public Result detail( Map<String,Object> params ){
		User user1=LoginUtils.getCurrentUserInfo();
		String branchId=user1.getBranchId();
		if(LoginUtils.isSuperAdmin()||LoginUtils.hasAnyRoles("platformAdm")){
			if(params!=null && params.containsKey("branchId")){
				branchId= (String) params.get("branchId");
			}
		}
		Map<String,Object> branchInterestsMap=this.branchInterestsService.detailMap(branchId);
		if(branchInterestsMap==null || branchInterestsMap.isEmpty() || branchInterestsMap.get("maxUsers")==null){
			BranchInterests branchInterests=this.branchInterestsService.getDefaultBranchInterests(branchId);
			Map<String,Object> calcUsers=branchService.calcBranchUsers( branchId);
			branchInterests.setMaxUsers(NumberUtil.getInteger(branchInterests.getMaxUsers(),10));
			branchInterests.setCurrUsers(NumberUtil.getInteger(calcUsers.get("currUsers"),1));
			this.branchInterestsService.insert(branchInterests);
			branchInterestsMap=this.branchInterestsService.detailMap(branchId);
		}
		return Result.ok().setData(branchInterestsMap);

	}
	/**
	 * 检查机构是否具有某种权益
	 * branchId-机构编号，exp-需要新增的经验值，bids-需要新增的投标次数，at-需要增加的金额
	 * @param branchInterests
	 * @return
	 */
	@ApiOperation( value = "检查会员是否具有权益",notes=" ")
	@ApiResponses({
			@ApiResponse(code = 200,response=BranchInterests.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/checkBranchInterests",method=RequestMethod.GET)
	public Result checkBranchInterests( Map<String,Object> branchInterests){
		
		
		String branchId= (String) branchInterests.get("branchId");
		if(!StringUtils.hasText((CharSequence) branchInterests.get("branchId"))){
			return Result.error("branchId-0","机构编号不能为空");
		}
		Integer bids=NumberUtil.getInteger(branchInterests.get("bids"),0);
		BigDecimal exp=NumberUtil.getBigDecimal(branchInterests.get("exp"),BigDecimal.ZERO);
		BigDecimal at=NumberUtil.getBigDecimal(branchInterests.get("at"),BigDecimal.ZERO);
		BranchInterests branchInterestsDb=this.branchInterestsService.selectOneById(branchId);
		if(branchInterestsDb==null){
			branchInterestsDb=this.branchInterestsService.getDefaultBranchInterests(branchId);
			Map<String,Object> calcUsers=branchService.calcBranchUsers( branchId);
			branchInterestsDb.setCurrUsers(NumberUtil.getInteger(calcUsers.get("currUsers"),1));
			this.branchInterestsService.insert(branchInterestsDb);
		}
		if(bids>0){
			if(branchInterestsDb.getBids()!=null && branchInterestsDb.getCmonthBids()+bids>branchInterestsDb.getBids()){
				return Result.error("bids-not-enough","投标次数超限");
			}
		}
		if(exp.compareTo(BigDecimal.ZERO)>0){
			if(branchInterestsDb.getSmaxExp()!=null && branchInterestsDb.getSmaxExp().compareTo(exp)<0){
				return Result.error("smaxExp-not-enough","投标工作量超限");
			}
		}
		if(at.compareTo(BigDecimal.ZERO)>0){
			if(branchInterestsDb.getSmaxAt()!=null && branchInterestsDb.getSmaxAt().compareTo(at)<0){
				return Result.error("smaxAt-not-enough","投标金额超限");
			}
		}
		return Result.ok().setData(branchInterestsDb);
	}
	
	/**
	@ApiOperation( value = "新增一条机构权益关联表信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200,response=BranchInterests.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Result addBranchInterests(@RequestBody BranchInterests branchInterests) {
		
		
		try{
		    boolean createPk=false;
			if(!StringUtils.hasText(branchInterests.getBranchId())) {
			    createPk=true;
				branchInterests.setBranchId(branchInterestsService.createKey("branchId"));
			}
			if(createPk==false){
                 if(branchInterestsService.selectOneObject(branchInterests) !=null ){
                    return Result.error("pk-exists","编号重复，请修改编号再提交");
                }
            }
			branchInterestsService.insert(branchInterests);
			return Result.ok().setData(branchInterests);
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
	@ApiOperation( value = "删除一条机构权益关联表信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}}")
	}) 
	@RequestMapping(value="/del",method=RequestMethod.POST)
	public Result delBranchInterests(@RequestBody BranchInterests branchInterests){
		
		
		try{
            if(!StringUtils.hasText(branchInterests.getBranchId())) {
                 return Result.error("pk-not-exists","请上送主键参数branchId");
            }
            BranchInterests branchInterestsDb = branchInterestsService.selectOneObject(branchInterests);
            if( branchInterestsDb == null ){
                return Result.error("data-not-exists","数据不存在，无法删除");
            }
			branchInterestsService.deleteByPk(branchInterests);
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
	@ApiOperation( value = "根据主键修改一条机构权益关联表信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200,response=BranchInterests.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public Result editBranchInterests(@RequestBody BranchInterests branchInterests) {
		
		
		try{
            if(!StringUtils.hasText(branchInterests.getBranchId())) {
                 return Result.error("pk-not-exists","请上送主键参数branchId");
            }
            BranchInterests branchInterestsDb = branchInterestsService.selectOneObject(branchInterests);
            if( branchInterestsDb == null ){
                return Result.error("data-not-exists","数据不存在，无法修改");
            }
			branchInterestsService.updateSomeFieldByPk(branchInterests);
			return Result.ok().setData(branchInterests);
		}catch (BizException e) { 
			logger.error("",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("",e);
			return Result.error(e);
		}  
	}
	*/

    @ApiOperation( value = "批量修改某些字段",notes="")
	@ApiResponses({
			@ApiResponse(code = 200,response=BranchInterests.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	@RequestMapping(value="/editSomeFields",method=RequestMethod.POST)
	public Result editSomeFields(@RequestBody Map<String,Object> branchInterestsMap) {
		
		if(!LoginUtils.isSuperAdmin() && !LoginUtils.hasAnyRoles("platformAdm")){
			return Result.error("superAdmin-role-required","必须拥有以下角色才能操作%s","superAdmin,platformAdmin");
		}
		try{
            List<String> branchIds= (List<String>) branchInterestsMap.get("$pks");
			if(branchIds==null || branchIds.size()==0){
				return Result.error("branchIds-0","branchIds不能为空");
			}

			Set<String> fields=new HashSet<>();
            fields.add("branchId");
			for (String fieldName : branchInterestsMap.keySet()) {
				if(fields.contains(fieldName)){
					return Result.error(fieldName+"-no-edit",fieldName+"不允许修改");
				}
			}
			Set<String> fieldKey=branchInterestsMap.keySet().stream().filter(i-> fieldsMap.containsKey(i)).collect(Collectors.toSet());
			fieldKey=fieldKey.stream().filter(i->!StringUtils.isEmpty(branchInterestsMap.get(i) )).collect(Collectors.toSet());

			if(fieldKey.size()<=0) {
				return Result.error("fieldKey-0","没有需要更新的字段");
 			}
			BranchInterests branchInterests = fromMap(branchInterestsMap,BranchInterests.class);
			List<BranchInterests> branchInterestssDb=branchInterestsService.listByIds(branchIds);
			if(branchInterestssDb==null ||branchInterestssDb.size()==0){
				return Result.error("data-0","记录已不存在");
			}
			List<BranchInterests> can=new ArrayList<>();
			List<BranchInterests> no=new ArrayList<>();
			User user = LoginUtils.getCurrentUserInfo();
			for (BranchInterests branchInterestsDb : branchInterestssDb) {
				Tips tips2 = new Tips("检查通过");
				if(!tips2.isOk()){
				    no.add(branchInterestsDb); 
				}else{
					can.add(branchInterestsDb);
				}
			}
			if(can.size()>0){
                branchInterestsMap.put("branchIds",can.stream().map(i->i.getBranchId()).collect(Collectors.toList()));
			    branchInterestsService.editSomeFields(branchInterestsMap); 
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

	/**
	@ApiOperation( value = "根据主键列表批量删除机构权益关联表信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	}) 
	@RequestMapping(value="/batchDel",method=RequestMethod.POST)
	public Result batchDelBranchInterests(@RequestBody List<BranchInterests> branchInterestss) {
		
         
        try{ 
            if(branchInterestss.size()<=0){
                return Result.error("data-0","请上送待删除数据列表");
            }
             List<BranchInterests> datasDb=branchInterestsService.selectListByIds(branchInterestss.stream().map(i-> i.getBranchId() ).collect(Collectors.toList()));

            List<BranchInterests> can=new ArrayList<>();
            List<BranchInterests> no=new ArrayList<>();
            for (BranchInterests data : datasDb) {
                if(true){
                    can.add(data);
                }else{
                    no.add(data);
                } 
            }
            List<String> msgs=new ArrayList<>();
            if(can.size()>0){
                branchInterestsService.batchDelete(branchInterestss);
                msgs.add(String.format("成功删除%s条数据.",can.size()));
            }
    
            if(no.size()>0){ 
                msgs.add(String.format("以下%s条数据不能删除.【%s】",no.size(),no.stream().map(i-> i.getBranchId() ).collect(Collectors.joining(","))));
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
