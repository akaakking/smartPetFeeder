package org.wlc.feeder.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * //TODO add class commment here
 *
 * @Author wfh
 * @Date 2024/3/27 下午9:50
 */
@TableName("pet")
@Data
public class PetDTO {
    private Long id;
    private String name;
    private Boolean gender;
    private Integer age;
    private Long weight;
    private Integer petType;
    private String avatar;
    private String feedingPlanJson;
    private String description;
    private String deviceId;
}
