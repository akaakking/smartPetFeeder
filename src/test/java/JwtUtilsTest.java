import org.junit.jupiter.api.Test;
import org.wlc.feeder.constant.GsonSingleton;
import org.wlc.feeder.dto.FeedPlan;
import org.wlc.feeder.dto.wechat.GetAccessTokenResult;
import org.wlc.feeder.dto.wechat.SendSubscribeMessageDTO;
import org.wlc.feeder.util.CommonUtils;
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

    @Test
    public void fds() {
        String a = "{\"access_token\":\"80_mPhzzEV9PelhCI7eEaIgGeIRXj8EIE_1Kntu61rt0Amfb3phR4K15EfyG9gJT193Q2AIoxjmQMld8_lqBm0jZSBc2k9z1wjw5F3SeyrBxP6X9To3XNlYjeKBm1IXWTbAFAONK\",\"expires_in\":7200}\n";

        System.out.println(GsonSingleton.getInstance().fromJson(a, GetAccessTokenResult.class).getAccess_token());

        System.out.println(CommonUtils.getyyyymmddHHmmss());
    }
}
