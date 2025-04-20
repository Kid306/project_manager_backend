package com.mdp.sys.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.err.BizException;
import com.mdp.core.service.BaseService;
import com.mdp.sys.cache.GradeCacheService;
import com.mdp.sys.entity.Grade;
import com.mdp.sys.mapper.GradeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 父类已经支持增删改查操作,因此,即使本类什么也不写,也已经可以满足一般的增删改查操作了.<br> 
 * 组织 com  顶级模块 mdp 大模块 sys 小模块 <br>
 * 实体 Grade 表 sys_grade 当前主键(包括多主键): grade_id; 
 ***/
@Service("mdp.sys.gradeService")
public class GradeService extends BaseService<GradeMapper,Grade>{
	static Logger logger =LoggerFactory.getLogger(GradeService.class);


	@Autowired
	GradeCacheService gradeCacheService;

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

	public List<Grade> getGradesFromCacheFirst(String gtype) {
		List<Grade> grades= gradeCacheService.getGrades(gtype);
		if(grades==null||grades.size()==0){
			Grade grade=new Grade();
			grade.setGtype(gtype);
			List<Grade> gradesDb=this.selectListByWhere(grade);
			gradeCacheService.putGrades(gtype,gradesDb);
			return gradesDb;
		}else{
			return grades;
		}
	}

	/**
	 *  将金额转换为工时后与传入的工时比较，取两者较大者作为最终工时
	 * @param exp 工时
	 * @param at 金额 金额按每小时100元可转化为工时
	 * @return
	 */
	public static BigDecimal getMaxExp(BigDecimal exp, BigDecimal at){
		if(exp==null){
			if(at==null){
				throw new BizException("at-0","金额不能为空");
			}
			exp=at.divide(BigDecimal.valueOf(100));
			return exp;
		}else{
			if(at==null){
				return exp;
			}else{
				BigDecimal atExp=at.divide(BigDecimal.valueOf(100));
				if(atExp.compareTo(exp)>0){
					return atExp;
				}else{
					return exp;
				}
			}
		}
	}
}

