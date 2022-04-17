package com.mofei.config;

import com.mofei.service.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/***自定义security相关配置*/
@Configuration
public class SecurityConfigure extends WebSecurityConfigurerAdapter {
    private MyUserDetailService myUserDetailService;
    @Autowired
    public void setMyUserDetailService(MyUserDetailService myUserDetailService) {
        this.myUserDetailService = myUserDetailService;
    }

    /**在工厂中指定密码加密方式*/
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(myUserDetailService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .mvcMatchers("/login.html").permitAll()
                .anyRequest().authenticated()
                .and()
                /**进行表单认证*/
                .formLogin()
                /**指定自定义登录页面*/
                .loginPage("/login.html")
                .loginProcessingUrl("/doLogin")
                /**登录成功后跳转路径*/
                .defaultSuccessUrl("/index.html",true)
                /**登录失败，重定向到登录页面*/
                .failureUrl("/login.html")
                .and()
                /**退出登录*/
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login.html")
                .and()
                /**关闭csrf*/
                .csrf().disable();

    }
}
