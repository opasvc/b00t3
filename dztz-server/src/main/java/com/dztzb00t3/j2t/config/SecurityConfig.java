package com.dztzb00t3.j2t.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

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
public class SecurityConfig {

    //这里将BCryptPasswordEncoder直接注册为Bean，Security会自动进行选择
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //    @Bean // 基于内存校验
//    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
//        UserDetails user = User
//                .withUsername("user")
//                .password(encoder.encode("password"))   //这里将密码进行加密后存储
//                .roles("USER")
//                .build();
//        System.out.println(encoder.encode("password"));  //一会观察一下加密出来之后的密码长啥样
//        UserDetails admin = User
//                .withUsername("admin")
//                .password(encoder.encode("password"))   //这里将密码进行加密后存储
//                .roles("ADMIN", "USER")
//                .build();
//        return new InMemoryUserDetailsManager(user, admin);
//    }


    @Bean // 基于数据库校验
    public DataSource dataSource() {
        //数据源配置
        return new PooledDataSource("com.mysql.cj.jdbc.Driver",
                "jdbc:mysql://localhost:3306/javatechie", "root", "rootroot");
    }

//    @Bean    //仅首次启动时创建一个新的用户用于测试，后续无需创建
//    public UserDetailsService userDetailsService(DataSource dataSource,
//                                                 PasswordEncoder encoder) {
//        JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);
//        //仅首次启动时创建一个新的用户用于测试，后续无需创建
//        manager.createUser(User.withUsername("user").password(encoder.encode("password")).roles("USER").build());
//        return manager;
//    }

    //手动创建一个AuthenticationManager用于处理密码校验
    private AuthenticationManager authenticationManager(UserDetailsManager manager,
                                                        PasswordEncoder encoder) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(manager);
        provider.setPasswordEncoder(encoder);
        return new ProviderManager(provider);
    }

    @Bean //密码校验器
    public UserDetailsManager userDetailsManager(DataSource dataSource,
                                                 PasswordEncoder encoder) throws Exception {
        JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);
        //为UserDetailsManager设置AuthenticationManager即可开启重置密码的时的校验
        manager.setAuthenticationManager(authenticationManager(manager, encoder));
        return manager;
    }

}
