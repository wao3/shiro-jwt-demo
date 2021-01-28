package com.github.wao3.shirojwtdemo.shiro.realms;

import com.github.wao3.shirojwtdemo.entity.User;
import com.github.wao3.shirojwtdemo.service.UserService;
import com.github.wao3.shirojwtdemo.utils.JWTUtil;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class CustomerRealm extends AuthorizingRealm {

    @Resource
    UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = JWTUtil.getUsername(principals.toString());
        User user = userService.selectUserByUserName(username);

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        user.getRoles().forEach(role -> {
            info.addRole(role.getName());
            role.getPerms().forEach(perm -> {
                info.addStringPermission(perm.getName());
            });
        });

        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String token = (String) authenticationToken.getCredentials();
        // 解密获得username，用于和数据库进行对比
        String username = JWTUtil.getUsername(token);
        if (username == null || !JWTUtil.verify(token, username)) {
            throw new AuthenticationException("token认证失败！");
        }
        // Token 合法则说明是合法用户。

        return new SimpleAuthenticationInfo(token, token, "JWTRealm");
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token != null;
    }
}
