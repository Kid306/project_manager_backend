package com.xm.core.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xm.core.entity.XmKpi;
import com.xm.rpt.XmRptQueryServiceApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mdp.core.service.BaseService;
import static com.mdp.core.utils.BaseUtils.*;
import com.mdp.core.entity.Tips;
import com.mdp.core.err.BizException;

import com.xm.core.entity.XmRisk;
import com.xm.core.mapper.XmRiskMapper;
/**
 * @author 唛盟开源 code-gen
 * @since 2025-4-10
 */
@Service
public class XmRiskService extends BaseService<XmRiskMapper,XmRisk> {
	static Logger logger =LoggerFactory.getLogger(XmRiskService.class);

	@Autowired
	XmRptQueryServiceApi xmRptQueryServiceApi;

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

    public List<Map<String, Object>> getXmRiskAttDist(IPage page, QueryWrapper qw, Map<String, Object> ext) {
		return xmRptQueryServiceApi.getXmRiskAttDist(page,qw,ext);
    }
}

