package com.mdp.core.entity;

import com.mdp.core.MsgTplUtils;
import com.mdp.core.err.BizException;
import com.mdp.core.utils.LogUtils;
import org.springframework.util.StringUtils;

/**
 * 语言转换
 */
public class LangTips extends Tips {



    static LangTips msg(boolean isOk,String tipscode,String defaultMsgTpl,Object...msgVars){

        if(StringUtils.hasText(tipscode)){
            LangTips tips=new LangTips();
            tips.setIsOk(isOk);
            String tpl=getMsgTplByTipscodeAndSiteType(tipscode,defaultMsgTpl);
            String msg=String.format(tpl,msgVars);
            tips.put("msg",msg);
            tips.put("tipscode",tipscode);
            tips.getAndPutMdcNoToTips();
            return tips;
        }else{
            LangTips tips=new LangTips();
            tips.setIsOk(isOk);
            tips.put("msg",String.format(defaultMsgTpl,msgVars));
            tips.getAndPutMdcNoToTips();
            return tips;
        }
    };
    public static LangTips fromTips(Tips tips){
        if(tips instanceof LangTips){
            return (LangTips)tips;
        }
        LangTips langTips=null;
        if(!tips.isOk()){
            langTips= LangTips.errMsg(tips.getTipscode(),tips.getMsg(),tips.getMsgVars());
        }else{
            langTips= LangTips.okMsg(tips.getTipscode(),tips.getMsg(),tips.getMsgVars());
        }
        String reqNo= (String) tips.get(LogUtils.REQ_NO_NAME);
        String gloNo= (String) tips.get(LogUtils.GLO_NO_NAME);
        if(!StringUtils.isEmpty(reqNo)) {
            langTips.put(LogUtils.REQ_NO_NAME, reqNo);
        }
        if(!StringUtils.isEmpty(gloNo)) {
            langTips.put(LogUtils.GLO_NO_NAME, gloNo);
        }
        return langTips;
    };

    public static LangTips fromBizException(BizException e){

        Tips tips=e.getTips();
        if(tips instanceof LangTips){
            return (LangTips) tips;
        }
       return LangTips.fromTips(tips);
    };
    public static LangTips okMsg(){
        return msg(true,"ok","成功");
    }
    public static LangTips okMsg(String tipscode,String defaultMsgTpl,Object...msgVars){
        return msg(true,tipscode,defaultMsgTpl,msgVars);
    };

    public static LangTips errMsg(String tipscode,String defaultMsgTpl,Object...msgVars){
        return msg(false,tipscode,defaultMsgTpl,msgVars);
    };
    public static LangTips errMsg(){
        return msg(false,"err","失败");
    };
    public LangTips setOkMsg(String tipscode,String defaultMsgTpl,Object...msgVars){
        if(StringUtils.hasText(tipscode)){
            setIsOk(true);
            String tpl=getMsgTplByTipscodeAndSiteType(tipscode,defaultMsgTpl);
            String msg=String.format(tpl,msgVars);
            this.put("msg",msg);
            this.put("tipscode",tipscode);
        }else{
            setIsOk(true);
            String msg=String.format(defaultMsgTpl,msgVars);
            this.put("msg",msg);
            this.remove("tipscode");
        }
        return this;
    }
    public LangTips setErrMsg(String tipscode,String defaultMsgTpl,Object...msgVars){
        if(StringUtils.hasText(tipscode)){
            setIsOk(false);
            String tpl=getMsgTplByTipscodeAndSiteType(tipscode,defaultMsgTpl);
            String msg=String.format(tpl,msgVars);
            this.put("msg",msg);
            this.put("tipscode",tipscode);
        }else{
            setIsOk(false);
            String msg=String.format(defaultMsgTpl,msgVars);
            this.put("msg",msg);
            this.remove("tipscode");
        }
        return this;
    }

    public static String transMsg(String tipscode,String defaultTpl,Object...msgVars){
        if(StringUtils.hasText(tipscode)){
            String tpl=getMsgTplByTipscodeAndSiteType(tipscode,defaultTpl);
            String msg=String.format(tpl,msgVars);
            return msg;
        }else{
            return String.format(defaultTpl,msgVars);
        }
    };
    public static String getMsgTplByTipscodeAndSiteType(String tipscode,String defaultTpl){
        if(!StringUtils.hasText(tipscode)){
            return defaultTpl;
        }else {
                String msg= MsgTplUtils.getMsgTpl(tipscode);
                if(StringUtils.isEmpty(msg)){
                    return defaultTpl;
                }else {
                    return msg;
                }
            }
        }
    public LangTips put(String key,Object value){
        super.put(key,value);
        return this;
    }
}
