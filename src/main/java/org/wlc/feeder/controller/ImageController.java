package org.wlc.feeder.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * //TODO add class commment here
 *
 * @Author wfh
 * @Date 2024/4/22 下午3:46
 */
@RestController
@Slf4j
@RequestMapping("/api")
public class ImageController {
    @Value("${image.dir.path}")
    private String imageDirPath;

    @javax.annotation.Resource
    private ResourceLoader resourceLoader;

    @GetMapping("/image/{id}")
    public ResponseEntity<Resource> getImage(@PathVariable("id") String id) {
        // 假设id为上传时生成的唯一文件名
        log.info("read image");
        String filePath = imageDirPath + id; // 替换为实际的图片存储路径前缀

        Resource resource = resourceLoader.getResource(filePath);
        if (resource == null || !resource.exists()) {
            return ResponseEntity.notFound().build();
        }

        // 设置响应头，包括Content-Type和Content-Disposition
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG); // 根据实际情况设置正确的图片类型
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + id + "\"");

        return ResponseEntity.ok()
                .headers(headers)
                .body(resource);
    }
}
