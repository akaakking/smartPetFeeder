package org.wlc.feeder.dto.wechat;

import lombok.Data;

/**
 * //TODO add class commment here
 *
 * @Author wfh
 * @Date 2024/5/22 下午6:46
 */
@Data
public class GetAccessTokenResult {
    private String access_token;
    private String expires_in;
}
