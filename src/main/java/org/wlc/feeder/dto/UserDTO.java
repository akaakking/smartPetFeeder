package org.wlc.feeder.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * //TODO add class commment here
 *
 * @Author wfh
 * @Date 2024/4/20 下午6:07
 */
@TableName("user")
@Data
public class UserDTO {
    private Long id;
    private String name;
    private String account;
    private String password;
    private String thirdAccount;

    public UserDTO(String thirdAccount, String name) {
        this.thirdAccount = thirdAccount;
        this.name = name;
    }
}
