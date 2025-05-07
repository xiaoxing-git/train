package com.xiaoxing.train.member.controller;

import com.xiaoxing.train.common.aspect.result.BaseResponse;
import com.xiaoxing.train.common.aspect.result.ResultUtils;
import com.xiaoxing.train.member.req.MemberLoginRequest;
import com.xiaoxing.train.member.req.MemberRegisterRequest;
import com.xiaoxing.train.member.req.MemberSendCodeRequest;
import com.xiaoxing.train.member.resp.MemberLoginResponse;
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
    public Long count() {
        return memberService.count();
    }

    /**
     * 会员注册
     *
     * @param mobile
     * @return 会员 id
     */
    @PostMapping("/register")
    public BaseResponse<Long> register(@Valid @RequestBody MemberRegisterRequest mobile) {
        long id = memberService.register(mobile);
        return ResultUtils.success(id);
    }

    /**
     * 发送验证码(伪)
     *
     * @param mobile
     * @return
     */
    @PostMapping("/sendCode")
    public BaseResponse<String> sendCode(@Valid @RequestBody MemberSendCodeRequest mobile) {
        String code = memberService.sendCode(mobile);
        return ResultUtils.success(code);
    }

    /**
     * 登录
     *
     * @param mobile
     * @return
     */
    @PostMapping("/login")
    public BaseResponse<MemberLoginResponse> login(@Valid @RequestBody MemberLoginRequest mobile) {
        MemberLoginResponse memberLoginResponse = memberService.login(mobile);
        return ResultUtils.success(memberLoginResponse);
    }
}
