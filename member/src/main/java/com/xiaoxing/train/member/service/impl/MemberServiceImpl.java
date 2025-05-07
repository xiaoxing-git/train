package com.xiaoxing.train.member.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoxing.train.common.aspect.exception.BusinessException;
import com.xiaoxing.train.common.aspect.result.ErrorCode;
import com.xiaoxing.train.member.domain.Member;
import com.xiaoxing.train.member.req.MemberRegisterRequest;
import com.xiaoxing.train.member.req.MemberSendCodeRequest;
import com.xiaoxing.train.member.service.MemberService;
import com.xiaoxing.train.member.mapper.MemberMapper;
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
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mobile",mobile.getMobile());
        Member one = getOne(queryWrapper);
        if (one!=null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"手机号已注册");
        }
        //未注册，进行注册
        Member member = new Member();
        long id = IdUtil.getSnowflakeNextId();
        member.setId(id);
        member.setMobile(mobile.getMobile());
        boolean saved = save(member);
        if (!saved){
            throw new RuntimeException("注册失败");
        }
        return member.getId();
    }

    @Override
    public String sendCode(MemberSendCodeRequest mobile) {
        //判断手机号是否已经注册过了
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mobile",mobile.getMobile());
        Member one = getOne(queryWrapper);
        if (one==null){
            //未注册，进行注册
            log.info("未注册，进行注册");
            Member member = new Member();
            long id = IdUtil.getSnowflakeNextId();
            member.setId(id);
            member.setMobile(mobile.getMobile());
            boolean saved = save(member);
            if (!saved){
                throw new RuntimeException("注册失败");
            }
        }
        //发送验证码
        return RandomUtil.randomNumbers(4);
    }
}




