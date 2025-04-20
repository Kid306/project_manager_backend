package com.xm.core.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xm.core.entity.XmProjectState;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
/**
 * @author maimeng-mdp code-gen
 * @since 2023-10-3
 */
public interface XmProjectStateMapper extends BaseMapper<XmProjectState> {

    /**
     * 自定义查询，支持多表关联
     * @param page 分页条件
     * @param ew 一定要，并且必须加@Param("ew")注解
     * @param ext 如果xml中需要根据某些值进行特殊处理，可以通过这个进行传递，非必须，注解也可以不加
     * @return
     */
    List<Map<String,Object>> selectListMapByWhere(IPage page, @Param("ew") QueryWrapper ew,@Param("ext") Map<String,Object> ext);


    List<Map<String, Object>> selectPortalProjectStates(Map<String, Object> params);
}

