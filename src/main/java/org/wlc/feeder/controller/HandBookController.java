package org.wlc.feeder.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wlc.feeder.dto.HandBookDTO;
import org.wlc.feeder.service.HandBookService;

import javax.annotation.Resource;
import java.util.List;

/**
 * //TODO add class commment here
 *
 * @Author wfh
 * @Date 2024/3/26 下午9:48
 */
@RestController
@RequestMapping("/api")
public class HandBookController {
    @Resource
    private HandBookService handBookService;

    @GetMapping("/handBook")
    public List<HandBookDTO> getHandBook() {
        return handBookService.getHandBook();
    }


}
