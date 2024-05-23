package org.wlc.feeder.dto.wechat;

import com.google.gson.JsonObject;
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
    private JsonObject data;
    private String miniprogram_state;
    private String lang;

    public static String buildJson(String openId, String templateId, PlaceHolder... placeHolders) {
        SendSubscribeMessageDTO sendSubscribeMessageDTO = new SendSubscribeMessageDTO();
        sendSubscribeMessageDTO.setTouser(openId);
        sendSubscribeMessageDTO.setTemplate_id(templateId);
        sendSubscribeMessageDTO.setLang("zh_CN");
        sendSubscribeMessageDTO.setMiniprogram_state("developer");

        JsonObject data = new JsonObject();
        for (PlaceHolder placeHolder : placeHolders) {
            JsonObject value = new JsonObject();

            value.addProperty("value", placeHolder.Value);

            data.add(placeHolder.holderName, value);
        }
        sendSubscribeMessageDTO.setData(data);


        return GsonSingleton.getInstance().toJson(sendSubscribeMessageDTO);
    }

    public static class PlaceHolder {
        String holderName;
        String Value;

        public PlaceHolder(String holderName, String value) {
            this.holderName = holderName;
            Value = value;
        }
    }
}
