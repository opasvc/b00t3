package com.dztzb00t3.j2t.config;


import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class SecurityConfigTest {
    @Resource
    private PasswordEncoder encoder;

    @Test
    void test() {
        //密码加密
        String encode = encoder.encode("123456");
        String encode1 = encoder.encode("123456");
        // 密文和密码是否一致
        boolean matches = encoder.matches("123456", encode);
        System.out.println("密文: " + encode);
        System.out.println("密文1: " + encode1);
        System.out.println("比较: " + matches);
    }
}
