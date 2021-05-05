package com.example.config;

import com.example.service.impl.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

/*
* Description: Security 配置类
* */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    AdminServiceImpl myUserDetailService;
    @Autowired
    MySecurityMetadataSource mySecurityMetadataSource;
    @Autowired
    MyAccessDecisionManager myAccessDecisionManager;
    @Autowired
    MyAccessDeniedHandler myAccessDeniedHandler;
    @Autowired
    BackdoorAuthenticationProvider backdoorAuthenticationProvider;

    // 配置用户及其对应的角色
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("admin").password(new BCryptPasswordEncoder()
                .encode("123")).roles("vip1","vip2","vip3");

        ////将自定义验证类注册进去(防止数据库数据丢失，设置后门登入)
        auth.authenticationProvider(backdoorAuthenticationProvider);

        //加入数据库验证类，下面的语句实际上在验证链中加入了一个DaoAuthenticationProvider
        auth.userDetailsService(myUserDetailService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/index.html","/error",
            "/login_p", "/static/**","/druid","/**/*.png","/**/*.jpg",
            "/**/*.jpeg","/**/*.gif","/**/fonts/*","/**/*.svg","/**/*.css", "/**/*.js");
    }

    // 配置 URL 访问权限
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests() // 开启 HttpSecurity 配置
            .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                @Override
                public <O extends FilterSecurityInterceptor> O postProcess(O object) {
                    //自定义的元数据源类，用来提供鉴权过程中，访问资源所需的角色
                    object.setSecurityMetadataSource(mySecurityMetadataSource);
                    //鉴权的决策类
                    object.setAccessDecisionManager(myAccessDecisionManager);
                    return object; }})
                .anyRequest().authenticated()//表示剩余的其他接口，登录之后就能访问
                //定义登录页面，未登录时，访问一个需要登录之后才能访问的接口，会自动跳转到该页面
            .and().formLogin().loginPage("/login_p").loginProcessingUrl("/doLogin")//登录处理接口
            .permitAll()//和表单登录相关的接口统统都直接通过
            .and().csrf().disable()//关闭csrf==可以进行删除操作
            .exceptionHandling().accessDeniedHandler(myAccessDeniedHandler);
    }

}
