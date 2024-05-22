package org.wlc.feeder.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.wlc.feeder.dao.PetMapper;
import org.wlc.feeder.dto.BlogDTO;
import org.wlc.feeder.dto.LikesDTO;
import org.wlc.feeder.dto.PetDTO;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
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

    @Resource
    private UploadService uploadService;

    public void savePet(PetDTO petDto) throws IOException {
        if (petDto.getAvatarFile() != null) {
            petDto.setAvatar(
                    uploadService.saveImage(petDto.getAvatarFile())
            );
        }

        petDto.setAvatarFile(null);

        if (petDto.getId() == null) {
            petMapper.insert(petDto);
        }

        petMapper.updateById(petDto);
    }

    public PetDTO getPet(Integer id) {
        return petMapper.selectById(id);
    }

    public List<PetDTO> getUserPet(Integer userId) {
        return petMapper.selectList(new QueryWrapper<PetDTO>().eq("user_id", userId));
    }

    public void deletePet(Integer id) {
        petMapper.deleteById(id);
    }

    // 检查deviceId是否用过， 直接找 deviceId 是否在pet表里边存在过
    public boolean deviceHasUsed(Integer deviceId, Integer petId) {
        List<PetDTO> pets = petMapper.selectList(new QueryWrapper<PetDTO>().eq("device_id", deviceId));

        if (CollectionUtils.isEmpty(pets)) {
            return false;
        }

        return !pets.get(0).getId().equals(petId);
    }
}
