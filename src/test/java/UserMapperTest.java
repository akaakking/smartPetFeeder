import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.wlc.feeder.Application;
import org.wlc.feeder.dao.UserMapper;
import org.wlc.feeder.dto.UserDTO;

import javax.annotation.Resource;

/**
 * //TODO add class commment here
 *
 * @Author wfh
 * @Date 2024/5/25 下午5:42
 */
@SpringBootTest(classes = Application.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class UserMapperTest {
    @Resource
    private UserMapper userMapper;

    @Test
    public void testUserMapper() {
        UserDTO userDTO = new UserDTO("123");
        userDTO.setName("wangfeihong");

        userMapper.insert(userDTO);

        System.out.println(userMapper.selectOne(new QueryWrapper<UserDTO>().eq("id", userDTO.getId())));
    }
}
