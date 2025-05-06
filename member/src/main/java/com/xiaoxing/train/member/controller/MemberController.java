package com.xiaoxing.train.member.controller;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiaoxing.train.common.aspect.exception.BusinessException;
import com.xiaoxing.train.common.aspect.result.BaseResponse;
import com.xiaoxing.train.common.aspect.result.ErrorCode;
import com.xiaoxing.train.common.aspect.result.ResultUtils;
import com.xiaoxing.train.member.domain.Member;
import com.xiaoxing.train.member.req.MemberRegisterRequest;
import com.xiaoxing.train.member.service.MemberService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
public class MemberController {
    @Resource
    MemberService memberService;
    @GetMapping("/count")
    public Long count(){
        return memberService.count();
    }

    /**
     * 会员注册
     * @param mobile
     * @return 会员 id
     */
    @PostMapping("/register")
    public BaseResponse<Long> register(@Valid @RequestBody MemberRegisterRequest mobile){
        //判断手机号是否已经注册过了
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mobile",mobile.getMobile());
        Member one = memberService.getOne(queryWrapper);
        if (one!=null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"手机号已注册");
        }
        //未注册，进行注册
        Member member = new Member();
        long id = IdUtil.getSnowflakeNextId();
        member.setId(id);
        member.setMobile(mobile.getMobile());
        boolean saved = memberService.save(member);
        if (!saved){
            throw new RuntimeException("注册失败");
        }
        return ResultUtils.success(member.getId());
    }
}
