package com.xiaoxing.train.member.controller;

import com.xiaoxing.train.member.service.MemberService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
public class MemberController {
    @Resource
    MemberService memberService;
    @GetMapping("/count")
    public Long count(){
        return memberService.count();
    }
}
