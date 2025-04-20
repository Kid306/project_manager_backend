package com.mdp.plat.client;

import com.mdp.core.entity.Tips;
import com.mdp.core.utils.BaseUtils;
import com.mdp.micro.client.CallBizService;
import com.mdp.micro.client.ResultWrapper;
import com.mdp.plat.client.entity.PlatformVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service("mdp.plat.client.PlatClientService")
public class PlatClientService {

	@Value("${mdp.platform-id:platform-001}")
	String platformId;

	@Value("${mdp.platform-query-uri:/lcode/mdp/plat/platform/detail}")
	String platformQueryUri;


	@Value("${mdp.platform-branch-id:platform-branch-001}")
	String platfomBranchId;


	@Value("${mdp.platform-shop-id:platform-shop-001}")
	String platfomShopId;
	
	@Autowired
    CallBizService callBizServie;

	PlatformVo platformVo=null;


	Logger log= LoggerFactory.getLogger(PlatClientService.class);

	
	public PlatformVo getPlatformVo() {
		if(platformVo!=null){
			return platformVo;
		}
		//this.initPlatform();不要这个了，防止服务器未开放该接口查询时，所有接口无法访问的问题出现
		if(platformVo==null){
			platformVo=new PlatformVo();
			platformVo.setBranchId(platfomBranchId);
			platformVo.setPlatformName("平台");
			platformVo.setPlatformTitle("平台");
			platformVo.setShopId(platfomShopId);
			platformVo.setDiscountPct(100);
			platformVo.setLocationId(platformVo.getShopId()+"-0");
		}
		return platformVo;

	}

	@Scheduled(cron="0 0/5 * * * ?")
	public void initPlatform() {
		log.info("加载平台信息");
		Map<String,Object> map=new HashMap<>();
		Map<String,Object> result=callBizServie.getForMap(platformQueryUri, map);
		Tips tips=ResultWrapper.resultToTips(result);
		if(tips.isOk()){
			PlatformVo vo=BaseUtils.fromMap((Map<String, Object>) result.get("data"),PlatformVo.class);
			vo.parseExtConfigJson();
			vo.parseExtInfoList();
			this.platformVo=vo;
		}
	}


}
