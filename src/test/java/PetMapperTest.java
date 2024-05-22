import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.wlc.feeder.Application;
import org.wlc.feeder.controller.UserController;
import org.wlc.feeder.dao.PetMapper;
import org.wlc.feeder.dto.PetDTO;
import org.wlc.feeder.util.JwtUtils;

import java.util.List;

/**
 * //TODO add class commment here
 *
 * @Author wfh
 * @Date 2024/5/18 上午10:53
 */
@SpringBootTest(classes = Application.class)
@Slf4j
public class PetMapperTest {
    @Resource
    private PetMapper petMapper;

    @Resource
    private UserController userController;

    @Test
    public void test() {
        PetDTO petDTO = new PetDTO(null);
        petDTO.setPetType("fdf");
        petMapper.insert(petDTO);
        log.info("" + petDTO.getId());

        PetDTO petDTO1 = petMapper.selectById(petDTO.getId());

        log.info(petDTO1.toString());
    }

    @Test
    public void test2() {
        PetDTO petDTO = new PetDTO("323");
        petDTO.setUserId(123);
        petMapper.insert(petDTO);

        ResponseEntity<List<PetDTO>> pet = userController.getPet(JwtUtils.generateToken(String.valueOf(123)));

        for (PetDTO dto : pet.getBody()) {
            log.info(dto.toString());
        }

    }
}
