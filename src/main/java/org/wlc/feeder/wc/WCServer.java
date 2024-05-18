package org.wlc.feeder.wc;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.wlc.feeder.constant.GsonSingleton;
import org.wlc.feeder.exception.BizException;
import org.wlc.feeder.service.DeviceService;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * //TODO add class commment here
 *
 * @Author wfh
 * @Date 2024/4/10 下午2:27
 */
@Component
@ServerEndpoint("/ws")
@Service
@Slf4j
public class WCServer {
    private static final BiMap<String, Session> clientMap = HashBiMap.create();

    private static final BiMap<String, CompletableFuture> FUTURES = HashBiMap.create();

    @Resource
    private DeviceService deviceService;

    @OnClose
    public void onClose(Session session) throws IOException {
        System.out.println("one connection closed");
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        throwable.printStackTrace();
    }

    @OnMessage
    public void onMessage(Session session, String message) {
        log.info(message);
        Message msg = GsonSingleton.getInstance().fromJson(message, Message.class);

        try {
            switch (msg.getCmd()) {
                case "connect":
                    dealConnect(session, msg.getContent());
                    break;
                case "food_detect_bowl":
                    dealFoodDetectBowl(session, msg);
                    break;
                case "food_detect_silo":
                    dealFoodDetectSilo(session, msg);
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            log.error("onMessage error", e);
        }
    }

    public void dealFoodDetectSilo(Session session, Message msg) throws ExecutionException, InterruptedException {
        String deviceId = clientMap.inverse().get(session);

        CompletableFuture completableFuture = FUTURES.get(deviceId);

        completableFuture.complete(msg.getContent());
    }

    public void dealFoodDetectBowl(Session session, Message msg) throws ExecutionException, InterruptedException {
        String deviceId = clientMap.inverse().get(session);

        CompletableFuture completableFuture = FUTURES.get(deviceId);

        completableFuture.complete(msg.getContent());
    }

    public void dealConnect(Session session, String deviceId) throws BizException {
        if (clientMap.containsKey(deviceId)) {
            sendMsg(session, "connect", "该设备已连接");
            log.error("该设备已连接 {}", deviceId);
        }

        if (!deviceService.deviceExist(deviceId)) {
            sendMsg(session, "connect", "该设备不存在");
            log.error("device {} 不存在", deviceId);
        }

        sendMsg(session, "connect", "连接成功");
        clientMap.put(deviceId, session);
    }

    private void sendMsg(Session session, String cmd, String content) {
        try {
            session.getBasicRemote().sendText(GsonSingleton.getInstance().toJson(new Message(cmd, content)));
        } catch (IOException e) {
            log.error("发送消息时出错",e);
            throw new RuntimeException(e);
        }
    }

    public CompletableFuture<Message> sendMsg(String deviceId, Message msg) {
        CompletableFuture<Message> result = new CompletableFuture<>();
        try {
            clientMap.get(deviceId).getBasicRemote().sendText(msg.toString());
        } catch (IOException e) {
            log.error("发送消息时出错",e);
            throw new RuntimeException(e);
        }

        FUTURES.put(deviceId, result);

        return result;
    }

    public void sendMsgNoReturn(String deviceId, Message msg) {
        try {
            clientMap.get(deviceId).getBasicRemote().sendText(msg.toString());
        } catch (IOException e) {
            log.error("发送消息时出错",e);
            throw new RuntimeException(e);
        }
    }
}
