package com.mdp.sys.vo;

import com.mdp.sys.entity.UserTpa;
import lombok.Data;

/**
 * 用户扫描邀请码后同步信息到sys系统进行系统更新
 */
@Data
public class InviteScanVo {
    /**
     * 邀请码
     */
    String inviteId;
    /**
     * 第三方系统用户信息
     */
    UserTpa tpa;



    /**
     * 是否已确定tpa已存在数据库
     */
    boolean tpadbExists=false;

    public boolean getTpadbExists() {
        return tpadbExists;
    }

    public void setTpadbExists(boolean tpadbExists) {
        this.tpadbExists = tpadbExists;
    }
}
