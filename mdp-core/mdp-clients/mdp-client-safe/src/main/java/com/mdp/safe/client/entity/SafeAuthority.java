package com.mdp.safe.client.entity;

import com.mdp.core.utils.NumberUtil;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.StringUtils;

import java.util.*;

public class SafeAuthority extends HashMap<String,Object> implements GrantedAuthority {
    private static final long serialVersionUID = 540L;
    //private  String authority,name,dataLvl,xxx等

    public SafeAuthority(){

    }

    public SafeAuthority(String roleid){
        super.put("roleid",roleid);
    }
    public SafeAuthority(String roleid, String rolename) {
        super.put("roleid",roleid);
        super.put("rolename",rolename);
    }
    public SafeAuthority(String roleid, String rolename,Integer dataLvl) {
        super.put("roleid",roleid);
        super.put("rolename",rolename);
        super.put("dataLvl",dataLvl);
    }

    public void setAuthority(String roleid){
        super.put("roleid",roleid);
    }
    public String getAuthority() {
        return (String) get("roleid");
    }
    public String getRolename(){
        return (String) super.get("rolename");
    }
    public void setRolename(String rolename){
        super.put("rolename",rolename);
    }
    public Integer getDataLvl(){
        return NumberUtil.getInteger(super.get("dataLvl"),null);
    }
    public void setDataLvl(Integer dataLvl){
        super.put("dataLvl",dataLvl);
    }

    public static String toRolesString(Collection<GrantedAuthority> gas){
        String roles="";
        List<String> roleList=new ArrayList<>();
        for (GrantedAuthority grantedAuthority : gas) {
            if(grantedAuthority instanceof SafeAuthority){
                roleList.add(((SafeAuthority)grantedAuthority).toSimpleString(grantedAuthority));
            }else {
                roleList.add(grantedAuthority.getAuthority());
            }
        }
        roles =String.join("|",roleList );
        return roles;
    }


    public static Collection<GrantedAuthority> toGasFromRolesString(String rolesStr){
        if(!StringUtils.hasText(rolesStr)){
            return null;
        }
        String[] roles=rolesStr.split("\\|",-1);
        Collection<GrantedAuthority> gas=new HashSet<>();
        for (int i = 0; i < roles.length; i++) {
            SafeAuthority safeAuthority=SafeAuthority.fromSimpleString(roles[i]);
            gas.add(safeAuthority);
        }
        return gas;

    }


    /**
     * 返回逗号分隔的属性值字符串
     * @return roleid,rolename,dataLvl
     */
    public static String toSimpleString(GrantedAuthority ga){
        List<String> strs=new ArrayList<>();
        String authority=ga.getAuthority();
        strs.add(authority);
        if(ga instanceof SafeAuthority){
            SafeAuthority sa=(SafeAuthority)ga;
            String rolename=sa.getRolename();
            Integer dataLvl=sa.getDataLvl();
            if(StringUtils.hasText(rolename)){
                strs.add(rolename);
            }
            if(dataLvl!=null){
                strs.add(dataLvl+"");
            }
        }

        return String.join(",",strs);
    }

    public static SafeAuthority fromSimpleString(String roleStr){

        if(StringUtils.hasText(roleStr)){
            SafeAuthority sa=new SafeAuthority();
            String[] aa=roleStr.split(",",-1);
            if(aa.length==1){
                sa.setAuthority(aa[0]);
            }else if(aa.length==2){
                sa.setAuthority(aa[0]);
                sa.setRolename(aa[1]);
            }else if(aa.length>2){
                sa.setAuthority(aa[0]);
                sa.setRolename(aa[1]);
                sa.setDataLvl(NumberUtil.getInteger(aa[2]));
            }
            return sa;
        }
        return null;


    }
}
