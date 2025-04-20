package com.xm.core.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xm.core.entity.XmTask;
import com.xm.core.vo.BatchRelTasksWithPhase;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
/**
 * @author maimeng-mdp code-gen
 * @since 2023-10-3
 */
public interface XmTaskMapper extends BaseMapper<XmTask> {

    /**
     * 自定义查询，支持多表关联
     * @param page 分页条件
     * @param ew 一定要，并且必须加@Param("ew")注解
     * @param ext 如果xml中需要根据某些值进行特殊处理，可以通过这个进行传递，非必须，注解也可以不加
     * @return
     */
    List<Map<String,Object>> selectListMapByWhere(IPage page, @Param("ew") QueryWrapper ew,@Param("ext") Map<String,Object> ext);

    List<XmTask> selectTaskListByIds(Map<String, Object> ids);

    void batchRelTasksWithMenu(Map<String, Object> map);

    void updateChildrenCntByIds(List<String> ids);

    void batchRelTasksWithPhase(BatchRelTasksWithPhase tasksPhase);

    void updateActCostAndActWorkloadAfterSettle(Map<String, Object> map);

    void calcWorkloadByRecord(List<String> ids);

    void upBrowseTimes(Map<String, Object> map);

    void updateSomeFieldByPkAfterPaySuccess(XmTask xmTaskUpdate);


    List<XmTask> listTenTaskByProjectIdAndProductId(Map<String, Object> projectId);

    List<XmTask> listTenTaskByProjectIdAndIterationId(Map<String, Object> map);

    void batchChangeParent(Map<String, Object> map);

    void updateTaskChildrenCntByTaskId(String taskId);

    void xmMenuSumParents(List<String> pidPathsList);

    void xmMenuBatchSumParents(List<String> ids);

    Map<String, Object> calcProjectAndTaskBudget(Map<String, Object> map);

    Map<String, Object> selectTotalTaskBudgetCost(Map<String, Object> map);

    Map<String, Object> selectTotalPhaseAndTaskBudgetCost(Map<String, Object> p);

    Map<String, Object> shareTaskDetail(Map<String, Object> xmTask);
 

    void batchUpdateInitWorkloadAndRateByActWorkload(Map<String, Object> map);


    void batchUpdateFinishState(Map<String, Object> map);

    void batchUpdateMactWorkloadAndRate(Map<String, Object> map);

    void batchUpdateInitWorkloadAndRate(Map<String, Object> map);

    void batchUpdateInitWorkloadAndRateByCalcType(Map<String, Object> parameter);
}

