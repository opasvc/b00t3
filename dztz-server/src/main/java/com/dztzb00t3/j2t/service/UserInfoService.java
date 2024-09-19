package com.dztzb00t3.j2t.service;

import com.dztzb003.j2t.common.result.R;
import com.dztzb003.j2t.common.domain.entity.UserInfo;

/**
 * 自定义用户表 service
 *
 * @author j2t
 * @date 2024/09/14 15:52
 */
public interface UserInfoService {

    public R login (UserInfo userInfo);

}
