package com.dztzb00t3.j2t.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

/**
 * security config
 *
 * @author dztz
 * @date 2024/09/13
 */

@Slf4j
@Configuration
@EnableWebSecurity
public class SecurityConfig implements WebSecurityConfigurer<WebSecurity>{

    //这里将BCryptPasswordEncoder直接注册为Bean，Security会自动进行选择
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean //security 6 lambda形式 SecurityFilterChain(安全过滤器链)
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .cors(AbstractHttpConfigurer::disable) // 关闭 security cors 开启跨域
                .authorizeHttpRequests(auth -> { // 配置请求的授权规则 配置请求拦截方式
                    auth.requestMatchers("/user/login").permitAll(); // .requestMatchers() 某个请求不需要身份校验 .permitAll() 随意访问
                    auth.requestMatchers("/user").permitAll();
                    auth.requestMatchers("/user/home").hasRole("USER");
                    auth.anyRequest().authenticated(); //其他请求都要校验
                })
                .formLogin(auth -> {
//                    auth.loginPage("/user");
//                    auth.loginProcessingUrl("/user/login");
                    auth.defaultSuccessUrl("/user/home");
                    auth.permitAll();
                })
//                .oauth2Login(Customizer.withDefaults()) // 使用 OAuth2 登录
                .build();
    }


    @Bean // 基于数据库校验
    public DataSource dataSource() {
        //数据源配置
        return new PooledDataSource("com.mysql.cj.jdbc.Driver",
                "jdbc:mysql://localhost:3306/javatechie", "root", "rootroot");
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
