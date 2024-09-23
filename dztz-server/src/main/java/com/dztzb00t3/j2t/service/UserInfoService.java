package com.dztzb00t3.j2t.service;

import com.dztzb003.j2t.common.domain.DTO.UserInfoQueryDTO;
import com.dztzb003.j2t.common.result.R;
import com.dztzb003.j2t.common.domain.entity.UserInfo;

import java.util.List;

/**
 * 自定义用户表 service
 *
 * @author j2t
 * @date 2024/09/14 15:52
 */
public interface UserInfoService {


    /**
     * @return userinfo 集合
     */
    List<UserInfo> getUserList(UserInfoQueryDTO queryDTO);


    /**
     * 登录返回用户token
     *
     * @param userInfo username && password
     * @return token
     */
    R login(UserInfo userInfo);

    /**
     * @param username 用户名
     * @return 用户名查重
     */
    R<Boolean> isUsernameRepeat(String username);




}
