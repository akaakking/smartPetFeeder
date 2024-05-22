package org.wlc.feeder.service;

import org.springframework.stereotype.Service;
import org.wlc.feeder.dao.DeviceMapper;
import org.wlc.feeder.dto.DeviceDTO;

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

    public Boolean deviceExist(Integer deviceId) {
        return deviceMapper.selectById(deviceId) != null;
    }

    public Integer findDeviceOwner(Integer deviceId) {
        return deviceMapper.selectById(deviceId).getUserId();
    }

    public void saveDevice(Integer deviceId) {
        DeviceDTO deviceDTO = new DeviceDTO();
        deviceDTO.setId(deviceId);
        deviceMapper.insert(deviceDTO);
    }
}
