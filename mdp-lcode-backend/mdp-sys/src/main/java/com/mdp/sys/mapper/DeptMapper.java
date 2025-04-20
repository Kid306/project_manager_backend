package com.mdp.sys.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.safe.client.entity.User;
import com.mdp.sys.entity.Dept;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface DeptMapper extends BaseMapper<Dept> {

    /**
     * 自定义查询，支持多表关联
     * @param page 分页条件
     * @param ew 一定要，并且必须加@Param("ew")注解
     * @param ext 如果xml中需要根据某些值进行特殊处理，可以通过这个进行传递，非必须，注解也可以不加
     * @return
     */
    List<Map<String,Object>> selectListMapByWhere(IPage page, @Param("ew") QueryWrapper ew,@Param("ext") Map<String,Object> ext);

    List<Dept> listSubDeptObjects(String deptid);

    Dept selectTopDeptByUserid(String userid);

    List<Dept> selectAncestorByDeptid(Dept rootDept);

    List<Map<String, Object>> selectDeptListAndCount(Map<String, Object> deptid);

    List<Map<String, Object>> selectUsersByDeptid(Map<String, Object> condition);

    Dept selectTopDept(Dept dept);

    List<Dept> selectOffspringByDeptid(Dept rootDept);

    Dept selectFatherByDeptid(Dept dept);

    int updateUser(List<User> user);

    List<Map<String, Object>> selectUsersByDeptids(List<Dept> deptList);

    List<Dept> searchDept(Map<String, Object> map);

    List<Map<String, Object>> selectCompanyUser(Map<String, Object> map);

    List<Map<String, Object>> selectWeixinSocsec(Map<String, Object> map);

    void insertProcessApprova(Map<String, Object> flowVars);

    void updateProcessApprova(Map<String, Object> flowVars);

    List<Map<String, Object>> selectChildrenByDeptid(Dept dept);

    void batchChangeParent(Map<String, Object> map);
}

