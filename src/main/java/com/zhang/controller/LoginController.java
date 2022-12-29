package com.zhang.controller;

import com.zhang.pojo.ResponseBo;
import com.zhang.pojo.User;
import com.zhang.util.MD5Utils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

    private Logger log = LoggerFactory.getLogger(LoginController.class);

    @GetMapping({"/login","/"})
    public String login() {
        log.info("进入登陆页面");
        return "index";
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseBo login (String Username,String Password,boolean Remeberme) {
//        log.info("username:"+Username+",Password:"+Password+",Remeberme:"+Remeberme);
        Password = MD5Utils.encrypt(Username, Password);
        UsernamePasswordToken token = new UsernamePasswordToken(Username,Password,Remeberme);
        Subject sub =  SecurityUtils.getSubject();
        try {
            sub.login(token);
            return ResponseBo.ok();
        }catch (UnknownAccountException e) {
            return ResponseBo.error(e.getMessage());
        }catch (IncorrectCredentialsException e) {
            return ResponseBo.error(e.getMessage());
        }catch (LockedAccountException e) {
            return ResponseBo.error(e.getMessage());
        }catch (AuthenticationException e) {
            return ResponseBo.error(e.getMessage());
        }
    }

    @RequestMapping("/tohomepage")
    public String homePage(Model model) {
        User user = (User)SecurityUtils.getSubject().getPrincipal();
        model.addAttribute("user",user);
        return "homepage";
    }

//    @RequestMapping("/logout")
//    public String logout() {
//        return "redirect:/tologin";
//    }

}
