package org.wlc.feeder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wlc.feeder.dto.BlogDTO;
import org.wlc.feeder.dto.PetDTO;
import org.wlc.feeder.dto.UserDTO;
import org.wlc.feeder.service.BlogService;
import org.wlc.feeder.service.LikesService;
import org.wlc.feeder.service.PetService;
import org.wlc.feeder.service.UserService;
import org.wlc.feeder.util.JwtUtils;

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

    @Resource
    private PetService petService;
    @Autowired
    private LikesService likesService;

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

    @PostMapping("/user/info")
    public void addUserInfo(@RequestBody UserDTO userDTO, @RequestHeader("Authorization") String token) {
        String userId = JwtUtils.validateAndGetOpenId(token);
        userDTO.setId(Integer.valueOf(userId));
        userService.saveUserInfo(userDTO);
    }

    @GetMapping("/user/info")
    public ResponseEntity<UserDTO> getUserInfo(@RequestHeader("Authorization") String token) {
        String openId = JwtUtils.validateAndGetOpenId(token);
        return ResponseEntity.ok(userService.getUserInfo(openId));
    }

    @GetMapping("/user/like/blog")
    public ResponseEntity<List<BlogDTO>> getBlog(@RequestHeader("Authorization") String token) {
        String userId = JwtUtils.validateAndGetOpenId(token);
        return ResponseEntity.ok(blogService.getUserLikeBlog(Integer.valueOf(userId)));
    }

    @GetMapping("/user/has-liked-article")
    public ResponseEntity<Boolean> getBlog(@RequestParam("blogId") Integer blogId, @RequestHeader("Authorization") String token) {
        String userId = JwtUtils.validateAndGetOpenId(token);
        return ResponseEntity.ok(likesService.likeBlog(Integer.valueOf(userId), blogId));
    }

    @GetMapping("/user/blog")
    public ResponseEntity<List<BlogDTO>> getUserBlog(@RequestHeader("Authorization") String token) {
        String userId = JwtUtils.validateAndGetOpenId(token);
        return ResponseEntity.ok(blogService.getBlogByUserId(Integer.valueOf(userId)));
    }

    @GetMapping("/user/pet")
    public ResponseEntity<List<PetDTO>> getPet(@RequestHeader("Authorization") String token) {
        String userId = JwtUtils.validateAndGetOpenId(token);
        return ResponseEntity.ok(petService.getUserPet(Integer.valueOf(userId)));
    }
}
