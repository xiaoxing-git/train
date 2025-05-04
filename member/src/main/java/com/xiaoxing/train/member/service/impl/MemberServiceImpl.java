package com.xiaoxing.train.member.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoxing.train.member.domain.Member;
import com.xiaoxing.train.member.service.MemberService;
import com.xiaoxing.train.member.mapper.MemberMapper;
import org.springframework.stereotype.Service;

/**
 * @author xiaoxing
 * @description 针对表【member(会员表)】的数据库操作Service实现
 * @createDate 2025-05-04 14:27:39
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member>
        implements MemberService {

}




