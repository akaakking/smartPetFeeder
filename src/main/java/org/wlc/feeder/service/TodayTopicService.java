package org.wlc.feeder.service;

import org.springframework.stereotype.Service;
import org.wlc.feeder.dao.TodayTopicMapper;
import org.wlc.feeder.dto.TodayTopicDTO;

import javax.annotation.Resource;
import java.util.List;

/**
 * //TODO add class commment here
 *
 * @Author wfh
 * @Date 2024/3/27 下午9:22
 */
@Service
public class TodayTopicService {
    @Resource
    private TodayTopicMapper todayTopicMapper;

    public List<TodayTopicDTO> getTodayTopic() {
        return todayTopicMapper.selectList(null);
    }
}
