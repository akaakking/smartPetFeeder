package org.wlc.feeder.controller;

import org.springframework.web.bind.annotation.*;
import org.wlc.feeder.dto.PetDTO;
import org.wlc.feeder.service.PetService;

import javax.annotation.Resource;

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
    public void savePet(@RequestBody PetDTO petDto) {
        petService.savePet(petDto);
    }

    @GetMapping("pet")
    public void getPet(@RequestBody PetDTO petDto) {
        petService.getPet(petDto.getId());
    }
}
