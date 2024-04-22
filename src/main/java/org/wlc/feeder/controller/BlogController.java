package org.wlc.feeder.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.wlc.feeder.dto.BlogDTO;
import org.wlc.feeder.service.BlogService;
import org.wlc.feeder.service.UrlGenerateService;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * //TODO add class commment here
 *
 * @Author wfh
 * @Date 2024/4/20 下午4:39
 */
@RestController
@RequestMapping("/api")
public class BlogController {
    @Resource
    private UrlGenerateService urlGenerateService;

    @Resource
    private BlogService blogService;

    @PostMapping("/blog")
    public ResponseEntity<String> saveBlog(@RequestParam("image") MultipartFile image,
                         @RequestBody BlogDTO blogDTO) throws IOException {
        String url = urlGenerateService.generateBlogUrl(blogService.saveBlog(image, blogDTO));

        return ResponseEntity.ok(url);
    }

    @GetMapping("/blog/{id}")
    public ResponseEntity<BlogDTO> getBlog(@PathVariable("id") Long id) {
        return ResponseEntity.ok(blogService.getBlogById(id));
    }
}
