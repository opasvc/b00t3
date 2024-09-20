package com.dztzb00t3.j2t.config.security;


import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;


/**
 * security config
 *
 * @author dztz
 * @date 2024/09/13
 */

@Slf4j
@Configuration
@EnableWebSecurity
public class SecurityConfig implements WebSecurityConfigurer<WebSecurity> {


    @Bean // 这里将BCryptPasswordEncoder直接注册为Bean，Security会自动进行选择
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /*
    CSRF（跨站请求伪造）和CORS（跨域资源共享）是Web安全领域的两个不同概念，解决不同的问题：
    CSRF（Cross-Site Request Forgery）
    目的：防止恶意网站向受信任的网站发送未经授权的请求。
    工作原理：攻击者诱导用户在已登录的情况下访问恶意网站，恶意网站发送伪造请求到受信任的网站，利用用户的身份执行操作。
    解决方案：通过在请求中包含唯一的CSRF令牌，服务器验证令牌的有效性来防止伪造请求。
    示例：用户在银行网站登录后，攻击者诱导用户点击恶意链接，导致用户账户资金被转移。
    CORS（Cross-Origin Resource Sharing）
    目的：允许浏览器从不同的域名请求资源，解决跨域请求的问题。
    工作原理：浏览器通过发送预检请求（OPTIONS）来询问服务器是否允许跨域请求，服务器响应允许的源、方法和头信息。
    解决方案：服务器配置允许的跨域请求源、方法和头信息。
    示例：前端应用在http://example.com，需要从http://api.example.com获取数据，服务器配置允许http://example.com的跨域请求。
     */
    @Bean //security 6 lambda形式 SecurityFilterChain(安全过滤器链)
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable());
        http// 关闭 security cors 开启跨域
                .cors(cors -> cors.disable());
        http
                .authorizeHttpRequests(auth -> { // 配置请求的授权规则 配置请求拦截方式
                    auth.requestMatchers("/user/login").permitAll(); // .requestMatchers() 某个请求不需要身份校验 .permitAll() 随意访问
//                    auth.requestMatchers("/user/home").hasRole("USER");
//                    auth.anyRequest().authenticated(); //其他请求都要校验
                    auth.anyRequest().permitAll();
                });
        http
                .formLogin(auth -> {
//                    auth.loginPage("/user");
//                    auth.loginProcessingUrl("/user/login");
//                    auth.defaultSuccessUrl("/user/home");
//                    auth.permitAll();
                });
        return http.build();
    }


    //手动创建一个AuthenticationManager用于处理密码校验
    @Bean
    public AuthenticationManager authenticationManager(UserDetailsManager manager,
                                                       PasswordEncoder encoder) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(manager);
        provider.setPasswordEncoder(encoder);
        return new ProviderManager(provider);
    }

    @Bean //密码校验器
    public UserDetailsManager userDetailsManager(DataSource dataSource,
                                                 PasswordEncoder encoder) {
        JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);
        //为UserDetailsManager设置AuthenticationManager即可开启重置密码的时的校验
        manager.setAuthenticationManager(authenticationManager(manager, encoder));
        return manager;
    }


    @Override
    public void init(WebSecurity builder) throws Exception {

    }

    @Override
    public void configure(WebSecurity builder) throws Exception {

    }
}
