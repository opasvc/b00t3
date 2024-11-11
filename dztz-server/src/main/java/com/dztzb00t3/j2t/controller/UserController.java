package com.dztzb00t3.j2t.controller;

import java.util.List;

import com.dztzb003.j2t.common.domain.DTO.QueryPage;
import com.dztzb003.j2t.common.domain.DTO.UserInfoQueryDTO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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


    @GetMapping("/find")
    public R<PageInfo> login(UserInfoQueryDTO dto, QueryPage queryPage) {
        log.info("dto: {};queryPage: {}", dto, queryPage);
        PageHelper.startPage(queryPage.getPageNum(), queryPage.getPageSize());
        List<UserInfo> userInfoList = userInfoService.getUserList(dto);
        PageInfo pageInfo = new PageInfo(userInfoList);
        return R.success(pageInfo);
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

    /**
     * @param username 用户名查重
     * @return 根据 username 查询
     */
    @GetMapping("/username/{username}")
    public R<Boolean> isUsernameRepeat(@PathVariable("username") String username) {
        return this.userInfoService.isUsernameRepeat(username);
    }


}
