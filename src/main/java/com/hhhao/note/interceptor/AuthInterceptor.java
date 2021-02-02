package com.hhhao.note.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.auth0.jwt.interfaces.Claim;
import com.hhhao.note.dto.login.UserInfo;
import com.hhhao.note.exception.CustomizeException;
import com.hhhao.note.mapper.NotesUserMapper;
import com.hhhao.note.util.FieldCode;
import com.hhhao.note.util.JwtUtils;
import com.hhhao.note.util.enums.RespondEnum;

/**
 * 实现接口HandlerInterceptor，重写preHandle方法 实现登录拦截
 * 
 * @author hhhao
 *
 */
@Component
public class AuthInterceptor implements HandlerInterceptor {
    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private NotesUserMapper userMapper;

    /**
     * 进入controller之前拦截
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        /**
         * 不是请求的controller里的方法，通过验证，系统目前不限制静态资源的访问
         */
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        String token = request.getHeader("Authorization");
        Map<String, Claim> claims = jwtUtils.verifyJwt(token);
        if (claims == null || claims.isEmpty()) {
            throw new CustomizeException(RespondEnum.UNAUTHORIZED);
        }
//		从jwt中获取用户信息，写入request中，供后续方法使用
        String email = claims.get(JwtUtils.PAYLOAD_EMAIL).asString();
        if (email == null || email.isEmpty()) {
            throw new CustomizeException(RespondEnum.UNAUTHORIZED.getCode(), "请重新登录");
        }
        UserInfo user = userMapper.selectCurrentUser(email);
        if (user == null) {
            throw new CustomizeException(RespondEnum.UNAUTHORIZED.getCode(), "请重新登录");
        }
        request.setAttribute(FieldCode.PAYLOAD_USER, user);
        return true;
    }
}
