package org.wlc.feeder.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * //TODO add class commment here
 *
 * @Author wfh
 * @Date 2024/3/27 下午9:23
 */
@Data
@TableName("today_topic")
public class TodayTopicDTO {
    private Long id;
    private String title;
    private String content;
    private String titleSrc;
}
