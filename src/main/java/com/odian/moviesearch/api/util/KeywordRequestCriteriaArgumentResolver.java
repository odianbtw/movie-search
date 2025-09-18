package com.odian.moviesearch.api.util;

import com.odian.moviesearch.core.application.model.DefaultRequestCriteria;
import com.odian.moviesearch.core.application.model.KeywordRequestValidator;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class KeywordRequestCriteriaArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterAnnotation(KeywordCriteria.class) != null;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        var keywordRequestCriteria = new DefaultRequestCriteria(new KeywordRequestValidator());
        keywordRequestCriteria.setPageable(RequestCriteriaBuilder.getPageable(request));
        keywordRequestCriteria.setOrder(RequestCriteriaBuilder.getOrder(request));
        keywordRequestCriteria.setSortBy(RequestCriteriaBuilder.getSortBy(request));
        keywordRequestCriteria.setRequestParameters(RequestCriteriaBuilder.getRequestParameters(request));
        return keywordRequestCriteria;
    }
}
