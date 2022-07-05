package com.test.client.fallback;

import com.test.client.BookClient;
import com.test.entity.Book;
import org.springframework.stereotype.Component;

/**
 * @author az
 * @description BookClient补救措施
 * @date 2022/7/6 0006
 */
@Component
public class BookFallbackClient implements BookClient {

    /**
     * 根据图书id查询图书信息
     * @param bid 图书id
     * @return
     */
    @Override
    public Book getBookByBid(Integer bid) {
        return Book.builder()
                .title("我是Book补救措施")
                .build();
    }
}
