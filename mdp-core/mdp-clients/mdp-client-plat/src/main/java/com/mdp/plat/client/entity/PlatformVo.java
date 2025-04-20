package com.mdp.plat.client.entity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mdp.core.utils.BaseUtils;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 组织 com  顶级模块 mdp 大模块 plat  小模块 <br> 
 * 实体 Platform所有属性名: <br>
 *	id,platformName,status,ctime,ltime,cuserid,cusername,logoUrl,platformTitle,keywords,minBuyAmount,autoConfirmReceipt,canBill,billContextList,cutStock,remarkJson,plusSales,evaluate,discountPct,usePriceGroup;<br>
 * 表 ADM.plat_platform plat_platform的所有字段名: <br>
 *	id,platform_name,status,ctime,ltime,cuserid,cusername,logo_url,platform_title,keywords,min_buy_amount,auto_confirm_receipt,can_bill,bill_context_list,cut_stock,remark_json,plus_sales,evaluate,discount_pct,use_price_group;<br>
 * 当前主键(包括多主键):<br>
 *	id;<br>
 */
@Schema(description="plat_platform")
public class PlatformVo  extends Platform implements java.io.Serializable   {
    Map<String,Object> extConfigMap=new HashMap<>();

    Map<String,ExtInfo> extInfosMap=new HashMap<>();

    /**
     * 下拉列表
     */
    List<ExtInfo> extInfoList=new ArrayList<>();

    public Object getExtConfig(String name){
        return extConfigMap.get(name);
    }
    public void parseExtConfigJson(){
        try {
            Map<String,Object> result=BaseUtils.objectMapper.readValue(super.getExtConfig(), HashMap.class);
            extConfigMap.putAll(result);
        }catch (Exception e){

        }
    }



    public void parseExtInfoList(){
        this.extInfoList=new ArrayList<>();
        if(StringUtils.hasText(getExtInfos())){
            JSONArray opList= JSON.parseArray(getExtInfos());
            for (Object o : opList) {
                ExtInfo extInfo=new ExtInfo();
                JSONObject jso=(JSONObject) o;
                extInfo.setId((String) jso.get("id"));
                extInfo.setName((String) jso.get("name"));
                extInfo.setRemark(jso.getString("remark"));
                extInfo.setType(jso.getString("type"));
                extInfo.setUrl(jso.getString("url"));
                extInfoList.add(extInfo);
                extInfosMap.put(extInfo.getId(),extInfo);
            }
        }
    }

    public ExtInfo getExtInfo(String id){
        return this.extInfosMap.get(id);
    }
}