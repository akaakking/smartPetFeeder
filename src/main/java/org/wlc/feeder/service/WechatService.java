package org.wlc.feeder.service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.wlc.feeder.constant.GsonSingleton;
import org.wlc.feeder.dto.wechat.UserInfo;
import org.wlc.feeder.enums.BizExceptionCodeEnum;
import org.wlc.feeder.exception.BizException;
import org.wlc.feeder.util.HttpUtil;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.concurrent.ExecutionException;

/**
 * //TODO add class commment here
 *
 * @Author wfh
 * @Date 2024/4/20 下午6:17
 */
@Slf4j
@Service
public class WechatService {
    // todo
    @Value("${wechat.appId}")
    private String appId;

    @Value("${wechat.appSecret}")
    private String appSecret;

    private String GET_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code";

    private String GET_USER_INFO_URL = "https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s&lang=zh_CN";

    LoadingCache<String, Object> ACCESS_TOKEN_CACHE = CacheBuilder.newBuilder()
            .maximumSize(100)
            .build(new CacheLoader<String, Object>() {
                @Override
                public Object load(String code) throws Exception {
                    return getAccessToken(code);
                }
            });

    public UserInfo getUserInfo(String code, String openId) throws ExecutionException {
        String url = null;
        url = String.format(GET_USER_INFO_URL, ACCESS_TOKEN_CACHE.get(code), openId);

        String response = null;
        try {
            response = HttpUtil.get(url);
        } catch (Exception e) {
            log.error("getUserInfo error: {}", e.getMessage());
            throw new RuntimeException(e);
        }

        log.info("get userinfo response {} , from wechat", response);

        return GsonSingleton.getInstance().fromJson(response, UserInfo.class);
    }

    public String getAccessToken(String code) throws BizException {
        String url = String.format(GET_ACCESS_TOKEN_URL, appId, appSecret, code);

        String response = null;

        try {
            response = HttpUtil.get(url);
        } catch (Exception e) {
            log.error("getAccessToken error: {}", e.getMessage());
            throw new RuntimeException(e);
        }

        JsonElement elmt = GsonSingleton.getInstance().fromJson(response, JsonObject.class).get("access_token");

        if (elmt == null || elmt.getAsString() == null) {
            log.error("getAccessToken error response {}", response);
            throw new BizException(BizExceptionCodeEnum.GET_WECHAT_ACCESS_TOKEN_ERROR);
        }

        String access_token = elmt.getAsString();

        return access_token;
    }
}
