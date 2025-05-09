package com.xiaoxing.train.member.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoxing.train.common.exception.BusinessException;
import com.xiaoxing.train.common.result.ErrorCode;
import com.xiaoxing.train.common.util.JWTUtil;
import com.xiaoxing.train.member.domain.Member;
import com.xiaoxing.train.member.mapper.MemberMapper;
import com.xiaoxing.train.member.req.MemberLoginRequest;
import com.xiaoxing.train.member.req.MemberRegisterRequest;
import com.xiaoxing.train.member.req.MemberSendCodeRequest;
import com.xiaoxing.train.member.resp.MemberLoginResponse;
import com.xiaoxing.train.member.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author xiaoxing
 * @description 针对表【member(会员表)】的数据库操作Service实现
 * @createDate 2025-05-04 14:27:39
 */
@Service
@Slf4j
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {

    @Override
    public long register(MemberRegisterRequest mobile) {
        //判断手机号是否已经注册过了
        boolean exist = exist(mobile.getMobile());
        if (exist) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "手机号已注册");
        }
        //未注册，进行注册
        Member member = new Member();
        long id = IdUtil.getSnowflakeNextId();
        member.setId(id);
        member.setMobile(mobile.getMobile());
        boolean saved = save(member);
        if (!saved) {
            throw new RuntimeException("注册失败");
        }
        return member.getId();
    }

    @Override
    public String sendCode(MemberSendCodeRequest mobile) {
        //判断手机号是否已经注册过了
        boolean exist = exist(mobile.getMobile());
        if (!exist) {
            //未注册，进行注册
            log.info("未注册，进行注册");
            Member member = new Member();
            long id = IdUtil.getSnowflakeNextId();
            member.setId(id);
            member.setMobile(mobile.getMobile());
            boolean saved = save(member);
            if (!saved) {
                throw new RuntimeException("注册失败");
            }
        }
        //发送验证码
        return RandomUtil.randomNumbers(4);
    }

    @Override
    public MemberLoginResponse login(MemberLoginRequest loginRequest) {
        //判断手机号是否已经注册过了
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mobile", loginRequest.getMobile());
        Member member = getOne(queryWrapper);
        if (member == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "手机号未注册");

        }
        //校验验证码
        if (!"8888".equals(loginRequest.getCode())) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "验证码错误");
        }
        MemberLoginResponse memberLoginResponse = BeanUtil
                .copyProperties(member, MemberLoginResponse.class);
        // 使用工具类获取 JWT token
        String token = JWTUtil.createToken(memberLoginResponse);
        memberLoginResponse.setToken(token);
        return memberLoginResponse;
    }

    private boolean exist(String mobile) {
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mobile", mobile);
        Member one = getOne(queryWrapper);
        if (one == null) {
            return false;
        } else {
            return true;
        }
    }
}




