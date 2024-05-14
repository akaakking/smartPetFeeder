import org.junit.jupiter.api.Test;
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
        String token = JwtUtils.generateToken("123");
        System.out.println(token);
    }
    //eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMjMiLCJpYXQiOjE3MTM3NzkwOTYsImV4cCI6MTcxMzg2NTQ5Nn0.08fGNF7oZA19NH0njKxm2NPiW2wwdX8GBQ7zoWKRp7o
    //

    @Test
    public void testValidateAndGetOpenId() {
        String token = JwtUtils.generateToken("123");
        System.out.println(JwtUtils.validateAndGetOpenId(token));
    }
}
