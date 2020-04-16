package com.wt.shiroConfig;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(customRealm());
        return securityManager;
    }

    @Bean
    public CustomRealm customRealm(){
        return new CustomRealm();
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilter(){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager());
        //设置登陆界面
        shiroFilterFactoryBean.setLoginUrl("anon/need_login");
        //未授权跳转此页面
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        /*
            后面对过滤链进行处理

        */
        filterChainDefinitionMap.put("/anon/**","anon");
        filterChainDefinitionMap.put("/register/**","anon");
        filterChainDefinitionMap.put("/timetable/readTimetable","authc");
        filterChainDefinitionMap.put("/test/test1","anon");
        filterChainDefinitionMap.put("/hisTimetable/**","anon");
        filterChainDefinitionMap.put("/studentInfo/**","anon");
        filterChainDefinitionMap.put("/group/**","authc");
        filterChainDefinitionMap.put("/cl/**","authc");
        filterChainDefinitionMap.put("/staff/**","authc");
        filterChainDefinitionMap.put("/score/toInputScoreChoose","roles[teacher]");
        filterChainDefinitionMap.put("/score/findScore","roles[teacher]");

        // 静态资源放行
        filterChainDefinitionMap.put("/image/**", "anon");
        filterChainDefinitionMap.put("/layui/**","anon");
        filterChainDefinitionMap.put("/js/**","anon");
        filterChainDefinitionMap.put("/css/**","anon");
        filterChainDefinitionMap.put("*.json","anon");
        filterChainDefinitionMap.put("/photo/**","authc");



        //坑二: 过滤链是顺序执行，从上而下，一般讲/** 放到最下面
        //authc : url定义必须通过认证才可以访问
        //anon  : url可以匿名访问
        filterChainDefinitionMap.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return  shiroFilterFactoryBean;
    }
    // 这里是为了能在html页面引用shiro标签
    @Bean(name = "shiroDialect")
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }
}
