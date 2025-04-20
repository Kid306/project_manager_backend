package com.mdp.sys.ctrl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.entity.LangTips;
import com.mdp.core.entity.Result;
import com.mdp.core.err.BizException;
import com.mdp.core.query.QueryTools;
import com.mdp.safe.client.entity.User;
import com.mdp.safe.client.utils.LoginUtils;
import com.mdp.sys.entity.Branch;
import com.mdp.sys.service.BranchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.mdp.core.utils.BaseUtils.toMap;

@RestController
@RequestMapping(value="/*/sys/branch")
@Tag(name = "管理端机构表", description = "管理端机构表（机构下面若干部门）-操作接口")
public class BranchController {
	
	static Logger logger =LoggerFactory.getLogger(BranchController.class);
	
	@Autowired
	private BranchService branchService;
	 

	Map<String,Object> fieldsMap = toMap(new Branch());
 
	
	@Operation( summary =  "管理端机构表（机构下面若干部门）-查询列表")
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Result listBranch(  @RequestParam Map<String,Object> params ){
		try {
			User user=LoginUtils.getCurrentUserInfo();
			QueryWrapper<Branch> qw = QueryTools.initQueryWrapper(Branch.class , params);
			IPage page = QueryTools.initPage(params);
			Map<String,Object> ext=new HashMap<>();
			List<Map<String,Object>> datas = branchService.getBaseMapper().selectListMapByWhere(page,qw,ext);
			return Result.ok("query-ok","查询成功").setData(datas).setTotal(page.getTotal());
		}catch (BizException e) {
			return Result.error(e);
		}catch (Exception e) {
			return Result.error(e.getMessage());
		}
	}
	

	@Operation( summary =  "管理端机构表（机构下面若干部门）-新增")
	  
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Result addBranch(@RequestBody Branch branch) {
		 branchService.save(branch);
         return Result.ok("add-ok","添加成功！");
	}

	@Operation( summary =  "管理端机构表（机构下面若干部门）-删除")
	  
	@RequestMapping(value="/del",method=RequestMethod.POST)
	public Result delBranch(@RequestBody Branch branch){
		branchService.removeById(branch);
        return Result.ok("del-ok","删除成功！");
	}

	@Operation( summary =  "管理端机构表（机构下面若干部门）-修改")
	  
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public Result editBranch(@RequestBody Branch branch) {
		branchService.updateById(branch);
        return Result.ok("edit-ok","修改成功！");
	}

    @Operation( summary =  "管理端机构表（机构下面若干部门）-批量修改某些字段" )

	@RequestMapping(value="/editSomeFields",method=RequestMethod.POST)
	public Result editSomeFields(   @RequestBody Map<String,Object> branchMap) {
        try{
            User user= LoginUtils.getCurrentUserInfo();
            branchService.editSomeFields(branchMap);
            return Result.ok("edit-ok","更新成功");
        }catch (BizException e) {
            logger.error("",e);
            return Result.error(e);
        }catch (Exception e) {
            logger.error("",e);
            return Result.error(e);
        }
	}

	@Operation( summary =  "管理端机构表（机构下面若干部门）-批量删除")
	  
	@RequestMapping(value="/batchDel",method=RequestMethod.POST)
	public Result batchDelBranch(@RequestBody List<Branch> branchs) {
	    User user= LoginUtils.getCurrentUserInfo();
        try{ 
            if(branchs.size()<=0){
                return Result.error("branch-batchDel-data-err-0","请上送待删除数据列表");
            }
             List<Branch> datasDb=branchService.listByIds(branchs.stream().map(i-> i.getId() ).collect(Collectors.toList()));

            List<Branch> can=new ArrayList<>();
            List<Branch> no=new ArrayList<>();
            for (Branch data : datasDb) {
                if(true){
                    can.add(data);
                }else{
                    no.add(data);
                } 
            }
            List<String> msgs=new ArrayList<>();
            if(can.size()>0){
                branchService.removeByIds(can);
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
        }catch (BizException e) { 
           return Result.error("branch-batchDel-err0",e.getMessage());
        }catch (Exception e) {
            return Result.error("branch-batchDel-err1",e.getMessage());
        }


	} 

	@Operation( summary =  "管理端机构表（机构下面若干部门）-根据主键查询一条数据")
      
    @GetMapping(value = "/queryById")
    public Result queryById(Branch branch) {
        Branch data = (Branch) branchService.getById(branch);
        return Result.ok().setData(data);
    }

}
