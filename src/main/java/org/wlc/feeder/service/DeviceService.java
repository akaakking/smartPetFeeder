package org.wlc.feeder.service;

import org.springframework.stereotype.Service;
import org.wlc.feeder.dao.DeviceMapper;

import javax.annotation.Resource;

/**
 * //TODO add class commment here
 *
 * @Author wfh
 * @Date 2024/5/11 下午11:01
 */
@Service
public class DeviceService {
    @Resource
    private DeviceMapper deviceMapper;

    public Boolean deviceExist(String deviceId) {
        return deviceMapper.selectById(deviceId) == null;
    }

    public Integer findDeviceOwner(Integer deviceId) {
        return deviceMapper.selectById(deviceId).getUserId();
    }
}
