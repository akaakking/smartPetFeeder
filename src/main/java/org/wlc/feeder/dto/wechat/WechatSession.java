package org.wlc.feeder.dto.wechat;

import lombok.Data;

/**
 * //TODO add class commment here
 *
 * @Author wfh
 * @Date 2024/4/20 下午9:37
 */
@Data
public class WechatSession {
    private String openid;
    private String session_key;
}
