package com.dztzb00t3.j2t.service.impl;

import com.dztzb00t3.j2t.mapper.UserInfoMapper;
import com.dztzb00t3.j2t.service.UserInfoService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
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


}
