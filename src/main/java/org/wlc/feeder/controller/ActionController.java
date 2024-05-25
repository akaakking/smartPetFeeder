package org.wlc.feeder.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wlc.feeder.dto.FeedPlan;
import org.wlc.feeder.service.ActionService;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;

/**
 * //TODO add class commment here
 *
 * @Author wfh
 * @Date 2024/4/10 下午9:14
 */
@RestController
@RequestMapping("/api")
public class ActionController {

    private static final Logger log = LoggerFactory.getLogger(ActionController.class);
    @Resource
    private ActionService actionService;

    // todo msgId 控制一个会话，但是这次不做这个东西。

    @GetMapping("/action/food/detect/bowl")
    public ResponseEntity<String> detectFoodBowl(@RequestParam String deviceId) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(actionService.detectFoodBowl(deviceId));
    }

    @GetMapping("/action/food/detect/silo")
    public ResponseEntity<String> detectFoodSilo(@RequestParam String deviceId) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(actionService.detectFoodSilo(deviceId));
    }

    @PostMapping("/action/feed")
    public void feed(@RequestParam String deviceId, @RequestParam Boolean status) {
        actionService.feed(deviceId, status);
    }

    @PostMapping("/action/feedPlan")
    public void feedPlan(@RequestBody FeedPlan feedPlan) {
        actionService.feedPlan(feedPlan);
    }

    @GetMapping("/action/camera/move")
    public void cameraMove(@RequestParam String deviceId, @RequestParam Integer direction) {
        actionService.cameraMove(deviceId, direction);
    }

    @GetMapping("/action/camera/regular")
    public ResponseEntity<String> cameraRegular(@RequestParam String deviceId, @RequestParam Boolean status) throws InterruptedException {
        return ResponseEntity.ok(actionService.cameraRegular(deviceId, status));
    }

    @GetMapping("/action/camera/detect")
    public ResponseEntity<String> cameraDetect(@RequestParam String deviceId, @RequestParam Boolean status) throws InterruptedException {
        return ResponseEntity.ok(actionService.cameraDetect(deviceId, status));
    }
}
