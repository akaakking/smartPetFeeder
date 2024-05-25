package org.wlc.feeder.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.wlc.feeder.dto.BlogDTO;
import org.wlc.feeder.service.BlogService;
import org.wlc.feeder.service.UrlGenerateService;
import org.wlc.feeder.util.JwtUtils;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * //TODO add class commment here
 *
 * @Author wfh
 * @Date 2024/4/20 下午4:39
 */
@RestController
@RequestMapping("/api")
public class BlogController {
    private static final Logger log = LoggerFactory.getLogger(BlogController.class);
    @Resource
    private UrlGenerateService urlGenerateService;

    @Resource
    private BlogService blogService;

    @PostMapping("/blog")
    public ResponseEntity<String> saveBlog(@RequestParam("image") MultipartFile image,
                                           @RequestParam("title") String title,
                                           @RequestParam("content") String content, @RequestHeader("Authorization") String token) throws IOException {
        log.info(title);
        log.info(content);
        String userId = JwtUtils.validateAndGetOpenId(token);
        BlogDTO blogDTO = new BlogDTO(null,Integer.valueOf(userId),title,null,content);
        String url = urlGenerateService.generateBlogUrl(blogService.saveBlog(image, blogDTO));

        return ResponseEntity.ok(url);
    }

    @GetMapping("/blog/all")
    public ResponseEntity<List<BlogDTO>> getAll() {
        return ResponseEntity.ok(blogService.getAll());
    }

    @GetMapping("/blog/detail")
    public ResponseEntity<BlogDTO> getBlog(@RequestParam("id") Integer id) {
        return ResponseEntity.ok(blogService.getBlogById(id));
    }
}
