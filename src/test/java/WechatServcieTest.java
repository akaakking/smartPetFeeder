import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.wlc.feeder.Application;
import org.wlc.feeder.dto.wechat.SendSubscribeMessageDTO;
import org.wlc.feeder.service.WechatService;
import org.wlc.feeder.util.CommonUtils;

import javax.annotation.Resource;

/**
 * //TODO add class commment here
 *
 * @Author wfh
 * @Date 2024/5/27 下午3:53
 */
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class WechatServcieTest {
    @Resource
    private WechatService wechatService;

    @Test
    public void test() {
        String message =
                SendSubscribeMessageDTO.buildJson("okfWk64AUjX6Ac_9_ecMmg8yMtT0", wechatService.getPetFeedingReminderTemplate(),
                        new SendSubscribeMessageDTO.PlaceHolder("name1", "壮壮"),
                        new SendSubscribeMessageDTO.PlaceHolder("phrase2", "定时喂食"),
                        new SendSubscribeMessageDTO.PlaceHolder("date3", CommonUtils.getyyyymmddHHmmss()),
                        new SendSubscribeMessageDTO.PlaceHolder("thing5", "已完成宠物食物的定点投送"));

        wechatService.sendWechatMessage(message);

    }

    @Test
    public void aVoidest()  {
        String mesage = SendSubscribeMessageDTO.buildJson("okfWk64-bZyRo3m4wzMQ8EBlBl9k", wechatService.getFoodShortageReminderTemplate(),
                new SendSubscribeMessageDTO.PlaceHolder("thing1", "粮仓内余粮不足"),
                new SendSubscribeMessageDTO.PlaceHolder("time3", CommonUtils.getyyyymmddHHmmss()));

        wechatService.sendWechatMessage(mesage);

    }
}
