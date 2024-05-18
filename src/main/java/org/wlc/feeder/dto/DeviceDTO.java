package org.wlc.feeder.dto;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * //TODO add class commment here
 *
 * @Author wfh
 * @Date 2024/5/8 下午7:58
 */
@TableName("device")
@Data
public class DeviceDTO {
    @TableId
    Integer id;
    String name;
    String description;
    Integer userId;
    Boolean status;
}
