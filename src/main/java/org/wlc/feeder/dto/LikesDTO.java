package org.wlc.feeder.dto;

import com.baomidou.mybatisplus.annotation.TableId;
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
    @TableId
    private Integer id;
    private Integer userId;
    private Integer blogId;
    private Integer likeStatus;
}

