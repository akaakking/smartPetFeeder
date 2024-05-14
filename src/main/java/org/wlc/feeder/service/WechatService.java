package org.wlc.feeder.service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.wlc.feeder.constant.GsonSingleton;
import org.wlc.feeder.dto.wechat.WechatSession;
import org.wlc.feeder.util.HttpUtil;

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
    @Value("${wechat.appId}")
    private String appId;

    @Value("${wechat.appSecret}")
    private String appSecret;

    private String GET_WECHAT_SESSION_URL = "https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code";

    LoadingCache<String, WechatSession> WECHAT_SESSION_CACHE = CacheBuilder.newBuilder()
            .maximumSize(100)
            .build(new CacheLoader<String, WechatSession>() {
                @Override
                public WechatSession load(String code) throws Exception {
                    String url = String.format(GET_WECHAT_SESSION_URL, appId, appSecret, code);
                    log.info(appId + appSecret + " "  + code);
                    log.info(url);
                    String response = null;
                    try {
                        response = HttpUtil.get(url);
                    } catch (Exception e) {
                        log.error("getWechatSession error: {}", e.getMessage());
                        throw new RuntimeException(e);
                    }

                    log.info("getWechatSession response: {}", response);

                    WechatSession wechatSession = GsonSingleton.getInstance().fromJson(response, WechatSession.class);

                    if (wechatSession.getOpenid() == null) {
                        log.error("getWechatSession error: {}", response);
                        throw new RuntimeException("获取wechatSession出现错误");
                    }

                    return wechatSession;
                }
            });

    public WechatSession getWechatSession(String code) throws ExecutionException {
        return WECHAT_SESSION_CACHE.get(code);
    }
}
