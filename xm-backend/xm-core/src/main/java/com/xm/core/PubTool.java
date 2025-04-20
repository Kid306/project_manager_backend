package com.xm.core;

import cn.hutool.core.date.DateUtil;
import com.xm.core.entity.XmKpi;
import com.xm.core.entity.XmMenu;
import com.xm.core.entity.XmTask;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

public class PubTool {

    public static String getPidPaths(String pidPahts, String trimId){
        if(!StringUtils.hasText(pidPahts)){
            return null;
        }
        int i=pidPahts.indexOf(trimId+",");
        if(i>0){
            return pidPahts.substring(0,i);
        }else{
            return pidPahts;
        }
    }

    public static Set<String> getPidSet(String pidPahts, String trimId){
        if(!StringUtils.hasText(pidPahts)){
            return new HashSet<>();
        }
        Set<String> sets=new HashSet<>();
        String[] pids=pidPahts.split(",");
        for (String pid : pids) {
            if("0".equals(pid)||pid.equals(trimId)){
                continue;
            }
            sets.add(pid);

        }
        return sets;
    }

    public static XmTask xmMenuToXmTask(XmMenu menu){
        XmTask xmTask=new XmTask();
        xmTask.setId(menu.getMenuId());
        xmTask.setName(menu.getMenuName());
        xmTask.setParentTaskid(menu.getPmenuId());
        xmTask.setPreTaskid(menu.getPhaseId());
        xmTask.setNtype(menu.getNtype());
        if("0".equals(menu.getNtype())){
            xmTask.setPtype("3");
        }else{
            xmTask.setPtype("2");
        }
        xmTask.setSortLevel(menu.getSeqNo());
        xmTask.setStartTime(menu.getStartTime());
        xmTask.setEndTime(menu.getEndTime());
        return xmTask;
    }
    
    public static List<Date> calcStartEndDate(Date now,String calcType){
        Date start=DateUtil.beginOfMonth(now);
        Date end=DateUtil.beginOfMonth(now);
        Date base = DateUtil.beginOfYear(now);
        if("Q".equals(calcType)){
            base = DateUtil.beginOfQuarter(now);
            base=DateUtil.offsetMonth(base,4);
            start = DateUtil.beginOfQuarter(base);
            end = DateUtil.endOfQuarter(base);
        }else if("H".equals(calcType)){

            if(now.getMonth()<3){
                start = base;
                end = DateUtil.endOfMonth( DateUtil.offsetMonth(base,5));
            }else if(now.getMonth()<9){
                base=DateUtil.offsetMonth(base,6);
                start = base;
                end = DateUtil.endOfMonth(DateUtil.offsetMonth(start,5));
            }else{
                base=DateUtil.offsetMonth(base,12);
                start = base;
                end = DateUtil.endOfMonth(DateUtil.offsetMonth(start,5));
            }
        }else if("Y".equals(calcType)){
            if(now.getMonth()<6){
                start = base;
                end = DateUtil.endOfYear(start);
            }else{
                base=DateUtil.offsetMonth(base,12);
                start = DateUtil.beginOfYear(base);
                end = DateUtil.endOfYear(start);
            }
        }else if("M".equals(calcType)){
            if(now.getDate()<15){
                start = DateUtil.beginOfMonth(now);
                end = DateUtil.endOfMonth(start);
            }else{
                start = DateUtil.beginOfMonth( DateUtil.offsetMonth(now,1) );
                end = DateUtil.endOfMonth(start);
            }
        }else if("W".equals(calcType)){
            if(now.getDay()<4){
                start = DateUtil.beginOfWeek(now);
                end = DateUtil.endOfWeek(start);
            }else{
                start = DateUtil.beginOfWeek( DateUtil.offsetWeek(now,1) );
                end = DateUtil.endOfWeek(start);
            }
        }
        return Arrays.asList(start,end);
    }

    // 排序号生成 按##.##.##格式生成
    public static void initKpisSeqNo(XmKpi parent, List<XmKpi> kpis){
        //排序号生成 按##.##.##格式生成
        if(parent!=null){
            List<XmKpi> topList=kpis.stream().filter(k->k.getParentId().equals(parent.getId())).collect(Collectors.toList());
            for (int i = 0; i < topList.size(); i++) {
                XmKpi xmTask=topList.get(i);
                int k=i+1;
                String seq=k<10?"0"+k:k+"";
                xmTask.setKpiIndex(parent.getKpiIndex()+"."+seq);

                initKpisSeqNo(xmTask,kpis);
            }
        }else{
            List<XmKpi> topList=kpis.stream().filter(k->k.getParentId().equals("0")).collect(Collectors.toList());
            for (int i = 0; i < topList.size(); i++) {
                XmKpi xmTask=topList.get(i);
                int k=i+1;
                String seq=k<10?"0"+k:k+"";
                xmTask.setKpiIndex(seq);
                initKpisSeqNo(xmTask,kpis);
            }
        }
    }

    // 排序号生成 按##.##.##格式生成
    public static void initTasksSeqNo(XmTask parent, List<XmTask> tasks){
        //排序号生成 按##.##.##格式生成
        if(parent!=null){
            List<XmTask> topList=tasks.stream().filter(k->k.getParentTaskid().equals(parent.getId())).collect(Collectors.toList());
            for (int i = 0; i < topList.size(); i++) {
                XmTask xmTask=topList.get(i);
                int k=i+1;
                String seq=k<10?"0"+k:k+"";
                xmTask.setSortLevel(parent.getSortLevel()+"."+seq);

                initTasksSeqNo(xmTask,tasks);
            }
        }else{
            List<XmTask> topList=tasks.stream().filter(k->k.getParentTaskid().equals("0")).collect(Collectors.toList());
            for (int i = 0; i < topList.size(); i++) {
                XmTask xmTask=topList.get(i);
                int k=i+1;
                String seq=k<10?"0"+k:k+"";
                xmTask.setSortLevel(seq);
                initTasksSeqNo(xmTask,tasks);
            }
        }
    }

    public static void initMenusSeqNo(XmMenu parent, List<XmMenu> menus) {
        //排序号生成 按##.##.##格式生成
        if(parent!=null){
            List<XmMenu> topList=menus.stream().filter(k->k.getPmenuId().equals(parent.getMenuId())).collect(Collectors.toList());
            for (int i = 0; i < topList.size(); i++) {
                XmMenu xmTask=topList.get(i);
                int k=i+1;
                String seq=k<10?"0"+k:k+"";
                xmTask.setSeqNo(parent.getSeqNo()+"."+seq);

                initMenusSeqNo(xmTask,menus);
            }
        }else{
            List<XmMenu> topList=menus.stream().filter(k->k.getPmenuId().equals("0")).collect(Collectors.toList());
            for (int i = 0; i < topList.size(); i++) {
                XmMenu xmMenu=topList.get(i);
                int k=i+1;
                String seq=k<10?"0"+k:k+"";
                xmMenu.setSeqNo(seq);
                initMenusSeqNo(xmMenu,menus);
            }
        }
    }

    public static void main(String[] args) {

        List<Date> dates=PubTool.calcStartEndDate(new Date(),"Q");
        System.out.println("calcType: Q , now: "+DateUtil.format(new Date(),"y-M-d"));
        System.out.println("            start: "+DateUtil.format(dates.get(0),"y-M-d"));
        System.out.println("              end: "+DateUtil.format(dates.get(1),"y-M-d"));


        dates=PubTool.calcStartEndDate(new Date(),"W");
        System.out.println("calcType: W , now: "+DateUtil.format(new Date(),"y-M-d"));
        System.out.println("            start: "+DateUtil.format(dates.get(0),"y-M-d"));
        System.out.println("              end: "+DateUtil.format(dates.get(1),"y-M-d"));

        dates=PubTool.calcStartEndDate(new Date(),"M");
        System.out.println("calcType: M , now: "+DateUtil.format(new Date(),"y-M-d"));
        System.out.println("            start: "+DateUtil.format(dates.get(0),"y-M-d"));
        System.out.println("              end: "+DateUtil.format(dates.get(1),"y-M-d"));

        dates=PubTool.calcStartEndDate(new Date(),"H");
        System.out.println("calcType: H , now: "+DateUtil.format(new Date(),"y-M-d"));
        System.out.println("            start: "+DateUtil.format(dates.get(0),"y-M-d"));
        System.out.println("              end: "+DateUtil.format(dates.get(1),"y-M-d"));

        dates=PubTool.calcStartEndDate(new Date(),"Y");
        System.out.println("calcType: Y , now: "+DateUtil.format(new Date(),"y-M-d"));
        System.out.println("            start: "+DateUtil.format(dates.get(0),"y-M-d"));
        System.out.println("              end: "+DateUtil.format(dates.get(1),"y-M-d"));

    }
}
