package com.example.component;

import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * 可以在连接上携带区域信息
 * 国际化
 */
public class MyLocaleResolver implements LocaleResolver {

    @Override
    public Locale resolveLocale(HttpServletRequest httpServletRequest) {
        String paramLanguage = httpServletRequest.getParameter("lang");
        if (!StringUtils.isEmpty(paramLanguage)) {
            String[] splits = paramLanguage.split("-");
            return new Locale(splits[0], splits[1]);
        } else {
            String acceptLanguage = httpServletRequest.getHeader("Accept-Language").split(",")[0];
            String[] splits = acceptLanguage.split("-");
            return new Locale(splits[0], splits[1]);
        }
        // 如果想使用当前系统的语言，则使用Locale.getDefault()
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

    }
}
