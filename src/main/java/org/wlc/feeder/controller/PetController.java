package org.wlc.feeder.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wlc.feeder.dto.PetDTO;
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

    @PostMapping("pet")
    public void savePet(@ModelAttribute PetDTO petDto, @RequestHeader("Authorization") String token) throws IOException {
        String openId = JwtUtils.validateAndGetOpenId(token);
        petDto.setUserId(Long.valueOf(openId));
        petService.savePet(petDto);
    }

    @GetMapping("pet")
    public ResponseEntity<PetDTO> getPet(@RequestParam("id") Long id) {
        return ResponseEntity.ok(petService.getPet(id));
    }
}
