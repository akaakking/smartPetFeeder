package org.wlc.feeder.dto;

import lombok.Data;
import org.wlc.feeder.constant.GsonSingleton;

/**
 * //TODO add class commment here
 *
 * @Author wfh
 * @Date 2024/5/12 上午10:01
 */
@Data
public class FeedPlan {
    private String deviceId;
    private String breakfast;
    private String lunch;
    private String dinner;
    private String duration;

    public String toString() {
        return GsonSingleton.getInstance().toJson(this);
    }
}
