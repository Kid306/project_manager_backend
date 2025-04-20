package com.mdp.mallm.shop.ctrl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.entity.Result;
import com.mdp.core.err.BizException;
import com.mdp.core.query.QueryTools;
import com.mdp.mallm.shop.entity.ShopLocation;
import com.mdp.mallm.shop.service.ShopLocationService;
import io.swagger.annotations.Api;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


@RestController("mdp.mall.shop.shopLocationController")
@RequestMapping(value="/*/mallm/shop/shopLocation")
@Api(tags={"门店地址表操作接口"})
public class ShopLocationController {
	static Log logger=LogFactory.getLog(ShopLocationController.class);
	
	@Autowired
	private ShopLocationService shopLocationService;
	
	
	/**
	 * 根据sys的dept表id查询这个部门下面的门店
	 * @author lyk
	 * @cdate 2019/9/6 10:09
	 * */
	@RequestMapping(value="/selectshopLocationBySysDeptId",method=RequestMethod.POST)
	public Result selectshopLocationBySysDeptId(@RequestBody Map<String,Object> params) {
		
		
		IPage page=QueryTools.initPage(params);
 		try {
			String branchId=(String)params.get("branchId");
			String deptid=(String)params.get("deptid");
			if(StringUtils.isEmpty(branchId)) {
				throw new BizException("请传递branchId");
			}
			if(params.containsKey("isHeadLocation")){
				params.put("isHead",params.get("isHeadLocation"));
			}
			QueryWrapper<ShopLocation> qw= QueryTools.initQueryWrapper(ShopLocation.class,params);
			List<Map<String,Object>> result= shopLocationService.selectListMapByWhere(page,qw,params);
			return Result.ok().setData( result);
		}catch(Exception e) {
			logger.error("",e);
            return Result.error(e);
		}
		

	}
	
}
