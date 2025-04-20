package com.mdp.core;

import java.util.Properties;

public class MsgTplUtils {

    public static Properties tpl=new Properties();

    public static String getSiteType() {
        return siteType;
    }

    public static void setSiteType(String siteType) {
        MsgTplUtils.siteType = siteType;
    }

    public static String siteType="cn";

    public static void setTpl(Properties tpl) {
        MsgTplUtils.tpl = tpl;
    }

    public static String getMsgTpl(String tipscode){
        if(tpl==null){
            return null;
        }
        if(tipscode.contains("@")){
            return (String) tpl.get(tipscode);
        }else {
            return (String) tpl.get(tipscode+"@"+siteType);
        }
    }
}
