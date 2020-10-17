package com.example.config;

import com.example.service.AdminService;
import com.example.service.impl.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
//开启SpringSecurity授权注解
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    AdminServiceImpl userDetailsService;
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * 身份认证管理器 可指定认证方式
     * 指定userDetailsService、authenticationProvider
     * @param auth
     * @throws Exception
     */
    //授权认证
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /**
         * inMemoryAuthentication
         * 在内存中注册用户
         */
        //Spring security 5.0中新增了多种加密方式,也改变了默认的密码格式.
        //inMemoryAuthentication表示使用基于内存的验证，还可以使用基于数据库的验证等，使用BCrypt编码对密码进行加密
        //指定密码加密方式
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("user1").password(new BCryptPasswordEncoder().encode("123")).roles("vip1")
                .and()
                .withUser("user2").password(new BCryptPasswordEncoder().encode("123")).roles("vip2")
                .and()
                .withUser("user3").password(new BCryptPasswordEncoder().encode("123")).roles("vip3")
                .and()
                .withUser("user").password(new BCryptPasswordEncoder().encode("123"))
                .roles("vip1","vip2","vip3");
    }

    /**
     * 安全设置
     * @param http
     * @throws Exception
     */
    //拦截请求
    //定制请求的授权规则,设置哪些url允许被某种角色访问
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //定制请求的授权规则
        //角色的设置需要和上面的权限一致，不然会报403
        // 需要具有ADMIN角色才能访问
        ////http.authorizeRequests().antMatchers("/admin").hasRole("ADMIN");
        //开启自动配置的登录功能
        ////http.formLogin();

        //不定向参数，匹配url规则
        //设置静态资源允许访问
        // 不需要登录就可以访问
        http.authorizeRequests().antMatchers("/").permitAll()
        //指定角色可访问
        .antMatchers("/level1/**").hasRole("vip1")
        .antMatchers("/level2/**").hasRole("vip2")
        .antMatchers("/level3/**").hasRole("vip3");
        //使用正则表达式进行匹配
        //.regexMatchers(".+[.]js").permitAll()

        //其他所有请求 都需要认证
        //.anyRequest().authenticated()

        //统一前缀 等同antMatchers("/prefix/demo")
        //.mvcMatchers("demo").servletPath("/prefix").permitAll()

        //开启自动配置的登录功能：效果：如果没有登陆，没有权限就会来到登陆页面
        //1,/login来到登录页面
        //2,/重定向到/login?error表示登录失败
        //指定登录页面
        http.formLogin().loginPage("/welcome")
        //指定登录参数 用户名、密码字段
        //.usernameParameter("username")
        //.passwordParameter("password")
        //指定登录地址
        //.loginProcessingUrl("/welcome")

        //登录成功页
        //如果是认证失败跳到登录的，登录成功后，会回到想去的页面，比较友好
        //第二个参数设置为true，等于failureForwardUrl
        .defaultSuccessUrl("/kungfu");

        //登录失败跳转
        //.failureForwardUrl("/login/failure")
        // .failureUrl("/login/failure")

        //启用注销功能，（需要提供一个action为/logout的form）并设置注销后访问的url，这里注销后跳转到首页
        //1,访问/logout表示用户注销，清空session
        //2,注销成功会返回 Login?Logout
        //注销成功之后来到首页
        //用户注销地址
        http.logout().logoutUrl("/logout").logoutSuccessUrl("/");
        //http.logout().logoutSuccessUrl("/");
        //启用rememberMe功能，将用户信息保存在cookie中
        //点击注销会删除cookie
        http.rememberMe();
    }


}
