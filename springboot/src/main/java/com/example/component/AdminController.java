package com.example.component;

import com.example.bean.Admin;
import com.example.service.impl.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class AdminController {

    @Autowired
    AdminServiceImpl adminService;

    @GetMapping("/login_p")
    public String login_p(){
        return "login_p";
    }

    @PostMapping("/doLogin")
    public String login(Admin admin,Map<String, Object> map, HttpSession httpSession) {

        Admin login = (Admin) adminService.loadUserByUsername(admin.getUsername());
        //if (!StringUtils.isEmpty(username) && "123".equals(password))
        if (!StringUtils.isEmpty(login)) {
            //登陆成功，防止表单重复提交，可以重定向到主页,LoginHandlerInterceptor
            httpSession.setAttribute("SignIn", login.getUsername());
            return "index";
        } else {
            //登录失败
            map.put("msg", "用户名或密码错误,请重新输入");
            return "login";
        }
    }
}