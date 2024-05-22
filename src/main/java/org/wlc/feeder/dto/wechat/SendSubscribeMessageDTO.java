package org.wlc.feeder.dto.wechat;

import lombok.Data;
import org.wlc.feeder.constant.GsonSingleton;

/**
 * //TODO add class commment here
 *
 * @Author wfh
 * @Date 2024/5/22 下午1:37
 */
@Data
public class SendSubscribeMessageDTO {
    private String touser;
    private String template_id;
    private String data;
    private String miniprogram_state;
    private String lang;

    public String toJson() {
        return GsonSingleton.getInstance().toJson(this);
    }
}
