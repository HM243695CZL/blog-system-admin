package com.hl.blog.security.component;

import cn.hutool.json.JSONUtil;
import com.hl.blog.common.api.CommonResult;
import com.hl.blog.common.api.ResultCode;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 未登录或登录过期时的处理类
 */
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        // 响应未登录或已过期信息
        response.getWriter().println(JSONUtil.parse(CommonResult.failed(ResultCode.UNAUTHORIZED)));
        response.getWriter().flush();
    }
}
