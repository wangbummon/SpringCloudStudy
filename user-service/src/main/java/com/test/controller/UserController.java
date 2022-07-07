package com.test.controller;

import com.test.entity.User;
import com.test.service.IUserService;
import com.test.util.HttpResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author az
 * @since 2022-07-02
 */
@Api(tags = "用户")
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;

    @ApiOperation("获取用户信息")
    @GetMapping("/{uid}")
    public User getUserByUid(@PathVariable("uid") Integer uid) {

        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String test = req.getHeader("Test");
        System.out.println(test);

        return userService.getById(uid);
    }


}
