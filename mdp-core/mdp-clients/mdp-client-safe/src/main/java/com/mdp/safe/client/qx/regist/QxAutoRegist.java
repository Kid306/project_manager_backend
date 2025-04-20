package com.mdp.safe.client.qx.regist;

import cn.hutool.extra.spring.SpringUtil;
import com.mdp.core.entity.Tips;
import com.mdp.micro.client.CallBizService;
import com.mdp.qx.HasQx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.lang.reflect.Method;
import java.util.*;

@Component
public class QxAutoRegist  {
    @Autowired
    CallBizService callBizServie;

    @Value("${mdp.auth.qx-regist-url:}")
    String qxRegistUrl;

    Logger log= LoggerFactory.getLogger(QxAutoRegist.class);

    public List<HasQx>  getHasQxList(){
        List<HasQx> list=new ArrayList<>();
         Map<String,Object> beans = SpringUtil.getApplicationContext().getBeansWithAnnotation(Component.class);
         if (beans != null) {
            Iterator<Map.Entry<String, Object>> entries = beans.entrySet().iterator();
            while(entries.hasNext()){
                Map.Entry<String, Object> entry = entries.next();
                String key = entry.getKey();
                Object bean = entry.getValue();
                Method[] methods2=bean.getClass().getMethods();
                for (Method method : methods2) {
                    HasQx mhasQx= AnnotationUtils.findAnnotation(method,HasQx.class);
                    if(mhasQx!=null){
                        list.add(mhasQx);
                    }
                }
            }
        }
        return list;
    }

    /**
     * 自动向权限服务器注册权限信息
     */
    public void autoRegistToDataBase(){
        List<HasQx> hasQxList=this.getHasQxList();
        if(hasQxList==null || hasQxList.size()==0){
            return;
        }
        Map<String,HasQx> map=new HashMap<>();
        for (HasQx hasQx : hasQxList) {
            HasQx hasQx1=map.get(hasQx.value());
            if(hasQx1==null){
                map.put(hasQx.value(),hasQx);
            }else{
                if(StringUtils.isEmpty(hasQx1.name())){
                    if(!StringUtils.isEmpty(hasQx.name())){
                        map.put(hasQx.value(),hasQx);
                    }
                }
            }
        }
        if(map.isEmpty()){
            return;
        }

        List<Map<String,Object>> qxMapList=new ArrayList<>();
        for (HasQx qx : map.values()) {
            Map<String,Object> qxMap=new HashMap<>();
            qxMap.put("qxId",qx.value());
            qxMap.put("qxName",qx.name());
            qxMap.put("moduleId",qx.moduleId());
            qxMap.put("moduleName",qx.moduleName());
            qxMapList.add(qxMap);
        }
        if(StringUtils.hasText(qxRegistUrl)){
            log.info("准备自动注册权限信息--远程地址【{}】--start----",qxRegistUrl);
            Map<String,Object> result=callBizServie.postForMap(qxRegistUrl,qxMapList);
            Tips tips= (Tips) result.get("tips");
            log.info("自动注册权限:  是否成功：【{}】,信息【{}】---------------end-----------",tips.isOk(),tips.getMsg());
        }
    }

    @PostConstruct
    /**
     * 需要等到微服务注册到注册中心后才能调取成功
     */
    public void autoRegist(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(50000);
                    QxAutoRegist.this.autoRegistToDataBase();
                } catch (Exception e) {
                    try {
                        Thread.sleep(50000);
                        QxAutoRegist.this.autoRegistToDataBase();
                    } catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }
                }

            }
        }).start();
    }

}
