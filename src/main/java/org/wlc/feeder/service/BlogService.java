package org.wlc.feeder.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.wlc.feeder.dao.BlogMapper;
import org.wlc.feeder.dto.BlogDTO;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

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
    private UploadService uploadService;

    public Long saveBlog(MultipartFile image, BlogDTO blogDTO) throws IOException {
        String imageUrl = uploadService.saveImage(image);

        blogDTO.setTitleSrc(imageUrl);

        blogMapper.insert(blogDTO);

        return blogDTO.getId();
    }

    public BlogDTO getBlogById(Long id) {
        return blogMapper.selectById(id);
    }

    public List<BlogDTO> getBlogByUserId(Long userId) {
        return blogMapper.selectList(new QueryWrapper<BlogDTO>().eq("user_id", userId));
    }
}
