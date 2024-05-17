package org.wlc.feeder.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;
import org.wlc.feeder.dao.BlogMapper;
import org.wlc.feeder.dto.BlogDTO;
import org.wlc.feeder.dto.LikesDTO;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * //TODO add class commment here
 *
 * @Author wfh
 * @Date 2024/4/20 下午5:54
 */
@Service
public class BlogService {
    @Resource
    private BlogMapper blogMapper;

    @Resource
    private LikesService likesService;

    @Resource
    private UploadService uploadService;

    public Long saveBlog(MultipartFile image, BlogDTO blogDTO) throws IOException {
        String imageUrl = uploadService.saveImage(image);

        blogDTO.setTitleSrc(imageUrl);

        blogMapper.insert(blogDTO);

        return blogDTO.getId();
    }

    public List<BlogDTO> getAll() {
        return blogMapper.selectList(null);
    }

    public BlogDTO getBlogById(Long id) {
        return blogMapper.selectById(id);
    }

    public List<BlogDTO> getBlogByUserId(Long userId) {
        return blogMapper.selectList(new QueryWrapper<BlogDTO>().eq("user_id", userId));
    }

    public List<BlogDTO> getUserLikeBlog(Long userId) {
        List<LikesDTO> userLikes = likesService.getUserLikes(userId);
        if (CollectionUtils.isEmpty(userLikes)) {
            return Collections.emptyList();
        }

        return userLikes.stream().map(likesDTO -> likesDTO.getBlogId()).map(blogId -> blogMapper.selectById(blogId)).collect(Collectors.toList());
    }
}
