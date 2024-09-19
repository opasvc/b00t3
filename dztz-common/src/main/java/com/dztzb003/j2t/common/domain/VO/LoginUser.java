package com.dztzb003.j2t.common.domain.VO;


import lombok.Data;
import java.util.List;
import java.io.Serializable;
import java.util.Collection;
import lombok.NoArgsConstructor;
import com.dztzb003.j2t.common.domain.entity.UserInfo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


/**
 * 实现security自带的 UserDetails
 *
 * @author j2t
 * @date 2024/09/15 00:59
 */

@Data
@NoArgsConstructor
public class LoginUser implements UserDetails, Serializable {

    private UserInfo userInfo;

    public LoginUser(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    /**
     * @return 返回密码
     */
    @Override
    public String getPassword() {
        return userInfo.getPassword();
    }

    /**
     * @return 返回用户名
     */
    @Override
    public String getUsername() {
        return userInfo.getUsername();
    }

    /**
     * @return 判断账号是否可用
     */
    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

    /**
     * @return 是否超时
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    /**
     * @return 账号是否锁定
     */
    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    /**
     * @return 是否为过期
     */
    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }
}
