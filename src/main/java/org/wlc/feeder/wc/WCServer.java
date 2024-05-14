package org.wlc.feeder.wc;

import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.ConcurrentReferenceHashMap;
import org.springframework.util.MultiValueMap;
import org.wlc.feeder.constant.GsonSingleton;
import org.wlc.feeder.exception.BizException;
import org.wlc.feeder.service.DeviceService;
import org.yeauty.annotation.*;
import org.yeauty.pojo.Session;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;

/**
 * //TODO add class commment here
 *
 * @Author wfh
 * @Date 2024/4/10 下午2:27
 */
@Component
@ServerEndpoint(path = "/ws", port = "54188")
@Slf4j
public class WCServer {
    private static final Map<String, Session> clientMap = new ConcurrentHashMap<>();

    private static final Map<String, CompletableFuture> FUTURES = new ConcurrentHashMap<>();

    @Resource
    private DeviceService deviceService;

    @BeforeHandshake
    public void handshake(Session session, HttpHeaders headers, @RequestParam String req, @RequestParam MultiValueMap reqMap, @PathVariable String arg, @PathVariable Map pathMap) {
        log.info("handshake");
        session.setSubprotocols("stomp");
        if (!"ok".equals(req)) {
            System.out.println("Authentication failed!");
            session.close();
        }
    }

    @OnOpen
    public void onOpen(Session session, HttpHeaders headers, @RequestParam String deviceId, @RequestParam MultiValueMap reqMap, @PathVariable String arg, @PathVariable Map pathMap) {
        clientMap.put(deviceId, session);
    }

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
        Message msg = GsonSingleton.getInstance().fromJson(message, Message.class);

        try {
            switch (msg.getCmd()) {
                case "connect":
                    dealConnect(session, msg.getContent());
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            log.error("onMessage error", e);
        }
    }

    public void dealConnect(Session session, String deviceId) throws BizException {
        if (clientMap.containsKey(deviceId)) {
            sendMsg(session,"connect","该设备已连接");
            log.error("该设备已连接 {}", deviceId);
        }

        if (!deviceService.deviceExist(deviceId)) {
            sendMsg(session,"connect","该设备不存在");
            log.error("device {} 不存在", deviceId);
        }

        sendMsg(session,"connect", "连接成功");
        clientMap.put(deviceId, session);
    }

    private void sendMsg(Session session, String cmd, String content) {
        session.sendText(GsonSingleton.getInstance().toJson(new Message(cmd, content)));
    }

    @OnBinary
    public void onBinary(Session session, byte[] bytes) {
        for (byte b : bytes) {
            System.out.println(b);
        }
        session.sendBinary(bytes);
    }

    @OnEvent
    public void onEvent(Session session, Object evt) {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent idleStateEvent = (IdleStateEvent) evt;
            switch (idleStateEvent.state()) {
                case READER_IDLE:
                    System.out.println("read idle");
                    break;
                case WRITER_IDLE:
                    System.out.println("write idle");
                    break;
                case ALL_IDLE:
                    System.out.println("all idle");
                    break;
                default:
                    break;
            }
        }
    }

    public CompletableFuture<Message> sendMsg(String deviceId, Message msg) {
        CompletableFuture<Message> result = new CompletableFuture<>();
        clientMap.get(deviceId).sendText(msg.toString());

        FUTURES.put(deviceId, result);

        return  result;
    }

    public void sendMsgNoReturn(String deviceId, Message msg) {
        clientMap.get(deviceId).sendText(msg.toString());
    }
}
