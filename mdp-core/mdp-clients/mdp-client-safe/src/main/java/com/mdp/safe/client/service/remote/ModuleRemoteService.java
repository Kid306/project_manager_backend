package com.mdp.safe.client.service.remote;

import com.mdp.core.entity.Tips;
import com.mdp.core.utils.BaseUtils;
import com.mdp.micro.client.CallBizService;
import com.mdp.safe.client.entity.Module;
import com.mdp.safe.client.entity.ModuleBranch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 远程获取资源的接口
 * 通过容器自动化注入，如果没有被初始化，将使用默认值
 * @see DefaultUserResourceRemoteService
 *
 */
@Service
public class ModuleRemoteService {


	@Value("${mdp.auth.module.load-uri:/lcode/mdp/menu/menuModule/list}")
	String loadUri="";
	@Value("${mdp.auth.module-branch.load-uri:/lcode/mdp/menu/menuModuleBranch/list}")
	String loadModuleBranchUri="";

	@Autowired
	CallBizService callBizService;

	Logger log= LoggerFactory.getLogger(ModuleRemoteService.class);


	public Module getModule(String moduleId){
		Map<String,Object> result= callBizService.getForMap(loadUri+"?id="+moduleId );
		Tips tips= (Tips) result.get("tips");
		if(tips.isOk()){
 			List<Map<String, Object>> datas = (List<Map<String, Object>>) result.get("data");
			if(datas!=null && datas.size()>0){
					Module module=BaseUtils.fromMap(datas.get(0),Module.class);
 					return module;
			}
		}
		return null;
	}


	public ModuleBranch getModuleBranch(String branchId, String moduleId) {
		Map<String,Object> result= callBizService.getForMap(loadModuleBranchUri+"?moduleId="+moduleId+"&branchId="+branchId );
		Tips tips= (Tips) result.get("tips");
		if(tips.isOk()){

			List<Map<String, Object>> datas = (List<Map<String, Object>>) result.get("data");
			if(datas!=null && datas.size()>0){
				ModuleBranch module=BaseUtils.fromMap(datas.get(0),ModuleBranch.class);
				return module;
			}
		}
		return null;
	}
	@PostConstruct
	public void init(){
		log.info("远程读取模块配置信息：");
		log.info(String.format("读取模块接口地址配置 属性【%s】,当前值【%s】,默认值【%s】","mdp.auth.module.load-uri",this.loadUri,"/lcode/mdp/menu/menuModule/list"));
		log.info(String.format("读取企业开通的模块接口地址配置 属性【%s】,当前值【%s】,默认值【%s】","mdp.auth.module-branch.load-uri",this.loadModuleBranchUri,"/lcode/mdp/menu/menuModuleBranch/list"));
	}

	public List<Module> getFreeModules() {
		Map<String,Object> result= callBizService.getForMap(loadUri+"?billMode=0" );
		Tips tips= (Tips) result.get("tips");
		if(tips.isOk()){
			List<Map<String, Object>> datas = (List<Map<String, Object>>) result.get("data");
			List<Module> moduleList=new ArrayList<>();
			if(datas!=null && datas.size()>0){
				Module module=BaseUtils.fromMap(datas.get(0),Module.class);
				moduleList.add(module);
 			}
			return moduleList;
		}
		return new ArrayList<>();
	}
}
