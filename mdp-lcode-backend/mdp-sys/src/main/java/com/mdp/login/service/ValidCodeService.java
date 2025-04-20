package com.mdp.login.service;

import com.mdp.core.service.BaseService;
import com.mdp.login.entity.ValidCode;
import com.mdp.login.mapper.ValidCodeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 父类已经支持增删改查操作,因此,即使本类什么也不写,也已经可以满足一般的增删改查操作了.<br> 
 * 组织 com  顶级模块 mk 大模块 mem 小模块 <br>
 * 实体 MemberValidCode 表 MK.mem_member_valid_code 当前主键(包括多主键): id; 
 ***/
@Service
public class ValidCodeService extends BaseService<ValidCodeMapper, ValidCode> {
	static Logger logger =LoggerFactory.getLogger(ValidCodeService.class);

}

