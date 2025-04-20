package com.xm.core.service;

import java.util.List;

public interface XmRecordServiceApi {

    List<String>  selectChangeProjectIds();


    List<String>  selectChangeProductIds();


    List<String>  selectChangeIterationIds();


    List<String>  selectChangeBranchIds();

    List<String> selectChangeCollectIds();

}
