package com.mdp.core.utils;

import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

public class TreeUtils {

    /**
     * 解析pidPaths，获得按从上到下分层的id值，用于更新sql等
     * @param pidPathsList
     * @param sortDir 0-从上到下，1-从下到上
     * @param ignorIds 需要忽略的id
     * @return
     */
    public static List<Set<String>> parsePidPaths(Collection<String> pidPathsList,int sortDir,String...ignorIds){
        List<Set<String>> list=new ArrayList<>();
        if(pidPathsList==null||pidPathsList.size()==0){
            return list;
        }
        Set<String> ignorSet=new HashSet<>();
        if(ignorIds!=null && ignorIds.length>0){
            ignorSet= Arrays.stream(ignorIds).collect(Collectors.toSet());
        }
        Set<String> pidPathsSet=pidPathsList.stream().collect(Collectors.toSet());
        for (String pidPaths : pidPathsSet) {
            if(!StringUtils.hasText(pidPaths)||"0,".equals(pidPaths)){
                continue;
            }
            if(pidPaths.startsWith("0,")){
                pidPaths=pidPaths.substring(2);
            }

            if(!StringUtils.hasText(pidPaths)){
                continue;
            }
            String[] pidPathss=pidPaths.split(",");
            for (int i = 0; i <pidPathss.length; i++) {
                if(list.size()<=i){
                    list.add(new HashSet<>());
                }
                Set<String> set=list.get(i);
                if(ignorSet.contains(pidPathss[i])){
                    continue;
                }
                set.add(pidPathss[i]);
            }
        }
        if(0==sortDir){
            return list.stream().filter(k->!k.isEmpty()).collect(Collectors.toList());
        }
        List<Set<String>> list2=new ArrayList<>();
        if(1<=sortDir){
            for (int i = list.size() - 1; i >= 0; i--) {
                Set<String> set=list.get(i);
                 if(!set.isEmpty()){
                     list2.add(set);
                 }
            }
        }
        return list2;
    }


    /**
     * 解析pidPaths，获得按从上到下分层的id值，用于更新sql等
     * @param pidPaths
     * @param sortDir 0-从上到下，1-从下到上
     * @param ignorIds 需要忽略的id
     * @return
     */
    public static List<String> parsePidPaths(String pidPaths,int sortDir,String...ignorIds){
        List<String> list=new ArrayList<>();
        if(ObjectUtils.isEmpty(pidPaths) ||"0,".equals(pidPaths)){
            return list;
        }
        Set<String> ignorSet;
        if(ignorIds!=null && ignorIds.length>0){
            ignorSet= Arrays.stream(ignorIds).collect(Collectors.toSet());
        } else {
            ignorSet = new HashSet<>();
        }
        Set<String> pidPathsSet= Arrays.stream(pidPaths.split(",")).collect(Collectors.toSet());

        if(0==sortDir){
            return pidPathsSet.stream().filter(k->!ignorSet.contains(k)).collect(Collectors.toList());
         }

        List<String> list1=pidPathsSet.stream().collect(Collectors.toList());
        List<String> list2=new ArrayList<>();
        int size= list1.size();
        if(1<=sortDir){
            for (int i = size - 1; i >= 0; i--) {
                String id=list1.get(i);
                if(ignorSet.contains(id)){
                    continue;
                }
                list2.add(id);
            }
        }
        return list2;
    }
}
