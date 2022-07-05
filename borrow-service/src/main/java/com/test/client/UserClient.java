package com.test.client;

import com.test.client.fallback.UserFallbackClient;
import com.test.entity.User;
import com.test.util.HttpResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author az
 * @description
 * @date 2022/7/4 0004
 */
@FeignClient(value = "user-service",fallback = UserFallbackClient.class)
public interface UserClient {

    @ApiOperation("获取用户信息")
    @GetMapping("/user/{uid}")
    User getUserByUid(@PathVariable("uid") Integer uid);

}
