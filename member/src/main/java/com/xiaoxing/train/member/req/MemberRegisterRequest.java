package com.xiaoxing.train.member.req;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MemberRegisterRequest {

    /**
     * 手机号
     */
    @NotBlank(message = "手机号不能为空")
    private String mobile;
}
