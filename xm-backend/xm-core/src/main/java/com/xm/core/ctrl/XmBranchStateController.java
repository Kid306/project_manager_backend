package com.xm.core.ctrl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.entity.Result;
import com.mdp.core.query.QueryTools;
import com.mdp.core.utils.RequestUtils;
import com.mdp.safe.client.entity.User;
import com.mdp.safe.client.utils.LoginUtils;
import com.mdp.swagger.ApiEntityParams;
import com.xm.core.entity.XmBranchState;
import com.xm.core.service.XmBranchStateService;
import io.swagger.annotations.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Map;

/**
 * url编制采用rest风格,如对XM.xm_branch_state 机构内所有项目指标汇总的操作有增删改查,对应的url分别为:<br>
 *  新增: core/xmBranchState/add <br>
 *  查询: core/xmBranchState/list<br>
 *  模糊查询: core/xmBranchState/listKey<br>
 *  修改: core/xmBranchState/edit <br>
 *  删除: core/xmBranchState/del<br>
 *  批量删除: core/xmBranchState/batchDel<br>
 * 组织 com.qqkj  顶级模块 xm 大模块 core 小模块 <br>
 * 实体 XmBranchState 表 XM.xm_branch_state 当前主键(包括多主键): id; 
 ***/
@RestController("xm.core.xmBranchStateController")
@RequestMapping(value="/xm/core/xmBranchState")
@Api(tags={"机构内所有项目指标汇总操作接口"})
public class XmBranchStateController {
	
	static Log logger=LogFactory.getLog(XmBranchStateController.class);
	
	@Autowired
	private XmBranchStateService xmBranchStateService;
	 
		
 
	
	@ApiOperation( value = "查询机构内所有项目指标汇总信息列表",notes="listXmBranchState,条件之间是 and关系,模糊查询写法如 {studentName:'%才哥%'}")
	@ApiEntityParams(XmBranchState.class)
	@ApiImplicitParams({
		@ApiImplicitParam(name="pageSize",value="每页记录数",required=false),
		@ApiImplicitParam(name="pageNum",value="当前页码,从1开始",required=false),
		@ApiImplicitParam(name="total",value="总记录数,服务器端收到0时，会自动计算总记录数，如果上传>0的不自动计算",required=false),
		@ApiImplicitParam(name="orderBy",value="排序列 如性别、学生编号排序 orderBy = sex desc,student_id desc",required=false),
		@ApiImplicitParam(name="count",value="是否进行总条数计算,count=true|false",required=false) 
	})
	@ApiResponses({
		@ApiResponse(code = 200,response= XmBranchState.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Result listXmBranchState( @ApiIgnore @RequestParam Map<String,Object> params){
 		RequestUtils.transformArray(params, "ids");
		User user=LoginUtils.getCurrentUserInfo();
		params.put("branchId",user.getBranchId());
		QueryWrapper<XmBranchState> qw= QueryTools.initQueryWrapper(XmBranchState.class,params);
		IPage page=QueryTools.initPage(params);
		List<Map<String,Object>>	datas = xmBranchStateService.selectListMapByWhere(page,qw,params);	//列出XmBranchState列表
		return Result.ok().setData(datas).setTotal(page.getTotal());
	}

	@ApiOperation( value = "查询机构内所有项目指标汇总信息列表",notes="listXmBranchState,条件之间是 and关系,模糊查询写法如 {studentName:'%才哥%'}")
	@ApiEntityParams(XmBranchState.class)
	@ApiResponses({
			@ApiResponse(code = 200,response= XmBranchState.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/list/portal/allBranchSum",method=RequestMethod.GET)
	public Result listPortalAllXmBranchSumState( @ApiIgnore @RequestParam Map<String,Object> params){
		RequestUtils.transformArray(params, "ids");
		User user=LoginUtils.getCurrentUserInfo();
		params.put("branchId",user.getBranchId());
		QueryWrapper<XmBranchState> qw= QueryTools.initQueryWrapper(XmBranchState.class,params);
		IPage page=QueryTools.initPage(params);
		List<Map<String,Object>>	datas = xmBranchStateService.listPortalAllXmBranchSumState(page,qw,params);	//列出XmBranchState列表
		return Result.ok().setData(datas).setTotal(page.getTotal());
	}

	@ApiOperation( value = "查询前后两周每日任务变化数量",notes="listXmBranchState,条件之间是 and关系,模糊查询写法如 {studentName:'%才哥%'}")
 	@ApiResponses({
			@ApiResponse(code = 200,response= XmBranchState.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/list/tasksSumDw",method=RequestMethod.GET)
	public Result tasksSumDw(){
		User user=LoginUtils.getCurrentUserInfo();
 		List<Map<String,Object>>	datas = xmBranchStateService.tasksSumDw(user.getUserid());	//列出XmBranchState列表
		return Result.ok().setData(datas);
	}

	@ApiOperation( value = "从项目汇总表汇总数据到机构汇总表",notes="")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}}")
	}) 
	@RequestMapping(value="/loadProjectStateToXmBranchState",method=RequestMethod.POST)
	public Result loadProjectStateToXmBranchState(@RequestBody XmBranchState xmBranchState){

			if(StringUtils.isEmpty(xmBranchState.getBranchId())) {
				return Result.error("机构编号branchId必填"); 
			}else {
	
				xmBranchStateService.loadProjectStateToXmBranchState(xmBranchState.getBranchId());
			}
			return Result.ok();
	}
 
	
	/**
	@ApiOperation( value = "新增一条机构内所有项目指标汇总信息",notes="addXmBranchState,主键如果为空，后台自动生成")
	@ApiResponses({
		@ApiResponse(code = 200,response=XmBranchState.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Result addXmBranchState(@RequestBody XmBranchState xmBranchState) {
		Map<String,Object> m = new HashMap<>();
		
				
			if(StringUtils.isEmpty(xmBranchState.getId())) {
				xmBranchState.setId(xmBranchStateService.createKey("id"));
			}else{
				 XmBranchState xmBranchStateQuery = new  XmBranchState(xmBranchState.getId());
				if(xmBranchStateService.countByWhere(xmBranchStateQuery)>0){
					return Result.error("编号重复，请修改编号再提交");
					
				}
			}
			xmBranchStateService.insert(xmBranchState);
			m.put("data",xmBranchState);
		}catch (BizException e) { 
			Tips tips=e.getTips();
			logger.error("",e);
		}catch (Exception e) {
			return Result.error(e.getMessage());
			logger.error("",e);
		}  
		m.put("tips", tips);
		return m;
	}
	*/
	
	/**
	@ApiOperation( value = "删除一条机构内所有项目指标汇总信息",notes="delXmBranchState,仅需要上传主键字段")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}}")
	}) 
	@RequestMapping(value="/del",method=RequestMethod.POST)
	public Result delXmBranchState(@RequestBody XmBranchState xmBranchState){
		Map<String,Object> m = new HashMap<>();
		
				
			xmBranchStateService.deleteByPk(xmBranchState);
		}catch (BizException e) { 
			Tips tips=e.getTips();
			logger.error("",e);
		}catch (Exception e) {
			return Result.error(e.getMessage());
			logger.error("",e);
		}  
		m.put("tips", tips);
		return m;
	}
	 */
	
	/**
	@ApiOperation( value = "根据主键修改一条机构内所有项目指标汇总信息",notes="editXmBranchState")
	@ApiResponses({
		@ApiResponse(code = 200,response=XmBranchState.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public Result editXmBranchState(@RequestBody XmBranchState xmBranchState) {
		Map<String,Object> m = new HashMap<>();
		
				
			xmBranchStateService.updateByPk(xmBranchState);
			m.put("data",xmBranchState);
		}catch (BizException e) { 
			Tips tips=e.getTips();
			logger.error("",e);
		}catch (Exception e) {
			return Result.error(e.getMessage());
			logger.error("",e);
		}  
		m.put("tips", tips);
		return m;
	}
	*/
	

	
	/**
	@ApiOperation( value = "根据主键列表批量删除机构内所有项目指标汇总信息",notes="batchDelXmBranchState,仅需要上传主键字段")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	}) 
	@RequestMapping(value="/batchDel",method=RequestMethod.POST)
	public Result batchDelXmBranchState(@RequestBody List<XmBranchState> xmBranchStates) {
		Map<String,Object> m = new HashMap<>();
		
				 
			xmBranchStateService.batchDelete(xmBranchStates);
		}catch (BizException e) { 
			Tips tips=e.getTips();
			logger.error("",e);
		}catch (Exception e) {
			return Result.error(e.getMessage());
			logger.error("",e);
		}  
		m.put("tips", tips);
		return m;
	} 
	*/
}
