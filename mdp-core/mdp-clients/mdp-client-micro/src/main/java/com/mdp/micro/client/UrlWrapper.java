package com.mdp.micro.client;

import com.mdp.core.utils.LogUtils;

public class UrlWrapper {

    public static String urlWrapper(String url) {
        String gloNo= LogUtils.getGloNo(true);
        if(url.contains("?")) {
            url=url+"&gloNo="+gloNo;
        }else {
            url=url+"?gloNo="+gloNo;
        }
        return url;
    }
    public static String withApiGate(String url,String apiGate) {
        if(url.startsWith("www") || url.startsWith("http")){
            return url;
        }else{
            if("".equals(url)||url==null){
                return apiGate;
            }
            if(apiGate.endsWith("/")){
                if(url.startsWith("/")){
                    url=apiGate+url.substring(1);
                }else{
                    url=apiGate+url;
                }
            }else if(url.startsWith("/")){
                url=apiGate+url;
            }else{
                url=apiGate+"/"+url;
            }
        }
        return url;
    }
}
