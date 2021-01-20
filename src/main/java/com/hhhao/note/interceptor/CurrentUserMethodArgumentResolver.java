package com.hhhao.note.interceptor;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.hhhao.note.annotation.CurrentUser;
import com.hhhao.note.dto.login.UserInfo;
import com.hhhao.note.util.FieldCode;

/**
 * 注解@CurrentUser解析器<br>
 * 使用了@AuthIgnore 之后为null
 * 
 * @author haoy
 *
 */
@Component
public class CurrentUserMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        return webRequest.getAttribute(FieldCode.PAYLOAD_USER, RequestAttributes.SCOPE_REQUEST);
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().isAssignableFrom(UserInfo.class)
                && parameter.hasParameterAnnotation(CurrentUser.class);
    }

}
