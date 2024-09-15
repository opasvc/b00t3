package com.dztzb00t3.j2t.service.impl;

import com.alibaba.fastjson2.JSON;
import com.dztzb003.j2t.common.domain.VO.LoginUser;
import com.dztzb003.j2t.common.domain.entity.UserInfo;
import com.dztzb003.j2t.common.result.R;
import com.dztzb003.j2t.common.utils.TokenUtils;
import com.dztzb00t3.j2t.mapper.UserInfoMapper;
import com.dztzb00t3.j2t.service.UserInfoService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

/**
 * 自定义用户表 serviceImpl
 *
 * @author j2t
 * @date 2024/09/14 15:52
 */
@Slf4j
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Resource
    private UserInfoMapper userInfoMapper;

    @Resource
    private AuthenticationManager authenticationManager;


    @Override
    public R login(UserInfo userInfo) {
        // 封装Authentication对象
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userInfo.getUsername(), userInfo.getPassword());
        //进行校验
        Authentication authenticate = authenticationManager.authenticate(auth);
        //
        if (authenticate == null)
            return R.error("登录失败");
        //放入的user对象
        Object loginUser = authenticate.getPrincipal();
        String jsonString = JSON.toJSONString(loginUser);
        String token = TokenUtils.generateToken(jsonString);


        return R.success(token);
    }
}
