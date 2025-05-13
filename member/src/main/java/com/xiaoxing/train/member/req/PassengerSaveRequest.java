package com.xiaoxing.train.member.req;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 乘车人信息请求类
 */
@Data
public class PassengerSaveRequest {
    /**
     * 会员id
     */
    private Long memberId;

    /**
     * 姓名
     */
    @NotBlank(message = "姓名不能为空")
    private String name;

    /**
     * 身份证号
     */
    @NotBlank(message = "身份证号不能为空")
    private String idCode;

    /**
     * 旅客类型
     */
    @NotBlank(message = "类型不能为空")
    private String type;
}
