package com.mdp.sys.ctrl;

import com.mdp.qx.HasQx;
import io.swagger.annotations.Api;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * url编制采用rest风格,如对ADMIN.sys_qx 权限定义的操作有增删改查,对应的url分别为:<br>
 *  新增: sys/qx/add <br>
 *  查询: sys/qx/list<br>
 *  模糊查询: sys/qx/listKey<br>
 *  修改: sys/qx/edit <br>
 *  删除: sys/qx/del<br>
 *  批量删除: sys/qx/batchDel<br>
 * 组织 com.qqkj  顶级模块 mdp 大模块 sys 小模块 <br>
 * 实体 Qx 表 ADMIN.sys_qx 当前主键(包括多主键): qx_id; 
 ***/
@RestController("mdp.sys.defaultQxRegistController")
@RequestMapping(value="/*/sys/qx/regist")
@Api(tags={"系统自动注册公共权限到数据库"})
public class DefaultQxRegistController {
	
	static Log logger=LogFactory.getLog(DefaultQxRegistController.class);

	
	@HasQx(value = "sys_role_public_op",name = "公共角色操作",moduleName = "组织管理",moduleId = "mdp-sys")
	@RequestMapping(value="/roleOp",method=RequestMethod.GET)
	public void roleOp(Map<String,Object> qx){

	}
	@HasQx(value = "common_query",name = "普通查询",moduleName = "通用",moduleId = "common")
	@RequestMapping(value="/commonQuery",method=RequestMethod.GET)
	public void commonQuery(Map<String,Object> qx){

	}
	@HasQx(value = "common_del",name = "普通删除",moduleName = "通用",moduleId = "common")
	@RequestMapping(value="/common_del",method=RequestMethod.GET)
	public void commonDel(Map<String,Object> qx){

	}
	@HasQx(value = "common_edit",name = "普通修改",moduleName = "通用",moduleId = "common")
	@RequestMapping(value="/commonEdit",method=RequestMethod.GET)
	public void commonEdit(Map<String,Object> qx){

	}
	@HasQx(value = "common_batch_edit",name = "普通批量修改",moduleName = "通用",moduleId = "common")
	@RequestMapping(value="/commonBatchEdit",method=RequestMethod.GET)
	public void commonBatchEdit(Map<String,Object> qx){

	}
	@HasQx(value = "common_batch_del",name = "普通批量删除",moduleName = "通用",moduleId = "common")
	@RequestMapping(value="/commonBatchDel",method=RequestMethod.GET)
	public void commonBatchDel(Map<String,Object> qx){

	}

	@HasQx(value = "common_batch_add",name = "普通批量新增",moduleName = "通用",moduleId = "common")
	@RequestMapping(value="/commonBatchAdd",method=RequestMethod.GET)
	public void commonBatchAdd(Map<String,Object> qx){

	}
	@HasQx(value = "common_import",name = "普通导入",moduleName = "通用",moduleId = "common")
	@RequestMapping(value="/commonImport",method=RequestMethod.GET)
	public void commonImport(Map<String,Object> qx){

	}
	@HasQx(value = "common_export",name = "普通导出",moduleName = "通用",moduleId = "common")
	@RequestMapping(value="/commonExport",method=RequestMethod.GET)
	public void commonExport(Map<String,Object> qx){

	}

}
