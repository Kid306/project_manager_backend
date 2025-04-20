package com.mdp.sys.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.client.AcClient;
import com.mdp.core.service.BaseService;
import com.mdp.core.utils.DateUtils;
import com.mdp.sys.entity.Grade;
import com.mdp.sys.entity.User;
import com.mdp.sys.entity.UserGradeRecord;
import com.mdp.sys.entity.UserInterests;
import com.mdp.sys.mapper.UserGradeRecordMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 父类已经支持增删改查操作,因此,即使本类什么也不写,也已经可以满足一般的增删改查操作了.<br> 
 * 组织 com  顶级模块 mdp 大模块 sys 小模块 <br>
 * 实体 UserGradeRecord 表 sys_user_grade_record 当前主键(包括多主键): id; 
 ***/
@Service("mdp.sys.userGradeRecordService")
public class UserGradeRecordService extends BaseService<UserGradeRecordMapper,UserGradeRecord>{

	static Logger logger =LoggerFactory.getLogger(UserGradeRecordService.class);
	@Autowired
    UserInterestsService userInterestsService;


	@Autowired
    GradeService gradeService;
    @Autowired
	UserService userService;

	@Autowired
    AcClient acClient;

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

	@Transactional
    public void addRecord(String userid, String state, String bizId, BigDecimal exp, BigDecimal at, String remark) {
        String bizMonth= DateUtils.getDate("yyyy-MM");
        BigDecimal maxExp=GradeService.getMaxExp(exp,at);
        if("1".equals(state)){
            UserGradeRecord record=this.add(userid,state,bizId,maxExp,at,remark);
            //更新UserInterests的投标次数及金额
            int bids=1;
            int i=userInterestsService.updateAfterBidSuccess(userid,bizMonth);
            if(i<=0){
                UserInterests userInsert=this.userInterestsService.getDefaultUserInterests(userid);
                userInsert.setCmonthBids(bids);
                userInsert.setCmonthExp(maxExp.intValue());
                userInsert.setCmonthAt(at);
                userInsert.setCtotalAt(at);
                userInsert.setCtotalBids(bids);
                userInsert.setCtotalExp(maxExp);
                userInsert.setLtime(new Date());
                userInsert.setCtime(new Date());
                userInsert.setIsFree("1");
                userInsert.setCtotalReceiveAt(at);
                userInterestsService.insert(userInsert);
            }
            //计算会员金额or会员工时
        }else {
            UserGradeRecord update=new UserGradeRecord();
            update.setExp(maxExp.intValue());
            update.setAt(at);
            update.setBizId(bizId);
            update.setUserid(userid);
            update.setRemark(remark);
            update.setState(state);
            int i=updateByBizIdAndUserid(update);
            if(i==0){
                UserGradeRecord record=this.add(userid,state,bizId,maxExp,at,remark);
            }
            //计算用户当前应该属于什么等级

            //先查出我的总积分
            BigDecimal totalExp=this.selectUserTotalExp(userid);
            List<Grade> grades= gradeService.getGradesFromCacheFirst("2");
            Grade gradeSelect=null;
            for (Grade grade : grades) {
                if(grade.getEndExp().compareTo(totalExp)>=0 && grade.getStartExp().compareTo(totalExp)<=0){
                    gradeSelect=grade;
                    break;
                }
            }
            if(gradeSelect!=null){
                //去更新用户表中的能力等级中的
                User userUpdate=new User();
                userUpdate.setUserid(userid);
                userUpdate.setGradeId(gradeSelect.getGradeId());
                userUpdate.setGradeName(gradeSelect.getGradeName());
                userService.updateSomeFieldByPk(userUpdate);
            }
            this.userInterestsService.updateAfterBidSuccess(userid,bizMonth);
        }

    }

    private BigDecimal selectUserTotalExp(String userid) {
        return baseMapper.selectUserTotalExp(userid);
    }


    private int updateByBizIdAndUserid(UserGradeRecord update){
        int i=baseMapper.updateByBizIdAndUserid(update);
        return i;
    }

    private UserGradeRecord add(String userid, String state, String bizId, BigDecimal exp, BigDecimal at , String remark){


	    UserGradeRecord userGradeRecord=new UserGradeRecord();
        userGradeRecord.setUserid(userid);
        userGradeRecord.setBizId(bizId);
        userGradeRecord.setCtime(new Date());
        userGradeRecord.setRemark(remark);
        userGradeRecord.setAt(at);
        userGradeRecord.setExp(exp.intValue());
        userGradeRecord.setDirect("1");
        userGradeRecord.setId(this.createKey("id"));
        userGradeRecord.setState(state);
        String bizMonth= DateUtils.getDate("yyyy-MM");
        userGradeRecord.setBizMonth(bizMonth);
        super.insert(userGradeRecord);
        return userGradeRecord;
    }
}

