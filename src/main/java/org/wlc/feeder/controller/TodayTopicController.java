package org.wlc.feeder.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wlc.feeder.dto.TodayTopicDTO;
import org.wlc.feeder.service.TodayTopicService;

import javax.annotation.Resource;
import java.util.List;

/**
 * //TODO add class commment here
 *
 * @Author wfh
 * @Date 2024/3/27 下午9:21
 */
@RestController
@RequestMapping("/api")
public class TodayTopicController {
    @Resource
    private TodayTopicService todayTopicService;

    @GetMapping("/todayTopic")
    public List<TodayTopicDTO> getTodayTopic()
    {
        return todayTopicService.getTodayTopic();
    }
}
