package org.wlc.feeder.wc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.wlc.feeder.constant.GsonSingleton;

/**
 * //TODO add class commment here
 *
 * @Author wfh
 * @Date 2024/5/8 下午7:55
 */
@Data
@AllArgsConstructor
public class Message {
    private String cmd;
    private Object content;

    public String toString() {
        return GsonSingleton.getInstance().toJson(this);
    }
}
