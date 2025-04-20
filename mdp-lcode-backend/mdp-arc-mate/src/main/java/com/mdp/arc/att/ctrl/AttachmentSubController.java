package com.mdp.arc.att.ctrl;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 为了兼容原来的arc/file/*的请求，创建子类
 ***/
@Deprecated
@RestController("mdp.arc.AttachmentSubController")
@RequestMapping(value="/*/arc/file")
public class AttachmentSubController  extends AttachmentController{

}
