package com.mdp.login.integration;


import java.util.HashMap;
import java.util.Map;

/**
 * @author chenyc
 * @date 2018-3-30
 **/
public class IntegrationParams {

    private String authType;
    private String userloginid;
    /**
     * userType:cust客户 staff员工
     */
    private String userType;

    private Map<String,Object> datas=new HashMap<>();

    private Map<String,String[]> authParameters=new HashMap<>();

    public String getAuthParameter(String paramter){
        String[] values = this.authParameters.get(paramter);
        if(values != null && values.length > 0){
            return values[0];
        }
        return null;
    }

    public String getAuthType() {
        return authType;
    }

    public void setAuthType(String authType) {
        this.authType = authType;
    }

    public String getUserloginid() {
        return userloginid;
    }

    public void setUserloginid(String userloginid) {
        this.userloginid = userloginid;
    }

    public Map<String, Object> getDatas() {
        return datas;
    }

    public void setDatas(Map<String, Object> datas) {
        this.datas = datas;
    }

    public void put(String key,Object value){
        this.datas.put(key,value);
    }

    public Object get(String key){
        return this.datas.get(key);
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public void setAuthParameters(Map<String, String[]> authParameters) {
        this.authParameters = authParameters;
    }

}
