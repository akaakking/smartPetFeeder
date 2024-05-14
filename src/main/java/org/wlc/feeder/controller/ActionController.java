package org.wlc.feeder.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wlc.feeder.dto.FeedPlan;
import org.wlc.feeder.service.ActionService;
import org.wlc.feeder.wc.Message;
import org.wlc.feeder.wc.WCServer;

import javax.annotation.Resource;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
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
    public void feed(@RequestParam String deviceId, @RequestParam Integer duration) {
        actionService.feed(deviceId, duration);
    }

    @GetMapping("/action/feedPlan")
    public void feedPlan(@RequestBody FeedPlan feedPlan) {
        actionService.feedPlan(feedPlan);
    }

    @GetMapping("/action/camera/move")
    public void cameraMove(@RequestParam String deviceId, @RequestParam String direction) {
        actionService.cameraMove(deviceId, direction);
    }

}
