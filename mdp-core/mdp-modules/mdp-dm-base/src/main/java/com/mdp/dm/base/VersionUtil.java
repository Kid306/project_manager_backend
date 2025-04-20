package com.mdp.dm.base;

import com.mdp.core.err.BizException;

public class VersionUtil {
    public static boolean supportVersion(boolean isSupport) {
        if(isSupport){
            return true;
        }
        throw new BizException("version-not-support","当前版本不支持，请联系唛盟客服，购买商业版");
    }
}
