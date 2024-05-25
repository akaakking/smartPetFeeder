import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.wlc.feeder.Application;
import org.wlc.feeder.controller.PetController;
import org.wlc.feeder.controller.UserController;
import org.wlc.feeder.dao.PetMapper;
import org.wlc.feeder.dto.PetDTO;
import org.wlc.feeder.dto.PetModifyDTO;
import org.wlc.feeder.exception.BizException;
import org.wlc.feeder.util.JwtUtils;

import java.io.IOException;
import java.util.List;

/**
 * //TODO add class commment here
 *
 * @Author wfh
 * @Date 2024/5/18 上午10:53
 */
@SpringBootTest(classes = Application.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class PetMapperTest {
    @Resource
    private PetController petController;
}
