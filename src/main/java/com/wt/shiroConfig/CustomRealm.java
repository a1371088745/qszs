package com.wt.shiroConfig;

import com.wt.entity.Login;
import com.wt.entity.Permission;
import com.wt.entity.Role;
import com.wt.entity.User;
import com.wt.service.impl.LoginServiceImpl;

import com.wt.service.impl.UserServiceImpl;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;


public class CustomRealm extends AuthorizingRealm {
    @Autowired
    private LoginServiceImpl loginService;
    @Autowired
    private UserServiceImpl userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String tel = (String) principalCollection.getPrimaryPrincipal();
        User user = userService.findRoleAndPermission(tel);
        HashSet<String> roles = new HashSet<String>();
        HashSet<String> permissions = new HashSet<String>();
        for (Role role:user.getRoleList()) {
            roles.add(role.getRoleName());
            for (Permission permission:role.getPermissionList()) {
                permissions.add(permission.getPermissionName());
            }
        }
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setRoles(roles);
        simpleAuthorizationInfo.setStringPermissions(permissions);
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String tel = (String) authenticationToken.getPrincipal();
        Login login = loginService.findLogin(tel);
        if(login==null){
            return null;
        }
        return new SimpleAuthenticationInfo(tel,login.getPassword(),this.getClass().getName());
    }
}
