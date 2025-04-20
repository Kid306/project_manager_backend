package com.mdp.core.config;

import com.mdp.core.MsgTplUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.Properties;

/**
 * 格式 mdp.msg.tpl.模板编号@语言=消息模板
 * 可变参数用%s代替
 * 如：
 * mdp.msg.tpl.tipscode1@cn=%s先生，您好，恭喜您下单成功,下单时间%s
 * mdp.msg.tpl.tipscode2@en=hello,MR %s,your order is success,order time %s
 */
@Configuration
@ConfigurationProperties(prefix = "mdp.msg")
@PropertySource(value = "classpath:msgtpl.properties")
public class MsgTplProperties {

     Properties tpl=new Properties();

    @Value(value = "${mdp.site-type:cn}")
    public  String siteType="cn";




    public void setTpl(Properties tpl) {
        this.tpl = tpl;
        MsgTplUtils.setTpl(tpl);
        MsgTplUtils.setSiteType(siteType);
    }

    public  String getMsgTpl(String tipscode){
        if(tipscode.contains("@")){
            return (String) tpl.get(tipscode);
        }else {
            return (String) tpl.get(tipscode+"@"+siteType);
        }
    }
}
