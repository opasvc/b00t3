package com.dztzb00t3.j2t.controller;

import com.dztzb003.j2t.common.entity.User;
import com.dztzb003.j2t.common.result.R;
import com.dztzb003.j2t.common.utils.TokenUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * user controller
 *
 * @author dztz
 * @date 2024/09/13
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserDetailsManager manager;

    @Resource
    private PasswordEncoder encoder;


    @GetMapping
    public R<String> login() {
        return R.success();
    }

    @PostMapping("/login")
    public R<Map<String, String>> login(@RequestBody User user) {
        log.info("login user:{}", user);
        String token = TokenUtils.generateToken(user.getUsername());

        Map<String, String> resultMap = Map.of("token", token);
        return R.success(resultMap);
    }

    @GetMapping("/change")
    public R changePassword(@RequestParam String oldPassword,
                            @RequestParam String newPassword) {
        manager.changePassword(oldPassword, encoder.encode(newPassword));
        return R.success("success");
    }


    @GetMapping("/home")
    public R home() {
        HashMap<Object, Object> hashMap = new HashMap<>();
        return R.success(hashMap);
    }
}
