package com.xiaoxing;

import com.xiaoxing.train.common.util.JWTUtil;
import com.xiaoxing.train.member.domain.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JWTUtilTest {

    String token = "";
    @Test
    @BeforeEach
    void createToken() {
        Member member = new Member();
        member.setId(1L);
        member.setMobile("123");
        token = JWTUtil.createToken(member);
    }

    @Test
    void validate() {
        boolean validate = JWTUtil.validate(token);
        Assertions.assertTrue(validate);
    }

    @Test
    void getDate() {
        Member date =JWTUtil.getDate(token,Member.class);
        System.out.println(date);
    }
}