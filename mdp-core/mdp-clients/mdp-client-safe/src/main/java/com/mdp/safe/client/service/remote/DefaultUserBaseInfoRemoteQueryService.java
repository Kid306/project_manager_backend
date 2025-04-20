package com.mdp.safe.client.service.remote;

import com.mdp.core.entity.Tips;
import com.mdp.core.err.BizException;
import com.mdp.core.utils.BaseUtils;
import com.mdp.micro.client.CallBizService;
import com.mdp.safe.client.entity.DeptPostRole;
import com.mdp.safe.client.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DefaultUserBaseInfoRemoteQueryService implements UserBaseInfoRemoteQueryService {


    @Value("${mdp.user.base.info.uri.getUserByUserid:/lcode/user/baseinfo}")
    String getUserByUseridUri="";

    @Value("${mdp.user.base.info.uri.queryByUserloginid:/lcode/user/queryByUserloginid}")
    String queryByUserloginidUri="";

    @Value("${mdp.user.base.info.uri.getUserDeptPostRoles:/lcode/user/base/info/getUserDeptPostRoles}")
    String getUserDeptPostRolesUri="";


    @Autowired
    CallBizService callBizService;

    @Override
    public List<User> queryByUserloginid(String userloginid,String idType, Map<String, Object> extParams) {
        String url= String.format(queryByUserloginidUri+"?userloginid=%s&idType=%s",userloginid,idType);
        Map<String,Object> result=callBizService.getForMap(url,new HashMap<>());
        Tips tips=BaseUtils.mapToTips(result);
        if(tips.isOk()){
             List<Map<String,Object>> datas= (List<Map<String, Object>>) result.get("data");
             if(datas==null || datas.size()==0){
                 return null;
             }else{
                 return datas.stream().map(i->BaseUtils.fromMap(i,User.class)).collect(Collectors.toList());
             }
        }else{
            throw new BizException(tips);
        }
    }

    @Override
    public User getUserByUserid(String userid, Map<String,Object> extParams) {
        Map<String,Object> result=callBizService.getForMap(getUserByUseridUri+"?userid="+userid,new HashMap<>());
        Tips tips=BaseUtils.mapToTips(result);
        if(tips.isOk()){
            return BaseUtils.fromMap((Map<String, Object>) result.get("data"),User.class);
        }
        return null;
    }

    @Override
    public List<DeptPostRole> getUserDeptPostRoles(String userid, Map<String,Object> extParams) {
        return null;
    }

    public CallBizService getCallBizService() {
        return callBizService;
    }

    public void setCallBizService(CallBizService callBizService) {
        this.callBizService = callBizService;
    }
}
