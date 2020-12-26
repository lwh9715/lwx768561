package com.example.config;

import com.example.component.LoginHandlerInterceptor;
import com.example.util.StringToDateConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import java.util.Locale;

//使用WebMvcConfigurerAdapter可以来扩展SpringMVC的功能
//@EnableWebMvc  接管SpringMVC
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Autowired
    LoginHandlerInterceptor loginHandlerInterceptor;

    /*
     *国际化区域转换
     * */
    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.US);
        return slr;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }

    /**
     * 注册拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //国际化这行很关键
        registry.addInterceptor(localeChangeInterceptor());
        System.out.println("测试WebMvcConfigurer--2");
        registry.addInterceptor(loginHandlerInterceptor).addPathPatterns("/**")
                .excludePathPatterns ( "/", "/index", "/druid", "/login", "/**/*.css", "/**/*.js",
                        "/**/*.png","/**/*.jpg","/**/*.jpeg","/**/*.gif","/**/fonts/*","/**/*.svg");
    }

    /**
     * 视图控制器(视图映射)
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index").setViewName("index");
    }

    /**
     * 添加自定义的Converters和Formatters.
     * @param registry
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        //添加字符串转换Date的自定义转换器
        registry.addConverter(new StringToDateConverter());
    }
}