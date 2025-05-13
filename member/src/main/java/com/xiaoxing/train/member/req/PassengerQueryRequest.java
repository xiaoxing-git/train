package com.xiaoxing.train.member.req;

import com.xiaoxing.train.common.req.PageRequest;
import lombok.Data;

/**
 * 乘车人查询请求类
 */
@Data
public class PassengerQueryRequest extends PageRequest {
    /**
     * 会员id
     */
    private Long memberId;

}
