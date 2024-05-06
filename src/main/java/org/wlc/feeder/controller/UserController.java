package org.wlc.feeder.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wlc.feeder.dto.BlogDTO;
import org.wlc.feeder.service.BlogService;
import org.wlc.feeder.service.UserService;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * //TODO add class commment here
 *
 * @Author wfh
 * @Date 2024/3/26 下午9:49
 */
@RestController
@RequestMapping("/api")
public class UserController {
    @Resource
    private UserService userService;

    @Resource
    private BlogService blogService;

    /**
     * 微信三方登陆流程
     * <p>
     * 前端拿到code，传给后端
     * 后端拿到code，请求微信服务器，拿到accessToken，
     * 后端使用accessToken，请求微信服务器，拿到openId。
     */
    @PostMapping("/user/login/wechatLogin")
    public ResponseEntity<String> wechatLogin(@RequestParam("code") String code) throws ExecutionException {
        return ResponseEntity.ok(userService.wechatLogin(code));
    }

    @GetMapping("/user/like/blog")
    public ResponseEntity<List<BlogDTO>> getBlog(@RequestParam Long id) {
        return ResponseEntity.ok(blogService.getBlogByUserId(id));
    }
}
