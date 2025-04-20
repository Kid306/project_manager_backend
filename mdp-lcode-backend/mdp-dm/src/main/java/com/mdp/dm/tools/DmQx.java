package com.mdp.dm.tools;

public enum DmQx {
    DM_INSERT,DM_UPDATE,DM_DELETE,DM_SELECT,DM_CREATE,DM_ALERT,DM_DROP,DM_META_QUERY;

    public static DmQx fromDLType(DLType dlType){
         return DmQx.valueOf("DM_"+dlType.name());
    }
}
