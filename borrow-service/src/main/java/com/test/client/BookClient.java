package com.test.client;

import com.test.entity.Book;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author az
 * @description
 * @date 2022/7/4 0004
 */
@FeignClient("book-service")
public interface BookClient {

    @ApiOperation("获取书籍相关信息")
    @GetMapping("/book/{bid}")
    Book getBookByBid(@PathVariable("bid") Integer bid);
}
