import org.junit.jupiter.api.Test;
import org.wlc.feeder.constant.GsonSingleton;
import org.wlc.feeder.dto.FeedPlan;
import org.wlc.feeder.dto.wechat.SendSubscribeMessageDTO;
import org.wlc.feeder.util.JwtUtils;

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
    public void dfsd() {
        FeedPlan feedPlan = new FeedPlan();
        feedPlan.setBreakfast("16:38");
        feedPlan.setLunch("18:00");
        feedPlan.setDinner("19:00");
        System.out.println(GsonSingleton.getInstance().toJson(feedPlan));

    }

    @Test
    public void testValidateAndGetOpenId() {
        String token = JwtUtils.generateToken("123");
        System.out.println(JwtUtils.validateAndGetOpenId(token));
    }

    @Test
    public void JsonTs() {
        System.out.println(SendSubscribeMessageDTO.buildJson("123", "123", new SendSubscribeMessageDTO.PlaceHolder("ty","1234"), new SendSubscribeMessageDTO.PlaceHolder("thing", "123")));
    }
}
