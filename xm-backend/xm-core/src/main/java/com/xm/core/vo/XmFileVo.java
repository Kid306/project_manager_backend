package com.xm.core.vo;

import com.xm.core.entity.XmAttachment;
import com.xm.core.entity.XmFile;
import io.swagger.annotations.ApiModel;

import java.util.List;

@ApiModel(description="xm_task")
public class XmFileVo extends XmFile {
    List<XmAttachment> attachment;

    public List<XmAttachment> getAttachment() {
        return attachment;
    }

    public void setAttachment(List<XmAttachment> attachment) {
        this.attachment = attachment;
    }
}
