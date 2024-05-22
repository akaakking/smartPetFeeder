package org.wlc.feeder.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wlc.feeder.dto.PetDTO;
import org.wlc.feeder.exception.BizException;
import org.wlc.feeder.service.DeviceService;
import org.wlc.feeder.service.PetService;
import org.wlc.feeder.util.JwtUtils;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Objects;

/**
 * //TODO add class commment here
 *
 * @Author wfh
 * @Date 2024/3/27 下午9:50
 */
@RestController
@RequestMapping("/api")
public class PetController {
    @Resource
    private PetService petService;

    @Resource
    private DeviceService deviceService;

    @PostMapping("/pet")
    public ResponseEntity<String> savePet(@ModelAttribute PetDTO petDto, @RequestHeader("Authorization") String token) throws IOException, BizException {
        // 如果是edit 则图片允许为null否则不允许
        if (petDto.getAvatarFile() == null && petDto.getId() == null) {
            throw new BizException("avatarFile is null");
        }

        // 检验device是否存在
        if (!Objects.isNull(petDto.getDeviceId())) {
            if (!deviceService.deviceExist(petDto.getDeviceId())) {
                throw new BizException("device not exist");
            }
        }

        String openId = JwtUtils.validateAndGetOpenId(token);
        petDto.setUserId(Integer.valueOf(openId));
        petService.savePet(petDto);

        return ResponseEntity.ok("success");
    }

    @GetMapping("/pet")
    public ResponseEntity<PetDTO> getPet(@RequestParam("id") Integer id) {
        return ResponseEntity.ok(petService.getPet(id));
    }

    @PostMapping("/pet/delete")
    public void deletePet(@RequestParam("id") Integer id) {
        petService.deletePet(id);
    }
}
