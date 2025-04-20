package com.mdp.login.util;

import org.springframework.util.StringUtils;

public class LockUtil {

    /**
     *
     锁定类型:锁定类型:
     0-注册等待邮箱验证，
     1-注册等待修改初始密码，
     2-注册等待验证手机号码，
     3-密码高风险，等待重新修改密码，
     9-业务需要锁定禁止登录，
     L-账户被锁定，请联系客服,
     7-机构不存在
     8-机构不存在8-机构账户未启用
     注意不能超过两位数
     */
    public static String getLockMsgByLockType(String lockType){
        String statusText=new String();
        if(!StringUtils.hasText(lockType)){
            return statusText;
        }
        if("0".equals(lockType)){
            statusText="注册等待邮箱验证";
        }else if("1".equals(lockType)){
            statusText="注册等待修改初始密码";
        }else if("2".equals(lockType)){
            statusText="注册等待验证手机号码";
        }else if("3".equals(lockType)){
            statusText="密码高风险，等待重新修改密码";
        }else if("9".equals(lockType)){
            statusText="业务需要锁定禁止登录";
        }else if("7".equals(lockType)){
            statusText="机构账户不存在";
        }else if("8".equals(lockType)){
            statusText="机构账户未启用";
        }else if("L".equals(lockType)){
            statusText="账户被锁定，请联系客服";
        }else{
            statusText="账户被锁定，请联系客服";
        }
        return statusText;
    }
}
