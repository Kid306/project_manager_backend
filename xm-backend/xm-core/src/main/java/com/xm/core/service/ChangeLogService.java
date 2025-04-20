package com.xm.core.service;

import com.mdp.core.utils.EntityUtils;
import com.mdp.core.utils.FieldCb;
import com.mdp.meta.client.entity.ItemVo;
import com.mdp.meta.client.entity.Option;
import com.mdp.meta.client.service.ItemService;
import com.xm.core.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ChangeLogService {
   public static Logger log= LoggerFactory.getLogger(ChangeLogService.class);
    public static Map<Class<?>,Map<String,String>> fieldIdDictLinks=new HashMap<>();
     public  static Map<Class<?>,FieldCb> cbMap=new HashMap<>();

    public static ItemService itemService;

    @Autowired
    public void setItemService(ItemService itemService) {
        this.itemService = itemService;
    }

    public static <T> String getChangeLogsString(Map<String,Object> newParams, T entity, String... ignoreFieldIds){
        return EntityUtils.getChangeLogs(newParams,entity,getFieldCb(entity.getClass()),ignoreFieldIds).stream().collect(Collectors.joining(";"));
    }

    public static  <T> FieldCb getFieldCb(Class<T> tClass){
        if(!fieldIdDictLinks.containsKey(tClass)){
            return null;
        }
        if(cbMap.containsKey(tClass)){
            return cbMap.get(tClass);
        }
        FieldCb fieldCb= new FieldCb() {
            @Override
            public String call(String fieldId, String fieldName, Object newVal, Object oldVal) {
                Map<String,String> map=fieldIdDictLinks.get(tClass);
                if(!map.containsKey(fieldId)){
                    return null;
                }
                try {
                    ItemVo itemVo=itemService.getDict("all",map.get(fieldId));

                    Optional<Option> newOption=itemVo.getOptions().stream().filter(k->k.getId().equals(newVal)).findAny();
                    String newName=newOption.isPresent()?newOption.get().getName(): (String) newVal;
                    Optional<Option> oldOption=itemVo.getOptions().stream().filter(k->k.getId().equals(oldVal)).findAny();
                    String oldName=oldOption.isPresent()?oldOption.get().getName(): (String) oldVal;
                    String remark=String.format("字段【%s】,旧值【%s】,新值【%s】",fieldName,oldName,newName);
                    return remark;
                }catch (Exception e){
                    log.warn("翻译错误，但是不影响运行",e);
                    return null;
                }

            }
        };
        cbMap.put(tClass,fieldCb);
        return fieldCb;
    }
    @PostConstruct
    public void init(){
        Map<String,String> bugMap=new HashMap<>();
        bugMap.put("priority","priority");
        bugMap.put("solution","bugSolution");
        bugMap.put("bugSeverity","bugSeverity");
        bugMap.put("bugStatus","bugStatus");
        bugMap.put("repRate","bugRepRate");
        bugMap.put("bugReason","bugReason");
        bugMap.put("bugType","bugType");
        fieldIdDictLinks.put(XmQuestion.class,bugMap);

        Map<String,String> menuMap=new HashMap<>();
        menuMap.put("dlvl","demandLvl");
        menuMap.put("source","demandSource");
        menuMap.put("dtype","demandType");
        menuMap.put("priority","priority");
        menuMap.put("status","menuStatus");
        menuMap.put("dclass","dclass");
        fieldIdDictLinks.put(XmMenu.class,menuMap);

        Map<String,String> taskMap=new HashMap<>();
        taskMap.put("level","priority");
        taskMap.put("taskState","taskState");
        taskMap.put("planType","planType");
        taskMap.put("bizFlowState","bizFlowState");
        taskMap.put("ptype","ptype");
        fieldIdDictLinks.put(XmTask.class,taskMap);


        Map<String,String> projectMap=new HashMap<>();
        projectMap.put("workType","workType");
        projectMap.put("status","projectStatus");
        projectMap.put("xmType","projectType");
        fieldIdDictLinks.put(XmProject.class,projectMap);


        Map<String,String> productMap=new HashMap<>();
        productMap.put("pstatus","xmProductPstatus");
        fieldIdDictLinks.put(XmProduct.class,productMap);

        Map<String,String> iterationMap=new HashMap<>();
        iterationMap.put("istatus","iterationStatus");
        fieldIdDictLinks.put(XmIteration.class,iterationMap);


        Map<String,String> casedbMap=new HashMap<>();
        casedbMap.put("status","casedbStatus");
        fieldIdDictLinks.put(XmTestCasedb.class,casedbMap);

        Map<String,String> caseMap=new HashMap<>();
        caseMap.put("caseStatus","testCaseStatus");
        caseMap.put("caseType","caseType");
        caseMap.put("cpriority","priority");
        fieldIdDictLinks.put(XmTestCase.class,caseMap);


        Map<String,String> caseExecMap=new HashMap<>();
        caseExecMap.put("priority","priority");
        caseExecMap.put("execStatus","testStepTcode");
        fieldIdDictLinks.put(XmTestPlanCase.class,caseExecMap);


        Map<String,String> casePlanMap=new HashMap<>();
        casePlanMap.put("status","testPlanStatus");
        casePlanMap.put("testType","testType");
        casePlanMap.put("tcode","testPlanTcode");
        fieldIdDictLinks.put(XmTestPlan.class,casePlanMap);

        Map<String,String> kpiMap=new HashMap<>();
        kpiMap.put("kstatus","kstatus");
        kpiMap.put("ktype","ktype");
        kpiMap.put("calcType","calcType");
        kpiMap.put("kclass","kclass");
        kpiMap.put("dataLink","dataLink");
        fieldIdDictLinks.put(XmKpi.class,kpiMap);

        Map<String,String> riskMap=new HashMap<>();
        riskMap.put("rstatus","rstatus");
        riskMap.put("rtype","risk_type");
        riskMap.put("odds","odds");
        riskMap.put("impact","impact");
        riskMap.put("rsgy","rsgy");
        riskMap.put("rlvl","rlvl");
        fieldIdDictLinks.put(XmRisk.class,riskMap);

    }
}
