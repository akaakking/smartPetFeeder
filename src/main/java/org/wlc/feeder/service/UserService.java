package org.wlc.feeder.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import org.wlc.feeder.dao.UserMapper;
import org.wlc.feeder.dto.UserDTO;
import org.wlc.feeder.dto.wechat.WechatSession;
import org.wlc.feeder.util.JwtUtils;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;

/**
 * //TODO add class commment here
 *
 * @Author wfh
 * @Date 2024/4/20 下午6:14
 */
@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private WechatService wechatService;

    public String wechatLogin(String code) throws ExecutionException {
        // 获取用户信息
        WechatSession wechatSession = wechatService.getWechatSession(code);

        if (wechatSession == null || wechatSession.getOpenid() == null) {
            throw new RuntimeException("获取用户信息失败");
        }

        // 保存用户信息
        saveWechatUser(wechatSession.getOpenid());

        // 保存登录态
        return JwtUtils.generateToken(wechatSession.getOpenid());
    }

    public void saveUserInfo(UserDTO userDTO) {
        userMapper.update(userDTO, new QueryWrapper<UserDTO>().eq("wechat_Id", userDTO.getWechatId()));
    }

    public void saveWechatUser(String wechatId) {
        UserDTO userDTO = userMapper.selectOne(new QueryWrapper<UserDTO>().eq("wechat_Id", wechatId));

        if (userDTO == null) {
            userMapper.insert(new UserDTO(wechatId));
        }
    }
}
