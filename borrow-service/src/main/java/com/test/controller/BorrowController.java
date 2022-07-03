package com.test.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.test.service.IBorrowService;
import com.test.util.HttpResult;
import com.test.vo.UserBookVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author az
 * @since 2022-07-02
 */
@Api(tags = "借阅")
@RestController
@RequestMapping("/borrow")
@RequiredArgsConstructor
public class BorrowController {

    private final IBorrowService borrowService;

    @HystrixCommand(fallbackMethod = "onError")
    @ApiOperation("查询借阅详情")
    @GetMapping("/{uid}/{bid}")
    public UserBookVo getUserBookByUidBid(@PathVariable("uid") Integer uid, @PathVariable("bid") Integer bid) {
        return borrowService.getBorrowListByBidUid(bid, uid);
    }

    /**
     * 备选方案 这里直接返回空
     * 参数与返回值要与主方案一致
     */
    public UserBookVo onError(@PathVariable("uid") Integer uid, @PathVariable("bid") Integer bid) {
        return new UserBookVo();
    }

}
