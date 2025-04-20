package com.mdp.micro.client;

import cn.hutool.extra.spring.SpringUtil;
import com.mdp.core.entity.Tips;
import com.mdp.core.utils.RequestUtils;
import com.mdp.micro.client.header.MicroHeader;
import com.mdp.micro.client.resolver.BasicTokenResolver;
import com.mdp.micro.client.resolver.BearerTokenResolver;
import com.mdp.micro.client.resolver.DefaultBasicTokenResolver;
import com.mdp.micro.client.resolver.DefaultBearerTokenResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.client.WebClient;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * 微服务调用
 * 如果是Oauth2，默认使用带password模式的客户端
 * 在无用户参与的场景下（比如后台自动运行的任务需要调其它微服务），可能需要使用client模式，此时，可以重新new一个 callBizService，根据需要注入不同的RestTemplate即可
 */
@Service("com.mdp.micro.client.CallBizService")
public class CallBizService {
	
	@Value("${spring.profiles.active:dev}")
	String profiles="dev";

	BearerTokenResolver bearerTokenResolver;

	@Autowired
	WebClient webClient;

	BasicTokenResolver basicTokenResolver;

	Consumer<HttpHeaders> initHeader;
	boolean defHeaderInit=false;


	public CallBizService(){

	}
	
	Logger logger = LoggerFactory.getLogger(CallBizService.class);
	
	@Value("${mdp.api-gate:http://gate}")//云环境下 
	//@Value("${mdp.api-gate:https://www.qingqinkj.com/api/m1}") //本地调试
	String apiGate="";

	public Map<String,Object> getForMap(String restUrl){
		 return getForMap(restUrl,new HashMap<>());
	}
	public Tips getForTips(String restUrl){
		return getForTips(restUrl,new HashMap<>());
	}
	public Map<String,Object> getForMap(String restUrl,Map<String,Object> params){
		restUrl=UrlWrapper.urlWrapper(restUrl);
		restUrl=UrlWrapper.withApiGate(restUrl,this.apiGate);
		Consumer<HttpHeaders> headers=this.createAuthorization();
		if(headers==null){
			Map result =  getInnerWebClient().get().uri(restUrl,params).retrieve().bodyToMono(Map.class).block();
			return ResultWrapper.resultToMap(result);
		}else{
			Map result =  getInnerWebClient().get().uri(restUrl,params).headers(headers).retrieve().bodyToMono(Map.class).block();
			return ResultWrapper.resultToMap(result);
		}

	}
	public Tips getForTips(String restUrl, Map<String,Object>  params){
		restUrl=UrlWrapper.urlWrapper(restUrl);
		restUrl=UrlWrapper.withApiGate(restUrl,this.apiGate);

		Consumer<HttpHeaders> headers=this.createAuthorization();
		if(headers==null){
			Map result =  getInnerWebClient().get().uri(restUrl,params).retrieve().bodyToMono(Map.class).block();
			return ResultWrapper.resultToTips(result);
		}else{
			Map result =  getInnerWebClient().get().uri(restUrl,params).headers(headers).retrieve().bodyToMono(Map.class).block();
			return ResultWrapper.resultToTips(result);
		}



	}
	public Map<String, Object> postForMap(String restUrl, Object params){
		restUrl=UrlWrapper.urlWrapper(restUrl);
		restUrl=UrlWrapper.withApiGate(restUrl,this.apiGate);



		Consumer<HttpHeaders> headers=this.createAuthorization();
		if(headers==null){
			Map result =  getInnerWebClient().post().uri(restUrl).bodyValue(params).retrieve().bodyToMono(Map.class).block();

			return ResultWrapper.resultToMap(result);
		}else{
			Map result =  getInnerWebClient().post().uri(restUrl).headers(headers).bodyValue(params).retrieve().bodyToMono(Map.class).block();

			return ResultWrapper.resultToMap(result);
		}

	}
	public Tips postForTips(String restUrl, Object params){
		restUrl=UrlWrapper.urlWrapper(restUrl);
		restUrl=UrlWrapper.withApiGate(restUrl,this.apiGate);


		Consumer<HttpHeaders> headers=this.createAuthorization();
		if(headers==null){

			Map result =  getInnerWebClient().post().uri(restUrl).bodyValue(params).retrieve().bodyToMono(Map.class).block();
			return ResultWrapper.resultToTips(result);
		}else{
			Map result =  getInnerWebClient().post().uri(restUrl).headers(headers).bodyValue(params).retrieve().bodyToMono(Map.class).block();
			return ResultWrapper.resultToTips(result);
		}

	}

	@Nullable
	public <T> T getForObject(String url, Class<T> responseType, Map<String, ?> uriVariables) {
		 url=UrlWrapper.urlWrapper(url);
		 url=UrlWrapper.withApiGate(url,this.apiGate);
		Consumer<HttpHeaders> headers=this.createAuthorization();
		if(headers==null){
			T result =  getInnerWebClient().get().uri(url,uriVariables).retrieve().bodyToMono(responseType).block();
			return result;
		}else{
			T result =  getInnerWebClient().get().uri(url,uriVariables).headers(headers).retrieve().bodyToMono(responseType).block();
			return result;
		}

	}

	@Nullable
	public <T> T postForObject(String url, @Nullable Object request, Class<T> responseType, Map<String, ?> uriVariables) {
		url=UrlWrapper.urlWrapper(url);
		url=UrlWrapper.withApiGate(url,this.apiGate);

		Consumer<HttpHeaders> headers=this.createAuthorization();
		if(headers==null){
			T result =  getInnerWebClient().post().uri(url,uriVariables).bodyValue(request).retrieve().bodyToMono(responseType).block();
			return result;
		}else{
			T result =  getInnerWebClient().post().uri(url,uriVariables).headers(headers).bodyValue(request).retrieve().bodyToMono(responseType).block();
			return result;
		}

	}

	public Consumer<HttpHeaders> createAuthorization(){
		if(this.defHeaderInit==false){
			boolean hadHeader=false;
			Consumer<HttpHeaders> consumer=null;
			Map<String,MicroHeader> microHeader= SpringUtil.getBeansOfType(MicroHeader.class);
			if(microHeader!=null && microHeader.size()>0){
				for (MicroHeader header : microHeader.values()) {
					Consumer<HttpHeaders> headerConsumer=header.initHeader();
					if(headerConsumer!=null){
						hadHeader=true;
						if(consumer==null){
							consumer=headerConsumer;
						}else{
							consumer.andThen(headerConsumer);
						}

					}
				}
			}else{
				this.initHeader=null;
			}
			this.defHeaderInit=true;
			if(hadHeader==true){
				this.initHeader=consumer;
			}else{
				this.initHeader=null;
			}
		}


		if(RequestUtils.getRequest()==null){
			return this.initHeader;
		};
		Consumer<HttpHeaders> consumer=null;
		Consumer<HttpHeaders> consumerBearer=this.resolverBearerAuthorization();
		if(consumerBearer!=null){
			consumer=consumerBearer;
		}
		Consumer<HttpHeaders> consumerBasic=resolverBasicAuthorization();
		if(consumerBasic!=null){
			if(consumer==null){
				consumer=consumerBasic;
			}else{
				consumer.andThen(consumerBasic);
			}
		}
		if(this.initHeader!=null){
			if(consumer==null){
				consumer=this.initHeader;
			}else{
				consumer.andThen(this.initHeader);
			}
		}
		return consumer;
	}

	/**
	 * 解析前端请求头中的Bearer值 Authorization: Bearer tokenValue
	 */
	public Consumer<HttpHeaders>  resolverBearerAuthorization(){
 		String tokenValue=bearerTokenResolver.resolve(RequestUtils.getRequest());
		logger.debug("Bearer:---->:"+tokenValue);
		if(StringUtils.hasText(tokenValue)){
			return x->{
				x.setBearerAuth(tokenValue);
			};
		}else{
			logger.debug("Bearer:----> null");
			return  null;
		}

	}
	/**
	 * 解析前端请求头中的Basic值 Authorization: Basic tokenValue
	 */
	public Consumer<HttpHeaders>  resolverBasicAuthorization(){

		String tokenValue=basicTokenResolver.resolve(RequestUtils.getRequest());
		logger.debug("Basic:---->"+tokenValue);
		if(StringUtils.hasText(tokenValue)){
			return x->{
				x.setBasicAuth(tokenValue);
			};
		}else {
			logger.debug("Basic:----> null ");
			return null;
		}

	}

	public String getApiGate() {
		return apiGate;
	}

	public void setApiGate(String apiGate) {
		this.apiGate = apiGate;
	}
	@PostConstruct
	public void init(){
		if(this.bearerTokenResolver==null){
			this.bearerTokenResolver=new DefaultBearerTokenResolver();
		}
		if(basicTokenResolver==null){
			basicTokenResolver=new DefaultBasicTokenResolver();
		}
	}

	protected WebClient getInnerWebClient(){
		return this.webClient;
	}
}
