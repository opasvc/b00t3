package com.dztzb00t3.j2t.controller;

import java.util.Map;
import java.util.HashMap;
import lombok.extern.slf4j.Slf4j;
import jakarta.annotation.Resource;
import com.dztzb003.j2t.common.result.R;
import com.dztzb00t3.j2t.service.UserInfoService;
import org.springframework.web.bind.annotation.*;
import com.dztzb003.j2t.common.domain.entity.UserInfo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;


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

    /**
     * security 自带mapper
     */
    @Resource
    private UserDetailsManager manager;

    /**
     * 自定义userinfo service
     */
    @Resource
    private UserInfoService userInfoService;

    /**
     * 密码加密器
     */
    @Resource
    private PasswordEncoder encoder;


    @GetMapping
    public R login() {
        return R.success();
    }

    @PostMapping("/login")
    public R login(@RequestBody UserInfo user) {
        log.info("login user:{}", user);
        return this.userInfoService.login(user);
    }

    @GetMapping("/change")
    public R<String> changePassword(@RequestParam String oldPassword,
                            @RequestParam String newPassword) {
        manager.changePassword(oldPassword, encoder.encode(newPassword));
        return R.success("success");
    }


    @GetMapping("/home")
    public R<Map> home() {
        HashMap<Object, Object> hashMap = new HashMap<>();
        return R.success(hashMap);
    }
}
