package org.example.backend.utils;

import org.example.backend.exception.ServiceException;
import org.example.backend.utils.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * 继承自默认的函数参数处理器，用于从添加了@CurrentUserId注解的方法参数中进行token解析
 */
@Component
public class CurrentUserIdResolver implements HandlerMethodArgumentResolver {
    private final JwtUtils jwtUtils;

    @Autowired
    public CurrentUserIdResolver(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    /** 判断是否支持该参数注入
     * 仅当参数上标注了 @CurrentUserId 注解，且参数类型为 Integer 时才注入。
     * @param parameter 待处理的函数参数对象
     * @return 返回true的函数参数对象就是我们要处理的对象
     */
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(CurrentUserId.class)
                && parameter.getParameterType().equals(Integer.class);
    }

    /** 已经过了筛选，现在从token中解析出对应的userId并注入函数参数
     * @param parameter 经过筛选的CurrentUserId参数
     * @param mavContainer 1
     * @param webRequest 对应的webRequest，从中解包token
     * @param binderFactory 1
     * @return 从webRequest中解析出的id
     */
    @Override
    @NonNull
    public Object resolveArgument(
            @NonNull MethodParameter parameter,
            @NonNull ModelAndViewContainer mavContainer,
            @NonNull NativeWebRequest webRequest,
            @NonNull WebDataBinderFactory binderFactory
    ) {
        String token = webRequest.getHeader("Authorization");
        if (token.startsWith("Bearer ")) {
            // 去除Bearer前缀,获取纯粹的token字符串
            token = token.substring(7);
            // 调用jwt类中的函数将token解析为userId，自动注入给注解的函数参数
            return jwtUtils.parseToken(token);
        }
        throw new ServiceException("未登录或token无效");
    }
}
