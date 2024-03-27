package org.wlc.feeder.service;

import org.springframework.stereotype.Service;
import org.wlc.feeder.dao.SliderPicMapper;
import org.wlc.feeder.dto.SliderPicDTO;

import javax.annotation.Resource;
import java.util.List;

/**
 * //TODO add class commment here
 *
 * @Author wfh
 * @Date 2024/3/26 下午9:58
 */
@Service
public class SlidePicService {

    @Resource
    private SliderPicMapper sliderPicMapper;

    public List<SliderPicDTO> getSliderPic() {
        return sliderPicMapper.selectList(null);
    }
}
