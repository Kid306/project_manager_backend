package com.xm.core;


import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service

/**
 *
 权限码0,1,2,3,4,5,67,8,9，逗号分割
 共10位,不定长，暂时只启用前2个位
 第0位代表计划及任务指派及crud权限：
 0-代表不限制,1-同组织，2-同项目组（默认），3-同小组
 第1位代表计划及任务指派及crud时是否检查上下级关系：0-否（默认），1是
 */
public class QxTool {

    /**
     * 获取
     第0位代表计划及任务指派及crud权限：
     0-代表不限制,1-同组织，2-同项目组（默认），3-同小组
     * @param qxCode
     * @return
     */
    public static String getProjectScopeQx(String qxCode,int teamType){
        String def="2";
        if(StringUtils.isEmpty(qxCode)){
            return def;
        }else{
                String[] qxCodeArr=qxCode.split(",");
                if(qxCodeArr.length<(teamType*2+2)){
                    return def;
                }else{
                    return qxCodeArr[teamType*2];
                }
        }
    }
    /**
     * 获取
     第1位代表计划及任务指派及crud时是否检查上下级关系：0-否（默认），1是
     * @param qxCode
     * @return
     */
    public static String getProjectTransmitQx(String qxCode,int teamType){
        String def="0";
        if(StringUtils.isEmpty(qxCode)){
            return def;
        }else{
            String[] qxCodeArr=qxCode.split(",");
            if(qxCodeArr.length<(teamType*2+2)){
                return def;
            }else{
                return qxCodeArr[teamType*2+1];
            }
        }
    }
    /**
     权限码0,1,2,3,4,5,67,8,9，逗号分割
     共10位,不定长，暂时只启用前6个位
     第0位代表需求指派及crud权限：
     0-代表不限制,1-同组织，2-同项目组（默认），3-同小组
     第1位代表需求指派及crud时是否检查上下级关系：0-否（默认），1是
     第2位代表测试相关(包括测试用例、测试库、测试计划、测试报告)指派及crud权限同第0位，
     第3位代表测试相关(包括测试用例、测试库、测试计划、测试报告)指派及crud时是否检查上下级关系，同第1位
     第4位代表迭代指派及crud时权限，同第0位
     第5位代表迭代指派及crud时是否检查上下级关系，同第1位
     * @param qxCode
     * @param teamType 0 代表需求相关，1代表测试，2代表迭代
     * @return
     */
    public static String getProductScopeQx(String qxCode,int teamType){
        String def="2";
        if(StringUtils.isEmpty(qxCode)){
            return def;
        }else{
            String[] qxCodeArr=qxCode.split(",");
            if(qxCodeArr.length<(teamType*2+2)){
                return def;
            }else{
                return qxCodeArr[teamType*2];
            }
        }
    }
    /**
     权限码0,1,2,3,4,5,67,8,9，逗号分割
     共10位,不定长，暂时只启用前6个位
     第0位代表需求指派及crud权限：
     0-代表不限制,1-同组织，2-同项目组（默认），3-同小组
     第1位代表需求指派及crud时是否检查上下级关系：0-否（默认），1是
     第2位代表测试相关(包括测试用例、测试库、测试计划、测试报告)指派及crud权限同第0位，
     第3位代表测试相关(包括测试用例、测试库、测试计划、测试报告)指派及crud时是否检查上下级关系，同第1位
     第4位代表迭代指派及crud时权限，同第0位
     第5位代表迭代指派及crud时是否检查上下级关系，同第1位
     * @param qxCode
     * @param teamType 0 代表需求相关，1代表测试，2代表迭代
     * @return
     */
    public static String getProductTransmitQx(String qxCode,int teamType){
        String def="0";
        if(StringUtils.isEmpty(qxCode)){
            return def;
        }else{
            String[] qxCodeArr=qxCode.split(",");
            if(qxCodeArr.length<(teamType*2+2)){
                return def;
            }else{
                return qxCodeArr[teamType*2+1];
            }
        }
    }

}
