package com.mdp.sys.tools;

import com.mdp.core.entity.LangTips;
import com.mdp.core.err.BizException;

import java.util.HashMap;
import java.util.Map;

public class QxTools {
    static Map<String,String> rolesMap=new HashMap<>();
    static {
        rolesMap.put("SUPERADMIN","超级管理员");
        rolesMap.put("PLATFORMADMIN","平台管理员");
        rolesMap.put("DBA","数据库管理员");
        rolesMap.put("BRANCHADMIN","机构管理员");
    }

    /**
     * 检测机构管理员是否有权限操作某些角色
     * @param roleids
     */
    public static void assertDisBranchAdmOpRoles(String ...roleids){
        if(roleids==null){
            throw new BizException("roleids-required","角色编号不能为空");
        }
        for (String roleid : roleids) {
            String rid=roleid.toUpperCase();
            if(rolesMap.containsKey(rid)){
                String roleName=rolesMap.get(rid);
                throw new BizException(LangTips.errMsg("pub-role-dis-branchAdm-op","%s为%s,您无权操作",roleid,roleName));
            }
        }
    }
}
