package com.mdp.sys.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.api.CacheHKVService;
import com.mdp.core.service.BaseService;
import com.mdp.core.utils.BaseUtils;
import com.mdp.meta.client.entity.ExtInfo;
import com.mdp.meta.client.entity.ItemVo;
import com.mdp.meta.client.service.ItemService;
import com.mdp.sys.entity.UserInterests;
import com.mdp.sys.mapper.UserInterestsMapper;
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
 * 实体 UserInterests 表 sys_user_interests 当前主键(包括多主键): userid; 
 ***/
@Service("mdp.sys.userInterestsService")
public class UserInterestsService extends BaseService<UserInterestsMapper,UserInterests>{
	static Logger logger =LoggerFactory.getLogger(UserInterestsService.class);

    @Autowired
    CacheHKVService cacheHKVService;


    @Autowired
    UserService userService;

    @Autowired
    ItemService itemService;

    UserInterests defaultInterests=null;
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
     * @param userid 用户编号
     * @return
     */
    public UserInterests getDefaultUserInterests(String userid){
        UserInterests defaultInterests =getDefaultInterests("2");
        UserInterests b=new UserInterests();
        BeanUtils.copyProperties(defaultInterests,b);
        b.setUserid(userid);
        b.setCurrUsers(1);
        b.setCmonthBids(0);
        b.setCtotalAt(BigDecimal.ZERO);
        b.setCtotalBids(0);
        b.setCtotalExp(BigDecimal.ZERO);
        b.setCtotalReceiveAt(BigDecimal.ZERO);
        b.setCtime(new Date());
        b.setCmonthAt(BigDecimal.ZERO);
        b.setCmonthExp(0);
        return b;

    }



    public UserInterests getDefaultInterests(String itype){
        if(defaultInterests!=null){
            return defaultInterests;
        }
        ItemVo itemVo=itemService.getDict("sysParam","mkInterests");
        Map<String,Object> map=new HashMap<>();
        for (ExtInfo extInfo : itemVo.getExtInfoList()) {
            map.put(extInfo.getId(),extInfo.getValue());
        }
        defaultInterests= BaseUtils.fromMap(map, UserInterests.class);
        return defaultInterests;
    }

    @Scheduled(cron = "0 0/30 * * * ?")
    public void clearDefault(){
        this.defaultInterests=null;
    }
 

    public int updateAfterBidSuccess(String userid,String bizMonth) {
        return baseMapper.updateAfterBidSuccess(map("userid",userid,"bizMonth",bizMonth));
    }

    @Transactional
    public int updateCurrUsersAfterChangeUser(String userid) {
        int i= baseMapper.updateCurrUsersAfterChangeUser(userid);
        if(i==0){
            UserInterests branchInterests=getDefaultUserInterests(userid);
            i=this.insert(branchInterests);
            i= updateCurrUsersAfterChangeUser(userid);
        }
        return i;
    }

    public Map<String, Object> detailMap(String userid) {
        return baseMapper.detailMap(userid);
    }

    public List<UserInterests> selectNeedOverUser() {
        return baseMapper.selectNeedOverUser(map());
    }

    public long interestsOverUpdate(UserInterests userInterestsUpdate) {
       return baseMapper.interestsOverUpdate(userInterestsUpdate);
    }
}

