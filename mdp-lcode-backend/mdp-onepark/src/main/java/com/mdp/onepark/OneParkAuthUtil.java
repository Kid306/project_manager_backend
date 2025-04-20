package com.mdp.onepark;

import cn.hutool.core.codec.Base64;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mdp.onepark.config.OneparkAuthConfig;
import com.mdp.onepark.dto.OneParkResDto;
import com.mdp.onepark.dto.OneParkTokenResDto;
import com.mdp.onepark.dto.OneParkUserResDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OneParkAuthUtil {
    @Autowired
    private OneparkAuthConfig oneparkAuthConfig;

    /***
     * @description 获取当前登录用户信息
     */
    private OneParkUserResDto getUserInfoByToken(String token) {
        try {
            log.info(oneparkAuthConfig.toString());
            CloseableHttpClient httpClient = HttpClients.createDefault();
            String url = oneparkAuthConfig.getBaseUrl() + oneparkAuthConfig.getApiUsersCurrent();
            log.info("url:{}", url);
            HttpGet httpGet = new HttpGet(url);

            httpGet.setHeader("Authorization", "Bearer " + token);

            CloseableHttpResponse response = httpClient.execute(httpGet);
            JSONObject jsonObject = JSON.parseObject(EntityUtils.toString(response.getEntity()));
            log.info("jsonObject:{}", jsonObject.toString());
            OneParkResDto<JSONObject> result = JSON.parseObject(jsonObject.toJSONString(), OneParkResDto.class);
            log.info("result:{}", result.toString());
            OneParkUserResDto oneParkUserResDto = JSON.parseObject(result.getData().toJSONString(), OneParkUserResDto.class);
            return oneParkUserResDto;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /***
     * @description 获取token
     */
    private OneParkTokenResDto getTokenByCode(String code) {
        try {
            log.info(oneparkAuthConfig.toString());
            CloseableHttpClient httpClient = HttpClients.createDefault();
            String url = oneparkAuthConfig.getBaseUrl() + oneparkAuthConfig.getApiOauthToken();
            url = url + "?grant_type=authorization_code&code=" + code;
            log.info("url:{}", url);
            HttpPost httpPost = new HttpPost(url);

            String authorization = "Basic " + Base64.encode(oneparkAuthConfig.getClientId() + ":" + oneparkAuthConfig.getClientSecretStr());
            httpPost.setHeader("Authorization", authorization);
            log.info("authorization:{}", authorization);

            CloseableHttpResponse response = httpClient.execute(httpPost);
            JSONObject jsonObject = JSON.parseObject(EntityUtils.toString(response.getEntity()));
            log.info("jsonObject:{}", jsonObject.toString());
            OneParkTokenResDto result = JSON.parseObject(jsonObject.toJSONString(), OneParkTokenResDto.class);
            log.info("result:{}", result.toString());
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /***
     * @description 退出登录
     */
    public void logout() {
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            String url = oneparkAuthConfig.getBaseUrl() + oneparkAuthConfig.getApiLogout();
            url = url + "?client_id=" + oneparkAuthConfig.getClientId();
            HttpGet httpGet = new HttpGet(url);
            httpClient.execute(httpGet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public OneParkUserResDto getUserInfoByCode(String code) {
        OneParkTokenResDto oneParkTokenResDto = this.getTokenByCode(code);
        if (oneParkTokenResDto == null) {
            return null;
        }
        OneParkUserResDto oneParkUserResDto = this.getUserInfoByToken(oneParkTokenResDto.getAccess_token());
        return oneParkUserResDto;
    }

}
