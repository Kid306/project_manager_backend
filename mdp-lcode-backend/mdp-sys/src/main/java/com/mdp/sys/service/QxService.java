package com.mdp.sys.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.err.BizException;
import com.mdp.core.service.BaseService;
import com.mdp.core.utils.BaseUtils;
import com.mdp.sys.entity.Qx;
import com.mdp.sys.mapper.QxMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 父类已经支持增删改查操作,因此,即使本类什么也不写,也已经可以满足一般的增删改查操作了.<br> 
 * 组织 com.qqkj  顶级模块 mdp 大模块 sys 小模块 <br>
 * 实体 Qx 表 ADMIN.sys_qx 当前主键(包括多主键): qx_id; 
 ***/
@Service("mdp.sys.qxService")
public class QxService extends BaseService<QxMapper,Qx>{

    /**
     * 自定义查询，支持多表关联
     * @param page 分页条件
     * @param ew 一定要，并且必须加@Param("ew")注解
     * @param ext 如果xml中需要根据某些值进行特殊处理，可以通过这个进行传递，非必须，注解也可以不加
     * @return
     */
    public List<Map<String,Object>> selectListMapByWhere(IPage page, QueryWrapper ew, Map<String,Object> ext){
        return baseMapper.selectListMapByWhere(page,ew,ext);
    }
    /**
     * 自动注册权限到数据库
     * @param list
     */
    public void autoRegist(List<Qx> list) {
        if(list==null || list.size()==0){
            return;
        }else{
            Map<String,Qx> qxMap=new HashMap<>();
            for (Qx qx : list) {
                if(StringUtils.isEmpty(qx.getQxId())){
                    throw new BizException("权限编号(qxId)不能为空");
                }
                if(qxMap.containsKey(qx.getQxId())){
                    Qx qx1=qxMap.get(qx.getQxId());
                    if(StringUtils.isEmpty(qx1.getQxName())){
                        if(!StringUtils.isEmpty(qx.getQxName())){
                            qxMap.put(qx.getQxId(),qx);
                        }
                    }
                }else{
                    qxMap.put(qx.getQxId(),qx);
                }
            }
            List<Qx> qxListDb=this.selectListByWhere(new Qx());
            Map<String,Qx> editMap=new HashMap<>();
            Map<String,Qx> insertMap=new HashMap<>();

            Map<String,Qx> dbQxMap=new HashMap<>();
            for (Qx qx : qxListDb) {
                dbQxMap.put(qx.getQxId(),qx);
                if(qxMap.containsKey(qx.getQxId())){
                    Qx qx1=qxMap.get(qx.getQxId());
                    if(!StringUtils.isEmpty(qx1.getQxName())){
                        if(!qx1.getQxName().equals(qx.getQxName())){
                            qx.setQxName(qx1.getQxName());
                            editMap.put(qx.getQxId(),qx);
                        }
                        if( !StringUtils.isEmpty(qx1.getModuleId()) && StringUtils.isEmpty(qx.getModuleId())){
                            qx.setModuleId(qx1.getModuleId());
                            editMap.put(qx.getQxId(),qx);
                        }
                    }
                }
            }

            for (Qx qx : list) {
                if(!dbQxMap.containsKey(qx.getQxId())){
                    insertMap.put(qx.getQxId(),qx);
                }
            }
            if(!editMap.isEmpty()){
                this.batchUpdate(editMap.values().stream().map(k-> BaseUtils.fromMap((Map<String, Object>) k,Qx.class)).collect(Collectors.toList()));
            }
            if(!insertMap.isEmpty()){
                this.batchInsert(insertMap.values().stream().map(k-> BaseUtils.fromMap((Map<String, Object>) k,Qx.class)).collect(Collectors.toList()));
            }

        }
    }

    /** 请在此类添加自定义函数 */

}

