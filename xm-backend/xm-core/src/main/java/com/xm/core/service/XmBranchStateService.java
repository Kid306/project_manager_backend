package com.xm.core.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.service.BaseService;
import com.xm.calc.service.XmCalcServiceApi;
import com.xm.core.entity.XmBranchState;
import com.xm.core.mapper.XmBranchStateMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
/**
 * @author maimeng-mdp code-gen
 * @since 2023-10-3
 */
@Service
public class XmBranchStateService extends BaseService<XmBranchStateMapper,XmBranchState> {
	static Logger logger =LoggerFactory.getLogger(XmBranchStateService.class);
	@Autowired
	XmCalcServiceApi xmCalcServiceApi;

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

    public List<Map<String, Object>> listPortalAllXmBranchSumState(IPage page, QueryWrapper<XmBranchState> qw, Map<String, Object> params) {
		return baseMapper.listPortalAllXmBranchSumState(page,qw,params);
    }

	public List<Map<String, Object>> tasksSumDw(String userid) {
		return baseMapper.taskSunDw(userid);
	}

	public void loadProjectStateToXmBranchState(String branchId) {
		xmCalcServiceApi.loadProjectStateToXmBranchState(branchId);
	}
}

