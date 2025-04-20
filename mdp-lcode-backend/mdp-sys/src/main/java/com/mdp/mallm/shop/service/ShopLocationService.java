package com.mdp.mallm.shop.service;


import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.service.BaseService;
import com.mdp.mallm.shop.entity.ShopLocation;
import com.mdp.mallm.shop.mapper.ShopLocationMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("mdp.mallm.shop.ShopLocationService")
@DS("mall-ds")
public class ShopLocationService extends BaseService<ShopLocationMapper, ShopLocation> {

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
}

