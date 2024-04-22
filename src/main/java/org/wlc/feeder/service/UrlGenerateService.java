package org.wlc.feeder.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * //TODO add class commment here
 *
 * @Author wfh
 * @Date 2024/4/22 下午4:52
 */
@Service
public class UrlGenerateService {
    @Value("${server.ip}")
    private String serverIp;

    public String generateImageUrl(String id) {
        return "http://" + serverIp + "/api/" + id;
    }

    public String generateBlogUrl(Long id) {
        return "http://" + serverIp + "/api/blog/" + id;
    }
}
