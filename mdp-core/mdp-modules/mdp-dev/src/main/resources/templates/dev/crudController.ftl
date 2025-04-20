package ${largeModulePackage}.ctrl;

import java.util.*;
import java.util.stream.Collectors;

import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.*;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.entity.Result;
import com.mdp.core.query.QueryTools;
import static com.mdp.core.utils.BaseUtils.*;
import com.mdp.core.entity.LangTips;
import com.mdp.safe.client.entity.User;
import com.mdp.safe.client.utils.LoginUtils;
import com.mdp.swagger.ApiEntityParams;

import ${largeModulePackage}.service.${entityName}Service;
import ${largeModulePackage}.entity.${entityName};
/**
 * @author 唛盟开源 code-gen
 * @since ${.now?date}
 */
@RestController
@RequestMapping(value="/${apiPath}/${smallEntityName}")
@Api(tags={"${tableRemarks}-操作接口"})
public class ${entityName}Controller {
	
	static Logger logger =LoggerFactory.getLogger(${entityName}Controller.class);
	
	@Autowired
	private ${entityName}Service ${smallEntityName}Service;

	@Operation( summary =  "${tableRemarks}-查询列表")
	@ApiEntityParams(${entityName}.class)
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Result list${entityName}(  @RequestParam Map<String,Object> params){
			User user=LoginUtils.getCurrentUserInfo();
			QueryWrapper<${entityName}> qw = QueryTools.initQueryWrapper(${entityName}.class , params);
			IPage page=QueryTools.initPage(params);
			List<Map<String,Object>> datas = ${smallEntityName}Service.selectListMapByWhere(page,qw,params);
			return Result.ok("query-ok","查询成功").setData(datas).setTotal(page.getTotal());
	}
	

	@Operation( summary =  "${tableRemarks}-新增")
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Result add${entityName}(@RequestBody ${entityName} ${smallEntityName}) {
		 ${smallEntityName}Service.save(${smallEntityName});
         return Result.ok("add-ok","添加成功！").setData(${smallEntityName});
	}

	@Operation( summary =  "${tableRemarks}-删除")
	@RequestMapping(value="/del",method=RequestMethod.POST)
	public Result del${entityName}(@RequestBody ${entityName} ${smallEntityName}){
		${smallEntityName}Service.removeById(${smallEntityName});
        return Result.ok("del-ok","删除成功！");
	}

	@Operation( summary =  "${tableRemarks}-修改")
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public Result edit${entityName}(@RequestBody ${entityName} ${smallEntityName}) {
		${smallEntityName}Service.updateById(${smallEntityName});
        return Result.ok("edit-ok","修改成功！");
	}

    @Operation( summary =  "${tableRemarks}-批量修改某些字段" )
    @ApiEntityParams( value = ${entityName}.class, props={ }, remark = "${tableRemarks}", paramType = "body" )
	@RequestMapping(value="/editSomeFields",method=RequestMethod.POST)
	public Result editSomeFields(   @RequestBody Map<String,Object> params) {
            User user= LoginUtils.getCurrentUserInfo();
            ${smallEntityName}Service.editSomeFields(params);
            return Result.ok("edit-ok","更新成功");
	}

	@Operation( summary =  "${tableRemarks}-批量删除")
	@RequestMapping(value="/batchDel",method=RequestMethod.POST)
	public Result batchDel${entityName}(@RequestBody List<${entityName}> ${smallEntityName}s) {
	    User user= LoginUtils.getCurrentUserInfo();
        if(${smallEntityName}s.size()<=0){
            return Result.error("batchDel-data-err-0","请上送待删除数据列表");
        }
        <#if (primaryKeysList?size >1 )>
         List<${entityName}> datasDb=${smallEntityName}Service.listByIds(${smallEntityName}s.stream().map(i->map(<#list primaryKeysList as column> "${column.camelsColumnName}",i.get${column.bigCamelsColumnName}() <#if column_has_next>, </#if></#list>)).collect(Collectors.toList()));
        </#if>
      <#if (primaryKeysList?size <=1 )>
         List<${entityName}> datasDb=${smallEntityName}Service.listByIds(${smallEntityName}s.stream().map(i-><#list primaryKeysList as column> i.get${column.bigCamelsColumnName}() <#if column_has_next>, </#if></#list>).collect(Collectors.toList()));
      </#if>

        List<${entityName}> can=new ArrayList<>();
        List<${entityName}> no=new ArrayList<>();
        for (${entityName} data : datasDb) {
            if(true){
                can.add(data);
            }else{
                no.add(data);
            }
        }
        List<String> msgs=new ArrayList<>();
        if(can.size()>0){
            ${smallEntityName}Service.removeByIds(can);
            msgs.add(LangTips.transMsg("del-ok-num","成功删除%s条数据.",can.size()));
        }

        if(no.size()>0){
            msgs.add(LangTips.transMsg("not-allow-del-num","以下%s条数据不能删除:【%s】",no.size(),no.stream().map(i-><#list primaryKeysList as column> i.get${column.bigCamelsColumnName}() <#if column_has_next>+" "+</#if></#list>).collect(Collectors.joining(","))));
        }
        if(can.size()>0){
             return Result.ok(msgs.stream().collect(Collectors.joining()));
        }else {
            return Result.error(msgs.stream().collect(Collectors.joining()));
        }
	} 

	@Operation( summary =  "${tableRemarks}-根据主键查询一条数据")
    @RequestMapping(value="/queryById",method=RequestMethod.GET)
    public Result queryById(${entityName} ${smallEntityName}) {
        ${entityName} data = (${entityName}) ${smallEntityName}Service.getById(${smallEntityName});
        return Result.ok().setData(data);
    }

}
