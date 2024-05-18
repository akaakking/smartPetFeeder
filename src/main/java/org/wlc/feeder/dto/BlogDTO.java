package org.wlc.feeder.dto;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * //TODO add class commment here
 *
 * @Author wfh
 * @Date 2024/4/20 下午4:45
 */
@TableName("blog")
@AllArgsConstructor
@Data
public class BlogDTO {
    @TableId
    private Integer id;
    private Integer userId;
    private String title;
    private String titleSrc;
    private String content;
}
