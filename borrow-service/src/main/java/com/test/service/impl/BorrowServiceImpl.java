package com.test.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.client.BookClient;
import com.test.client.UserClient;
import com.test.entity.Book;
import com.test.entity.Borrow;
import com.test.entity.User;
import com.test.mapper.BorrowMapper;
import com.test.service.IBorrowService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.test.util.HttpResult;
import com.test.util.MyBeanUtils;
import com.test.vo.BookUserListVo;
import com.test.vo.UserBookListVo;
import com.test.vo.UserBookVo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author az
 * @since 2022-07-02
 */
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class BorrowServiceImpl extends ServiceImpl<BorrowMapper, Borrow> implements IBorrowService {

    private final BorrowMapper borrowMapper;

    private final UserClient userClient;

    private final BookClient bookClient;

    ///**
    // * 根据用户id查询此用户所有借阅书籍
    // *
    // * @param uid 用户id
    // * @return
    // */
    //@Override
    //public List<UserBookListVo> getBorrowListByUid(Integer uid) {
    //    List<Borrow> borrowList = borrowMapper.selectList(new LambdaQueryWrapper<Borrow>().eq(Borrow::getUid, uid));
    //
    //
    //}
    //
    ///**
    // * 根据书籍id查询借阅过此书的所有用户
    // *
    // * @param bid 书籍id
    // * @return
    // */
    //@Override
    //public List<BookUserListVo> getBorrowListByBid(Integer bid) {
    //    return null;
    //}

    /**
     * 根据书籍id和用户id查询借阅详情
     *
     * @param bid 书籍id
     * @param uid 用户id
     * @return
     */
    @Override
    public UserBookVo getBorrowListByBidUid(Integer bid, Integer uid) {
        LambdaQueryWrapper<Borrow> lambda = new LambdaQueryWrapper<>();
        lambda.eq(Borrow::getBid, bid).eq(Borrow::getUid, uid);
        Borrow borrow = borrowMapper.selectOne(lambda);

        User user = userClient.getUserByUid(uid);
        Book book = new Book();
        if (null != borrow) {
            book = bookClient.getBookByBid(borrow.getBid());
        }

        return UserBookVo.builder()
                .user(user)
                .book(book)
                .build();
    }
}
