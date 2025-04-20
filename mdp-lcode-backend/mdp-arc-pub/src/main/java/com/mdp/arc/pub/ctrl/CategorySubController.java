package com.mdp.arc.pub.ctrl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 为了兼容就接口 arc/category/**的请求
 ***/
@RestController("mdp.arc.CategorySubController")
@RequestMapping(value="/*/arc/category")
@Deprecated
public class CategorySubController extends CategoryController {

}
