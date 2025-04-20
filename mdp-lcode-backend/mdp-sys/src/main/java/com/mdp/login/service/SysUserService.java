package com.mdp.login.service;

import com.mdp.core.service.BaseService;
import com.mdp.core.utils.BaseUtils;
import com.mdp.login.entity.SysUser;
import com.mdp.login.mapper.SysUserMapper;
import com.mdp.safe.client.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 父类已经支持增删改查操作,因此,即使本类什么也不写,也已经可以满足一般的增删改查操作了.<br> 
 * 组织 com  顶级模块 mdp 大模块 oauth2.client.user 小模块 <br>
 * 实体 SysUser 表 sys_user 当前主键(包括多主键): userid; 
 ***/
@Service
public class SysUserService extends BaseService<SysUserMapper, SysUser> {
	static Logger logger =LoggerFactory.getLogger(SysUserService.class);




    public void userRegister(User user) {
		save(BaseUtils.fromMap(user,SysUser.class));
    }

	public void userUpdate(User user) {
		updateById(BaseUtils.fromMap(user,SysUser.class));
	}

	public void updatePassword(User user) {
		baseMapper.updateUserPassword(user);
	}

	public void resetPasswordByPhoneno(User user) {
		baseMapper.updateUserPassword(user);
	}

	public void resetPasswordByEmail(User user) {
		baseMapper.updateUserPassword(user);
	}

	public void userUnregister(User user) {
		baseMapper.unregister(user);
	}
}

