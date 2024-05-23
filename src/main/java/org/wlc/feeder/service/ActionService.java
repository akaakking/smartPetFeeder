package org.wlc.feeder.service;

import org.springframework.stereotype.Service;
import org.wlc.feeder.dto.FeedPlan;
import org.wlc.feeder.wc.Message;
import org.wlc.feeder.wc.WCServer;

import javax.annotation.Resource;
import javax.annotation.WillClose;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * //TODO add class commment here
 *
 * @Author wfh
 * @Date 2024/5/8 下午10:16
 */
@Service
public class ActionService {
    @Resource
    private WCServer wcServer;

    public String detectFoodBowl(String deviceId) throws ExecutionException, InterruptedException {
        CompletableFuture<Message> food_detect_bowl = wcServer.sendMsg(deviceId, new Message("food_detect_bowl", null));

        Message message = food_detect_bowl.get();
        return (String) message.getContent();
    }

    public String detectFoodSilo(String deviceId) throws ExecutionException, InterruptedException {
        CompletableFuture<Message> food_detect_bowl = wcServer.sendMsg(deviceId, new Message("food_detect_silo", null));

        Message message = food_detect_bowl.get();
        return (String) message.getContent();
    }

    public void feed(String deviceId, Boolean status) {
        wcServer.sendMsgNoReturn(deviceId, new Message("feed", String.valueOf(status)));
    }

    public void feedPlan(FeedPlan feedPlan) {
        Map content = new HashMap();
        content.put("times", feedPlan.toString());
        content.put("duration", feedPlan.getDuration());
        wcServer.sendMsgNoReturn(feedPlan.getDeviceId(), new Message("feed_plan", content));
    }

    public void cameraMove(String deviceId, Integer direction) {
        wcServer.sendMsgNoReturn(deviceId,new Message("camera_move", direction * 15 + ""));
    }

    public String cameraRegular(String deviceId) {
        return null;
    }

}
