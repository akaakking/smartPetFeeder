package org.wlc.feeder.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wlc.feeder.dto.LikesDTO;
import org.wlc.feeder.service.LikesService;
import org.wlc.feeder.util.JwtUtils;

import javax.annotation.Resource;

/**
 * //TODO add class commment here
 *
 * @Author wfh
 * @Date 2024/3/26 下午9:50
 */
@RestController
@RequestMapping("/api")
public class LikesController {
    @Resource
    private LikesService likesService;

    @PostMapping("/blog/like")
    public void like(@RequestBody LikesDTO likesDTO,  @RequestHeader("Authorization") String token) {
        String openId = JwtUtils.validateAndGetOpenId(token);
        likesDTO.setUserId(Integer.valueOf(openId));
        likesService.saveLikes(likesDTO);
    }

    @GetMapping("/blog/like/count")
    public ResponseEntity<Long> getLikeCount(@RequestParam Integer blogId) {
        return ResponseEntity.ok(likesService.countUserLikes(blogId));
    }
}
