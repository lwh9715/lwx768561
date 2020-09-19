package com.deadline.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {

    @PostMapping("/user/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Map<String, Object> map, HttpSession httpSession) {
        if (!StringUtils.isEmpty(username) && "123".equals(password)) {
            //登录成功
            httpSession.setAttribute("loginUser", username);
            return "redirect:/main.html";
        } else {
            //登录失败
            map.put("msg", "用户名或密码错误");
            return "redirect:/user/login";
        }

    }

}
