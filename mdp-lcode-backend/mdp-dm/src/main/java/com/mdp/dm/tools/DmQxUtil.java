package com.mdp.dm.tools;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mdp.core.entity.DmField;
import com.mdp.core.entity.DmTable;
import com.mdp.core.entity.LangTips;
import com.mdp.core.err.BizException;
import com.mdp.core.utils.ObjectTools;
import com.mdp.safe.client.entity.Qx;
import com.mdp.safe.client.entity.User;
import com.mdp.safe.client.utils.LoginUtils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class DmQxUtil {
    public static Set<String> branchIdSet=new HashSet<>();
    public static String branchIdRegex="";

    static {
        branchIdSet.add("branch_id");
        branchIdSet.add("branch_no");
        branchIdSet.add("tenant_id");
        branchIdSet.add("tenant_no");
        branchIdRegex="(?i)"+branchIdSet.stream().map(k->".*"+k+".*").collect(Collectors.joining("|"));
    }

    public static boolean isBranchId(DmField field){
        return field.getColumnName().matches(branchIdRegex);
    }
    public static QueryWrapper addBranchQx(QueryWrapper qw, List<DmField> fields){
        User user=LoginUtils.getCurrentUserInfo();
         Optional<DmField> optional=fields.stream().filter(k->isBranchId(k)).findAny();
        if(optional.isPresent()){
            qw.eq(optional.get().getColumnName(),user.getBranchId());
            return qw;
        }else {
            return qw;
        }
    }
    public static DmField getBranchField(List<DmField> fields){
        List<DmField> bfields=fields.stream().filter(k->isBranchId(k)).collect(Collectors.toList());
        if(bfields==null || bfields.size()==0){
            return null;
        }
        return bfields.get(0);
    }

    public static Object getBranchValue(List<DmField> fields,Map<String,Object> params){
         List<DmField> bfields=fields.stream().filter(k->isBranchId(k)).collect(Collectors.toList());
        for (DmField bfield : bfields) {
            String camelFieldName=ObjectTools.camelName(bfield.getColumnName());
            Object v=params.get(camelFieldName);
            if(v!=null){
                return v;
            }
        }
        return null;
    }

    public static void checkDmRoles(){
        if(!LoginUtils.hasAnyRoles(DmRole.DBA.name(),DmRole.SUB_DBA.name())){
            throw new BizException("dm-oper-role-required","权限不足");
        };
    }

    /**
     * 检查表格权限
     */
    public static List<DmTable> getHasQxTableList(List<DmTable> tableNames, DLType ...dlTypes){
        if(LoginUtils.hasAnyRoles(DmRole.DBA.name())){
            return tableNames;
        }else{
            List<DmTable> hasQxTables=new ArrayList<>();
            Map<String, Qx> myQxs=LoginUtils.getMyQxs();
            if(myQxs==null || myQxs.isEmpty()){
                return hasQxTables;
            }

            Map<String,List<Qx>> myQxList=new HashMap<>();
            for (Map.Entry<String, Qx> qm : myQxs.entrySet()) {
                if(ObjectTools.isEmpty(qm.getValue().getQxType())){
                    continue;
                }
                List<Qx> qxList=myQxList.get(qm.getValue().getQxType());
                if(qxList==null){
                    qxList=new ArrayList<>();
                    qxList.add(qm.getValue());
                }
                String qxType=qm.getValue().getQxType().toUpperCase();

                myQxList.put(qxType,qxList);
            }
            for (DmTable table : tableNames) {
                if(hasTableQx(table.getTableName(),myQxList,dlTypes)){
                    hasQxTables.add(table);
                }
            }

            return hasQxTables;
        }
    }
    /**
     * 检查表格权限
     */
    public static boolean hasTableQx(String tableName,Map<String,List<Qx>> myQxList, DLType ...dlTypes){


            for (DLType dlType : dlTypes) {
                DmQx dmQx=DmQx.fromDLType(dlType);
                List<Qx> qxL=myQxList.get(dmQx.name());
                if(qxL==null){
                    continue;
                }
                for (Qx qx : qxL) {
                    if(ObjectTools.isEmpty(qx.getQxRegex())){
                        continue;
                    }
                    if(matchTable(tableName,qx.getQxRegex())){
                        return true;
                    }
                }

            }
            return false;
    }
    /**
     * 检查表格权限
     */
    public static void checkTableQx(String tableName, DLType ...dlTypes){

        if(LoginUtils.hasAnyRoles(DmRole.DBA.name())){
            return ;
        }else {
            Map<String, Qx> myQxs = LoginUtils.getMyQxs();
            if (myQxs == null || myQxs.isEmpty()) {
                throw new BizException(LangTips.errMsg("dm-oper-table-qx-required", "无权限操作表格【%s】,需要角色【%s】或者以下权限之一【%s】", tableName, "dba", Arrays.stream(dlTypes).map(d -> d.name()).collect(Collectors.joining("、"))));

            }

            Map<String, List<Qx>> myQxList = new HashMap<>();
            for (Map.Entry<String, Qx> qm : myQxs.entrySet()) {
                if(ObjectTools.isEmpty(qm.getValue().getQxType())){
                    continue;
                }
                List<Qx> qxList = myQxList.get(qm.getValue().getQxType());
                if (qxList == null) {
                    qxList = new ArrayList<>();
                    qxList.add(qm.getValue());
                }
                String qxType=qm.getValue().getQxType().toUpperCase();
                myQxList.put(qxType, qxList);
            }
            if (!hasTableQx(tableName, myQxList, dlTypes)) {
                throw new BizException(LangTips.errMsg("dm-oper-table-qx-required", "无权限操作表格【%s】,需要角色【%s】或者以下权限之一【%s】", tableName, "dba", Arrays.stream(dlTypes).map(d -> d.name()).collect(Collectors.joining("、"))));
            }
        }
    }
    /**
     * 表格名字是否满足通行表达式
     * @param tableName
     * @return
     */
    public static boolean matchTable(String tableName,String qxRegex){
        // 定义要匹配的字符串
        String text = tableName;

        // 定义正则表达式，匹配任意字母字符
        String regex = qxRegex;

        // 编译正则表达式
        Pattern pattern = Pattern.compile(regex);

        // 创建匹配器
        Matcher matcher = pattern.matcher(text);

        return matcher.matches();
    }
}
