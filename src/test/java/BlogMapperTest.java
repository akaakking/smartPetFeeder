import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.wlc.feeder.Application;
import org.wlc.feeder.dao.BlogMapper;
import org.wlc.feeder.dto.BlogDTO;

import javax.annotation.Resource;

/**
 * //TODO add class commment here
 *
 * @Author wfh
 * @Date 2024/5/23 上午11:25
 */
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BlogMapperTest {
    @Resource
    private BlogMapper blogMapper;

    @Test
    public void insertBlogContainEmoji() {
        BlogDTO blogDTO  = new BlogDTO(null,123,"rfsdfdsfdsfds", "①狗粮➕自制（添加鸡胸肉\uD83E\uDD69➕蔬菜\uD83E\uDD6C➕水果\uD83C\uDF49）适口性高，锻炼胃消化能力", "①狗粮➕自制（添加鸡胸肉\uD83E\uDD69➕蔬菜\uD83E\uDD6C➕水果\uD83C\uDF49）适口性高，锻炼胃消化能力");
        blogMapper.insert(blogDTO);
    }
}
