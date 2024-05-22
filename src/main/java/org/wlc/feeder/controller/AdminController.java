package org.wlc.feeder.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wlc.feeder.annotation.AdminAuth;
import org.wlc.feeder.service.DeviceService;
import org.wlc.feeder.wc.WCServer;

import javax.annotation.Resource;

/**
 * //TODO add class commment here
 *
 * @Author wfh
 * @Date 2024/5/22 上午11:11
 */
@RestController
@RequestMapping("/api")
public class AdminController {
    @Resource
    private DeviceService deviceService;

    @AdminAuth
    @GetMapping("/admin/devices/online")
    public ResponseEntity<String> getOnlineDevices() {
        return ResponseEntity.ok(WCServer.getOnlineDevice());
    }

    @AdminAuth
    @PostMapping("/admin/deivce/add")
    public void addDevice(@RequestParam Integer deviceId) {
        deviceService.saveDevice(deviceId);
    }
}
