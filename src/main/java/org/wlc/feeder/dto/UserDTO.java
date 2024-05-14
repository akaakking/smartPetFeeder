package org.wlc.feeder.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * //TODO add class commment here
 *
 * @Author wfh
 * @Date 2024/4/20 下午6:07
 */
@TableName("user")
@Data
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String name;
    private String account;
    private String avatar;
    private String password;
    private String wechatId;

    public UserDTO(String wechatId) {
        this.wechatId = wechatId;
    }
}
