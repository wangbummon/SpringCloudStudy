package com.test.client.fallback;

import com.test.client.UserClient;
import com.test.entity.User;
import org.springframework.stereotype.Component;

/**
 * @author az
 * @description UserClient补救措施
 * @date 2022/7/6 0006
 */
@Component
public class UserFallbackClient implements UserClient {

    /**
     * 根据用户id获取用户信息
     * @param uid 用户id
     * @return
     */
    @Override
    public User getUserByUid(Integer uid) {
        return User.builder()
                .name("我是User补救措施")
                .build();
    }
}
