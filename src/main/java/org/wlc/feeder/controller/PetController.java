package org.wlc.feeder.controller;

import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wlc.feeder.constant.GsonSingleton;
import org.wlc.feeder.dto.PetDTO;
import org.wlc.feeder.dto.PetModifyDTO;
import org.wlc.feeder.exception.BizException;
import org.wlc.feeder.service.DeviceService;
import org.wlc.feeder.service.PetService;
import org.wlc.feeder.util.JwtUtils;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * //TODO add class commment here
 *
 * @Author wfh
 * @Date 2024/3/27 下午9:50
 */
@RestController
@RequestMapping("/api")
public class PetController {
    private static final Logger log = LoggerFactory.getLogger(PetController.class);
    @Resource
    private PetService petService;

    @Resource
    private DeviceService deviceService;

    @PostMapping("/pet")
    public ResponseEntity<String> savePet(@ModelAttribute PetDTO petDto, @RequestHeader("Authorization") String token) throws IOException, BizException {
        if (Strings.isBlank(petDto.getName())) {
            throw new BizException("姓名为必填字段");
        }

        if (petDto.getDeviceId() == null) {
            throw new BizException("机器标识未填写");
        }

        if (!deviceService.deviceExist(petDto.getDeviceId())) {
            throw new BizException("机器标识不存在");
        }

        if (petService.deviceHasUsed(petDto.getDeviceId(), petDto.getId())) {
            throw new BizException("机器标识已经被使用");
        }

        if (Strings.isBlank(petDto.getBreakfast()) || Strings.isBlank(petDto.getLunch()) || Strings.isBlank(petDto.getDinner())) {
            throw new BizException("三餐时间未填写");
        }

        String openId = JwtUtils.validateAndGetOpenId(token);
        petDto.setUserId(Integer.valueOf(openId));
        petService.savePet(petDto);

        return ResponseEntity.ok("success");
    }

    @PostMapping("/pet/modify")
    public ResponseEntity<String> modifyPet(@ModelAttribute PetModifyDTO petModifyDTO, @RequestHeader("Authorization") String token) throws BizException, IOException {
        log.info("modify pet: {}", GsonSingleton.getInstance().toJson(petModifyDTO));

        // 检验device是否存在, 还需要检查，是否是一个宠物一个device
        if (Strings.isBlank(petModifyDTO.getName())) {
            throw new BizException("姓名为必填字段");
        }

        if (petModifyDTO.getDeviceId() == null) {
            throw new BizException("机器标识未填写");
        }

        if (!deviceService.deviceExist(petModifyDTO.getDeviceId())) {
            throw new BizException("机器标识不存在");
        }

        if (petService.deviceHasUsed(petModifyDTO.getDeviceId(), petModifyDTO.getId())) {
            throw new BizException("该机器已经被使用");
        }

        String openId = JwtUtils.validateAndGetOpenId(token);
        petModifyDTO.setUserId(Integer.valueOf(openId));

        PetDTO petDTO = new PetDTO();
        BeanUtils.copyProperties(petModifyDTO, petDTO);
        petService.savePet(petDTO);

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
