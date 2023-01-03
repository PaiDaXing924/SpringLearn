package com.zhang.shiro;

import com.zhang.dao.UserPermissionMapper;
import com.zhang.dao.UserRoleMapper;
import com.zhang.pojo.Permission;
import com.zhang.pojo.Role;
import com.zhang.pojo.User;
import com.zhang.service.FindUser;
import com.zhang.util.MD5Utils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private FindUser findUser;
    private Logger log = LoggerFactory.getLogger(ShiroRealm.class);
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private UserPermissionMapper userPermissionMapper;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        String userName = user.getUserName();
        log.info("用户" + userName + "获取权限-----ShiroRealm.doGetAuthorizationInfo");
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

        // 获取用户角色集
        List<Role> roleList = userRoleMapper.findByUserName(userName);
        Set<String> roleSet = new HashSet<String>();
        for (Role role: roleList) {
            roleSet.add(role.getName());
        }

        //获取用户权限集
        List<Permission> preList = userPermissionMapper.findByUserName(userName);
        Set<String> preSet = new HashSet<String>();
        for (Permission pre:
        preList) {
            preSet.add(pre.getName());
        }

        SimpleAuthorizationInfo authorInfo = new SimpleAuthorizationInfo();
        authorInfo.setRoles(roleSet);
        authorInfo.setStringPermissions(preSet);

        return authorInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        String password = new String((char[]) token.getCredentials());

        log.info("用户" + username + "开始认证-----ShiroRealm.doGetAuthenticationInfo");
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