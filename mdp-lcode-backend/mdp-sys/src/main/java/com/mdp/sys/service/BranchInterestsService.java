package com.mdp.sys.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.service.BaseService;
import com.mdp.core.utils.BaseUtils;
import com.mdp.meta.client.entity.ExtInfo;
import com.mdp.meta.client.entity.ItemVo;
import com.mdp.meta.client.service.ItemService;
import com.mdp.sys.entity.BranchInterests;
import com.mdp.sys.mapper.BranchInterestsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 父类已经支持增删改查操作,因此,即使本类什么也不写,也已经可以满足一般的增删改查操作了.<br> 
 * 组织 com  顶级模块 mdp 大模块 sys 小模块 <br>
 * 实体 BranchInterests 表 sys_branch_interests 当前主键(包括多主键): branch_id; 
 ***/
@Service("mdp.sys.branchInterestsService")
public class BranchInterestsService extends BaseService<BranchInterestsMapper,BranchInterests> {


    @Autowired
    ItemService itemService;

    BranchInterests defaultInterests=null;
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
     *
     * @param branchId 机构编号
     * @return
     */
    public BranchInterests getDefaultBranchInterests(String branchId){
        BranchInterests defaultInterests =getDefaultInterests("2");
        BranchInterests b=new BranchInterests();
        BeanUtils.copyProperties(defaultInterests,b);
        b.setBranchId(branchId);
        b.setCurrUsers(1);
        b.setCmonthBids(0);
        b.setCtotalAt(BigDecimal.ZERO);
        b.setCtotalBids(0);
        b.setCtotalExp(BigDecimal.ZERO);
        b.setCtime(new Date());
        b.setIstatus("1");
        return b;

    }



    public BranchInterests getDefaultInterests(String itype){
        if(defaultInterests!=null){
            return defaultInterests;
        }
        ItemVo itemVo=itemService.getDict("sysParam","mkInterests");
        Map<String,Object> map=new HashMap<>();
        for (ExtInfo extInfo : itemVo.getExtInfoList()) {
            map.put(extInfo.getId(),extInfo.getValue());
        }
        defaultInterests=BaseUtils.fromMap(map,BranchInterests.class);
        return defaultInterests;
    }

    @Scheduled(cron = "0 0/30 * * * ?")
    public void clearDefault(){
        this.defaultInterests=null;
    }

	static Logger logger =LoggerFactory.getLogger(BranchInterestsService.class);

    public int updateAfterBidSuccess(Map<String, Object> branchInterests) {
        return baseMapper.updateAfterBidSuccess(branchInterests);
    }

    @Transactional
    public int updateCurrUsersAfterChangeUser(String branchId) {
        int i= baseMapper.updateCurrUsersAfterChangeUser(branchId);
        if(i==0){
            BranchInterests branchInterests=getDefaultBranchInterests(branchId);
            i=this.insert(branchInterests);
            i= baseMapper.updateCurrUsersAfterChangeUser(branchId);
        }
        return i;
    }

    public Map<String, Object> detailMap(String branchId) {
        return baseMapper.detailMap(branchId);
    }
}

