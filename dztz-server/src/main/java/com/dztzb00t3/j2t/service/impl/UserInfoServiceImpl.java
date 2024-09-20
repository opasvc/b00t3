package com.dztzb00t3.j2t.service.impl;

import lombok.extern.slf4j.Slf4j;
import com.alibaba.fastjson2.JSON;
import jakarta.annotation.Resource;
import com.dztzb003.j2t.common.result.R;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import com.dztzb00t3.j2t.mapper.UserInfoMapper;
import com.dztzb003.j2t.common.utils.TokenUtils;
import com.dztzb00t3.j2t.service.UserInfoService;
import com.dztzb003.j2t.common.domain.entity.UserInfo;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.util.CollectionUtils;

import java.util.List;

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

    /**
     * @param userInfo username && password
     * @return token
     */
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
        User loginUser =(User) authenticate.getPrincipal();
//        String jsonString = JSON.toJSONString(loginUser);
        String token = TokenUtils.generateToken(loginUser.getUsername());
        return R.success(token);
    }





    /**
     * @return List->UserInfo
     */
    @Override
    public List<UserInfo> getUserList() {
        return this.userInfoMapper.queryUserInfoListAll("user");
    }

    /**
     * @param username 用户名
     * @return 用户名查重
     */
    @Override
    public R<Boolean> isUsernameRepeat(String username) {
        if (StringUtils.isBlank(username)) {
            return R.success(false);
        }
        List<UserInfo> userInfos = this.userInfoMapper.queryUserInfoListAll(username);
        if (userInfos.isEmpty()) {
            return R.success(true);
        }
        return R.success(false);
    }



}
