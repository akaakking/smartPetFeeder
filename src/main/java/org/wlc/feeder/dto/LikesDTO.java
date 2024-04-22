package org.wlc.feeder.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * //TODO add class commment here
 *
 * @Author wfh
 * @Date 2024/4/21 下午5:20
 */
@TableName("user_blog_likes")
@Data
public class LikesDTO {
    private Long id;
    private Long userId;
    private Long blogId;
    private Integer likeStatus;
}
