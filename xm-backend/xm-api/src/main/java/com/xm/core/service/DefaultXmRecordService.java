package com.xm.core.service;

import com.mdp.core.err.BizException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class DefaultXmRecordService implements XmRecordServiceApi{

    public List<String>  selectChangeProjectIds(){
        checkVersion();
        return new ArrayList<>();
    };


    public List<String>  selectChangeProductIds(){

        checkVersion();
        return new ArrayList<>();
    };


    public List<String>  selectChangeIterationIds(){

        checkVersion();
        return new ArrayList<>();
    };


    public List<String>  selectChangeBranchIds(){

        checkVersion();
        return new ArrayList<>();
    }

    @Override
    public List<String> selectChangeCollectIds() {
        checkVersion();
        return Collections.emptyList();
    }

    ;

    public void checkVersion(){
        throw new BizException("not-support-version","请购买商业版");
    }
}
