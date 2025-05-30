// package com.mdp.app.service;
//
// import com.mdp.core.entity.Tips;
// import com.mdp.micro.client.CallBizService;
// import org.apache.commons.logging.Log;
// import org.apache.commons.logging.LogFactory;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.http.ResponseEntity;
// import org.springframework.stereotype.Service;
//
// import java.util.HashMap;
// import java.util.Map;
//
// @Service
// public class WxpubClientService {
// 	Log log=LogFactory.getLog(WxpubClientService.class);
// 	@Autowired
// 	CallBizService restTemplate;
//
//
// 	@Value("${mdp.api-gate:http://gate}")
// 	String apiGate="";//http://localhost:7041
//
//
// 	  /**
//        *通知wxpub清理相关缓存
//        **/
//     public Tips clearAppTpAuth(String authId){
// 			String url = apiGate + "/wxpub/cache/clearAppTpAuthCache";
// 			ResponseEntity<Map> postForEntity = null;
// 			Map<String,Object> params=new HashMap<>();
// 			params.put("authId", authId);
// 			Tips tips=restTemplate.postForTips(url,params);
// 			return tips;
//     }
//
// }
