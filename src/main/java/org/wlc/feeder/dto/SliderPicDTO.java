package org.wlc.feeder.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.List;

/**
 * //TODO add class commment here
 *
 * @Author wfh
 * @Date 2024/3/26 下午9:54
 */
@Data
@TableName("slider_pic")
public class SliderPicDTO {
    private Long id;
    // 图片地址
    private String picUrl;
}
