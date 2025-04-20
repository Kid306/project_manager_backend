package com.mdp.dm.ctrl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.api.CacheHKVService;
import com.mdp.core.entity.DmField;
import com.mdp.core.entity.LangTips;
import com.mdp.core.entity.Result;
import com.mdp.core.query.QueryTools;
import com.mdp.core.utils.ObjectTools;
import com.mdp.dm.entity.DmDataSet;
import com.mdp.dm.service.DmDataMetaService;
import com.mdp.dm.service.DmDataSetService;
import com.mdp.dm.tools.DmQxUtil;
import com.mdp.safe.client.entity.User;
import com.mdp.safe.client.utils.LoginUtils;
import com.mdp.swagger.ApiEntityParams;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
/**
 * @author maimeng-mdp code-gen
 * @since 2024-4-25
 */
@RestController
@RequestMapping(value="/*/dm/dataSet")
@Api(tags={"数据集-操作接口"})
public class DmDataSetController {
	
	static Logger logger =LoggerFactory.getLogger(DmDataSetController.class);
	
	@Autowired
	private DmDataSetService dataSetService;

	@Autowired
	DmDataMetaService dataMetaService;


	@Autowired
	CacheHKVService cacheHKVService;

	@ApiOperation( value = "数据集-查询列表",notes=" ")
	@ApiEntityParams(DmDataSet.class)
	@ApiResponses({
		@ApiResponse(code = 200,response= DmDataSet.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Result listDataSet(@ApiIgnore @RequestParam Map<String,Object> params){
			User user=LoginUtils.getCurrentUserInfo();
			QueryWrapper<DmDataSet> qw = QueryTools.initQueryWrapper(DmDataSet.class , params);
			qw.eq("cbranch_id",user.getBranchId());
			IPage page=QueryTools.initPage(params);
			List<Map<String,Object>> datas = dataSetService.selectListMapByWhere(page,qw,params);
			return Result.ok("query-ok","查询成功").setData(datas).setTotal(page.getTotal());
	}



	@ApiOperation( value = "数据集-新增",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200,response= DmDataSet.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Result addDataSet(@RequestBody DmDataSet dataSet) {
		DmQxUtil.checkDmRoles();
		User user=LoginUtils.getCurrentUserInfo();
		dataSet.setCbranchId(user.getBranchId());
		dataSet.setCuserid(user.getUserid());
		dataSet.setCtime(new Date());
		 dataSetService.save(dataSet);
         return Result.ok("add-ok","添加成功！");
	}

	@ApiOperation( value = "数据集-删除",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}}")
	}) 
	@RequestMapping(value="/del",method=RequestMethod.POST)
	public Result delDataSet(@RequestBody DmDataSet dataSet){
		User user=LoginUtils.getCurrentUserInfo();
		DmDataSet dataSetDb=dataSetService.getById(dataSet.getId());
		if(dataSetDb==null){
			return Result.error("dataSet-0","数据集不存在");
		}
		if(!user.getUserid().equalsIgnoreCase(dataSetDb.getCuserid())){
			return Result.error("no-oper-qx","无权限操作。");
		}
		dataSetService.removeById(dataSet);
		dataSetService.removeDataSetFromCache(dataSetDb.getId());
        return Result.ok("del-ok","删除成功！");
	}

	@ApiOperation( value = "数据集-修改",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200,response= DmDataSet.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public Result editDataSet(@RequestBody DmDataSet dataSet) {

		User user=LoginUtils.getCurrentUserInfo();
		DmDataSet dataSetDb=dataSetService.getById(dataSet.getId());
		if(dataSetDb==null){
			return Result.error("dataSet-0","数据集不存在");
		}
		if(!user.getUserid().equalsIgnoreCase(dataSetDb.getCuserid())){
			return Result.error("no-oper-qx","无权限操作。");
		}
		dataSetService.updateById(dataSet);
		dataSetService.removeDataSetFromCache(dataSetDb.getId());
        return Result.ok("edit-ok","修改成功！");
	}

    @ApiOperation( value = "数据集-批量修改某些字段",notes="")
    @ApiEntityParams( value = DmDataSet.class, props={ }, remark = "数据集", paramType = "body" )
	@ApiResponses({
			@ApiResponse(code = 200,response= DmDataSet.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	@RequestMapping(value="/editSomeFields",method=RequestMethod.POST)
	public Result editSomeFields( @ApiIgnore @RequestBody Map<String,Object> params) {
            User user= LoginUtils.getCurrentUserInfo();
			List<String> pks= (List<String>) params.get("$pks");
			if(pks==null || pks.size()==0){
				return Result.error("pks-0","请上送主键列表$pks");
			}
			List<DmDataSet> dataSets=this.dataSetService.listByIds(pks);
			if(dataSets==null||dataSets.size()==0){
				return Result.error("data-0","数据不存在");
			}
			for (DmDataSet dataSetDb : dataSets) {
				if(!user.getUserid().equalsIgnoreCase(dataSetDb.getCuserid())){
					return Result.error("no-oper-qx","无权限操作。");
				}else{
					dataSetService.removeDataSetFromCache(dataSetDb.getId());
				}
			}

            dataSetService.editSomeFields(params);
            return Result.ok("edit-ok","更新成功");
	}

	@ApiOperation( value = "数据集-批量删除",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	}) 
	@RequestMapping(value="/batchDel",method=RequestMethod.POST)
	public Result batchDelDataSet(@RequestBody List<DmDataSet> dataSets) {
	    User user= LoginUtils.getCurrentUserInfo();
        if(dataSets.size()<=0){
            return Result.error("batchDel-data-err-0","请上送待删除数据列表");
        }
         List<DmDataSet> datasDb=dataSetService.listByIds(dataSets.stream().map(i-> i.getId() ).collect(Collectors.toList()));

        List<DmDataSet> can=new ArrayList<>();
        List<DmDataSet> no=new ArrayList<>();
        for (DmDataSet dataSetDb : datasDb) {
			if(user.getUserid().equalsIgnoreCase(dataSetDb.getCuserid())){
                can.add(dataSetDb);
				dataSetService.removeDataSetFromCache(dataSetDb.getId());
            }else{

                no.add(dataSetDb);
            }
        }
        List<String> msgs=new ArrayList<>();
        if(can.size()>0){
            dataSetService.removeByIds(can);
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

	@ApiOperation( value = "数据集-根据主键查询一条数据",notes=" ")
     @ApiResponses({
            @ApiResponse(code = 200,response= DmDataSet.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
    })
    @RequestMapping(value="/queryById",method=RequestMethod.GET)
    public Result queryById(DmDataSet dataSet) {
        DmDataSet data = (DmDataSet) dataSetService.getDataSetFromCacheFirst(dataSet.getId());
		List<DmField> dmFields=new ArrayList<>();
		if(!ObjectTools.isEmpty(data.getDsSql())){
			try{
				dmFields=dataMetaService.getColumnsInfoBySqlFirstCache(data.getDataSource(),data.getDsSql());
			}catch (Exception e){
				logger.error("",e);
			}
		}
		return Result.ok().setData(data).put("fields",dmFields);
    }

}
