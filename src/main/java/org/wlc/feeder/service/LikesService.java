package org.wlc.feeder.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import org.wlc.feeder.dao.LikesMapper;
import org.wlc.feeder.dto.BlogDTO;
import org.wlc.feeder.dto.LikesDTO;

import javax.annotation.Resource;
import java.util.List;

/**
 * //TODO add class commment here
 *
 * @Author wfh
 * @Date 2024/4/21 下午5:23
 */
@Service
public class LikesService {
    @Resource
    private LikesMapper likesMapper;

    public void saveLikes(LikesDTO likesDTO) {
        LikesDTO record = likesMapper.selectOne(new QueryWrapper<LikesDTO>().eq("blog_id", likesDTO.getBlogId()).eq("user_id", likesDTO.getUserId()));

        if (record == null) {
            likesMapper.insert(likesDTO);
            return;
        }

        if (record.getLikeStatus().equals(likesDTO.getLikeStatus())) {
            return;
        }

        record.setLikeStatus(likesDTO.getLikeStatus());
        likesMapper.updateById(record);
    }

    public List<LikesDTO> getUserLikes(Long userId) {
        return likesMapper.selectList(new QueryWrapper<LikesDTO>().eq("user_id", userId));
    }

    public Long countUserLikes(Long blogId) {
        return likesMapper.selectCount(new QueryWrapper<LikesDTO>().eq("blog_id", blogId).eq("like_status", 1));
    }
}
