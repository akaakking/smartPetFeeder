package org.wlc.feeder.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * //TODO add class commment here
 *
 * @Author wfh
 * @Date 2024/4/22 下午4:04
 */
@Service
public class UploadService {
    @Value("${image.dir.path}")
    private String imageDirPath;

    @Value("${server.ip}")
    private String serverIp;

    public String saveImage(MultipartFile image) throws IOException {
        // 1. 检查文件是否为空
        if (image.isEmpty()) {
            throw new IllegalArgumentException("Image file must not be empty.");
        }

        // 2. 生成唯一文件名（可选，根据实际需求进行优化）
        String fileName = generateUniqueFileName(image.getOriginalFilename());

        // 3. 构建目标文件路径
        Path targetPath = Paths.get(imageDirPath, fileName);

        // 4. 保存文件到指定目录
        Files.copy(image.getInputStream(), targetPath);

        // 5. 返回保存后的文件URL（假设服务器提供静态资源访问，URL需根据实际部署情况调整）
        String imageUrl = "http://" + serverIp + "/api/" + fileName; // 替换为实际的图片访问URL前缀

        return imageUrl;
    }

    /**
     * 生成一个唯一的文件名，防止文件覆盖。
     * 实现可以基于时间戳、随机数、文件原始名哈希等方式，这里仅作简单示例。
     */
    private String generateUniqueFileName(String originalFilename) {
        // 示例：使用时间戳作为唯一标识符附加到原文件名后
        return System.currentTimeMillis() + "_" + originalFilename;
    }

}
