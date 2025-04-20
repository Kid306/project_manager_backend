package com.mdp.core.ctrl;

import com.mdp.core.entity.Result;
import com.mdp.core.utils.ObjectTools;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 连通性测试接口
 */
@RestController
public class LinkTest {

    @RequestMapping(value="/linkTest",method= RequestMethod.GET)
    public Result  linkTest( @RequestParam Map<String,Object> params){
        String username= (String) params.get("username");
        if(ObjectTools.isEmpty(username)){
            username="虽然不知道您叫什么名字，请您放心，唛盟平台已经成功启动了";
        }
        return Result.ok().setData(username);
    }
}
