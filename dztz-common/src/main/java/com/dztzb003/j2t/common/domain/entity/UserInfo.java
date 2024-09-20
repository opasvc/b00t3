package com.dztzb003.j2t.common.domain.entity;

import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 自定义用户表 entity
 *
 * @author j2t
 * @date 2024/09/14 15:52
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo implements Serializable {

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
