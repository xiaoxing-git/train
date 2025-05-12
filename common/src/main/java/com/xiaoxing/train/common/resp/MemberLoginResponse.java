package com.xiaoxing.train.common.resp;

import lombok.Data;

@Data
public class MemberLoginResponse {

    /**
     * id
     */
    private Long id;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * JWT-token
     */
    private String token;

}
