package com.dztzb00t3.j2t.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.ResultMap;
import com.dztzb003.j2t.common.domain.entity.UserInfo;

/**
 * 自定义用户表 mapper
 *
 * @author j2t
 * @date 2024/09/14 15:52
 */
@Mapper
public interface UserInfoMapper {

    /**
     * @param username 用户名
     * @return userinfo
     */
    @Select("select * from user_info where username = #{username} ")
    UserInfo queryUserInfoByUsername(@Param("username") String username);
}
