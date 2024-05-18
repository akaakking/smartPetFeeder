package org.wlc.feeder.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NonNull;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

/**
 * //TODO add class commment here
 *
 * @Author wfh
 * @Date 2024/3/27 下午9:50
 */
@TableName("pet")
@Data
@ToString // todo 头像昵称必填
public class PetDTO {
    @TableId
    private Integer id;
    private Integer userId;

    @NotNull
    @NotBlank
    private String name;
    private Boolean gender;
    private Integer age;
    private Integer weight;
    private String petType;
    private String avatar;
    private String description;
    private String feedTimeGap;
    private String breakfast;
    private String lunch;
    private String dinner;
    private String deviceId;
    @TableField(exist = false)
    @NotNull
    private MultipartFile avatarFile;
}
