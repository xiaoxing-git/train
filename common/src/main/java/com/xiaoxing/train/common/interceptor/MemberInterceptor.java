package com.xiaoxing.train.common.interceptor;

import cn.hutool.core.util.StrUtil;
import com.xiaoxing.train.common.context.LoginMemberContext;
import com.xiaoxing.train.common.exception.BusinessException;
import com.xiaoxing.train.common.resp.MemberLoginResponse;
import com.xiaoxing.train.common.result.ErrorCode;
import com.xiaoxing.train.common.util.JWTUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 会员-拦截器
 */
@Component
public class MemberInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        if (StrUtil.isBlank(token)){
            throw new BusinessException(ErrorCode.NOT_LOGIN,"token 为空");
        }
        // 解析 token 保存的内容
        MemberLoginResponse memberLoginResponse = JWTUtil.getDate(token, MemberLoginResponse.class);
        ThreadLocal<MemberLoginResponse> memberContext = LoginMemberContext.getMemberContext();
        // 将内容保存到上下文
        memberContext.set(memberLoginResponse);
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
