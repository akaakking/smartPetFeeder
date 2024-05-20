package org.wlc.feeder.controller;

import org.apache.logging.log4j.util.Strings;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.wlc.feeder.dto.PetDTO;
import org.wlc.feeder.exception.BizException;
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
    @Resource
    private PetService petService;

    @PostMapping("/pet")
    public ResponseEntity<String> savePet(@Validated @ModelAttribute PetDTO petDto, @RequestHeader("Authorization") String token) throws IOException, BizException {
        if (Strings.isBlank(petDto.getName())) {
            throw new BizException("name is blank");
        }

        if (!"edit".equals(petDto.getPetType()) && petDto.getAvatarFile() == null) {
            throw new BizException("avatarFile is null");
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
