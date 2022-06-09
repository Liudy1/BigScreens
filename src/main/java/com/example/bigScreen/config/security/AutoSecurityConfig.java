package com.example.bigScreen.config.security;

import com.example.bigScreen.config.jwt.JWTAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;

/**
 * @author: SuKai
 * @date: 2022/4/13
 * @time: 14:16
 * @description: security配置类
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // 开启方法权限注解
public class AutoSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 无权限处理类
     */
    @Autowired
    private UserAccessDeniedHandler userAccessDeniedHandler;

    /**
     * 用户未登录处理类
     */
    @Autowired
    private UserNotLoginHandler userNotLoginHandler;

    /**
     * 用户登录成功处理类
     */
    @Autowired
    private UserLoginSuccessHandler userLoginSuccessHandler;

    /**
     * 用户登录失败处理类
     */
    @Autowired
    private UserLoginFailureHandler userLoginFailureHandler;

    /**
     * 用户登出成功处理类
     */
    @Autowired
    private UserLogoutSuccessHandler userLogoutSuccessHandler;

    /**
     * 用户登录验证
     */
    @Autowired
    private UserAuthenticationProvider userAuthenticationProvider;

    /**
     * 用户权限注解
     */
    @Autowired
    private UserPermissionEvaluator userPermissionEvaluator;

    /**
     * 加密方式，没使用这种加密方式
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 注入自定义PermissionEvaluator
     *
     * @return
     */
    @Bean
    public DefaultWebSecurityExpressionHandler userSecurityExpressionHandler() {
        DefaultWebSecurityExpressionHandler handler = new DefaultWebSecurityExpressionHandler();
        handler.setPermissionEvaluator(userPermissionEvaluator);
        return handler;
    }

    /**
     * 用户登录验证
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(userAuthenticationProvider);
    }


    @Value("${jwt.antMatchers}")
    private String antMatchers;//白名单,这里是不做验证的方法

    /**
     * 安全权限配置
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests() // 权限配置
                .antMatchers(antMatchers.split(",")).permitAll()// 获取白名单（不进行权限验证）
//                .anyRequest().authenticated() // 其他的需要登陆后才能访问
                .anyRequest().permitAll()
                .and().httpBasic().authenticationEntryPoint(userNotLoginHandler) // 配置未登录处理类
                .and().formLogin().loginProcessingUrl("/user/login")// 配置登录URL
                //.and().formLogin().loginPage("/user/login")// 配置登录URL
                .successHandler(userLoginSuccessHandler) // 配置登录成功处理类
                .failureHandler(userLoginFailureHandler) // 配置登录失败处理类
                .and().logout().logoutUrl("/logout/submit")// 配置登出地址
                .logoutSuccessHandler(userLogoutSuccessHandler) // 配置用户登出处理类
                .and().logout()   //退出登录相关配置
                .logoutSuccessUrl("/page/index") //退出成功后跳转的页面
                .deleteCookies("JSESSIONID")    //退出时要删除的Cookies的名字
                .and().exceptionHandling().accessDeniedHandler(userAccessDeniedHandler)// 配置没有权限处理类
                .and().cors()// 开启跨域
                .and().csrf().disable(); // 禁用跨站请求伪造防护
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // 禁用session（使用Token认证）
        http.headers().cacheControl(); // 禁用缓存
        http.addFilter(new JWTAuthenticationFilter(authenticationManager()));
    }
}
