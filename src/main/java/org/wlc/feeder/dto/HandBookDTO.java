package org.wlc.feeder.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * //TODO add class commment here
 *
 * @Author wfh
 * @Date 2024/3/27 下午9:40
 */
@Data
@TableName("hand_book")
public class HandBookDTO {
    private Long id;
    private String handBookSrc;
    private String handBookTitle;
    private String content;
}
