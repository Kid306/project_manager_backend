package com.xm.core.ctrl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.entity.LangTips;
import com.mdp.core.entity.Result;
import com.mdp.core.query.QueryTools;
import com.mdp.safe.client.cache.DeptRedisCacheService;
import com.mdp.safe.client.entity.Dept;
import com.mdp.safe.client.entity.User;
import com.mdp.safe.client.utils.LoginUtils;
import com.mdp.swagger.ApiEntityParams;
import com.xm.core.entity.XmCollect;
import com.xm.core.service.XmCollectService;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
/**
 * @author maimeng-mdp code-gen
 * @since 2023-10-3
 */
@RestController
@RequestMapping(value="/xm/core/xmCollect")
@Api(tags={"xm_collect-操作接口"})
public class XmCollectController {
	
	static Logger logger =LoggerFactory.getLogger(XmCollectController.class);
	
	@Autowired
	private XmCollectService xmCollectService;

	@Autowired
	DeptRedisCacheService deptRedisCacheService;

	@ApiOperation( value = "xm_collect-查询列表",notes=" ")
	@ApiEntityParams(XmCollect.class)
	@ApiResponses({
		@ApiResponse(code = 200,response=XmCollect.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Result listXmCollect(@ApiIgnore @RequestParam Map<String,Object> params){
			User user=LoginUtils.getCurrentUserInfo();
			params.remove("branchId");
			params.put("linkBranchId",user.getBranchId());
			QueryWrapper<XmCollect> qw = QueryTools.initQueryWrapper(XmCollect.class , params);
			IPage page=QueryTools.initPage(params);
		if(!StringUtils.isEmpty(user.getDeptid())){
			Dept dept=deptRedisCacheService.getDept(user.getDeptid());
			if(dept==null){
				params.put("myIdPath","");
			}else{
				params.put("myIdPath",dept.getIdPath());
			}
		}
		params.put("myDeptid",user.getDeptid());
		params.put("myUserid",user.getUserid());
		List<Map<String,Object>> datas =new ArrayList<>();
		if(params.containsKey("api")){
			datas = xmCollectService.selectListMapByWhereWithState(page,qw,params);
		}else {
			datas = xmCollectService.selectListMapByWhere(page,qw,params);
		}
			return Result.ok("query-ok","查询成功").setData(datas).setTotal(page.getTotal());
	}

	@ApiOperation( value = "xm_collect-查询列表",notes=" ")
	@ApiEntityParams(XmCollect.class)
	@ApiResponses({
			@ApiResponse(code = 200,response=XmCollect.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/listWithState",method=RequestMethod.GET)
	public Result listWithState(@ApiIgnore @RequestParam Map<String,Object> params){
		params.put("api","listWithState");
		return this.listXmCollect(params);
	}
	

	@ApiOperation( value = "xm_collect-新增",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200,response=XmCollect.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Result addXmCollect(@RequestBody XmCollect xmCollect) {
		User user = LoginUtils.getCurrentUserInfo();
		xmCollect.setCtime(new Date());
		xmCollect.setCuserid(user.getUserid());
		xmCollect.setCusername(user.getUsername());
		xmCollect.setBranchId(user.getBranchId());
		xmCollect.setId(this.xmCollectService.createKey("id"));
		if (!StringUtils.hasText(xmCollect.getOpenLvl())){
			xmCollect.setOpenLvl("1");
		}

		if (!StringUtils.hasText(xmCollect.getAdmUserid())){
			xmCollect.setAdmUserid(user.getUserid());
			xmCollect.setAdmUsername(user.getUsername());
		}
		if (!StringUtils.hasText(xmCollect.getDeptid())) {
			xmCollect.setDeptid(user.getDeptid());
			Dept dept = deptRedisCacheService.getDept(user.getDeptid());
			xmCollect.setDeptidPath(dept.getIdPath());
		}
		if (!StringUtils.hasText(xmCollect.getDeptidPath())) {
			Dept dept = deptRedisCacheService.getDept(xmCollect.getDeptid());
			xmCollect.setDeptidPath(dept.getIdPath());
		}
		xmCollectService.insert(xmCollect);
		xmCollectService.loadProjectStateToXmCollectState(xmCollect.getId());
         return Result.ok("add-ok","添加成功！");
	}

	@ApiOperation( value = "xm_collect-删除",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}}")
	}) 
	@RequestMapping(value="/del",method=RequestMethod.POST)
	public Result delXmCollect(@RequestBody XmCollect xmCollect){
		xmCollectService.removeById(xmCollect);
        return Result.ok("del-ok","删除成功！");
	}

	@ApiOperation( value = "xm_collect-修改",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200,response=XmCollect.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public Result editXmCollect(@RequestBody XmCollect xmCollect) {
		xmCollectService.updateById(xmCollect);
        return Result.ok("edit-ok","修改成功！");
	}

	@ApiOperation( value = "xm_collect-执行项目集统计",notes=" ")
	@ApiResponses({
			@ApiResponse(code = 200,response=XmCollect.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	@RequestMapping(value="/loadProjectStateToXmCollectState",method=RequestMethod.POST)
	public Result loadProjectStateToXmCollectState(@RequestBody XmCollect xmCollect) {
		xmCollectService.loadProjectStateToXmCollectState(xmCollect.getId());
		return Result.ok("edit-ok","修改成功！");
	}


    @ApiOperation( value = "xm_collect-批量修改某些字段",notes="")
    @ApiEntityParams( value = XmCollect.class, props={ }, remark = "xm_collect", paramType = "body" )
	@ApiResponses({
			@ApiResponse(code = 200,response=XmCollect.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	@RequestMapping(value="/editSomeFields",method=RequestMethod.POST)
	public Result editSomeFields( @ApiIgnore @RequestBody Map<String,Object> params) {
            User user= LoginUtils.getCurrentUserInfo();
            xmCollectService.editSomeFields(params);
            return Result.ok("edit-ok","更新成功");
	}

	@ApiOperation( value = "xm_collect-批量删除",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	}) 
	@RequestMapping(value="/batchDel",method=RequestMethod.POST)
	public Result batchDelXmCollect(@RequestBody List<XmCollect> xmCollects) {
	    User user= LoginUtils.getCurrentUserInfo();
        if(xmCollects.size()<=0){
            return Result.error("batchDel-data-err-0","请上送待删除数据列表");
        }
         List<XmCollect> datasDb=xmCollectService.listByIds(xmCollects.stream().map(i-> i.getId() ).collect(Collectors.toList()));

        List<XmCollect> can=new ArrayList<>();
        List<XmCollect> no=new ArrayList<>();
        for (XmCollect data : datasDb) {
            if(true){
                can.add(data);
            }else{
                no.add(data);
            }
        }
        List<String> msgs=new ArrayList<>();
        if(can.size()>0){
            xmCollectService.removeByIds(can);
            msgs.add(LangTips.transMsg("del-ok-num","成功删除%s条数据.",can.size()));
        }

        if(no.size()>0){
            msgs.add(LangTips.transMsg("not-allow-del-num","以下%s条数据不能删除:【%s】",no.size(),no.stream().map(i-> i.getId() ).collect(Collectors.joining(","))));
        }
        if(can.size()>0){
             return Result.ok(msgs.stream().collect(Collectors.joining()));
        }else {
            return Result.error(msgs.stream().collect(Collectors.joining()));
        }
	} 

	@ApiOperation( value = "xm_collect-根据主键查询一条数据",notes=" ")
     @ApiResponses({
            @ApiResponse(code = 200,response=XmCollect.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
    })
    @RequestMapping(value="/queryById",method=RequestMethod.GET)
    public Result queryById(XmCollect xmCollect) {
        XmCollect data = (XmCollect) xmCollectService.getById(xmCollect);
        return Result.ok().setData(data);
    }

}
