package org.wlc.feeder.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wlc.feeder.dto.SliderPicDTO;
import org.wlc.feeder.service.SlidePicService;

import javax.annotation.Resource;
import java.util.List;

/**
 * //TODO add class commment here
 *
 * @Author wfh
 * @Date 2024/3/26 下午9:47
 */
@RestController
@RequestMapping("/api")
public class SlidePicController {
    @Resource
    private SlidePicService slidePicService;

    @GetMapping("/sliderPic")
    public List<SliderPicDTO> getSliderPic() {
        return slidePicService.getSliderPic();
    }
}
