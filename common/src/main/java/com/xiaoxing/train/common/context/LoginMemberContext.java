package com.xiaoxing.train.common.context;

import com.xiaoxing.train.common.exception.BusinessException;
import com.xiaoxing.train.common.resp.MemberLoginResponse;
import com.xiaoxing.train.common.result.ErrorCode;
import lombok.Data;
import lombok.Getter;

/**
 * 登录会员上下文
 */
@Data
public class LoginMemberContext {
    @Getter
    private static ThreadLocal<MemberLoginResponse> memberContext = new ThreadLocal<>();

    public static void setMemberContext(ThreadLocal<MemberLoginResponse> memberContext) {
        LoginMemberContext.memberContext = memberContext;
    }

    public static long getId(){
        try {
            return memberContext.get().getId();
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.NULL_ERROR,"上下文不存在");
        }
    }
}
