package com.mdp.sys.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.safe.client.entity.Role;
import com.mdp.sys.entity.User;
import com.mdp.sys.entity.UserTpa;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserMapper extends BaseMapper<User> {

    /**
     * 自定义查询，支持多表关联
     * @param page 分页条件
     * @param ew 一定要，并且必须加@Param("ew")注解
     * @param ext 如果xml中需要根据某些值进行特殊处理，可以通过这个进行传递，非必须，注解也可以不加
     * @return
     */
    List<Map<String,Object>> selectListMapByWhere(IPage page, @Param("ew") QueryWrapper ew,@Param("ext") Map<String,Object> ext);

    List<Role> loadUserRolesByUserid(Map<String, Object> params);

    User selectOneObjectWithPassword(User user);

    List<Map<String, Object>> selectListSimpleMapByWhere(IPage page,Map<String, Object> user);

    List<User> selectListByPhonenosAndBranchId(Map<String, Object> map);

    List<User> selectListByEmailsAndBranchId(Map<String, Object> map);

    void setUsersToBranchAdm(Map<String, Object> map);

    void setUsersUnBranchAdm(Map<String, Object> map);

    Map<String, Object> detailWithInterests(String userid);

    Long checkIdCardNoExistsAndValid(Map<String, Object> map);

    long interestsOverUpdate(User userUpdate);

    List<Map<String, Object>> listUserNames(Map<String, Object> user);

    List<User> selectListByTpaOpenidOrTpaUnionidOrPhoneno(UserTpa userTpa);
}

