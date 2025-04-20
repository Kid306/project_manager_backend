package com.mdp.sys.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.service.BaseService;
import com.mdp.core.utils.BaseUtils;
import com.mdp.sys.entity.UserSkill;
import com.mdp.sys.entity.UserSkillsVo;
import com.mdp.sys.mapper.UserSkillMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
/**
 * 父类已经支持增删改查操作,因此,即使本类什么也不写,也已经可以满足一般的增删改查操作了.<br> 
 * 组织 com  顶级模块 mdp 大模块 sys 小模块 <br>
 * 实体 UserSkill 表 sys_user_skill 当前主键(包括多主键): userid,skill_id; 
 ***/
@Service("mdp.sys.userSkillService")
public class UserSkillService extends BaseService<UserSkillMapper,UserSkill>{

    @Autowired
    UserService userService;

	static Logger logger =LoggerFactory.getLogger(UserSkillService.class);

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

    public void batchEdit(UserSkillsVo userSkillsVo) {
        UserSkill userSkillDelete=new UserSkill();
        userSkillDelete.setUserid(userSkillsVo.getUserid());
        this.deleteByWhere(userSkillDelete);
        //后保存
        if(userSkillsVo.getSkills()!=null && userSkillsVo.getSkills().size()>0){
            List<Map<String,Object>> skills=userSkillsVo.getSkills();
            List<UserSkill> userSkillList=new ArrayList<>();
            for (int i = skills.size() - 1; i >= 0; i--) {
                Map<String,Object> skill = skills.get(i);
                skill.put("skillId",skill.get("id"));
                UserSkill userSkill = BaseUtils.fromMap(skill,UserSkill.class);
                userSkill.setUserid(userSkillsVo.getUserid());
                userSkillList.add(userSkill);
            }
            String skillNames=skills.stream().map(k->(String)k.get("skillName")).collect(Collectors.joining(","));
            String skillIds=skills.stream().map(k->(String)k.get("id")).collect(Collectors.joining(","));
            super.batchInsert(userSkillList);
            this.userService.editSomeFields(map("userids", Arrays.asList(userSkillsVo.getUserid()),"skillNames",skillNames,"skillIds",skillIds));
        }else{
            this.userService.editSomeFields(map("userids", Arrays.asList(userSkillsVo.getUserid()),"skillNames","","skillIds",""));
        }
    }
}

