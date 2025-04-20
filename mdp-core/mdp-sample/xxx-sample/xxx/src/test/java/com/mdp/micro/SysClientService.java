package com.mdp.micro;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mdp.core.entity.Tips;
import com.mdp.core.err.BizException;
import com.mdp.core.utils.BaseUtils;
import com.mdp.core.utils.ObjectTools;
import com.mdp.micro.client.CallBizService;
import com.mdp.mq.queue.Push;
import com.mdp.tpa.client.service.AppTpAuthService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SysClientService {
	Log log=LogFactory.getLog(SysClientService.class);
	@Autowired
    CallBizService callBizService;
	
	@Autowired
	ObjectMapper om;

    @Autowired
    Push push;
    
    @Autowired
    AppTpAuthService tpAuthService;
	
	@Value("${mdp.api-gate:http://gate}")
	String apiGate="";//http://localhost:7041


	  /**
     * urlPath访问路径
     * params请求的参数 
     * post请求
     **/
    public SysUser claim(String inviteId, SysUserTpa tpa, boolean tpadbExists){
        String url = apiGate + "/lcode/mdp/sys/userTpa/claim";
         Map<String,Object> params=new HashMap<>();
        params.put("inviteId",inviteId);
        params.put("tpa",tpa);
        params.put("tpadbExists",tpadbExists);
        Map<String, Object> result  = callBizService.postForMap(url, params);

        log.debug(JSON.toJSONString("claim-result:"+result));
        Tips tips = (Tips) result.get("tips");
        if(tips.isOk()==false){
            throw new BizException(tips);
        }else{
            return BaseUtils.fromMap((Map<String, Object>) result.get("data"),SysUser.class);
        }
    }

    public SysUser bind(String inviteId, SysUserTpa tpa, boolean tpadbExists) {
        String url = apiGate + "/lcode/mdp/sys/userTpa/bind";
        Map<String,Object> params=new HashMap<>();
        params.put("inviteId",inviteId);
        params.put("tpa",tpa);
        params.put("tpadbExists",tpadbExists);
        Map<String, Object> result  = callBizService.postForMap(url, params);
        log.debug(JSON.toJSONString("claim-result:"+result));
        Tips tips = (Tips) result.get("tips");
        if(tips.isOk()==false){
            throw new BizException(tips);
        }else{
            return BaseUtils.fromMap((Map<String, Object>) result.get("data"),SysUser.class);
        }
    }

    public SysUser invite(String inviteId, SysUserTpa tpa, boolean tpadbExists) {
        String url = apiGate + "/lcode/mdp/sys/userTpa/invite";
        Map<String,Object> params=new HashMap<>();
        params.put("inviteId",inviteId);
        params.put("tpa",tpa);
        params.put("tpadbExists",tpadbExists);
        Map<String, Object> result  = callBizService.postForMap(url, params);
        log.debug(JSON.toJSONString("claim-result:"+result));

        Tips tips = (Tips) result.get("tips");
        if(tips.isOk()==false){
            throw new BizException(tips);
        }else{
            return BaseUtils.fromMap((Map<String, Object>) result.get("data"),SysUser.class);
        }
    }

    public SysUser registerUserByTpa(String inviteId,SysUserTpa tpa, boolean tpadbExists) {
        String url = apiGate + "/lcode/mdp/sys/userTpa/registerUserByTpa";
        Map<String,Object> params=new HashMap<>();
        params.put("inviteId",inviteId);
        params.put("tpa",tpa);
        params.put("tpadbExists",tpadbExists);
        Map<String, Object> result  = callBizService.postForMap(url, params);
        log.debug(JSON.toJSONString("claim-result:"+result));
        Tips tips = (Tips) result.get("tips");
        if(tips.isOk()==false){
            throw new BizException(tips);
        }else{
            return BaseUtils.fromMap((Map<String, Object>) result.get("data"),SysUser.class);
        }
    }

    public List<SysUserTpa> getSysUserTpaByUserid( String userid,String appType,String appid) {
        String url = apiGate + "/lcode/mdp/sys/userTpa/listByUserid";
        Map<String,Object> params=new HashMap<>();
        params.put("userid",userid);
        if(ObjectTools.isNotEmpty(appType)){
            params.put("appType",appType);
        }
        if(ObjectTools.isNotEmpty(appid)){
            params.put("appid",appid);
        }
        Map<String, Object> result  = callBizService.getForMap(url, params);
        Tips tips = (Tips) result.get("tips");
        if(tips.isOk()==false){
            throw new BizException(tips);
        }else{
            List<Map<String,Object>> data= (List<Map<String, Object>>) result.get("data");
            if(data==null){
                return new ArrayList<>();
            }
            List<SysUserTpa> l=data.stream().map(k->BaseUtils.fromMap(k,SysUserTpa.class)).collect(Collectors.toList());
            return l;
        }
    }

    public SysUserTpa getSysUserTpaByOpenid(String openid) {
        String url = apiGate + "/lcode/mdp/sys/userTpa/queryById?openid={openid}";
        Map<String,Object> params=new HashMap<>();
        params.put("openid",openid);
        Map<String, Object> result  = callBizService.getForMap(url, params);
        Tips tips = (Tips) result.get("tips");
        if(tips.isOk()==false){
            throw new BizException(tips);
        }else{
            return BaseUtils.fromMap((Map<String, Object>) result.get("data"),SysUserTpa.class);
        }
    }
}
