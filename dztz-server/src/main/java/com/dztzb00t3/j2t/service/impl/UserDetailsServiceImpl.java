package com.dztzb00t3.j2t.service.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import com.dztzb00t3.j2t.mapper.UserInfoMapper;
import com.dztzb003.j2t.common.domain.VO.LoginUser;
import com.dztzb003.j2t.common.domain.entity.UserInfo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


/**
 * 实现security自带的 UserDetailsService
 *
 * @author j2t
 * @date 2024/09/15 00:59
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserInfoMapper userInfoMapper;

    /**
     * @param username 用户名
     * @return 根据用户名查询用户信息
     * @throws UsernameNotFoundException e
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = userInfoMapper.queryUserInfoByUsername(username);
        if (userInfo == null)
            throw new UsernameNotFoundException("用户名不存在: "+username);

        return new LoginUser(userInfo);
    }




}
