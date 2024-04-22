package org.wlc.feeder.service;

import org.springframework.stereotype.Service;
import org.wlc.feeder.dao.PetMapper;
import org.wlc.feeder.dto.PetDTO;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * //TODO add class commment here
 *
 * @Author wfh
 * @Date 2024/4/16 下午10:28
 */
@Service
public class PetService {
    @Resource
    private PetMapper petMapper;

    public void savePet(PetDTO petDto) {
        if (Objects.isNull(petDto.getId())) {
            petMapper.updateById(petDto);
        }

        petMapper.insert(petDto);
    }

    public PetDTO getPet(Long id) {
        return petMapper.selectById(id);
    }
}
