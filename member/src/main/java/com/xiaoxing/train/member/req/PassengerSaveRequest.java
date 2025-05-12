package com.xiaoxing.train.member.req;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 乘车人信息请求类
 */
@Data
public class PassengerSaveRequest {
    /**
     * 会员id
     */
    @NotNull
    private Long memberId;

    /**
     * 姓名
     */
    @NotBlank
    private String name;

    /**
     * 身份证号
     */
    @NotBlank
    private String idCode;

    /**
     * 旅客类型
     */
    @NotBlank
    private String type;
}
