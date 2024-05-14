package org.wlc.feeder.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

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
    private Long userId;
    private String name;
    private Boolean gender;
    private Integer age;
    private Long weight;
    private String petType;
    private String avatar;
    private String description;
    private String feedTimeGap;
    private String breakfast;
    private String lunch;
    private String dinner;
    private String deviceId;
    private MultipartFile avatarFile;
}
