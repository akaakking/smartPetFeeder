import org.junit.jupiter.api.Test;
import org.wlc.feeder.constant.GsonSingleton;
import org.wlc.feeder.dto.FeedPlan;
import org.wlc.feeder.dto.wechat.SendSubscribeMessageDTO;
import org.wlc.feeder.util.JwtUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * //TODO add class commment here
 *
 * @Author wfh
 * @Date 2024/4/21 下午4:23
 */
public class JwtUtilsTest {
    @Test
    public void testGenerateToken() {
        String token = JwtUtils.generateToken("1444417");
        System.out.println(token);
    }

    @Test
    public void testValidateAndGetOpenId() {
        String token = JwtUtils.generateToken("123");
        System.out.println(JwtUtils.validateAndGetOpenId(token));
    }
}
