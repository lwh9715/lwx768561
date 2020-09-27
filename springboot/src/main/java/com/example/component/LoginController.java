package com.example.component;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {

    @PostMapping(value = "/index/sign")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Map<String, Object> map, HttpSession httpSession) {
        if (!StringUtils.isEmpty(username) && "123".equals(password)) {
            //登陆成功，防止表单重复提交，可以重定向到主页,LoginHandlerInterceptor
            httpSession.setAttribute("SignIn", username);
            return "redirect:/customers";
        } else {
            //登录失败
            map.put("msg", "用户名或密码错误,请重新输入");
            return "index";
        }

    }
}
