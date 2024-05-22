package org.wlc.feeder.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * //TODO add class commment here
 *
 * @Author wfh
 * @Date 2024/5/22 下午5:15
 */
@Data
public class PetModifyDTO {
    @TableId
    private Integer id;
    private Integer userId;

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
    private Integer deviceId;
}
