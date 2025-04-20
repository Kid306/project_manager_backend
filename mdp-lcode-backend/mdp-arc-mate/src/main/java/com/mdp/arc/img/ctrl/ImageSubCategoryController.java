package com.mdp.arc.img.ctrl;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 为了兼容 arc/imageCategory这种请求
 ***/
@RestController("mdp.arc.iamge.ImageSubCategoryController")
@RequestMapping(value="/*/arc/imageCategory")
@Api(tags={"图片分类操作接口"})
@Deprecated
public class ImageSubCategoryController  extends ImageCategoryController{

}
