package org.wlc.feeder.service;

import org.springframework.stereotype.Service;
import org.wlc.feeder.dao.HandBookMapper;
import org.wlc.feeder.dto.HandBookDTO;

import javax.annotation.Resource;
import java.util.List;

/**
 * //TODO add class commment here
 *
 * @Author wfh
 * @Date 2024/3/27 下午9:44
 */
@Service
public class HandBookService {
    @Resource
    private HandBookMapper handBookMapper;

    public List<HandBookDTO> getHandBook() {
        return handBookMapper.selectList(null);
    }

}
