package com.mdp.arc.img.ctrl;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 为了兼容原来的arc/image
 ***/
@RestController("mdp.arc.ImageSubController")
@RequestMapping(value="/*/arc/image")
@Api(tags={"图片素材库操作接口"})
@Deprecated
public class ImageSubController extends ImageController {

}
