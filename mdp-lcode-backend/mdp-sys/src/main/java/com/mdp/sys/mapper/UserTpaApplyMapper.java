package com.mdp.sys.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.sys.entity.UserTpaApply;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserTpaApplyMapper extends BaseMapper<UserTpaApply> {

    /**
     * 自定义查询，支持多表关联
     * @param page 分页条件
     * @param ew 一定要，并且必须加@Param("ew")注解
     * @param ext 如果xml中需要根据某些值进行特殊处理，可以通过这个进行传递，非必须，注解也可以不加
     * @return
     */
    List<Map<String,Object>> selectListMapByWhere(IPage page, @Param("ew") QueryWrapper ew,@Param("ext") Map<String,Object> ext);

}

