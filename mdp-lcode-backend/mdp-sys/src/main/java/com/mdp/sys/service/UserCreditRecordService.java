package com.mdp.sys.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.service.BaseService;
import com.mdp.sys.entity.Credit;
import com.mdp.sys.entity.User;
import com.mdp.sys.entity.UserCreditRecord;
import com.mdp.sys.mapper.UserCreditRecordMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 父类已经支持增删改查操作,因此,即使本类什么也不写,也已经可以满足一般的增删改查操作了.<br> 
 * 组织 com  顶级模块 mdp 大模块 sys 小模块 <br>
 * 实体 UserCreditRecord 表 sys_user_credit_record 当前主键(包括多主键): userid; 
 ***/
@Service("mdp.sys.userCreditRecordService")
public class UserCreditRecordService extends BaseService<UserCreditRecordMapper,UserCreditRecord>{
	static Logger logger =LoggerFactory.getLogger(UserCreditRecordService.class);

	@Autowired
	CreditService creditService;

	@Autowired
	UserService userService;

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
	 * 引起信用等级变化的业务分类 bizType
	 * 1-个人实名认证，10
	 * 2-企业认证，10
	 * 3-银行卡认证，5
	 * 4-手机号认证，5
	 * 5-邮箱认证，5
	 * 6-微信认证，5
	 * 10-加入三保服务保障，20,40,60
	 * 11-完成平台任务，每个任务3分(舍弃、不再支持)
	 * 12-邀请用户实名注册，每个注册用户得2分(舍弃、不再支持)
	 * 13-邀请用户完成任务。每个任务完成得3分(舍弃、不再支持)
	 * 14-其它
	 * 如果是
	 * @param userid
	 * @param bizType
	 * @param bizId
	 */
	public UserCreditRecord initCreditRecordByBizType(String userid,String bizType,String bizId,String remark){
		UserCreditRecord creditRecord=new UserCreditRecord();
		creditRecord.setBizType(bizType);
		creditRecord.setUserid(userid);
		creditRecord.setBizId(bizId);
		creditRecord.setCtime(new Date());
		creditRecord.setRemark(remark);
		creditRecord.setId(this.createKey("id"));
		if("1".equals(bizType)){
			creditRecord.setScore(10);
		}else if("2".equals(bizType)){
			creditRecord.setScore(10);
		}else if("3".equals(bizType)){
			creditRecord.setScore(5);
		}else if("4".equals(bizType)){
			creditRecord.setScore(5);
		}else if("5".equals(bizType)){
			creditRecord.setScore(5);
		}else if("6".equals(bizType)){
			creditRecord.setScore(5);
		}else if("10".equals(bizType)){
			if(StringUtils.hasText(bizId)){
				if("1".equals(bizId)){//青铜
					creditRecord.setScore(20);
				}else if("2".equals(bizId)){//白银
					creditRecord.setScore(40);
				}else if("3".equals(bizId)){//黄金
					creditRecord.setScore(60);
				}else{
					creditRecord.setScore(0);
				}
			}else{
				creditRecord.setScore(0);
			}

		}else if("11".equals(bizType)){
			creditRecord.setScore(3);
		}else if("12".equals(bizType)){
			creditRecord.setScore(2);
		}else if("13".equals(bizType)){
			creditRecord.setScore(3);
		}else if("14".equals(bizType)){
			creditRecord.setScore(1);
		}
		return creditRecord;

	}

	/**
	 * 登记行用积分流水，并更新用户的信用等级及信用分
	 * 引起信用等级变化的业务分类 bizType
	 * 1-个人实名认证，10
	 * 2-企业认证，10
	 * 3-银行卡认证，5
	 * 4-手机号认证，5
	 * 5-邮箱认证，5
	 * 6-微信认证，5
	 * 10-加入三保服务保障，20,40,60
	 * 11-完成平台任务，每个任务3分(舍弃、不再支持)
	 * 12-邀请用户实名注册，每个注册用户得2分(舍弃、不再支持)
	 * 13-邀请用户完成任务。每个任务完成得3分(舍弃、不再支持)
	 * 14-其它
	 * 如果是
	 * @param userid
	 * @param bizType
	 * @param bizId
	 */
	public void addCreditScore(String userid,String bizType,String bizId,String remark){
		UserCreditRecord userCreditRecord=this.initCreditRecordByBizType(userid,bizType,bizId,remark);
		//统计每个业务分类的总分是否已经超出最高分。
		UserCreditRecord bizTypeScore=baseMapper.selectCredScoreGroupByBizType(map("userid",userCreditRecord.getUserid(),"bizType",userCreditRecord.getBizType()));
		if(bizTypeScore!=null && bizTypeScore.getScore()>0){
			if("10".equals(userCreditRecord.getBizType())){
				userCreditRecord.setScore(userCreditRecord.getScore()-bizTypeScore.getScore());
			}else if("11".equals(userCreditRecord.getBizType())){
				if(bizTypeScore.getScore()==60){
					return;
				} else if(bizTypeScore.getScore()+userCreditRecord.getScore()>60){
					userCreditRecord.setScore(bizTypeScore.getScore()+userCreditRecord.getScore()-60);
				}
			}else if("12".equals(userCreditRecord.getBizType())){
				if(bizTypeScore.getScore()==30){
					return;
				} else if(bizTypeScore.getScore()+userCreditRecord.getScore()>30){
					userCreditRecord.setScore(bizTypeScore.getScore()+userCreditRecord.getScore()-30);
				}
			}else if("13".equals(userCreditRecord.getBizType())){
				if(bizTypeScore.getScore()==30){
					return;
				}else if(bizTypeScore.getScore()+userCreditRecord.getScore()>30){
					userCreditRecord.setScore(bizTypeScore.getScore()+userCreditRecord.getScore()-30);
				}
			}else{
				return;
			}
		}
		userCreditRecord.setDirect("1");
		super.insert(userCreditRecord);
		this.updateUserCreditAndScore(userCreditRecord.getUserid());
	}

	public void updateUserCreditAndScore(String userid){

		Integer creditScore=baseMapper.sumCreditScoreByUserid(userid);
		if(creditScore==null){
			creditScore=0;
		}
		Credit credit=creditService.selectOneByCreditScore(creditScore);
		if(credit==null){
			return;
		}
		User user=new User();
		user.setUserid(userid);
		user.setCreditId(credit.getId());
		user.setCreditScore(creditScore);
		userService.updateSomeFieldByPk(user);
	}


}

