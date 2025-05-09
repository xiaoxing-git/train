package com.xiaoxing.train.common.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JWTUtilTest {

    String token = "";
    @Test
    @BeforeEach
    void createToken() {
        String a = "xiaoxing";
        token = JWTUtil.createToken(a);
    }

    @Test
    void validate() {
        boolean validate = JWTUtil.validate(token);
        Assertions.assertTrue(validate);
    }
}