package com.xiaoxing.train.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaoxing.train.member.domain.Member;
import com.xiaoxing.train.member.req.MemberLoginRequest;
import com.xiaoxing.train.member.req.MemberRegisterRequest;
import com.xiaoxing.train.member.req.MemberSendCodeRequest;
import com.xiaoxing.train.member.resp.MemberLoginResponse;

/**
 * @author xiaoxing
 * @description 针对表【member(会员表)】的数据库操作Service
 * @createDate 2025-05-04 14:27:39
 */
public interface MemberService extends IService<Member> {
    /**
     * 会员注册
     *
     * @param mobile
     * @return
     */
    long register(MemberRegisterRequest mobile);

    /**
     * 发送验证码(伪)
     *
     * @param mobile
     * @return
     */
    String sendCode(MemberSendCodeRequest mobile);

    /**
     * 登录
     *
     * @param mobile
     * @return
     */
    MemberLoginResponse login(MemberLoginRequest mobile);
}
