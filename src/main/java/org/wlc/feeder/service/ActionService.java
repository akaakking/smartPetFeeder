package org.wlc.feeder.service;

import org.springframework.stereotype.Service;
import org.wlc.feeder.dto.FeedPlan;
import org.wlc.feeder.wc.Message;
import org.wlc.feeder.wc.WCServer;

import javax.annotation.Resource;
import javax.annotation.WillClose;
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
        return message.getContent();
    }

    public String detectFoodSilo(String deviceId) throws ExecutionException, InterruptedException {
        CompletableFuture<Message> food_detect_bowl = wcServer.sendMsg(deviceId, new Message("food_detect_silo", null));

        Message message = food_detect_bowl.get();
        return message.getContent();
    }

    public void feed(String deviceId, Integer duration) {
        wcServer.sendMsgNoReturn(deviceId, new Message("feed", duration.toString()));
    }

    public void feedPlan(FeedPlan feedPlan) {
        wcServer.sendMsgNoReturn(feedPlan.getDeviceId(), new Message("feed_plan", feedPlan.toString()));
    }

    public void cameraMove(String deviceId, String direction) {
        wcServer.sendMsgNoReturn(deviceId,new Message("camera_move", direction));
    }

    public String cameraRegular(String deviceId) {
        return null;
    }

}
