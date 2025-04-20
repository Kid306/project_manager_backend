package com.xm.core.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xm.core.entity.XmMenu;
import com.xm.core.vo.XmIterationMenuVo;
import com.xm.core.vo.XmPhaseMenusVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
/**
 * @author maimeng-mdp code-gen
 * @since 2023-10-3
 */
public interface XmMenuMapper extends BaseMapper<XmMenu> {

    /**
     * 自定义查询，支持多表关联
     * @param page 分页条件
     * @param ew 一定要，并且必须加@Param("ew")注解
     * @param ext 如果xml中需要根据某些值进行特殊处理，可以通过这个进行传递，非必须，注解也可以不加
     * @return
     */
    List<Map<String,Object>> selectListMapByWhere(IPage page, @Param("ew") QueryWrapper ew,@Param("ext") Map<String,Object> ext);

    List<XmMenu> getUserCanOpMenusByIds(Map<String, Object> map);

    List<Map<String, Object>> selectListMapByWhereWithPlan(IPage page, @Param("ew") QueryWrapper ew,@Param("ext") Map<String,Object> ext);

    List<Map<String, Object>> selectListMapByWhereWithState(IPage page, @Param("ew") QueryWrapper ew,@Param("ext") Map<String,Object> ext);

    List<Map<String, Object>> selectListMapByWhereWithPhase(IPage page, @Param("ew") QueryWrapper ew,@Param("ext") Map<String,Object> ext);

    void updateMenuChildrenCntByMenuId(String menuId);

    void updateChildrenCntByIds(List<String> ids);

    List<Map<String, Object>> queryTaskUsersByMenuId(String menuId);

    List<XmMenu> selectExistIterationMenus(Map<String, Object> menuIds);

    void doBatchDeleteByProductIds(List<String> productIds);

    void batchUnIteration(XmIterationMenuVo xmIterationMenus);

    void batchIteration(XmIterationMenuVo xmIterationMenus);

    void batchUnProductPhase(XmPhaseMenusVo xmPhaseMenusVo);

    void batchProductPhase(XmPhaseMenusVo xmPhaseMenusVo);

    List<XmMenu> listTenMenuByProductIdAndIterationId(Map<String, Object> map);

    void batchChangeParent(Map<String, Object> map);

    List<XmMenu> selectListByIdsWithsChildrenCnt(List<String> ids);

    void updateUps(List<String> menuIds);

    void updateComments(List<String> menuIds);

    void upReads(Map<String, Object> map);

    void batchUpdateFinishState(Map<String, Object> parameter);

    void batchUpdateInitWorkloadAndRate(Map<String, Object> parameter);

    void batchUpdateInitWorkloadAndRateByCalcType(Map<String, Object> parameter);
}

