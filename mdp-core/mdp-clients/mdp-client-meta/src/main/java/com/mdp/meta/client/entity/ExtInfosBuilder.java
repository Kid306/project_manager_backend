package com.mdp.meta.client.entity;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class ExtInfosBuilder {

    List<ExtInfo> extInfoList=new ArrayList<>();

    Map<String,ExtInfo> extInfosMap=new HashMap<>();

    public static ExtInfosBuilder build(String extInfo){
        ExtInfosBuilder build=new ExtInfosBuilder();
        build.parse(extInfo);
        return build;
    }

    public ExtInfosBuilder parse(String extInfoStr){
        if(StringUtils.hasText(extInfoStr)){
            JSONArray opList=JSON.parseArray(extInfoStr);
            if(opList==null)
                return this;
            for (Object o : opList) {
                ExtInfo extInfo=json2ExtInfo((JSONObject) o);
                extInfoList.add(extInfo);
                extInfosMap.put(extInfo.getId(),extInfo);
            }
        }
        return this;
    }
    public List<ExtInfo> list(){
        return this.extInfoList;
    }
    public Map<String,ExtInfo> map(){
        return this.extInfosMap;
    }

    public String getValue(String fieldId){
        ExtInfo extInfo=this.extInfosMap.get(fieldId);
        if(extInfo==null){
            return extInfo.getValue();
        }else{
            return null;
        }
    }
    public ExtInfosBuilder call(Function<ExtInfosBuilder,ExtInfosBuilder> f){
        return f.apply(this);
    }

    public static ExtInfo json2ExtInfo(JSONObject jso){
        ExtInfo extInfo=new ExtInfo();
        extInfo.setId((String) jso.get("id"));
        extInfo.setName((String) jso.get("name"));
        extInfo.setRemark(jso.getString("remark"));
        extInfo.setType(jso.getString("type"));
        extInfo.setUrl(jso.getString("url"));
        extInfo.setValue(jso.getString("value"));

        return extInfo;
    }
    public ExtInfo getExtInfo(String fieldId){
        return this.extInfosMap.get(fieldId);
    }
}
