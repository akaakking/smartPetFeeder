package org.wlc.feeder.service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.wlc.feeder.constant.GsonSingleton;
import org.wlc.feeder.dto.wechat.GetAccessTokenResult;
import org.wlc.feeder.dto.wechat.WechatSession;
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
    @Value("${wechat.appId}")
    private String appId;

    @Value("${wechat.appSecret}")
    private String appSecret;

    @Value("${wechat.petFeedingReminderTemplate}")
    private String petFeedingReminderTemplate;

    @Value("${wechat.foodShortageReminderTemplate}")
    private String foodShortageReminderTemplate;

    private volatile String accessToken;

    public static String GET_WECHAT_SESSION_URL = "https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code";

    public static String GET_WECHAT_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";

    public static String SEND_SUBSCRIBE_MESSAGE_URL = "https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token=%s";

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

//     使用Cron表达式定义刷新频率，这个例子中每小时刷新一次
    @Scheduled(cron = "0 0 * * * ?")
    public void refresh() {
        // 实现你的刷新逻辑
        try {
            log.info("开始刷新token");
            accessToken = fetchNewAccessToken();
            log.info("刷新token成功 {}", accessToken);
        } catch (Exception e) {
            log.error("刷新token失败",e);
        }
    }

    public String fetchNewAccessToken() throws URISyntaxException, IOException {
        String url = String.format(GET_WECHAT_ACCESS_TOKEN_URL, appId, appSecret);
        String httpResult = HttpUtil.get(url);
        log.info("get accessToken response {}", httpResult);

        return GsonSingleton.getInstance().fromJson(httpResult, GetAccessTokenResult.class).getAccess_token();
    }

    // 两种订阅消息 1. 提醒用户喂食成功/失败 2. 提醒用户粮食没有了
    public void sendWechatMessage(String message) {
        if (accessToken == null) {
            try {
                accessToken = fetchNewAccessToken();
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        log.info("sendWechatMessage accessToken {}", accessToken);
        String url = String.format(SEND_SUBSCRIBE_MESSAGE_URL, accessToken);

        try {
            String post = HttpUtil.post(url, message);
            log.info("sendWechatMessage response {}", post);
        } catch (Exception e) {
            log.error("sendWechatMessage error: {}", e.getMessage(),e);
        }
    }

    public String getPetFeedingReminderTemplate() {
        return petFeedingReminderTemplate;
    }

    public String getFoodShortageReminderTemplate() {
        return foodShortageReminderTemplate;
    }
}
