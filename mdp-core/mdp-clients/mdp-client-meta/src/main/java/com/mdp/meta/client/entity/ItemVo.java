package com.mdp.meta.client.entity;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemVo extends Item{

    /**
     * 下拉列表
     */
    List<Option> options;



    /**
     * 下拉列表
     */
    List<ExtInfo> extInfoList=new ArrayList<>();

    Map<String,ExtInfo> extInfosMap=new HashMap<>();


    public List<ExtInfo> getExtInfoList() {
        return extInfoList;
    }

    public void setExtInfoList(List<ExtInfo> extInfoList) {
        this.extInfoList = extInfoList;
    }


    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    public static ItemVo build(Item item){
         ItemVo itemVo=new ItemVo();
        BeanUtils.copyProperties(item,itemVo);
        itemVo.parse();
         return itemVo;
    }

    public void parse(){
        this.extInfoList=new ArrayList<>();
        this.extInfosMap=new HashMap<>();
        if(StringUtils.hasText(getExtInfos())){
            JSONArray opList=JSON.parseArray(getExtInfos());
            if(opList==null)
                return;
            for (Object o : opList) {
                ExtInfo extInfo=json2ExtInfo((JSONObject) o);
                extInfoList.add(extInfo);
                extInfosMap.put(extInfo.getId(),extInfo);
            }
        }
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

    public static List<ExtInfo> parseExtInfosList(String extInfos){
        List<ExtInfo> extInfoList=new ArrayList<>();

        if(StringUtils.hasText(extInfos)){
            JSONArray opList=JSON.parseArray(extInfos);
            if(opList==null)
                return extInfoList;
            for (Object o : opList) {
                extInfoList.add(json2ExtInfo((JSONObject) o));
            }
        }
        return extInfoList;
    }
    public static Map<String,ExtInfo> parseExtInfosMap(String extInfos){
        Map<String,ExtInfo> extInfosMap=new HashMap<>();

        if(StringUtils.hasText(extInfos)){
            JSONArray opList=JSON.parseArray(extInfos);
            if(opList==null)
                return extInfosMap;
            for (Object o : opList) {
                ExtInfo extInfo=json2ExtInfo((JSONObject) o);
                extInfosMap.put(extInfo.getId(),extInfo);
            }
        }
        return extInfosMap;
    }

    public ExtInfo getExtInfo(String id){
        return this.extInfosMap.get(id);
    }
}
