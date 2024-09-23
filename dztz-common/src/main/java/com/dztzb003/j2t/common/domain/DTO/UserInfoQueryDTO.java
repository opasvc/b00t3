package com.dztzb003.j2t.common.domain.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * userinfo query object
 *
 * @author j2t
 * @date 2024/09/23
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoQueryDTO {

    /**
     * id primary
     */
    private Long id;

    /**
     * 用户名
     */
    private String name;

    /**
     * 账户名 unique, not null
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 角色
     */
    private String roles;

    /**
     * 账户状态 0-正常;1-异常;2-封禁;3-注销;4-锁定;
     */
    private Integer user_status;

    /**
     * 注册时间
     */
    private LocalDateTime createTime;

    /**
     * 最近登录时间
     */
    private LocalDateTime loginTime;

    /**
     * 登录 ip
     */
    private String loginIp;

    /**
     * 注册时ip
     */
    private String createIp;
}
