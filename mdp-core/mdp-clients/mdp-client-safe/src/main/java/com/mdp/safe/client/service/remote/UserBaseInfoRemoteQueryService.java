package com.mdp.safe.client.service.remote;

import com.mdp.safe.client.entity.DeptPostRole;
import com.mdp.safe.client.entity.User;

import java.util.List;
import java.util.Map;

/**
 * 用户查询等远程访问接口
 * 如果没有自定义实现类，将使用默认的实现类
 * @see DefaultUserBaseInfoRemoteQueryService
 */
public interface UserBaseInfoRemoteQueryService {

    /**
     * 前端用户可以通过userid,unionid,email,phoneNo,tpaOpenId等编号进行登录，登录前需要将上述编号对应的userid查出来，
     * 转换成userid进行登录。
     * 由于mdp平台属于主账户-分账户的体系，除了userid是唯一外，其它都有可能有多个，
     * @param userloginid 用于登录的用户编号，电话号码，第三方账户、邮箱等
     * @param idType userid,unionid,email,phoneNo,tpaOpenId,all
     * @param extParams
     * @return
     */
    List<User> queryByUserloginid(String userloginid,String idType, Map<String,Object> extParams);

    User getUserByUserid(String userid, Map<String,Object> extParams);

    List<DeptPostRole> getUserDeptPostRoles(String userid, Map<String,Object> extParams);
}
