package com.odian.moviesearch.api.util;

import com.odian.moviesearch.core.application.model.DefaultRequestCriteria;
import com.odian.moviesearch.core.application.model.FilmRequestValidator;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class FilmRequestCriteriaArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterAnnotation(FilmCriteria.class) != null;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        var filmCriteria = new DefaultRequestCriteria(new FilmRequestValidator());
        filmCriteria.setPageable(RequestCriteriaBuilder.getPageable(request));
        filmCriteria.setOrder(RequestCriteriaBuilder.getOrder(request));
        filmCriteria.setSortBy(RequestCriteriaBuilder.getSortBy(request));
        filmCriteria.setRequestParameters(RequestCriteriaBuilder.getRequestParameters(request));
        return filmCriteria;
    }
}
