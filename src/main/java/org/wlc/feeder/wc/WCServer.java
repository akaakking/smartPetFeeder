package org.wlc.feeder.wc;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.wlc.feeder.constant.GsonSingleton;
import org.wlc.feeder.dto.wechat.SendSubscribeMessageDTO;
import org.wlc.feeder.exception.BizException;
import org.wlc.feeder.service.DeviceService;
import org.wlc.feeder.service.PetService;
import org.wlc.feeder.service.UserService;
import org.wlc.feeder.service.WechatService;
import org.wlc.feeder.util.AppContextUtil;
import org.wlc.feeder.util.CommonUtils;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

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

    private static final BiMap<String, CompletableFuture<Message>> FUTURES = HashBiMap.create();

    private DeviceService deviceService;

    private UserService userService;

    private WechatService wechatService;

    private PetService petService;

    @OnClose
    public void onClose(Session session) throws IOException {
        log.info("device {} 下线", clientMap.inverse().get(session));
        clientMap.inverse().remove(session);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        log.error(throwable.toString());
        throwable.printStackTrace();
    }

    @OnMessage
    public void onMessage(Session session, String message) {
        if (deviceService == null) {
            deviceService = AppContextUtil.getBean(DeviceService.class);
            userService = AppContextUtil.getBean(UserService.class);
            wechatService = AppContextUtil.getBean(WechatService.class);
            petService = AppContextUtil.getBean(PetService.class);
        }

        log.info("收到消息：{}", message);
        Message msg = GsonSingleton.getInstance().fromJson(message, Message.class);

        try {
            switch (msg.getCmd()) {
                case "connect":
                    dealConnect(session, (String) msg.getContent());
                    break;
                case "food_detect_bowl":
                    dealFoodDetectBowl(session, msg);
                    break;
                case "food_detect_silo":
                    dealFoodDetectSilo(session, msg);
                    break;
                case "regular_test_bowl":
                    log.info("余量不足，需要提醒用户");
                    regular_test_bowl(session);
                    break;
                case "regular_test_silo":
                    log.info("余量不足，需要提醒用户");
                    regular_test_silo(session);
                    break;
                case "feed_complete":
                    log.info("已经完成投喂");
                    feedComplete(session);
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            log.error("onMessage error", e);
        }
    }

    private void feedComplete(Session session) {
        String deviceId = clientMap.inverse().get(session);
        Integer deviceOwner = deviceService.findDeviceOwner(Integer.valueOf(deviceId));
        String wechatId = userService.getUserInfo(deviceOwner).getWechatId();
        String petName = petService.findPetByDeviceId(deviceId).getName();

        wechatService.sendWechatMessage(SendSubscribeMessageDTO.buildJson(wechatId, wechatService.getPetFeedingReminderTemplate(),
                new SendSubscribeMessageDTO.PlaceHolder("name1", petName),
                new SendSubscribeMessageDTO.PlaceHolder("phrase2", "宠物定时喂食"),
                new SendSubscribeMessageDTO.PlaceHolder("date3", CommonUtils.getyyyymmddHHmmss()),
                new SendSubscribeMessageDTO.PlaceHolder("thing5", "已完成宠物食物的定点投送")));
    }

    private void regular_test_bowl(Session session) {
        String deviceId = clientMap.inverse().get(session);
        Integer deviceOwner = deviceService.findDeviceOwner(Integer.valueOf(deviceId));
        String wechatId = userService.getUserInfo(deviceOwner).getWechatId();


        wechatService.sendWechatMessage(SendSubscribeMessageDTO.buildJson(wechatId, wechatService.getFoodShortageReminderTemplate(),
                new SendSubscribeMessageDTO.PlaceHolder("thing1", "宠物已经完成进食"),
                new SendSubscribeMessageDTO.PlaceHolder("time3", CommonUtils.getyyyymmddHHmmss())));
    }

    private void regular_test_silo(Session session) {
        String deviceId = clientMap.inverse().get(session);
        Integer deviceOwner = deviceService.findDeviceOwner(Integer.valueOf(deviceId));
        String wechatId = userService.getUserInfo(deviceOwner).getWechatId();


        wechatService.sendWechatMessage(SendSubscribeMessageDTO.buildJson(wechatId, wechatService.getFoodShortageReminderTemplate(),
                new SendSubscribeMessageDTO.PlaceHolder("thing1", "宠物粮仓内余粮不足请及时补充"),
                new SendSubscribeMessageDTO.PlaceHolder("time3", CommonUtils.getyyyymmddHHmmss())));
    }

    public void dealFoodDetectSilo(Session session, Message msg) throws ExecutionException, InterruptedException {
        String deviceId = clientMap.inverse().get(session);

        CompletableFuture<Message> completableFuture = FUTURES.get(deviceId);

        completableFuture.complete(msg);
    }

    public void dealFoodDetectBowl(Session session, Message msg) throws ExecutionException, InterruptedException {
        String deviceId = clientMap.inverse().get(session);

        CompletableFuture<Message> completableFuture = FUTURES.get(deviceId);

        completableFuture.complete(msg);
    }

    public void dealConnect(Session session, String deviceId) throws BizException {
        if (clientMap.containsKey(deviceId)) {
            sendMsg(session, "connect", "该设备已连接");
            log.error("该设备已连接 {}", deviceId);
        }

        if (!deviceService.deviceExist(Integer.valueOf(deviceId))) {
            sendMsg(session, "connect", "该设备不存在");
            log.error("device {} 不存在", deviceId);
        }

        sendMsg(session, "connect", "连接成功");
        synchronized (clientMap) {
            clientMap.put(deviceId, session);
        }
    }

    private void sendMsg(Session session, String cmd, String content) {
        try {
            session.getBasicRemote().sendText(GsonSingleton.getInstance().toJson(new Message(cmd, content)));
        } catch (IOException e) {
            log.error("发送消息时出错", e);
            throw new RuntimeException(e);
        }
    }

    public CompletableFuture<Message> sendMsg(String deviceId, Message msg) {
        if (!isDeviceOnline(deviceId)) {
            log.info("device {} 不在线，无法发送消息", deviceId);
        }

        CompletableFuture<Message> result = new CompletableFuture<>();
        try {
            clientMap.get(deviceId).getBasicRemote().sendText(msg.toString());
        } catch (IOException e) {
            log.error("发送消息时出错", e);
            throw new RuntimeException(e);
        }

        FUTURES.put(deviceId, result);

        return result;
    }

    public void sendMsgNoReturn(String deviceId, Message msg) {
        if (!isDeviceOnline(deviceId)) {
            log.info("device {} 不在线，无法发送消息", deviceId);
        }
        try {
            clientMap.get(deviceId).getBasicRemote().sendText(msg.toString());
        } catch (IOException e) {
            log.error("发送消息时出错", e);
            throw new RuntimeException(e);
        }
    }

    public static String getOnlineDevice() {
        log.info("online device {}", clientMap.keySet());
        return clientMap.keySet().stream().collect(Collectors.joining(","));
    }

    public boolean isDeviceOnline(String deviceId) {
        return clientMap.containsKey(deviceId);
    }
}
