package com.zhang.shiro;

import com.zhang.pojo.User;
import com.zhang.service.FindUser;
import com.zhang.util.MD5Utils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;


public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private FindUser findUser;
    private Logger log = LoggerFactory.getLogger(ShiroRealm.class);

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("执行了--》授权方法doGetAuthorizationInfo");
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        String password = new String((char[]) token.getCredentials());

        log.info("开始认证用户："+username);
        User user = findUser.findUser(username);

        if (user == null) {
            throw new UnknownAccountException("没有此用户");
        } if (!password.equals(user.getPassword())) {
            throw new IncorrectCredentialsException("用户名或密码错误");
        } if ("0".equals(user.getStatus())) {
            throw new LockedAccountException("用户被锁定，请 TMD 联系管理员解锁");
        }

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user,password,getName());
        return info;
    }
}